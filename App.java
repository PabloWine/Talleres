import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException, FileNotFoundException {
        System.out.println("HELLO WORLD");
        Rito rito = new RitoImpl();
        leerCuentas(rito);
        leerPersonajes(rito);
        leerEstadisticas(rito);
        inicio(rito);
    }

    public static void leerCuentas(Rito rito) throws IOException, FileNotFoundException {

        BufferedReader buffer = new BufferedReader(
                new InputStreamReader(new FileInputStream("C:/Cursos/Java/Taller/Taller1_nuevo/Cuentas.txt"), "utf-8"));
        String linea;
        while ((linea = buffer.readLine()) != null) {
            String partes[] = linea.split(",");
            String nombreCuenta = partes[0];
            String contraseña = partes[1];
            String nick = partes[2];
            String region;
            int nivel = Integer.parseInt(partes[3]);
            int rp = Integer.parseInt(partes[4]);
            int totalPj = Integer.parseInt(partes[5]);
            String skins = "";
            for (int i = 6; i < partes.length - 1; i++) {
                if (i + 1 == (partes.length - 1)) {
                    skins = skins + partes[i];
                } else {
                    skins = skins + partes[i] + ",";
                }
            }
            region = partes[partes.length - 1];
            rito.añadirCuenta(nombreCuenta, contraseña, nick, nivel, rp, totalPj, skins, region);
        }
    }

    public static void leerPersonajes(Rito rito) throws IOException, FileNotFoundException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:/Cursos/Java/Taller/Taller1_nuevo/Personajes.txt"), "utf-8"));
        String linea;
        while (((linea = buffer.readLine()) != null)) {
            String partes[] = linea.split(","), nombreCampeon = partes[0], rol = partes[1], informacionSkins = "";
            int cantidadSkins = Integer.parseInt(partes[2]);
            for (int i = 3; i < partes.length; i++) {
                if (i + 1 == partes.length) {
                    informacionSkins += partes[i];
                } else {
                    informacionSkins = informacionSkins + partes[i] + ",";
                }
            }
            rito.añadirPersonaje(nombreCampeon, rol, cantidadSkins, informacionSkins);
        }
    }

    public static void leerEstadisticas(Rito rito) throws IOException, FileNotFoundException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:/Cursos/Java/Taller/Taller1_nuevo/Estadisticas.txt"), "utf-8"));
        String linea;
        while ((linea = buffer.readLine()) != null) {
            String partes[] = linea.split(","), nombreCampeon = partes[0];
            int recaudacion = Integer.parseInt(partes[1]) * (int) 6.15;
            try {
                rito.añadirEstadistica(nombreCampeon, recaudacion);
            } catch (Exception e) {
                System.out.println("e");
            }
        }
    }

    public static void registro(Rito rito) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre de usuario: ");
        String nombreCuenta = teclado.next();
        System.out.println("Ingrese su contraseña: ");
        String contraseña = teclado.next();
        System.out.println("Ingrese su nick: ");
        String nick = teclado.next();
        System.out.println("Ingrese su region: ");
        String region = teclado.next();
        while (!region.equalsIgnoreCase("las") && !region.equalsIgnoreCase("Lan") && !region.equalsIgnoreCase("euw")
                && !region.equalsIgnoreCase("kr") && !region.equalsIgnoreCase("na") && !region.equalsIgnoreCase("ru")) {
            System.out.println("La region ingresada no es valida. reintentelo.");
            System.out.println("Ingrese su region: ");
            region = teclado.next();
        }
        try {
            rito.añadirCuenta(nombreCuenta, contraseña, nick, 0, 0, 0, "", region);
            System.out.println("registro exitoso!");
        } catch (Exception e) {
            System.out.println("registro fallido!");
        }
    }

    public static void menuCuenta(Rito rito, String nombreCuenta) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("1) Comprar Skin");
        System.out.println("2) Comprar Personaje");
        System.out.println("3) Skins Disponibles");
        System.out.println("4) Ver Inventario");
        System.out.println("5) Recargar RP");
        System.out.println("6) Enseñar Datos");
        System.out.println("7) Salir");
        System.out.println("");
        String opcion = teclado.next();
        while (!opcion.equals("7")) {
            switch (opcion) {
            case "1":
                System.out.println("¡Comprar Skin!");
                rito.comprarSkin(nombreCuenta);
                break;
            case "2":
                System.out.println("¡Comprar Personaje!");
                rito.comprarPersonaje(nombreCuenta);
                break;
            case "3":
                System.out.println("¡Skins Disponibles!");
                rito.skinsDisponibles(nombreCuenta);
                break;
            case "4":
                System.out.println("¡Mi Inventario!");
                rito.verInventario(nombreCuenta);
                break;
            case "5":
                System.out.println("¡Recargar RP!");

                System.out.println("RP Actual: " + rito.miRP(nombreCuenta));
                System.out.println("Cantidad a agregar: ");
                int cantidad = (int) teclado.nextInt();
                while (cantidad <= 0) {
                    System.out.println("La cantidad ingresada no es valida. Reintentelo.");
                    System.out.println("Cantidad a agregar: ");
                    cantidad = (int) teclado.nextInt();
                }
                rito.agregarRP(nombreCuenta, cantidad);
                System.out.println("Nuevo saldo: " + rito.miRP(nombreCuenta));
                break;
            case "6":
                System.out.println("¡Mis Datos!");
                rito.informacionUsuario(nombreCuenta);
                break;
            default:
                System.out.println("Opcion ingresada invalida");
            }
            System.out.println("1) Comprar Skin");
            System.out.println("2) Comprar Personaje");
            System.out.println("3) Skins Disponibles");
            System.out.println("4) Ver Inventario");
            System.out.println("5) Recargar RP");
            System.out.println("6) Enseñar Datos");
            System.out.println("7) Salir");
            System.out.println("");
            opcion = teclado.next();
        }

    }

    public static void menuAdmin(Rito rito, String nombreCuenta) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("1) Recaudacion Por Rol");
        System.out.println("2) Recaudacion Total");
        System.out.println("3) Recaudacion por personajes");
        System.out.println("4) Cantidad de Personajes por Rol");
        System.out.println("5) Agregar Personaje");
        System.out.println("6) Agregar Skin");
        System.out.println("7) Bloquear Cuenta");
        System.out.println("8) Desplegar Cuenta");
        System.out.println("9) Salir");
        System.out.println("");
        String opcion = teclado.next();
        while (!opcion.equals("9")) {
            switch (opcion) {
            case "1":
                System.out.println("Ventas por rol!");
                rito.recaudacionPorRol();
                break;
            case "2":
                System.out.println("Recaudacion total!");
                rito.recaudacionPorRegion();
                break;
            case "3":
                System.out.println("Recaudacion por personaje!");
                rito.recaudacionPersonajes();
                break;
            case "4":
                System.out.println("Cantidad de personajes por rol!");
                rito.personajesPorRol();
                break;
            case "5":
                System.out.println("Agregar personajke!");
                rito.agregarPersonaje();
                break;
            case "6":
                System.out.println("Agregar skin!");
                rito.agregarSkin();
            case "7":
                System.out.println("Bloquear cuenta!");
                rito.blockearCuenta();
            case "8":
                System.out.println("Desplegar informacion!");
                rito.darInfo();
                break;
            default:
                System.out.println("Opcion no valida");
                break;

            }
            System.out.println("1) Recaudacion Por Rol");
            System.out.println("2) Recaudacion Total");
            System.out.println("3) Recaudacion por personajes");
            System.out.println("4) Cantidad de Personajes por Rol");
            System.out.println("5) Agregar Personaje");
            System.out.println("6) Agregar Skin");
            System.out.println("7) Bloquear Cuenta");
            System.out.println("8) Desplegar Cuenta");
            System.out.println("9) Salir");
            System.out.println("");
            opcion = teclado.next();

        }

    }

    public static void inicio(Rito rito) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("1) Inicio de Sesion");
        System.out.println("2) Registro");
        System.out.println("3) Salir");
        System.out.println("");
        String opcion = teclado.next();
        while (!opcion.equalsIgnoreCase("1") && !opcion.equalsIgnoreCase("2") && !opcion.equalsIgnoreCase("3")) {
            System.out.println("La opcion ingresada no es valida. Reintentelo.");
            System.out.println("1) Inicio de Sesion");
            System.out.println("2) Registro");
            System.out.println("3) Salir");
            System.out.println("");
            opcion = teclado.next();
        }
        while (!opcion.equals("3")) {
            if (opcion.equals("1")) {
                System.out.println("Ingrese su cuenta: ");
                String usuario = teclado.next();
                System.out.println("Ingrese su contraseña: ");
                String contraseña = teclado.next();
                int go = rito.iniciarSesion(usuario, contraseña);
                switch (go) {
                case 2:
                    System.out.println("ADMIN");
                    menuAdmin(rito, usuario);
                    break;
                case 1:
                    System.out.println("Cuenta");
                    menuCuenta(rito, usuario);
                    break;
                default:
                    System.out.println("Datos ingresados invalidos.");
                }
            } else {
                registro(rito);
            }
            System.out.println("1) Inicio de Sesion");
            System.out.println("2) Registro");
            System.out.println("3) Salir");
            System.out.println("");
            opcion = teclado.next();
        }
    }

}
