package Entity;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

//IMPORTS NECESARIOS PARA LA FUNCIONALIDAD DEL PROGRAMA
import java.io.Serializable;
import java.time.LocalDate;

public class Titular implements Serializable {

    //ATRIBUTOS ESPECIFICOS DE LA CLASE TITULAR
    private String celular;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String nombre;
    private String telefono;

    //CONSTRUCTOR DE LA CLASE CUENTA TITULAR
    public Titular(String celular, String direccion, LocalDate fechaNacimiento, String nombre, String telefono) {
        this.celular = celular;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    //TO STRING DE LA CLASE TITULAR
    @Override
    public String toString() {
        return "Titular{\n"
                + "nombre---------------fecha Nacimiento----TelFijo-----Celular-------Direccion\n" + nombre + "        *" + this.fechaNacimiento + "        *" + telefono + "        *" + celular + "        *" + direccion + "\n";
    }

    //GETTERS Y SETTERS DE LA CLASE TITULAR
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
