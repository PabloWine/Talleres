package Taller2;

import java.io.*;
import java.util.*;

public interface SistemaSemestre {
        public boolean ingresarEstudiante(String rut, String correo, int nivel, String contraseña);

        public boolean ingresarAsignaturaCursada(String rut, String codigo, double nota);

        public boolean ingresarAsignaturaInscrita(String rut, String codigo, String paralelo);

        public boolean ingresarAsignaturaObligatoria(String codigo, String nombre, int credito, String tipo, int nivel,
                        int cantidadPreRequisitos, String codigos);

        public boolean ingresarAsignaturaOpcional(String codigo, String nombre, int credito, String tipo, int nivel,
                        int creditosPreRequisitos);

        public boolean ingresaProfesores(String rut, String correo, String contraseña, int salario);

        public boolean asociarParaleloAsignaturaProfesor(String paralelo, String codigo, String rutProfesor);

        public boolean inscribirAsignatura(String correo, String codigo, String paralelo);

        public boolean eliminarAsignatura(String correo, String codigo);

        public String verParalelos(String correo);

        public String verAsignaturasProfesor(String correo);

        public String inicioSesion(String correo, String contraseña);

        public String periodo(Date fecha);

        public String desplegarAsignaturasDisponibles(String correo);

        public boolean comprobarCodigo(String correo, String codigo);

        public String desplegarParalelosDisponibles(String codigo);

        public boolean comprobarParalelo(String paralelo, String codigo);

        public String desplegarAsignaturasInscritas(String correo);

        public boolean revisarParaleloProfe(String paral, String correo);

        public String desplegarAlumnos(String paral);

        boolean definirNotas(String correo, double nota);

        public int terminarSistema() throws IOException;

}
