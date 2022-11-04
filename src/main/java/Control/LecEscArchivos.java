package Control;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

//IMPORTS NECESARIOS PARA LA FUNCIONALIDAD DEL PROGRAMA
import Entity.Cuenta;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LecEscArchivos implements Serializable {

    //METODO ENCARGADO DE LEER EL ARCHIVO Y GUARDAD LOS DATO  
    public void leerArchivo(Banco banco) throws FileNotFoundException, ArchivosExce, IOException {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //INICIALIZAMOS EL FORMATO DE LA FECHA
            InputStreamReader input = new InputStreamReader(new FileInputStream("cuentas.txt"));//IDENTIFICAMOS EL ARCHIVO
            BufferedReader buff = new BufferedReader(input);
            int numC = banco.getCuentas().size();
            Cuenta.setConsecutivo(numC);
            String cadena = buff.readLine();

            while (!cadena.equalsIgnoreCase("#FIN")) {
                if (cadena.equals("#Cuenta")) { //SE COMPRUEBA CUAL ES EL TITULO DE LA LINEA QUE ESTA LEYENDO
                    LocalDate fecha = LocalDate.now();
                    cadena = buff.readLine();
                    cadena = buff.readLine();
                    cadena = quitarEspaciosCuen(cadena);
                    String[] aux = dividirCadenas(cadena, "-");//LISTA ENCARGADA DE GUARDAR LOS DATOS Y DIVIDIR LAS CADENAS POR LOS -
                    banco.getCuentas().add(Banco.crearCuenta(Cuenta.getConsecutivo(), Float.parseFloat(aux[0]), true, fecha, aux[1], aux[2]));
                } else { //SE COMPRUEBA CUAL ES EL TITULO DE LA LINEA QUE ESTA LEYENDO
                    cadena = buff.readLine();
                    cadena = buff.readLine();
                    while (!cadena.equalsIgnoreCase("0")) {
                        cadena = quitarEspaciosTit(cadena);
                        String[] auxi = dividirCadenas(cadena, "\\*");
                        LocalDate fechaNaci = LocalDate.parse(auxi[1], formato);
                        banco.getCuentas().get(Cuenta.getConsecutivo() - 1).getTitulares().add(banco.getCuentas().get(Cuenta.getConsecutivo() - 1).crearTitular(auxi[0], fechaNaci, auxi[2], auxi[3], auxi[4]));
                        cadena = buff.readLine();
                    }
                }
                cadena = buff.readLine();
            }
        } catch (FileNotFoundException e) {//OBTIENE LOS ERRORES DEL ARCHIVO
            throw new ArchivosExce("El archivo no pudo ser encontrado o no se pudo abrir: " + e);
        } catch (IOException | NumberFormatException e) { //OBTIENE LOS ERRORES DE LA ENTRADA Y SALIDA DEL PROGRAMA
            throw new ArchivosExce("Hubo un error general: " + e);
        }
    }

    //METODO ENCARGADO DE RETORNAR UNA LISTA DE TODAS LAS CUENTAS Y ESCRIBIR EL ARCHIVO DE INFORME GENERAL
    public ArrayList<String> informeGeneral(Banco banco) throws IOException, ArchivosExce {
        try {
            ArrayList<String> aux = new ArrayList<>();
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("informeGeneral.txt")); //SE ABRE EL ARCHIVO INFORMEGENERAL.TXT
            for (Cuenta cuenta : banco.getCuentas()) {
                aux.add(cuenta.toString());//SE AGREGA LA INFORMACION A LA LISTA
                out.write(cuenta.toString() + "\n");//SE ESCRIBE EL ARCHIVO
            }
            out.close();//SE CIERRRA EL ARCHIVO
            return aux;//SE RETORNA LA LISTA
        } catch (IOException e) { //OBTIENE LOS ERRORES DE LA ENTRADA Y SALIDA DEL PROGRAMA
            throw new ArchivosExce("No se pudo crear el archivo correctamente: " + e);
        }
    }

    //METODO ENCARGADO DE GUARDAR LOS DATOS DEL BANCO EN UN ARCHIVO TIPO DAT
    public void serializar(Banco banco) throws IOException, ArchivosExce {
        try {
            FileOutputStream fileOut = new FileOutputStream("JaviBank.dat");//SE ABRE EL ARCHIVO
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(banco);//SE ESCIBE EL ARCHIVO
        } catch (IOException e) { //OBTIENE LOS ERRORES DE LA ENTRADA Y SALIDA DEL PROGRAMA
            throw new ArchivosExce("No se pudo crear el archivo correctamente: " + e);
        }
    }

    //METODO ENCARGADO DE DESSERIALIZAR LO ENCONTRADO EN EL ARCHIVO TIPO DAT
    public Banco deserializar() throws ClassNotFoundException, IOException, ArchivosExce {
        try {
            Banco ban = null;
            FileInputStream fileIn = new FileInputStream("JaviBank.dat"); //SE ABRE EL ARCHIVO
            ObjectInputStream in = new ObjectInputStream(fileIn); //SE ESPECIALIZA EL FILTRO PARA LEER EL OBJETO
            ban = (Banco) in.readObject(); //SE ASIGNA A BAN LO LEIDO EN EL ARCHIVO
            in.close();//SE CIERRA EL ARCHIVO
            return ban;
        } catch (FileNotFoundException e) {//OBTIENE LOS ERRORES DEL ARCHIVO
            throw new ArchivosExce("El archivo no pudo ser encontrado o no se pudo abrir: " + e);
        } catch (InvalidClassException e) {//OBTIENE EL ERRO DE UN SERIAL DESCONOSIDO
            throw new ArchivosExce("Ocurrio un error desconocido con el UID Serial Version del archivo: " + e);
        } catch (IOException | NumberFormatException e) { //OBTIENE LOS ERRORES DE LA ENTRADA Y SALIDA DEL PROGRAMA
            throw new ArchivosExce("Hubo un error general: " + e);
        }
    }

    //ESTE METODO ES LA ENCARGADA DE RECIBIR Y PARTIR UNA CADENA SEGUN UNA SUBCADENA PREVIA  Y REGRESAR UN ARREGLO TIPO STRING QUE CONTIENE CADA UNO DE LAS PALABRAS DE LA CADENA 
    public static String[] dividirCadenas(String cadPrin, String subCad) {
        String[] lista = cadPrin.split(subCad);//SE ASIGNA A LA LISTA LA CADENA YA CORTADA SEGUN LA SUBCADENA
        return lista; //SE RETORNA LA LISTA
    }

    //ESTE METODO ES LA ENCARGADA DE RECIBIR UNA CADENA Y REGRESAR ESTA CADENA SIN LOS ESPACIOS EXTRAS
    public static String quitarEspaciosTit(String cadena) {
        cadena = cadena.replaceAll(" ", "");//Se quitan los espacios extras que posee la cadena
        return cadena;
    }

    //ESTE METODO ES LA ENCARGADA DE RECIBIR UNA CADENA Y REGRESAR ESTA CADENA SIN LOS ESPACIOS EXTRAS
    public static String quitarEspaciosCuen(String cadena) {
        cadena = cadena.replaceAll("[-]+", "-");//Se quitan los espacios extras que posee la cadena
        return cadena;
    }

}
