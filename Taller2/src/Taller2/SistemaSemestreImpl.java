package Taller2;

import java.io.*;
import java.util.*;

import javax.crypto.NullCipher;

public class SistemaSemestreImpl implements SistemaSemestre {
    private ListaAsignaturas listaAsignaturas;
    private ListaAsignaturasCursadas listaAsignaturasCursadas;
    private ListaAsignaturasInscritas listaAsignaturasInscritas;
    private ListaEstudiantes listaEstudiantes;
    private ListaParalelos listaParalelos;
    private ListaProfesores listaProfesores;

    public SistemaSemestreImpl() {
        listaAsignaturas = new ListaAsignaturas(1000);
        listaEstudiantes = new ListaEstudiantes(1000);
        listaParalelos = new ListaParalelos(1000);
        listaProfesores = new ListaProfesores(1000);
    }

    @Override
    public String periodo(Date fecha) {
        Date inicio1 = new Date(2021, 3, 8);
        Date inicio2 = new Date(2021, 5, 2);

        Date mitad1 = new Date(2021, 5, 3);
        Date mitad2 = new Date(2021, 7, 11);

        Date final1 = new Date(2021, 7, 12);
        Date final2 = new Date(2021, 7, 25);

        Date finSemestre = new Date(2021, 7, 26);

        if (fecha.after(inicio1) && fecha.before(inicio2)) {
            return "inicio";
        } else if (fecha.after(mitad1) && fecha.before(mitad2)) {
            return "mitad";
        } else if (fecha.after(final1) && fecha.before(final2)) {
            return "fin";
        }
        return "disfrute sus vacaciones";

    }

    @Override
    public String inicioSesion(String correo, String contraseña) {
        Estudiante e = listaEstudiantes.buscarEstudiante(correo);
        Profesor p = listaProfesores.buscarProfesor(correo);
        System.out.println("Ingresados: " + correo + "->" + contraseña);

        try {
            if (correo.equals("Admin") && contraseña.equals("GHI_789")) {
                return "admin";
            }
            if (e != null) {
                if (e.getCorreo().equals(correo) && e.getContraseña().equals(contraseña)) {
                    return "estudiante";
                }
            }
            if (p != null) {
                if (p.getCorreo().equals(correo) && p.getContraseña().equals(contraseña)) {
                    return "profesor";
                }
            }
        } catch (Exception err) {
            return "datos no validos";
        }
        return "datos no validos";

    }

    @Override
    public boolean ingresarEstudiante(String rut, String correo, int nivel, String contraseña) {
        Estudiante e = new Estudiante(rut, correo, nivel, contraseña);
        return listaEstudiantes.agregarEstudiante(e);
    }

    @Override
    public boolean ingresarAsignaturaCursada(String rut, String codigo, double nota) {
        Estudiante e = listaEstudiantes.buscarEstudiante(rut);
        if (e != null) {
            AsignaturaCursada ac = new AsignaturaCursada(codigo, nota);
            return e.getLAC().agregarAsignaturaCursada(ac);

        }
        return false;
    }

    @Override
    public boolean ingresarAsignaturaInscrita(String rut, String codigo, String paralelo) {
        Estudiante e = listaEstudiantes.buscarEstudiante(rut);
        if (e != null) {
            AsignaturaInscrita ai = new AsignaturaInscrita(codigo, paralelo);
            return e.getLAI().agregarAsignaturaInscrita(ai);
        }
        return false;
    }

