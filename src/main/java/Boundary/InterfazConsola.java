package Boundary; //PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

//IMPORTS NECESARIOS PARA EL FUNCIONAMIENTO
import Control.ArchivosExce;
import Control.Banco;
import Control.BancoExce;
import Control.CuentaExce;
import Control.LecEscArchivos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class InterfazConsola implements Serializable {

    //INICIA EL MAIN
    public static void main(String[] args) throws IOException, ClassNotFoundException, ArchivosExce, CuentaExce, BancoExce {

        Scanner scanner = new Scanner(System.in);

        //SE CREAN LOS OBJETOS NECESARIOS PARA EL PROGRAMA
        Banco banco = new Banco();
        LecEscArchivos archi = new LecEscArchivos();

        //VARIABLE QUE GUIARA EL MENU
        int opcion = 0;

        //INICIO DEL DO WHILE EN EL CUAL ESTARA ALMACENADO EL MENU
        do {

            //MENU SOLICITADO CON LAS OPCIONES NECESARIAS
            System.out.println("                                                                                        .:MENU:.");
            System.out.println("                                                           .:1. Para crear cuentas y titulares dado un archivo de texto:.");
            System.out.println("                                                                         .:2. Para consignar en una cuenta:.");
            System.out.println("                                                                          .:3. Para retirar de una cuenta:.");
            System.out.println("                                                                    .:4. Para transferir de una cuenta a otra:.");
            System.out.println("                                                   .:5. Para dado un numero de cuenta, mostrar todos sus titulares con su edad:.");
            System.out.println("                                        .:6. Para dada una fecha, mostrar todas las cuentas de los titulares que nacieron antes de esa fecha:.");
            System.out.println("                                              .:7. Para dada una fecha, mostrar todas las cuentas que se abrieron antes de esa fecha:.");
            System.out.println("                                                                      .:8. Para eliminar una cuenta del banco:.");
            System.out.println("                                                        .:9. Para generar un informe general por consola y en un archivo:.");
            System.out.println("                                                                                .:10. Para serializar:.");
            System.out.println("                                                                               .:11. Para deserializar:.");
            System.out.println("                                                                            .:12. Para salir del programa:.");

            //SE REALIZA UN TRY PARA CORROBORAR QUE SE INGRESE EL DATO DEL TIPO CORRECTO
            try {
                System.out.println("Ingresa la opcion deseada: ");
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) { //OBTIENE LOS ERRORES DE DIGITACION POR PARTE DEL USUARIO
                System.out.println("Error de digitacion :" + e);
            } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                System.out.println("Error general: " + e);
            }

            switch (opcion) {
                case 1:
                    try {
                    archi.leerArchivo(banco); //INICIALMENTE SE LEE EL ARCHIVO Y SE CREAN LOS OBJETO PARA QUE TODO FUNCIONE CORRECTAMENTE
                    System.out.println("El archivo se leyo correctamente :)");
                } catch (FileNotFoundException e) { //OBTIENE LOS ERRORES DEL ARCHIVO
                    System.out.println("El archivo no pudo ser encontrado o no se pudo abrir: " + e);
                } catch (IOException | NumberFormatException e) { //OBTIENE LOS ERRORES DE LA ENTRADA Y SALIDA DEL PROGRAMA
                    System.out.println("Hubo un error general: " + e);
                }
                break;

                case 2:
                    try {
                    System.out.println("Ingresa el numero de cuenta");
                    int numCue = scanner.nextInt();
                    System.out.println("Ingresa el monto a consignar");
                    float monto = scanner.nextFloat();
                    System.out.println("Asi estaba la cuenta antes " + banco.infoCuenta(numCue));
                    System.out.println();
                    System.out.println("Asi quedo la cuenta " + numCue + ": " + banco.consignarACuenta(numCue, monto));//SE ENVIA EL NUMERO Y EL MONTO PARA ASI CONSIGNAR EL DINERO A LA CUENTA
                } catch (InputMismatchException e) { //OBTIENE LOS ERRORES DE DIGITACION POR PARTE DEL USUARIO
                    System.out.println("Error de digitacion :" + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Error general: " + e);
                }
                break;

                case 3:
                    try {
                    System.out.println("Ingresa el numero de cuenta");
                    int numCuen = scanner.nextInt();
                    System.out.println("Ingresa el monto a retirar");
                    float mont = scanner.nextFloat();
                    System.out.println("Asi estaba la cuenta antes " + banco.infoCuenta(numCuen));
                    System.out.println();
                    System.out.println("Asi quedo la cuenta " + numCuen + ": " + banco.retirarDeCuenta(numCuen, mont));//SE ENVIA EL NUMERO Y EL MONTO PARA ASI RETIRAR EL DINERO DE LA CUENTA
                } catch (InputMismatchException e) { //OBTIENE LOS ERRORES DE DIGITACION POR PARTE DEL USUARIO
                    System.out.println("Error de digitacion :" + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Error general: " + e);
                }
                break;

                case 4:
                    try {
                    System.out.println("Ingresa el numero de cuenta origen");
                    int numCueO = scanner.nextInt();
                    System.out.println("Ingresa el numero de cuenta destino");
                    int numCueD = scanner.nextInt();
                    System.out.println("Ingresa el monto a transferir");
                    float montoC = scanner.nextFloat();
                    System.out.println(banco.transferir(numCueO, numCueD, montoC));
                } catch (InputMismatchException e) { //OBTIENE LOS ERRORES DE DIGITACION POR PARTE DEL USUARIO
                    System.out.println("Error de digitacion :" + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Error general: " + e);
                }
                break;

                case 5:
                    try {
                    List<String> aux = new ArrayList<>();
                    List<Integer> aux2 = new ArrayList<>();
                    System.out.println("Ingresa el numero de cuenta");
                    int num = scanner.nextInt();
                    LocalDate fecha = LocalDate.now();
                    System.out.println("Titulares de la cuenta " + num + ": ");
                    aux = banco.mostrarTitulares(num);//SE GUARDAN LOS TITULARES EN LA LISTA DE AUX
                    aux2 = banco.calcularEdad(num, fecha);//SE GUARDAN LAS EDADES EN LA SEGUNDA LISTA DE AUX
                    for (int i = 0; i < banco.getCuentas().get(num).getTitulares().size(); i++) {
                        System.out.print(aux.get(i) + " edad: ");//SE IMPRIMERN LAS LISTAS
                        System.out.println(aux2.get(i) + " anios");
                    }
                } catch (InputMismatchException e) { //OBTIENE LOS ERRORES DE DIGITACION POR PARTE DEL USUARIO
                    System.out.println("Error de digitacion :" + e);
                } catch (IndexOutOfBoundsException e) {//OBTIENE EL ERROR DE ENTRAR A UNA UBICACION NO EXISTENTE
                    System.out.println("Error, cuenta no existente: " + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Error general: " + e);
                }
                break;

                case 6:
                    try {
                    System.out.println("Ingresa la fecha (aaaa-mm-dd)");
                    String dates = scanner.next();//SE INGRESA LA FECHA EN STRING
                    LocalDate fechas = LocalDate.parse(dates); //SE TRANSFORMA A LOCALDATE
                    System.out.println(banco.mostrarCuentasFechaNacimineto(fechas));
                } catch (DateTimeParseException e) {//OBTIENE LOS ERRORES DE LA MALA DIGITACION DE LA FECHA
                    System.out.println("Error en la digitacion de las fechas: " + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Error general: " + e);
                }
                break;

                case 7:
                    try {
                    System.out.println("Ingresa la fecha (aaaa-mm-dd)");
                    String date = scanner.next();//SE INGRESA LA FECHA EN STRING
                    LocalDate localDate = LocalDate.parse(date); //SE TRANSFORMA A LOCALDATE
                    System.out.println("Cuentas creadas antes del " + localDate + ":" + banco.mostrarCuentasFechaCreacion(localDate));//SE IMPRIMEN LAS LISTAS DE LAS CUENTAS CREADAS ANTES DE ESTA
                } catch (DateTimeParseException e) {//OBTIENE LOS ERRORES DE LA MALA DIGITACION DE LA FECHA
                    System.out.println("Error en la digitacion de las fechas: " + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Error general: " + e);
                }
                break;

                case 8:
                    try {
                    System.out.println("Ingresa el numero de la cuenta que deseas eliminar:");
                    int numeroC = scanner.nextInt();
                    System.out.println("La cuenta " + banco.removerCuenta(numeroC) + " fue eliminada correctamente ");
                } catch (InputMismatchException e) { //OBTIENE LOS ERRORES DE DIGITACION POR PARTE DEL USUARIO
                    System.out.println("Error de digitacion :" + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Error general: " + e);
                }
                break;

                case 9:
                    try {
                    System.out.println("Cuentas: " + archi.informeGeneral(banco));//SE IMPRIMEN TODAS LAS CUENTAS
                } catch (IOException e) {
                    System.out.println("No se pudo crear el archivo correctamente: " + e);
                }
                break;

                case 10:
                    try {
                    archi.serializar(banco);//SE SALVA EL ARCHIVO DE TIPO DAT CON TODO EL OBJETO DE BANCO
                    System.out.println("Se creo el archivo correctamente :)");
                } catch (IOException e) {//OBTIENE LOS ERRORES DE LA ENTRADA Y SALIDA DEL PROGRAMA
                    System.out.println("No se pudo crear el archivo correctamente: " + e);
                }
                break;

                case 11:
                    try {
                    banco = archi.deserializar();//SE CARGA LA INFORMACION DEL ARCHIVO DAT A BANCO
                    System.out.println("Se guado la informacion correctamente :)");
                } catch (FileNotFoundException e) {
                    System.out.println("El archivo no pudo ser encontrado o no se pudo abrir: " + e);
                } catch (InvalidClassException e) {//OBTIENE EL ERRO DE UN SERIAL DESCONOSIDO
                    System.out.println("Ocurrio un error desconocido con el UID Serial Version del archivo: " + e);
                } catch (Exception e) {// OBTIENE LOS ERRORES GENERALES DEL PROGRAMA
                    System.out.println("Hubo un error general: " + e);
                }
                break;

                case 12:
                    //SALIMOS DEL SWITCH CASE Y DEL DO WHILE
                    System.out.println("Muchas gracias por usar nuestro programa, vuelve pronto :)");
                    opcion = 13;
                    break;

                default:
                    //MENSAJE QUE SE IMPRIME SI SE INGRESA UNA OPCION INCORRECTA
                    System.out.println("Ingresaste una opcion incorrecta, intentalo de nuevo :(");
                    opcion = 12;
                    break;
            }
        } while (opcion < 13);
    }
}
