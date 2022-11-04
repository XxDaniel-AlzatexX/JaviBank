package Entity;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

//IMPORTS NECESARIOS PARA LA FUNCIONALIDAD DEL PROGRAMA
import Control.CuentaExce;
import java.io.Serializable;
import java.time.LocalDate;

public class CuentaAhorros extends Cuenta implements Serializable {//CLASE QUE EXTIENDE DE LA CLASE CUENTA

    //ATRIBUTOS ESPECIFICOS DE LA CLASE CUENTAAHORROS
    private String tarjetaDebito;

    //CONSTRUCTOR DE LA CLASE CUENTAAHORROS
    public CuentaAhorros(String tarjetaDebito, boolean estado, LocalDate fechaCreacion, int numero, double saldo) {
        super(estado, fechaCreacion, numero, saldo);
        this.tarjetaDebito = tarjetaDebito;
    }

    //LA IMPLEMENTACION DEL METODO CONSIGNAR DE LA CLASE CUENTA
    @Override
    public boolean consignar(double monto) {
        super.saldo += monto;//SE SUMA EL SALDO AL SALDO ANTERIOR
        return true;
    }

    //LA IMPLEMENTACION DEL METODO RETIRAR DE LA CLASE CUENTA
    @Override
    public boolean retirar(double monto) throws CuentaExce {
        if (monto <= super.saldo) {//SE COMPRUEBA SI ES POSIBLE RETIRAR, DE SERLO ASI, LO HACE
            super.saldo -= monto;
            return true;
        } else {
            throw new CuentaExce("No se pudo retirar el dinero correctamente"); //SI NO ES POSIBLE RETIRAR IMPRIME EL MENSAJE RESPECTIVO
        }
    }

    //TO STRING DE LA CLASE CUENTAAHORROS
    @Override
    public String toString() {
        return "\nCuenta Ahorros #" + super.numero + " {\n"
                + "Saldo------Tipo------Tarjeta------Estado------FechaCreacion\n"
                + saldo + "------Ahorros------" + tarjetaDebito + "-------" + super.estado + "-------" + super.fechaCreacion + "\n}"
                + super.titulares;
    }

    //GETTERS Y SETTERS DE LA CLASE CUENTAAHORROS
    public String getTarjetaDebito() {
        return tarjetaDebito;
    }

    public void setTarjetaDebito(String tarjetaDebito) {
        this.tarjetaDebito = tarjetaDebito;
    }

}
