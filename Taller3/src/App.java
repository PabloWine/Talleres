import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        SistemaEnvios sys = new SistemaEnviosImpl();
        leerLocalizacion(sys);
        leerCliente(sys);
        ingresarEntregas(sys);
        menu(sys);
        actualizarArchivo(sys);
    }

    /**
     * Function used to display all the corresponding menu options
     * 
     * @param sys System
     */
    public static void menu(SistemaEnvios sys) {
        Scanner teclado = new Scanner(System.in);
        String passw = "";
        System.out.println("Ingrese su id: ");
        String id = teclado.next();
        while (id == null) {
            System.out.println("Opcion no valida. Ingrese su id: ");
            id = teclado.next();
        }
        if (id.equals("Admin")) {
            System.out.println("Ingrese su contraseña: ");
            passw = teclado.next();
        }
        String retorno = sys.inicioSesion(id, passw);
        if (retorno.equalsIgnoreCase("nothing")) {
            System.out.println("Los datos ingresados no existen. Bienvenidoa l registro");
            System.out.println("Ingrese su rut: ");
            String rut = teclado.next();
            System.out.println("Ingrese su nombre: ");
            String nombre = teclado.next();
            System.out.println("Ingrsee su apellido: ");
            String apellido = teclado.next();
            System.out.println("Ingrese su ciudad de origen: ");
            String ciudad = teclado.next();
            if (sys.registro(rut, nombre, apellido, ciudad)) {
                System.out.println("Registro exitoso!");
                retorno = sys.inicioSesion(rut, passw);
            } else {
                System.out.println("No se ha podido registrar.");
            }

        }
        if (retorno.equals("Admin")) {
            System.out.println("Bienvenido administrador");
            System.out.println("1) Menu");
            System.out.println("2) Entregas por localizacion");
            System.out.println("3) Entregas por cliente");
            System.out.println("4) Registro de ganancias");
            int op = teclado.nextInt();
            while (0 > op && op > 4) {
                System.out.println("Opcion no valida, reintentelo");
                System.out.println("Bienvenido administrador");
                System.out.println("1) Menu");
                System.out.println("2) Entregas por localizacion");
                System.out.println("3) Entregas por cliente");
                System.out.println("4) Registro de ganancias");
                op = teclado.nextInt();
            }
            if (op == 1) {
                System.out.println(sys.entregasPorTipo());
            } else if (op == 2) {
                System.out.println(sys.entregaPorLocalizacion());
            } else if (op == 3) {
                System.out.println(sys.entregasPorCliente());
            } else if (op == 4) {
                System.out.println(sys.registroDeGanancias());
            }

        } else {
            System.out.println("Bienvenido " + id + "!");
            System.out.println("1) Realizar entrega");
            System.out.println("2) Recargar saldo");
            System.out.println("3) Ver tus entregas");
            int opc = teclado.nextInt();
            while (0 > opc && opc > 3) {
                System.out.println("La opcion ingresada no es valida, rententelo");
                System.out.println("1) Realizar entrega");
                System.out.println("2) Recargar saldo");
                System.out.println("3) Ver tus entregas");
                opc = teclado.nextInt();
            }
            if (opc == 1) {
                System.out.println("Ingrese el rut destinatario: ");
                String destino = teclado.next();
                System.out.println("Ingrese el tipo: ");
                String tipo = teclado.next();
                int i = new Random().nextInt(900000) + 100000;
                String i2 = String.valueOf(i);
                if (tipo.equalsIgnoreCase("d")) {
                    System.out.println("Ingrese el peso en gramos: ");
                    double pesoGramos = teclado.nextDouble();
                    System.out.println("Ingrese el grosor en mm: ");
                    double grosorMM = teclado.nextDouble();
                    if (sys.realizarEntrega(destino, tipo, id, (pesoGramos / 1000) * (grosorMM / 10) * 100)) {
                        if (sys.ingresarDocumento(i2, id, destino, pesoGramos, grosorMM)) {
                            System.out.println("Entreag del documento completada");
                        } else {
                            System.out.println("No se completo la entrega");
                        }

                    }

                } else if (tipo.equalsIgnoreCase("e")) {
                    System.out.println("Ingrese el peso en gramos: ");
                    double peso1 = teclado.nextDouble();
                    double peso = peso1 / 1000;
                    System.out.println("Ingrse el largo en cm: ");
                    double largo1 = teclado.nextDouble();
                    double largo = largo1 / 100;
                    System.out.println("Ingrese el ancho en cm: ");
                    double ancho1 = teclado.nextDouble();
                    double ancho = ancho1 / 100;
                    System.out.println("Ingrese la profundidad en cm: ");
                    double profundidad1 = teclado.nextDouble();
                    double profundidad = profundidad1 / 100;
                    if (sys.realizarEntrega(destino, tipo, id, peso * (largo * ancho * profundidad) * 50)) {
                        if (sys.ingresarEncomienda(i2, id, destino, peso1, largo1, ancho1, profundidad1)) {
                            System.out.println("Encomienda ingresada con exito");
                        } else {
                            System.out.println("No se completo la entrega");
                        }
                    }
                } else if (tipo.equalsIgnoreCase("v")) {
                    double precioMaterial = 0;
                    System.out.println("Ingrse el material: ");
                    String material = teclado.next();
                    System.out.println("Ingrese el peso: ");
                    double peso = teclado.nextDouble();
                    if (material.equalsIgnoreCase("Cuero")) {
                        precioMaterial = 200;
                    } else if (material.equalsIgnoreCase("Plástico")) {
                        precioMaterial = 150;
                    } else {
                        precioMaterial = 100;
                    }
                    if (sys.realizarEntrega(destino, tipo, id, peso * 150 * precioMaterial)) {
                        if (sys.ingresarValija(i2, id, destino, material, peso)) {
                            System.out.println("Valija entregada");
                        } else {
                            System.out.println("No se completo la entrega");
                        }
                    }
                }
            } else if (opc == 2) {
                System.out.println("Ingrese la cantidad a recargar: ");
                double cantidad = teclado.nextDouble();
                if (sys.recargarSaldo(id, cantidad)) {
                    System.out.println("recarga exitosa");
                } else {
                    System.out.println("No se ha compleado la recarga");
                }
            } else if (opc == 3) {
                if (sys.verMisEntrefgas(id) == null) {
                    System.out.println("No tiene entregas");
                } else {
                    System.out.println(sys.verMisEntrefgas(id));
                }
            }

        }

    }

    /**
     * Function used to read the locations of the txt
     * 
     * @param sys System
     * @throws FileNotFoundException
     */
    public static void leerLocalizacion(SistemaEnvios sys) throws FileNotFoundException {
        Scanner teclado = new Scanner(new File("localizacion.txt"));
        while (teclado.hasNextLine()) {
            String partes[] = teclado.nextLine().split(",");
            String localizacion = partes[0];

            try {
                sys.ingresarLocalizacion(localizacion);
            } catch (Exception e) {
                System.out.println("La localizacion no ha podido ser ingresada");
            }
        }
    }

    /**
     * Function used to read all the clients of the System
     * 
     * @param sys System
     * @throws FileNotFoundException
     */
    public static void leerCliente(SistemaEnvios sys) throws FileNotFoundException {
        Scanner teclado = new Scanner(new File("clientes.txt"));
        while (teclado.hasNextLine()) {
            String partes[] = teclado.nextLine().split(",");
            String rut = partes[0];
            String nombre = partes[1];
            String apellido = partes[2];
            double saldo = Double.parseDouble(partes[3]);
            String localizacion = partes[4];
            try {
                sys.ingresarCliente(rut, nombre, apellido, saldo, localizacion);
            } catch (Exception e) {
                System.out.println("El cliente no ha podido ser ingresado");
            }

        }
    }

    /**
     * Function used to read the existing deliveries in the system
     * 
     * @param sys System.
     * @throws FileNotFoundException
     */
    public static void ingresarEntregas(SistemaEnvios sys) throws FileNotFoundException {
        Scanner teclado = new Scanner(new File("entregas.txt"));
        while (teclado.hasNextLine()) {
            String partes[] = teclado.nextLine().split(",");
            String codigo = partes[0].trim();
            String tipo = partes[1].trim();
            String rutRemitente = partes[2].trim();
            String rutDestinatario = partes[3].trim();
            if (tipo.equalsIgnoreCase("D")) {
                double pesoGramos = Double.parseDouble(partes[4].trim());
                double grosorMM = Double.parseDouble(partes[5].trim());
                if (sys.ingresarDocumento(codigo, rutRemitente, rutDestinatario, pesoGramos, grosorMM)) {
                } else {
                    System.out.println("El docuimento no ha podidpo ser ingresado");
                }
            } else if (tipo.equalsIgnoreCase("E")) {
                double pesoGramos = Double.parseDouble(partes[4].trim());
                double largoCM = Double.valueOf(partes[5].trim());
                double anchoCM = Double.valueOf(partes[6].trim());
                double profundidadCM = Double.valueOf(partes[7].trim());
                if (sys.ingresarEncomienda(codigo, rutRemitente, rutDestinatario, pesoGramos, largoCM, anchoCM,
                        profundidadCM)) {
                } else {
                    System.out.println("La encomienda no ha podido ser ingresada");
                }

            } else if (tipo.equalsIgnoreCase("V")) {
                String material = partes[4].trim();
                double pesoGramos = Double.parseDouble(partes[5].trim());
                if (sys.ingresarValija(codigo, rutRemitente, rutDestinatario, material, pesoGramos)) {
                } else {
                    System.out.println("Valija no ha podido ser ingresada");
                }
            }
        }
    }

    /**
     * Function used for file writing
     * 
     * @param sys
     * @throws IOException
     */
    public static void actualizarArchivo(SistemaEnvios sys) throws IOException {
        ArchivoSalida archivo = new ArchivoSalida("clientes.txt");
        Registro registro = new Registro(1);

        String datos = sys.obtenerDatosClientes();

        registro.agregarCampo(datos);
        archivo.writeRegistro(registro);

    }
}
