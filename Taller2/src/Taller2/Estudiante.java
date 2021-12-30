package Taller2;

class Estudiante {

    private String rut;
    private String correo;
    private int nivel;
    private String contraseña;
    private int credito;
    private ListaAsignaturasCursadas LAC;
    private ListaAsignaturasInscritas LAI;

    public Estudiante(String rut, String correo, int nivel, String contraseña) {
        this.rut = rut;
        this.correo = correo;
        this.nivel = nivel;
        this.contraseña = contraseña;
        this.credito = 0;
        LAC = new ListaAsignaturasCursadas(1000);
        LAI = new ListaAsignaturasInscritas(1000);

    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public ListaAsignaturasCursadas getLAC() {
        return LAC;
    }

    public void setLAC(ListaAsignaturasCursadas lAC) {
        LAC = lAC;
    }

    public ListaAsignaturasInscritas getLAI() {
        return LAI;
    }

    public void setLAI(ListaAsignaturasInscritas lAI) {
        LAI = lAI;
    }

}
