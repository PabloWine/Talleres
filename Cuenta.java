class Cuenta {
    private String nombreCuenta;
    private String contraseña;
    private String nick;
    private int nivel;
    private int rp;
    private int totalPersonajes;
    private String skins;
    private String region;
    private ListaCuentas listaCuentas;

    public Cuenta(String nombreCuenta, String contraseña, String nick, int nivel, int rp, int totalPersonajes,
            String skins, String region) {
        this.nombreCuenta = nombreCuenta;
        this.contraseña = contraseña;
        this.nick = nick;
        this.nivel = nivel;
        this.rp = rp;
        this.totalPersonajes = totalPersonajes;
        this.skins = skins;
        this.region = region;
        listaCuentas = new ListaCuentas(100);
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNick() {
        return nick;
    }

    public int getNivel() {
        return nivel;
    }

    public int getRP() {
        return rp;
    }

    public int getTotalPersonajes() {
        return totalPersonajes;
    }

    public String getSkins() {
        return skins;
    }

    public String getRegion() {
        return region;
    }

    public ListaCuentas getListaCuentas() {
        return listaCuentas;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setRP(int rp) {
        this.rp = rp;
    }

    public void setTotalPersonajes(int totalPersonajes) {
        this.totalPersonajes = totalPersonajes;
    }

    public void setSkins(String skins) {
        this.skins = skins;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Cuentas{" + "nombreCuenta='" + nombreCuenta + '\'' + ", contrasenaCuenta='" + contraseña + '\''
                + ", nickCuenta='" + nick + '\'' + ", nivelCuenta=" + nivel + ", rpCuenta=" + rp + ", totalPersonajes="
                + totalPersonajes + ", Skins='" + skins + '\'' + ", regionCuenta='" + region + '\'' + '}';
    }
}