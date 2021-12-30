package Taller2;

class Paralelo {
    private String paralelo;
    private int cantidadAlumnos;
    private Asignatura asignatura;
    private Profesor profesor;

    public Paralelo(String paralelo) {
        this.paralelo = paralelo;
        asignatura = null;
        profesor = null;
        cantidadAlumnos = 0;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}
