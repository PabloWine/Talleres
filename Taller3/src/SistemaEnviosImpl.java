import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SistemaEnviosImpl implements SistemaEnvios {

    ArrayList<Localizacion> listaLocalizaciones;
    LinkedList<Cliente> listaClientes;
    ListaEnvios listaEnvios;

    public SistemaEnviosImpl() {
        listaLocalizaciones = new ArrayList<Localizacion>();
        listaClientes = new LinkedList<Cliente>();
        listaEnvios = new ListaEnvios();
    }

    @Override
    public void ingresarLocalizacion(String nombre) {
        Localizacion l = new Localizacion(nombre);
        listaLocalizaciones.add(l);

    }

    @Override
    public void ingresarCliente(String rut, String nombre, String apellido, double saldo, String ciudadOrigen) {
        Cliente c = new Cliente(rut, nombre, apellido, saldo);
        int pos = 0;
        for (Localizacion l : listaLocalizaciones) {
            if (l.getNombre().equals(ciudadOrigen)) {
                c.setLoca(l);
            }
        }
        listaClientes.add(c);
    }

    @Override
    public boolean ingresarDocumento(String codigo, String rutRemitente, String rutDestinatario, double pesoGramos,
            double grosorMM) {
        double pesoKilos = pesoGramos / 1000;
        double grosorCM = grosorMM / 10;

        if (pesoKilos <= 1.5 && grosorCM <= 5) {

            Documento d = new Documento(codigo, pesoKilos, grosorCM);
            for (Cliente c : listaClientes) {
                if (c.getRut().equalsIgnoreCase(rutRemitente)) {
                    d.setClienteRemitente(c);
                    c.getEnvios().add(d);
                    c.getLoca().setGanancias(c.getLoca().getGanancias() + d.valor());
                    c.getLoca().setCantEnvios(c.getLoca().getCantEnvios() + 1);
                } else if (c.getRut().equalsIgnoreCase(rutDestinatario)) {
                    d.setClienteDestinatario(c);
                    c.getRecibidos().add(d);
                    c.getLoca().setCantEnvios(c.getLoca().getCantRecibidos() + 1);

                }

            }
            listaEnvios.ingresar(d);
            return true;
        }
        return false;
    }

    @Override
    public boolean ingresarEncomienda(String codigo, String rutRemitente, String rutDestinatario, double pesoGramos,
            double largoCM, double anchoCM, double profundidadCM) {
        double pesoKilos = pesoGramos / 1000;
        double largoM = largoCM / 100, anchoM = anchoCM / 100, profundidadM = profundidadCM / 100;
        if (pesoKilos <= 50 && largoM <= 1.2 && anchoM <= 0.8 && profundidadM <= 0.8) {
            Encomienda e = new Encomienda(codigo, pesoKilos, pesoKilos, largoM, profundidadM);
            for (Cliente c : listaClientes) {
                if (c.getRut().equals(rutRemitente)) {
                    e.setClienteRemitente(c);
                    c.getEnvios().add(e);
                    c.getLoca().setGanancias(c.getLoca().getGanancias() + e.valor());
                    c.getLoca().setCantEnvios(c.getLoca().getCantEnvios() + 1);

                } else if (c.getRut().equals(rutDestinatario)) {
                    e.setClienteDestinatario(c);
                    c.getRecibidos().add(e);
                    c.getLoca().setCantEnvios(c.getLoca().getCantRecibidos() + 1);

                }
            }
            listaEnvios.ingresar(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean ingresarValija(String codigo, String rutRemitente, String rutDestinatario, String material,
            double peso) {
        peso = peso / 1000;
        if (peso <= 2) {
            Valija v = new Valija(codigo, peso, material);
            for (Cliente c : listaClientes) {
                if (c.getRut().equals(rutRemitente)) {
                    v.setClienteRemitente(c);
                    c.getEnvios().add(v);
                    c.getLoca().setGanancias(c.getLoca().getGanancias() + v.valor());
                    c.getLoca().setCantEnvios(c.getLoca().getCantEnvios() + 1);

                } else if (c.getRut().equals(rutDestinatario)) {
                    v.setClienteDestinatario(c);
                    c.getRecibidos().add(v);
                    c.getLoca().setCantEnvios(c.getLoca().getCantRecibidos() + 1);

                }
            }

            listaEnvios.ingresar(v);
            return true;
        }
        return false;

    }

    @Override
    public String inicioSesion(String rut, String contraseña) {
        if (rut.equals("Admin")) {
            if (contraseña.equals("choripan123")) {
                return "Admin";
            }
        }
        for (Cliente c : listaClientes) {
            if (c.getRut().equals(rut)) {
                return c.getRut();
            }
        }
        return "nothing";
    }

    @Override
    public boolean registro(String rut, String nombre, String apellido, String ciudadOrigen) {

        for (Cliente c : listaClientes) {
            if (c.getRut().equals(rut)) {
                return false;
            }
        }

        Cliente c = new Cliente(rut, nombre, apellido, 0);
        for (Localizacion l : listaLocalizaciones) {
            if (l.getNombre().equals(ciudadOrigen)) {
                c.setLoca(l);
            }
        }
        listaClientes.add(c);
        return true;

    }

    @Override
    public boolean realizarEntrega(String rutDestinatario, String tipo, String rutRemitente, double total) {
        Cliente destinatario = null, remitente = null;
        for (Cliente c : listaClientes) {
            if (c.getRut().equals(rutDestinatario)) {
                destinatario = c;
            } else if (c.getRut().equals(rutRemitente)) {
                remitente = c;
            }
        }
        if (destinatario != null && remitente != null) {
            if (destinatario.getSaldo() > total) {
                remitente.getLoca().setGanancias(remitente.getLoca().getGanancias() + total);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean recargarSaldo(String rutCliente, double recarga) {
        for (Cliente c : listaClientes) {
            if (c.getRut().equals(rutCliente)) {
                c.setSaldo(c.getSaldo() + recarga);
                return true;
            }
        }
        return false;
    }

    @Override
    public String verMisEntrefgas(String rutCliente) {
        String text = "";
        for (Cliente c : listaClientes) {
            if (c.getRut().equals(rutCliente)) {
                text += c.getEnvios().toString();
                text += c.getRecibidos().toString();
            }
        }
        return text;
    }

    @Override
    public String entregasPorTipo() {
        String text = "";
        for (int i = 0; i < listaEnvios.getSize(); i++) {

        }
        return text;
    }

    @Override
    public String entregaPorLocalizacion() {
        String text = "";
        for (Localizacion l : listaLocalizaciones) {
            text += l.getNombre() + " Envios: " + l.getCantEnvios() + " Recibidos: " + l.getCantRecibidos() + "\n";
        }
        return text;
    }

    @Override
    public String entregasPorCliente() {
        String text = "";
        for (Cliente c : listaClientes) {
            text += c.toString();
        }
        return text;
    }

    @Override
    public String registroDeGanancias() {
        String text = "";
        double total = 0;
        for (Localizacion l : listaLocalizaciones) {
            text += l.getNombre() + l.getGanancias() + "\n";
            total += l.getGanancias();
        }
        text += "Total ganancias: " + total;
        return text;
    }

    @Override
    public String obtenerDatosClientes() {
        String text = "";
        for (Cliente c : listaClientes) {
            text += c.getRut() + "," + c.getNombre() + "," + c.getApellido() + "," + c.getSaldo() + ","
                    + c.getLoca().getNombre();
        }
        return text;
    }

}
