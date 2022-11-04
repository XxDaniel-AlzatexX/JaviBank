package Control;//PAQUETE EN EL QUE SE ENCUENTRA LA CLASE

public class BancoExce extends Exception {

    //SE DECLARAN LOS ATRIBUTOS DE LA CLASE
    private String detalle;

    //SE DECLARA EL CONSTRUCTOR DE LA CLASE
    public BancoExce(String s) {
        detalle = s;
    }

    //SE DECLARA EL METODO TO STRING DE LA CLASE
    @Override
    public String toString() {
        return "BancoException{" + "detalle=" + detalle + '}';
    }

}
