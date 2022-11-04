package Entity;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

//IMPORTS NECESARIOS PARA LA FUNCIONALIDAD DEL PROGRAMA
import Control.CuentaExce;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta implements Serializable {

    //ATRIBUTOS Y RELACIONES ESPECIFICOS DE LA CLASE CUENTA
    protected static int consecutivo;
    protected boolean estado;
    protected LocalDate fechaCreacion;
    protected int numero;
    protected double saldo;
    protected List<Titular> titulares = new ArrayList<>();

    //CONSTRUCTOR DE LA CLASE CUENTA
    public Cuenta(boolean estado, LocalDate fechaCreacion, int numero, double saldo) {
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.numero = numero;
        this.saldo = saldo;
    }

    //METODO QUE CREA UN TITULAR Y RECIBE TODOS LOS ATRIBUTOS DE ESTE
    public Titular crearTitular(String nombre, LocalDate fechaNac, String telefono, String celular, String direccion) {
        Titular titular = new Titular(celular, direccion, fechaNac, nombre, telefono);//CREA UN TITULAR
        return titular;//RETORNA EL TITULAR
    }

    //METODO QUE RETORNA UNA LISTA CON LOS TITULARES DE LA CUENTA RECIBIDA
    public List<String> mostrarTitulares(Cuenta cuenta) {
        List<String> tit = new ArrayList<>();
        for (int i = 0; i < titulares.size(); i++) {
            tit.add(cuenta.getTitulares().get(i).getNombre());//AGREGA EL NOMBRE DEL TITULAR A LA LISTA
        }
        return tit;//RETORNA LA LISTA CON EL NOMBRE DE LOS TITULARES
    }

    //METODO QUE RETORNA UNA LISTA CON LAS FECHAS DE NACIMIENTO DE LOS TITULARES DE LA CUENTA RECIBIDA
    public List<LocalDate> BuscarFechasTitulares(Cuenta cuenta) {
        List<LocalDate> fechas = new ArrayList<>();
        for (int i = 0; i < titulares.size(); i++) {
            fechas.add(cuenta.getTitulares().get(i).getFechaNacimiento());//AGREGA LA FECHA DE NACIMINETO DEL TITULAR A LA LISTA
        }
        return fechas;//RETORNA LA LISTA CON LAS FECHAS DE LOS TITULARES
    }

    //METODO ABSTRACTO CONSIGNAR 
    public abstract boolean consignar(double monto);

    //METODO ABSTRACTO RETIRAR
    public abstract boolean retirar(double monto) throws CuentaExce;

    //GETTERS Y SETTERS DE LA CLASE CUENTA
    public static int getConsecutivo() {
        return consecutivo;
    }

    public static void setConsecutivo(int consecutivo) {
        Cuenta.consecutivo = consecutivo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Titular> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Titular> titulares) {
        this.titulares = titulares;
    }

}
