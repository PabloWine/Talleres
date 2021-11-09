import java.io.*;
import java.util.*;

class RitoImpl implements Rito {
    ListaPersonajes listaPersonajes;
    ListaCuentas listaCuentas;
    ListaEstadisticas listaEstadisticas;

    public RitoImpl() {
        listaCuentas = new ListaCuentas(1000);
        listaEstadisticas = new ListaEstadisticas(1000);
        listaPersonajes = new ListaPersonajes(155);
    }

    @Override
    public int iniciarSesion(String nombreCuenta, String contraseña) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        if (nombreCuenta.equals("ADMIN") && contraseña.equals("ADMIN")) {
            return 2;
        } else if (cuenta != null && cuenta.getContraseña().equals(contraseña)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean añadirCuenta(String nombreCuenta, String contraseña, String nick, int nivel, int rp,
            int totalPersonajes, String skins, String region) {
        Cuenta cuenta = new Cuenta(nombreCuenta, contraseña, nick, nivel, rp, totalPersonajes, skins, region);
        listaCuentas.agregarCuentas(cuenta);
        return false;
    }

    @Override
    public boolean añadirPersonaje(String nombreCampeon, String rol, int cantidadSkins, String skins) {
        Personaje personaje = new Personaje(nombreCampeon, rol, cantidadSkins, skins);
        listaPersonajes.añadirPersonaje(personaje);
        return false;
    }

    @Override
    public boolean añadirEstadistica(String nombreCampeon, int totalRecaudadoCampeon) {
        Estadistica estadistica = new Estadistica(nombreCampeon, totalRecaudadoCampeon);
        listaEstadisticas.añadirEstadistica(estadistica);
        return false;
    }

    @Override
    public String verInventario(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        System.out.println("Skins de: " + nombreCuenta);
        try {
            String partes[] = cuenta.getSkins().split(",");
            for (int i = 0; i < partes.length; i++) {
                System.out.println("Campeon: " + partes[i - 1]);
                for (int j = 0; j < Integer.parseInt(partes[i]); j++) {
                    System.out.println(partes[i + (j + 1)]);
                }
            }
        } catch (Exception e) {
            System.out.println("La cuenta ingresada no posee Skins!");
        }
        return "";
    }

    @Override
    public void agregarRP(String nombreCuenta, int cantidad) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        cuenta.setRP(cuenta.getRP() + cantidad);
    }

    @Override
    public int miRP(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        return cuenta.getRP();
    }

    private String censurarContraseña(String contraseña) {
        String contraseñaCensurada = "";
        for (int i = 0; i < contraseña.length(); i++) {
            if (i != contraseña.length() - 1 && i != contraseña.length() - 2 && i != contraseña.length() - 3) {
                contraseñaCensurada += "*";
            } else {
                contraseñaCensurada += contraseña.charAt(i);
            }
        }
        return contraseñaCensurada;
    }

