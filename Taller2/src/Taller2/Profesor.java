package Taller2;

class Profesor {
    private String rut;
    private String correo;
    private String contraseña;
    private int salario;

    public Profesor(String rut, String correo, String contraseña, int salario) {
        this.rut = rut;
        this.correo = correo;
        this.contraseña = contraseña;
        this.salario = salario;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

}
