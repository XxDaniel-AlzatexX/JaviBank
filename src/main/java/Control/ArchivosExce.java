package Control;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

public class ArchivosExce extends Exception {

    //SE DECLARAN LOS ATRIBUTOS DE LA CLASE
    private String detalle;

    //SE DECLARA EL CONSTRUCTOR DE LA CLASE
    public ArchivosExce(String s) {
        detalle = s;
    }

    //SE DECLARA EL METODO TO STRING DE LA CLASE
    @Override
    public String toString() {
        return "ArchivosException{" + "detalle=" + detalle + '}';
    }

}