    @Override
    public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int credito, String tipo, int nivel,
            int cantidadPreRequisitos, String codigos) {
        Asignatura ao = new AsignaturaObligatoria(codigo, nombre, credito, tipo, nivel,
                cantidadPreRequisitos, codigos);
        return listaAsignaturas.agregarAsignatura(ao);

    }

    @Override
    public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int credito, String tipo, int nivel,
            int creditosPreRequisitos) {
        Asignatura ai = new AsignaturaOpcional(codigo, nombre, credito, tipo, creditosPreRequisitos);
        return listaAsignaturas.agregarAsignatura(ai);
    }

    @Override
    public boolean ingresaProfesores(String rut, String correo, String contraseña, int salario) {
        Profesor p = new Profesor(rut, correo, contraseña, salario);
        return listaProfesores.agregarProfesor(p);
    }

    @Override
    public boolean asociarParaleloAsignaturaProfesor(String paralelo, String codigo, String rutProfesor) {
        Paralelo pa = new Paralelo(paralelo);
        Asignatura a = listaAsignaturas.buscarAsignatura(codigo);
        Profesor p = listaProfesores.buscarProfesorPorRut(rutProfesor);
        if (a != null && p != null) {
            pa.setAsignatura(a);
            pa.setProfesor(p);
            return listaParalelos.agregarParalelo(pa);
        }
        return false;

    }

    private String comprobarClase(String correo) {
        Estudiante e = listaEstudiantes.buscarEstudiante(correo);
        Profesor p = listaProfesores.buscarProfesor(correo);
        if (e != null) {
            return "estudiante";
        } else if (e != null) {
            return "profesor";
        } else if (e.equals("Admin")) {
            return "admin";
        } else {
            return null;
        }
    }

    @Override
    public String desplegarAsignaturasDisponibles(String correo) {
        String text = "";
        String ocupacion = comprobarClase(correo);
        if (ocupacion.equalsIgnoreCase("estudiante")) {
            Estudiante e = listaEstudiantes.buscarEstudiante(correo);
            if (e != null) {
                for (int i = 0; i < listaAsignaturas.getCant(); i++) {
                    Asignatura a = listaAsignaturas.getAsignaturaIndice(i);
                    if (a != null) {
                        System.out.println(a.getCodigo());
                        if (a instanceof AsignaturaObligatoria) {

                            AsignaturaObligatoria ao = (AsignaturaObligatoria) a;
                            if (e.getNivel() == ao.getNivelMalla() && e.getCredito() + ao.getCreditoNecesario() < 40) {
                                text += "Asignatura: " + ao.getNombre() + " || " + "Codigo: " + ao.getCodigo() + "\n";

                            } else {
                                continue;
                            }
                        } else if (a instanceof AsignaturaOpcional) {
                            AsignaturaOpcional aop = (AsignaturaOpcional) a;

                            if (e.getCredito() > aop.getCreditosPreRequisito()
                                    && e.getCredito() + aop.getCreditoNecesario() < 40) {

                                text += aop.getNombre() + "||" + aop.getCodigo() + "\n";
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return text;

    }

    @Override
    public String desplegarParalelosDisponibles(String codigo) {
        String text = "";
        Asignatura a = listaAsignaturas.buscarAsignatura(codigo);
        if (a != null) {
            for (int i = 0; i < listaParalelos.getCant(); i++) {
                Paralelo p = listaParalelos.getParaleloIndice(i);
                if (codigo.equals(p.getAsignatura().getCodigo())) {
                    text += p.getParalelo() + "\n";
                }
            }
            return text;
        }
        throw new NullPointerException("El codigo indicado no es valido.");

    }

    @Override
    public boolean comprobarParalelo(String paralelo, String codigo) {
        Paralelo p = listaParalelos.buscarParalelo(paralelo);
        Asignatura a = listaAsignaturas.buscarAsignatura(codigo);
        if (p != null && a != null) {

            for (int i = 0; i < listaParalelos.getCant(); i++) {
                if (p.getAsignatura().getCodigo().equals(a.getCodigo())) {
                    return true;
                }
            }
        }
        throw new NullPointerException("Los datos indicados no son validos.");

    }

    @Override
    public boolean comprobarCodigo(String correo, String codigo) {
        Asignatura a = listaAsignaturas.buscarAsignatura(codigo);
        Estudiante e = listaEstudiantes.buscarEstudiante(correo);
        if (a != null && e != null) {
            if (a instanceof AsignaturaObligatoria) {
                AsignaturaObligatoria ao = (AsignaturaObligatoria) a;
                if (e.getNivel() == ao.getNivelMalla() && e.getCredito() + ao.getCreditoNecesario() < 40) {
                    return true;
                }

            } else if (a instanceof AsignaturaOpcional) {
                AsignaturaOpcional aop = (AsignaturaOpcional) a;
                if (e.getCredito() > aop.getCreditosPreRequisito()
                        && e.getCredito() + aop.getCreditoNecesario() < 40) {
                    return true;
                }
            }
        }

        throw new NullPointerException("Los datos ingresados no son validos.");

    }

    @Override
    public boolean inscribirAsignatura(String correo, String codigo, String paralelo) {
        Estudiante e = listaEstudiantes.buscarEstudiante(correo);
        Asignatura a = listaAsignaturas.buscarAsignatura(codigo);
        if (e != null && a != null) {
            AsignaturaInscrita ai = new AsignaturaInscrita(a.getCodigo(), paralelo);

            return true;
        }
        throw new NullPointerException("Los datos indicados no son validos.");

    }

    @Override
    public String desplegarAsignaturasInscritas(String correo) {
        String text = "";
        Estudiante e = listaEstudiantes.buscarEstudiante(correo);
        if (e != null) {
            if (e.getLAI().getCant() == 0) {
                text = "No tiene asignaturas inscritas";
            }
            for (int i = 0; i < e.getLAI().getCant(); i++) {
                AsignaturaInscrita ai = e.getLAI().getAsignaturaInscritaIndice(i);
                text += "Asignatura Inscrita: " + ai.getCodigo() + "\n";
            }

        }
        return text;
    }

    @Override
    public boolean eliminarAsignatura(String correo, String codigo) {
        Estudiante e = listaEstudiantes.buscarEstudiante(correo);
        if (e != null) {
            AsignaturaInscrita ai = e.getLAI().buscarAsignaturaInscrita(codigo);
            if (ai != null) {
                Asignatura a = listaAsignaturas.buscarAsignatura(codigo);
                e.getLAI().eliminarAsignaturaInscrita(codigo);
                e.setCredito(e.getCredito() - a.getCreditoNecesario());
                return true;
            }
        }
        return false;

    }

    @Override
    public String verParalelos(String correo) {
        String text = "";
        Profesor p = listaProfesores.buscarProfesor(correo);
        if (p != null) {
            String rut = p.getRut();
            for (int i = 0; i < listaParalelos.getCant(); i++) {
                Paralelo pa = listaParalelos.getParaleloIndice(i);
                if (pa.getProfesor().getRut().equalsIgnoreCase(p.getRut())) {
                    text += pa.getParalelo() + "\n";
                }
            }
        }
        return text;

    }

    @Override
    public boolean revisarParaleloProfe(String paral, String correo) {

        Profesor p = listaProfesores.buscarProfesor(correo);
        for (int i = 0; i < listaParalelos.getCant(); i++) {
            Paralelo pa = listaParalelos.getParaleloIndice(i);
            if (pa.getProfesor().getRut().equalsIgnoreCase(p.getRut())) {
                if (paral.equals(pa.getParalelo())) {
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public String desplegarAlumnos(String paral) {
        String text = "";
        int cont = 0;
        Paralelo pa = listaParalelos.buscarParalelo(paral);
        for (int i = 0; i < listaEstudiantes.getCant(); i++) {
            Estudiante e = listaEstudiantes.getEstudianteIndice(i);
            if (e.getLAI().getAsignaturaInscritaIndice(i).getParalelo().equals(paral)) {
                text += "Rut: " + e.getRut() + "Correo: " + e.getCorreo() + "\n";
            }

        }
        if (text.equals("")) {
            text = "No tiene alumnos inscritos en el paralelo";
        }
        return text;
    }

    @Override
    public String verAsignaturasProfesor(String correo) {
        String text = "";
        Profesor profesor = listaProfesores.buscarProfesor(correo);
        String rut = profesor.getRut();
        for (int i = 0; i < listaParalelos.getCant(); i++) {
            Paralelo paralelo = listaParalelos.getParaleloIndice(i);
            String rutParalelo = paralelo.getProfesor().getRut();
            if (rut.equalsIgnoreCase(rutParalelo)) {
                text += "Asignatura: " + paralelo.getAsignatura().getNombre();

            }
        }
        return text;
    }

    @Override
    public boolean definirNotas(String correo, double nota) {
        Estudiante e = listaEstudiantes.buscarEstudiante(correo);
        if (e != null) {
            for (int i = 0; i < e.getLAI().getCant(); i++) {
                AsignaturaInscrita ai = listaAsignaturasInscritas.getAsignaturaInscritaIndice(i);
                AsignaturaCursada ac = new AsignaturaCursada(ai.getCodigo(), nota);
                e.getLAI().eliminarAsignaturaInscrita(ai.getCodigo());
                e.getLAC().agregarAsignaturaCursada(ac);
                return true;
            }
        }
        return false;
    }

    @Override
    public int terminarSistema() throws IOException {
        int cont = 0;
        String rut = "";
        for (int i = 0; i < listaEstudiantes.getCant(); i++) {
            Estudiante e = listaEstudiantes.getEstudianteIndice(i);
            if (e.getNivel() == 10) {
                rut = e.getRut() + "\n";
                listaEstudiantes.eliminarEstudiante(rut);
                cont++;
            }
        }
        PrintWriter file = new PrintWriter("EstudiantesEgresados.txt");
        file.println(rut);
        file.close();
        return cont;
    }

}