    private void cambiarContraseña(Cuenta cuenta) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese su contraseña actual: ");
        String contraseñaPresente = teclado.next(), contraseñaFutura = "", contraseñaFutura2 = "";
        if (contraseñaPresente.equals(cuenta.getContraseña())) {
            System.out.println("Ingrese su nueva contraseña: ");
            contraseñaFutura = teclado.next();
            System.out.println("Repita su nueva contraseña: ");
            contraseñaFutura2 = teclado.next();
            if (contraseñaFutura.equals(contraseñaFutura2)) {
                cuenta.setContraseña(contraseñaFutura);
                System.out.println("Su contraseña fue cambiada exitosamente!:");
            } else {
                System.out.println("Las contraseñas ingresadas no coinciden.");
            }
        } else {
            System.out.println("La contraseña ingresada no es la actual.");
        }
    }

    @Override
    public void informacionUsuario(String nombreCuenta) {
        Scanner teclado = new Scanner(System.in);
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        System.out.println("Nombre Cuenta: " + cuenta.getNombreCuenta());
        System.out.println("Nick: " + cuenta.getNick());
        String contraseña = censurarContraseña(cuenta.getContraseña());
        System.out.println("Contraseña: " + contraseña);
        System.out.println("¿Desea realizar un cambio de contraseña? (S/N): ");
        String opcion = teclado.next();
        while (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N")) {
            System.out.println("La opcion ingresada no es valida. Reintentelo.");
            System.out.println("¿Desea realizar un cambio de contraseña? (S/N): ");
            opcion = teclado.next();
        }
        if (opcion.equalsIgnoreCase("S")) {
            cambiarContraseña(cuenta);
        }
    }

    private boolean usuarioSkin(String parte, String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        String partes[] = cuenta.getSkins().split(",");
        int contador = 0;
        for (int i = 0; i < partes.length; i++) {
            if (partes[i].equalsIgnoreCase(parte)) {
                contador++;
            }
        }
        return contador > 0;

    }

    private String conversionCalidad(String inicial) {
        switch (inicial) {
        case "M":
            return "MITICA";
        case "D":
            return "DEFINITIVA";
        case "L":
            return "LEGENDARIA";
        case "N":
            return "NORMAL";
        case "E":
            return "EPICA";
        default:
            return "No se encuentra";
        }
    }

    @Override
    public String skinsDisponibles(String nombreCuenta) {

        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personaje personaje = listaPersonajes.getPersonajePuntual(i);
            System.out.println("Campeon: " + personaje.getNombreCampeon());
            String partes[] = personaje.getInformacionSkins().split(",");
            for (int j = 0; j <= 100; j++) {
                try {
                    if (!usuarioSkin(partes[j], nombreCuenta)) {
                        System.out.println("-> " + conversionCalidad(partes[j + 1] + partes[j]));
                    }
                } catch (Exception e) {
                    System.out.println("Error.");
                }
            }
            if (i != listaPersonajes.getCantidad() - 1) {
                System.out.println("---------");
            }
        }
        return "";
    }

    private boolean comprobarPersonaje(String nombrePersonaje) {
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personaje personaje = listaPersonajes.getPersonajePuntual(i);
            if (nombrePersonaje.equalsIgnoreCase(personaje.getNombreCampeon())) {
                return true;
            }

        }
        return false;
    }

    private boolean personajeUsuario(String nombrePersonaje, String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        String partes[] = cuenta.getSkins().split(",");
        for (int i = 0; i < partes.length; i++) {
            if (partes[i].equalsIgnoreCase(nombrePersonaje)) {
                return true;
            }
        }
        return false;

    }

    public int posicion = 0;

    private boolean comprobarSkin(String nombrePersonaje, String skin, String nombreCuenta) {
        Personaje personaje = listaPersonajes.buscarPersonaje(nombrePersonaje);
        String partes[] = personaje.getInformacionSkins().split(",");
        for (int i = 0; i < partes.length; i++) {
            if (partes[i].equalsIgnoreCase(skin) && !usuarioSkin(partes[i], nombreCuenta)) {
                posicion = i;
                return true;
            }
        }
        return false;
    }

    public int precioSkin(String calidad) {
        switch (calidad) {
        case "M":
            return 3250;
        case "D":
            return 2750;
        case "L":
            return 1820;
        case "E":
            return 1350;
        case "N":
            return 975;
        default:
            return 132131232;

        }
    }

    @Override
    public String comprarSkin(String nombreCuenta) {
        Scanner teclado = new Scanner(System.in);
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        System.out.println("Ingresa el nombre del personaje dueño de la skin: ");
        String nombrePersonaje = teclado.next();
        while (!comprobarPersonaje(nombrePersonaje) && !personajeUsuario(nombrePersonaje, nombreCuenta)) {
            System.out.println("El personaje ingresado no es valido para comprar una skin. Reintentelo.");
            System.out.println("Ingresa el nombre del personaje dueño de la skin: ");
            nombrePersonaje = teclado.next();
        }

        System.out.println("Ingrese el nombre de la skin que desea comprar: ");
        String nombreSkin = teclado.next();
        while (!comprobarSkin(nombrePersonaje, nombreSkin, nombreCuenta)) {
            System.out.println("La skin ingresada no es valida para comprar. Reintentelo.");
            System.out.println("Ingrese el nombre de la skin que desea comprar: ");
            nombreSkin = teclado.next();
        }

        Personaje personaje = listaPersonajes.buscarPersonaje(nombrePersonaje);
        String partes[] = personaje.getInformacionSkins().split(",");
        String calidadSkin = conversionCalidad(partes[posicion + 1]);
        String nombreDeLaSkin = partes[posicion];
        int precio = precioSkin(partes[posicion + 1]);
        System.out.println("Nombre: " + nombreDeLaSkin);
        System.out.println("Calidad: " + calidadSkin);
        System.out.println("Precio: " + precio);
        System.out.println("¿Desea confirmar la compra? (S/N): ");
        String opcion = teclado.next();
        while (!opcion.equalsIgnoreCase("Y") && !opcion.equalsIgnoreCase("N")) {
            System.out.println("La opcion ingresada no es valida. Reintentelo.");
            System.out.println("¿Desea confirmar la compra? (S/N): ");
            opcion = teclado.next();
        }
        if (opcion.equalsIgnoreCase("Y")) {
            if (cuenta.getRP() < precio) {
                System.out.println("No posee la cantidad de RP suficiente para completar la compra.");
            } else {
                cuenta.setRP(cuenta.getRP() - 975);
                cuenta.setNivel(cuenta.getNivel() + 1);
                String partes2[] = cuenta.getSkins().split(","), actuales = "";

                for (int i = 0; i < partes2.length; i++) {
                    if (partes2[i].equalsIgnoreCase(nombrePersonaje)) {
                        int actual = Integer.parseInt(partes2[i + 1]);
                        partes2[i + 1] = String.valueOf(actual + 1);
                        partes2[i + 1] += "," + nombreSkin;
                    }
                    if (i == partes2.length - 1) {
                        actuales += partes2[i];
                    } else {
                        actuales += partes2[i] + ",";
                    }
                }
                Estadistica estadistica = listaEstadisticas.buscarEstadistica(nombrePersonaje);
                int valor_clp = precio * (int) 6.15;
                estadistica.setTotalRecaudadoCampeon(estadistica.getTotalRecaudadoCampeon() + valor_clp);

                cuenta.setSkins(actuales);
                System.out.println("Compra completada!");
            }
        }
        return "";
    }

    @Override
    public String comprarPersonaje(String nombreCuenta) {
        Scanner teclado = new Scanner(System.in);
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        System.out.println("Ingrese el nombre del personaje a comprar: ");
        String nombrePersonaje = teclado.next();

        while (!comprobarPersonaje(nombrePersonaje) && !personajeUsuario(nombrePersonaje, nombreCuenta)) {
            System.out.println("El personaje ingresado no es valido. Riententelo.");
            System.out.println("Ingrese el nombre del personaje a comprar: ");
            nombrePersonaje = teclado.next();
        }
        System.out.println("¿Desea confirmar la compra? (S/N): ");
        String opcion = teclado.next();
        while (!opcion.equalsIgnoreCase("Y") && !opcion.equalsIgnoreCase("N")) {
            System.out.println("La opcion ingresada no es valida. Reintenetlo");
            System.out.println("¿Desea confirmar la compra? (S/N): ");
            opcion = teclado.next();
        }
        if (opcion.equalsIgnoreCase("Y")) {
            if (cuenta.getRP() < 975) {
                System.out.println("No tiene saldo suficiente para comprar el personaje.");
            } else {
                cuenta.setRP(cuenta.getRP() - 975);
                cuenta.setNivel(cuenta.getNivel() + 1);
                cuenta.setSkins(cuenta.getSkins() + ("," + nombrePersonaje + ",0"));
                Estadistica estadistica = listaEstadisticas.buscarEstadistica(nombrePersonaje);
                int valor_clp = 975 * (int) 6.15;
                estadistica.setTotalRecaudadoCampeon(estadistica.getTotalRecaudadoCampeon() + valor_clp);
                System.out.println("Compra completada.");
            }
        }
        return "";
    }

    @Override
    public String recaudacionPorRol() {
        int contADC = 0, contMID = 0, contJG = 0, contTOP = 0, contSUP = 0;
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personaje personaje = listaPersonajes.getPersonajePuntual(i);
            Estadistica estadistica = listaEstadisticas.getEstadisticaPuntual(i);
            if (estadistica.getNombreCampeon().equals(personaje.getNombreCampeon())) {
                if (personaje.getRol().equalsIgnoreCase("ADC")) {
                    contADC += estadistica.getTotalRecaudadoCampeon();
                }
                if (personaje.getRol().equalsIgnoreCase("MID")) {
                    contMID += estadistica.getTotalRecaudadoCampeon();
                }
                if (personaje.getRol().equalsIgnoreCase("TOP")) {
                    contTOP += estadistica.getTotalRecaudadoCampeon();
                }

                if (personaje.getRol().equalsIgnoreCase("JG")) {
                    contJG += estadistica.getTotalRecaudadoCampeon();
                }
                if (personaje.getRol().equalsIgnoreCase("SUP")) {
                    contSUP += estadistica.getTotalRecaudadoCampeon();
                }
            }

        }

        System.out.println("La cantidad de ventas por rol:");
        System.out.println("ADC: " + contADC);
        System.out.println("SUP: " + contSUP);
        System.out.println("MID: " + contMID);
        System.out.println("TOP: " + contTOP);
        System.out.println("JG: " + contJG);
        return "";
    }

    @Override
    public String recaudacionPorRegion() {
        int contLAS = 0, contLAN = 0, contEUW = 0, contKR = 0, contNA = 0, contRU = 0;
        for (int i = 0; i < listaCuentas.getCantidad(); i++) {
            Cuenta cuenta = listaCuentas.getRegion(i);
            if (cuenta.getRegion().equalsIgnoreCase("LAN")) {
                contLAN += cuenta.getRP() * (int) 6.15;
            }
            if (cuenta.getRegion().equalsIgnoreCase("LAS")) {
                contLAS += cuenta.getRP() * (int) 6.15;
            }
            if (cuenta.getRegion().equalsIgnoreCase("EUW")) {
                contEUW += cuenta.getRP() * (int) 6.15;
            }
            if (cuenta.getRegion().equalsIgnoreCase("KR")) {
                contKR += cuenta.getRP() * (int) 6.15;
            }
            if (cuenta.getRegion().equalsIgnoreCase("NA")) {
                contNA += cuenta.getRP() * (int) 6.15;
            }
            if (cuenta.getRegion().equalsIgnoreCase("RU")) {
                contRU += cuenta.getRP() * (int) 6.15;
            }

        }
        System.out.println("Recaudacion por region: ");
        System.out.println("LAN: " + contLAN);
        System.out.println("LAS: " + contLAS);
        System.out.println("EUW: " + contEUW);
        System.out.println("KR: " + contKR);
        System.out.println("NA: " + contNA);
        System.out.println("RU: " + contRU);
        return "";
    }

    @Override
    public String recaudacionPersonajes() {
        for (int i = 0; i < listaEstadisticas.getCantidad(); i++) {
            Estadistica estadistica = listaEstadisticas.getEstadisticaPuntual(i);
            System.out.println("Campeon: " + estadistica.getNombreCampeon());
            System.out.println("Total Recaudado: $" + estadistica.getTotalRecaudadoCampeon());

        }
        return "";
    }

    @Override
    public String personajesPorRol() {
        int ADC = 0, SUP = 0, MID = 0, TOP = 0, JG = 0;
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personaje personaje = listaPersonajes.getPersonajePuntual(i);
            if (personaje.getRol().equalsIgnoreCase("ADC")) {
                ADC++;
            }
            if (personaje.getRol().equalsIgnoreCase("SUP")) {
                SUP++;
            }
            if (personaje.getRol().equalsIgnoreCase("MID")) {
                MID++;
            }
            if (personaje.getRol().equalsIgnoreCase("TOP")) {
                TOP++;
            }
            if (personaje.getRol().equalsIgnoreCase("JG")) {
                JG++;
            }
        }
        System.out.println("Cantidad de personajes por rol: ");
        System.out.println("ADC: " + ADC);
        System.out.println("SUP: " + SUP);
        System.out.println("MID: " + MID);
        System.out.println("JG: " + JG);
        System.out.println("TOP: " + TOP);
        return "";
    }

    @Override
    public void agregarSkin() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del personaje: ");
        String nombrePersonaje = teclado.next();
        while (!comprobarPersonaje(nombrePersonaje)) {
            System.out.println("Personaje invalido Reintentelo.");
            System.out.println("Ingrese el nombre del personaje: ");
            nombrePersonaje = teclado.next();
        }

        String rol = "";
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            if (nombrePersonaje.equals(listaPersonajes.getPersonajePuntual(i))) {
                Personaje personaje = listaPersonajes.getRolPuntual(i);
                rol = personaje.getRol();
                break;
            }
        }
        int contador = 0;
        System.out.println("Ingrese el nombre de al skin: ");
        String nombre_skin = teclado.next();
        System.out.println("Ingrese su calidad: ");
        String calidad = teclado.next();

        String agregar = nombre_skin + "," + calidad;

        Personaje personaje2 = new Personaje(nombrePersonaje, rol, 2, agregar);
        System.out.println("Skin agregada!");
    }

    @Override
    public void agregarPersonaje() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del personaje: ");
        String nombrePersonaje = teclado.next();
        while (!comprobarPersonaje(nombrePersonaje)) {
            System.out.println("EL nombre del personaje ingresado no es valido.");
            System.out.println("Ingrese el nombre del personaje: ");
            nombrePersonaje = teclado.next();
        }

        System.out.println("Ingrese el rol del personaje");
        String rol = teclado.next();
        while (!rol.equalsIgnoreCase("ADC") && !rol.equalsIgnoreCase("SUP") && !rol.equalsIgnoreCase("TOP")
                && !rol.equalsIgnoreCase("JG") && !rol.equalsIgnoreCase("MID")) {
            System.out.println("El rol ingresado no es valido. Reintentelo.");
            System.out.println("Ingrese el rol del personaje");
            rol = teclado.next();
        }

        System.out.println("Ingrese la cantidad de skins del personaje");
        int cantidadSkins = teclado.nextInt();
        System.out.println("Ingrese el nombre de la skin: ");
        String nombre_skin = teclado.next();
        System.out.println("Ingrese la calidad de la skin: ");
        String calidad = teclado.next();
        String skins = nombre_skin + "," + calidad;

        Personaje per = new Personaje(nombrePersonaje, rol, cantidadSkins, skins);
        listaPersonajes.añadirPersonaje(per);
    }

    private boolean comprobarCuenta(String nombreCuenta) {
        for (int i = 0; i < listaCuentas.getCantidad(); i++) {
            Cuenta cuenta = listaCuentas.getCuentaPuntual(i);
            if (nombreCuenta.equalsIgnoreCase(cuenta.getNombreCuenta())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String blockearCuenta() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrsee la cuenta a bloquear: ");
        String nombreCuenta = teclado.next();
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        while (!comprobarCuenta(nombreCuenta)) {
            System.out.println("La cuenta ingresada no es valida. Reintentelo.");
            System.out.println("Ingrsee la cuenta a bloquear: ");
            nombreCuenta = teclado.next();
        }
        System.out.println("¿Desea confirmar el bloqueo? (S/N): ");
        String opcion = teclado.next();
        while (!opcion.equalsIgnoreCase("Y") && !opcion.equalsIgnoreCase("N")) {
            System.out.println("La opcion ingresada no es valida. Reintentelo.");
            System.out.println("¿Desea confirmar el bloqueo? (S/N): ");
            opcion = teclado.next();
        }
        if (opcion.equalsIgnoreCase("Y")) {
            listaCuentas.eliminarCuenta(nombreCuenta);
            System.out.println("Cuenta bloqueada con exito.");
        }
        return null;
    }

    @Override
    public void darInfo() {
        for (int i = 0; i < listaCuentas.getCantidad(); i++) {
            listaCuentas.getCuentaPuntual(i).toString();
        }
    }

    @Override
    public void escribirDatos() throws IOException, FileNotFoundException, InterruptedIOException {
        ArchivoSalida arch = new ArchivoSalida("Cuentas.txt");
        for (int i = 0; i < listaCuentas.getCantidad(); i++) {
            Cuenta cuentas = listaCuentas.getCuentaPuntual(i);
            Registro registro = new Registro(8);
            registro.agregarCampo(cuentas.getNombreCuenta());
            registro.agregarCampo(cuentas.getContraseña());
            registro.agregarCampo(cuentas.getNick());
            registro.agregarCampo(cuentas.getNivel());
            registro.agregarCampo(cuentas.getRP());
            registro.agregarCampo(cuentas.getTotalPersonajes());
            registro.agregarCampo(cuentas.getSkins());
            registro.agregarCampo(cuentas.getRegion());
            arch.writeRegistro(registro);
        }
        arch.close();
        ArchivoSalida arch1 = new ArchivoSalida("Estadisticas.txt");
        for (int i = 0; i < listaEstadisticas.getCantidad(); i++) {
            Estadisticas estadistica = listaEstadisticas.getEstadisticaPuntual(i);
            Registro registro = new Registro(2);
            registro.agregarCampo(estadistica.getNombreCampeon());
            registro.agregarCampo(estadistica.getTotalRecaudado());
            arch1.writeRegistro(registro);
        }
        arch1.close();
        ArchivoSalida arch2 = new ArchivoSalida("Personajes.txt");
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personajes personaje = listaPersonajes.getPersonajePuntual(i);
            Registro registro = new Registro(4);
            registro.agregarCampo(personaje.getNombreCampeon());
            registro.agregarCampo(personaje.getRol());
            registro.agregarCampo(personaje.getCantSkins());
            registro.agregarCampo(personaje.getDatosSkins());
            arch2.writeRegistro(registro);
        }
        arch2.close();

    }

}