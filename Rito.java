
public interface Rito {
    int iniciarSesion(String nombreCuenta, String contraseña);

    boolean añadirCuenta(String nombreCuenta, String contraseña, String nick, int nivel, int rp, int totalPersonajes,
            String skins, String region);

    boolean añadirPersonaje(String nombreCampeon, String rol, int cantidadSkins, String skins);

    boolean añadirEstadistica(String nombreCampeon, int totalRecaudadoCampeon);

    String verInventario(String nombreCuenta);

    void agregarRP(String nombreCuenta, int cantidad);

    int miRP(String nombreCuenta);

    void informacionUsuario(String nombreCuenta);

    String skinsDisponibles(String nombreCuenta);

    String comprarSkin(String nombreCuenta);

    String comprarPersonaje(String nombreCuenta);

    String recaudacionPorRol();

    String recaudacionPorRegion();

    String recaudacionPersonajes();

    String personajesPorRol();

    void agregarSkin();

    void agregarPersonaje();

    String blockearCuenta();

    public int precioSkin(String calidad);

    void darInfo();

    void escribirDatos();

}
