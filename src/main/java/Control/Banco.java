package Control;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

//IMPORTS NECESARIOS PARA LA FUNCIONALIDAD DEL PROGRAMA
import Entity.Cuenta;
import Entity.CuentaAhorros;
import Entity.CuentaCorriente;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Banco implements Serializable {

    //RELACION DE LA CLASE BANCO
    private List<Cuenta> cuentas = new ArrayList<>();

    //METODO ENCARGADO DE BUSCAR UNA CUENTA A BASE DEL NUMERO DE ESTA
    public Cuenta buscarCuenta(int numero) throws BancoExce {
        Cuenta nueva = null;
        for (Cuenta actual : cuentas) {
            if (actual.getNumero() == numero) {
                nueva = actual;
            }
        }
        if (nueva != null) {
            return nueva;
        } else {
            throw new BancoExce("La cuenta no fue encontrada");
        }
    }

    //METODO ENCARGADO DE CREAR UNA CUENTA APARTIR DE LOS DATOS RECIIDOS
    public static Cuenta crearCuenta(int numCuenta, float saldo, boolean estado, LocalDate fechaCreacion, String tipo, String tarSob) {
        Cuenta.setConsecutivo(Cuenta.getConsecutivo() + 1);
        if (tipo.equals("ahorros")) {
            CuentaAhorros cuenAhor = new CuentaAhorros(tarSob, estado, fechaCreacion, numCuenta, saldo);
            return cuenAhor;
        } else {
            CuentaCorriente cuenCorr = new CuentaCorriente(Double.parseDouble(tarSob), 0, estado, fechaCreacion, numCuenta, saldo);
            return cuenCorr;
        }
    }

    //METODO ENCARGADO DE CONSIGNAR A UNA CUENTA APARTIR DE SU NUMERO Y EL MONTO A CONSIGNAR
    public String consignarACuenta(int numero, double monto) throws BancoExce {
        for (Cuenta actual : cuentas) {
            if (actual instanceof CuentaAhorros) {
                if (actual.getNumero() == numero) {
                    actual.consignar(monto);
                    return actual.toString();
                }
            } else if (actual instanceof CuentaCorriente) {
                if (actual.getNumero() == numero) {
                    actual.consignar(monto);
                    return actual.toString();
                }
            }
        }
        throw new BancoExce("No fue posible consignar en la cuenta");
    }
    
    //METODO ENCARGADO DE RETIRAR DE  UNA CUENTA APARTIR DE SU NUMERO Y EL MONTO A RETIRAR
    public String retirarDeCuenta(int numero, double monto) throws CuentaExce, BancoExce {
        for (Cuenta actual : cuentas) {
            if (actual instanceof CuentaAhorros) {
                if (actual.getNumero() == numero) {
                    if (actual.retirar(monto) == true) {
                        return actual.toString();
                    }
                }
            } else if (actual instanceof CuentaCorriente) {
                if (actual.getNumero() == numero) {
                    if (actual.retirar(monto) == true) {
                        return actual.toString();
                    }
                }
            }
        }
        throw new BancoExce("No fue posible retirar de la cuenta");//EXCEPCION POR SI NO SE REALIZO LA OPERACIÓN 
    }
    
    //METODO ENCARGADO DE TRANSFERIR ENTRE CUENTAS APARTIR DE SU NUMERO Y EL MONTO A TRANSFERIR
    public List<String> transferir(int numeroOrigen, int numeroDestino, double monto) throws CuentaExce, BancoExce {
        List<String> aux = new ArrayList<>();
        if (buscarCuenta(numeroOrigen) != null) {
            if (buscarCuenta(numeroDestino) != null) {
                if (retirarDeCuenta(numeroOrigen, monto) != null) {//SE RETIRA DE DINERO DE LA CUENTA
                    aux.add(consignarACuenta(numeroDestino, monto));//SE AGREGA A LA LISTA LOS DATOS DE LA CUENTA DE DESTINO Y SE CONSIGNA 
                    aux.add("\n");
                    aux.add(infoCuenta(numeroOrigen)); //SE AGREGA A LA LISTA LOS DATOS DE LA CUENTA DE ORIGEN
                    return aux;
                }
            }
        }
        throw new BancoExce("No fue posible transferir el dinero");//EXCEPCION POR SI NO SE REALIZO LA OPERACIÓN 
    }

    //METODO ENCARGADO DE RETORNAR UNA LISTA DE TITULARES APARTIR DEL NUMERO DE CUENTA
    public List<String> mostrarTitulares(int numero) {
        List<String> aux = new ArrayList<>();
        for (Cuenta actual : cuentas) {
            if (actual.getNumero() == numero) {
                aux = actual.mostrarTitulares(actual); //SE AGREGAN LOS TITULARES A LA LISTA
            }
        }
        return aux;
    }

    //METODO ENCARGADO DE CALCULAR LA EDAD DE LAS PERSONAS APARTIR DE EL NUMERO DE LA CUENTA Y SU FECHA DE NACIEMIENTO
    public ArrayList<Integer> calcularEdad(int numero, LocalDate fecha) {
        ArrayList<Integer> edades = new ArrayList<>();
        for (int i = 0; i < cuentas.get(numero).getTitulares().size(); i++) {
            Period aux = Period.between(cuentas.get(numero).getTitulares().get(i).getFechaNacimiento(), fecha);
            edades.add(aux.getYears());
        }
        return edades;
    }

    //METODO ENCARGADO DE RETORNAR UNA LISTA DE TITULARES CREADOS ANTES DE LA FECHA ENVIADA
    public List<String> mostrarCuentasFechaNacimineto(LocalDate fecha) {
        int aux;
        List<LocalDate> fech = new ArrayList<>();
        List<String> cuen = new ArrayList<>();
        for (Cuenta actual : cuentas) {
            aux = 0;
            fech = actual.BuscarFechasTitulares(actual);
            for (int i = 0; i < fech.size(); i++) {
                if (fecha.isAfter(fech.get(i))) {
                    aux++;
                }
            }
            if (aux > 0) {
                cuen.add(actual.toString());
            }
        }
        return cuen;
    }

    //METODO ENCARGADO DE RETORNAR UNA LISTA DE TITULARES CREADOS ANTES DE LA FECHA ENVIADA
    public List<String> mostrarCuentasFechaCreacion(LocalDate fecha) {
        ArrayList<String> aux = new ArrayList<>();
        LocalDate fechaNueva;//SE CREA UN NUEVO LOCAL DATE
        for (Cuenta actual : cuentas) {
            fechaNueva = actual.getFechaCreacion();
            if (fechaNueva.isBefore(fecha)) {//SE IDENTIFICA SI LA FECHA ES MENOR A LA FECHA ASIGNADA
                aux.add(actual.toString());//SE AGREGA A LA LISTA
            }
        }
        return aux;
    }

    //METODO ENCARGADO DE REMOVER UNA CUENTA
    public Cuenta removerCuenta(int numero) throws BancoExce {
        Cuenta copia = null;
        if (buscarCuenta(numero) != null) {
            copia = buscarCuenta(numero);
            cuentas.remove(buscarCuenta(numero));
        }
        return copia;
    }

    //METODO ENCARGADO DE MOSTRAR LA INFORMACION DE UNA CUENTA APARTIR DE SU NUMERO
    public String infoCuenta(int numero) throws BancoExce {
        for (Cuenta actual : cuentas) {
            if (actual.getNumero() == numero) {
                return actual.toString();
            }
        }
        throw new BancoExce("No fue posible mostrar la informacion de la cuenta");
    }

    //GETTERS Y SETTERS DE LA CLASE BANCO
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

}
