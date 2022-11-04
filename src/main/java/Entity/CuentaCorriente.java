package Entity;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

//IMPORTS NECESARIOS PARA LA FUNCIONALIDAD DEL PROGRAMA
import Control.CuentaExce;
import java.io.Serializable;
import java.time.LocalDate;

public class CuentaCorriente extends Cuenta implements Serializable {//CLASE QUE EXTIENDE DE LA CLASE CUENTA

    //ATRIBUTOS ESPECIFICOS DE LA CLASE CUENTACORRIENTE
    private double sobreGiro;
    private double sobreGiroUsado;

    //CONSTRUCTOR DE LA CLASE CUENTACORRIENTE
    public CuentaCorriente(double sobreGiro, double sobreGiroUsado, boolean estado, LocalDate fechaCreacion, int numero, double saldo) {
        super(estado, fechaCreacion, numero, saldo);
        this.sobreGiro = sobreGiro;
        this.sobreGiroUsado = sobreGiroUsado;
    }

    //LA IMPLEMENTACION DEL METODO CONSIGNAR DE LA CLASE CUENTA
    @Override
    public boolean consignar(double monto) {
        if (monto > this.sobreGiroUsado) {//REVISA SI EL MONTO ES MAYOR AL SOBREGIRO USADO
            monto -= this.sobreGiroUsado;
            this.sobreGiroUsado = 0;
            super.saldo += monto;
        } else {
            this.sobreGiroUsado -= monto;//SI EL MONTO ES MENOR QUE EL SOBREGIRO USADO, SOLO SE RESTA EL SOBREGIRO USADO CON EL MONTO
        }
        return true;
    }

    //LA IMPLEMENTACION DEL METODO RETIRAR DE LA CLASE CUENTA
    @Override
    public boolean retirar(double monto) throws CuentaExce {
        if (monto <= (super.saldo + this.sobreGiro - this.sobreGiroUsado)) { //COMPRUEBA SI ES POSIBLE RETIRAR DE LA CUENTA CORRIENTE
            if (monto <= super.saldo) {//SI EL MONTO ES MENOR AL SALDO
                super.saldo -= monto;
            } else if (monto > super.saldo) {//SI EL MONTO ES SUPERIOR AL SALDO
                monto -= super.saldo;
                super.saldo = 0;
                sobreGiroUsado += monto;
            }
            return true;
        }
        throw new CuentaExce("No fue posible retirar el dinero");//SI NO ES POSIBLE RETIRAR DE LA CUENTA SE MUESTRA EL MENSAJE RESPECTIVO
    }

    //TO STRING DE LA CLASE CUENTAACORRIENTE
    @Override
    public String toString() {
        return "\nCuenta Corriente #" + super.numero + " {\n"
                + "Saldo------Tipo------Sobregiro------SobregiroUsado------Estado------FechaCreacion\n"
                + saldo + "------Corriente------" + this.sobreGiro + "-------" + this.sobreGiroUsado + "-------" + super.estado + "-------" + super.fechaCreacion + "\n}"
                + super.titulares;
    }

    //GETTERS Y SETTERS DE LA CLASE CUENTACORRIENTE
    public double getSobreGiro() {
        return sobreGiro;
    }

    public void setSobreGiro(double sobreGiro) {
        this.sobreGiro = sobreGiro;
    }

    public double getSobreGiroUsado() {
        return sobreGiroUsado;
    }

    public void setSobreGiroUsado(double sobreGiroUsado) {
        this.sobreGiroUsado = sobreGiroUsado;
    }

}
