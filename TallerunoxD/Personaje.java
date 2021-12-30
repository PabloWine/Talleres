public class Personaje {
    private String nombreCampeon;
    private String rol;
    private int cantidadSkins;
    private String informacionSkins;

    public Personaje(String nombreCampeon, String rol, int cantidadSkins, String informacionSkins) {
        this.nombreCampeon = nombreCampeon;
        this.rol = rol;
        this.cantidadSkins = cantidadSkins;
        this.informacionSkins = informacionSkins;
    }

    public String getNombreCampeon() {
        return nombreCampeon;
    }

    public String getRol() {
        return rol;
    }

    public int getCantidadSkins() {
        return cantidadSkins;
    }

    public String getInformacionSkins() {
        return informacionSkins;
    }

    public void setNombreCampeon(String nombreCampeon) {
        this.nombreCampeon = nombreCampeon;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCantidadSkins(int cantidadSkins) {
        this.cantidadSkins = cantidadSkins;
    }

    public void setInformacionSkins(String informacionSkins) {
        this.informacionSkins = informacionSkins;
    }

}
