class Estadistica {
    private String nombreCampeon;
    private int totalRecaudadoCampeon;

    public Estadistica(String nombreCampeon, int totalRecaudadoCampeon) {
        this.nombreCampeon = nombreCampeon;
        this.totalRecaudadoCampeon = totalRecaudadoCampeon;
    }

    public String getNombreCampeon() {
        return nombreCampeon;
    }

    public int getTotalRecaudadoCampeon() {
        return totalRecaudadoCampeon;
    }

    public void setNobmreCampeon(String nombreCampeon) {
        this.nombreCampeon = nombreCampeon;
    }

    public void setTotalRecaudadoCampeon(int totalRecaudadoCampeon) {
        this.totalRecaudadoCampeon = totalRecaudadoCampeon;
    }
}
