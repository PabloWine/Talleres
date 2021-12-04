package Taller2;

import java.io.*;
import java.util.*;

public class App {
	public static void main(String[] args) throws IOException {
		SistemaSemestre sys = new SistemaSemestreImpl();
		leerEstudiantes(sys);
		leerAsignaturas(sys);
		leerProfesores(sys);
		leerParalelos(sys);
		menu(sys);

	}

	public static void leerEstudiantes(SistemaSemestre sys) throws FileNotFoundException {
		Scanner teclado = new Scanner(new File("estudiantes.txt"));
		while (teclado.hasNextLine()) {
			String partes[] = teclado.nextLine().split(",");
			String rut = partes[0];
			String correo = partes[1];
			int nivel = Integer.parseInt(partes[2]);
			String contraseña = partes[3];
			sys.ingresarEstudiante(rut, correo, nivel, contraseña);
			String partes2[] = teclado.nextLine().split("");
			int asignaturasCursadas = Integer.parseInt(partes2[0]);
			for (int i = 0; i < asignaturasCursadas; i++) {
				String partes3[] = teclado.nextLine().split(",");
				String codigoAsignatura = partes3[0];
				Double notaFinal = Double.valueOf((partes3[1]));

				AsignaturaCursada ac = new AsignaturaCursada(codigoAsignatura, notaFinal);
				System.out.println(ac.getCodigo() + "," + ac.getNota());
				try {
					sys.ingresarAsignaturaCursada(rut, codigoAsignatura, notaFinal);
				} catch (Exception error) {
					System.out.println(error);
				}

			}

			String partes4[] = teclado.nextLine().split(",");
			int asignaturasInscritas = Integer.parseInt(partes4[0]);
			for (int j = 0; j < asignaturasInscritas; j++) {
				String partes5[] = teclado.nextLine().split(",");
				String codigoAsignaturaInscrita = partes5[0];
				String paralelo = ((partes5[1]));
				try {
					sys.ingresarAsignaturaInscrita(rut, codigoAsignaturaInscrita, paralelo);
				} catch (Exception e) {

				}
			}
		}
	}

	public static void leerProfesores(SistemaSemestre sys) throws FileNotFoundException {
		Scanner lectura = new Scanner(new File("profesores.txt"));
		while (lectura.hasNextLine()) {
			String partes[] = lectura.nextLine().split(",");
			String rut = partes[0].trim();
			String correo = partes[1].trim();
			String contraseña = partes[2].trim();
			int salario = Integer.parseInt(partes[3].trim());
			sys.ingresaProfesores(rut, correo, contraseña, salario);
		}
	}

	public static void leerParalelos(SistemaSemestre sys) throws FileNotFoundException {
		Scanner lectura = new Scanner(new File("paralelos.txt"));
		while (lectura.hasNextLine()) {
			String partes[] = lectura.nextLine().split(",");
			String paralelo = partes[0].trim();
			String codigo = partes[1].trim();
			String rut = partes[2].trim();
			sys.asociarParaleloAsignaturaProfesor(paralelo, codigo, rut);
		}

	}

	public static void leerAsignaturas(SistemaSemestre sys) throws FileNotFoundException {

		Scanner lectura = new Scanner(new File("asignaturas.txt"));
		while (lectura.hasNextLine()) {
			String partes[] = lectura.nextLine().split(",");
			String codigo = partes[0].trim();
			String nombreAsignatura = partes[1].trim();
			int creditos = Integer.parseInt(partes[2].trim());
			String tipoAsignatura = partes[3].trim();
			String codigos = "";
			int nivel = 0, cantidadAsignaturasPreRequisito = 0;
			int creditosPreRequisitos = 0;
			if (tipoAsignatura.equalsIgnoreCase("obligatoria")) {
				nivel = Integer.parseInt(partes[4].trim());

				cantidadAsignaturasPreRequisito = Integer.parseInt(partes[5].trim());

				for (int i = 6; i < cantidadAsignaturasPreRequisito + 6; i++) {

					if (i + 1 == cantidadAsignaturasPreRequisito - 1) {
						codigos += partes[i];
					} else {
						codigos = codigos + partes[i] + ",";
					}
				}
			} else if (tipoAsignatura.equalsIgnoreCase("opcional")) {
				creditosPreRequisitos = Integer.parseInt(partes[4].trim());
			}
			if (tipoAsignatura.equalsIgnoreCase("obligatoria")) {
				sys.ingresarAsignaturaObligatoria(codigo, nombreAsignatura, creditos, tipoAsignatura, nivel,
						cantidadAsignaturasPreRequisito, codigos);
			} else {
				sys.ingresarAsignaturaOpcional(codigo, nombreAsignatura, creditos, tipoAsignatura, nivel,
						creditosPreRequisitos);
			}
		}
	}

	public static void menu(SistemaSemestre sys) throws IOException {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingrese su correo: ");
		String corr = teclado.next();
		System.out.println("Ingrese su contraseña: ");
		String pass = teclado.next();
		while (corr == null || pass == null) {
			System.out.println("los datos ingreasdos no son validos.");
			System.out.println("Ingrese su correo: ");
			corr = teclado.next();
			System.out.println("Ingrese su contraseña: ");
			pass = teclado.next();

		}

		String respuesta = sys.inicioSesion(corr, pass);
		while (respuesta.equalsIgnoreCase("datos no validos")) {
			System.out.println("Los datos ingresados no son validos, reintentelo.");
			System.out.println("Ingrese su correo: ");
			corr = teclado.next();
			System.out.println("Ingrese su contraseña: ");
			pass = teclado.next();
			respuesta = sys.inicioSesion(corr, pass);

		}
		if (respuesta.equalsIgnoreCase("estudiante")) {
			System.out.println("Bienvenido estudiante!");
			System.out.println("Ingrese la fecha!");
			System.out.println("Ingrese el dia: ");
			int dia = teclado.nextInt();
			if (0 > dia || dia > 31) {
				System.out.println("El dia indicado no es valido, reintentelo.");
				System.out.println("Ingrese el dia: ");
				dia = teclado.nextInt();
			}

			System.out.println("Ingrese el mes: ");
			int mes = teclado.nextInt();
			if (0 > mes || mes > 12) {
				System.out.println("El mes indicado no es valido, reintentelo.");
				System.out.println("Ingrese el mes: ");
				mes = teclado.nextInt();

			}
			System.out.println("Ingrese el año: ");
			int año = teclado.nextInt();
			if (año > 2021) {
				System.out.println("El año ingresado no es valido, reintentelo.");
				System.out.println("Ingrese el año: ");
				año = teclado.nextInt();

			}
			Date fecha = new Date(año, mes, dia);
			String semestre = sys.periodo(fecha);

			if (semestre.equals("inicio")) {
				System.out.println("Inicio de semestre.");
				System.out.println("Ingrese lo que desea hacer: ");
				System.out.println("1) Inscripcion de asignaturas.");
				System.out.println("2) Eliminacion de Asignaturas.");
				String opcion = teclado.next();
				while (!opcion.equalsIgnoreCase("1") && !opcion.equalsIgnoreCase("2")) {
					System.out.println("La opcion ingresada no es valida, reintentelo.");
					System.out.println("Ingrese lo que desea hacer: ");
					System.out.println("1) Inscripcion de asignaturas.");
					System.out.println("2) Eliminacion de Asignaturas.");
					opcion = teclado.next();
				}
				if (opcion.equalsIgnoreCase("1")) {
					System.out.println("--------------------------------------------");
					System.out.println("Inscripcion de asignaturas");
					System.out.println("--------------------------------------------");
					String disponibles = sys.desplegarAsignaturasDisponibles(corr);
					System.out.println(disponibles);
					System.out.println("Ingrese el codigo de su asignatura: ");
					String codi = teclado.next();
					while (codi == null) {
						System.out.println("Valor no valido, reintentelo.");
						System.out.println("Ingrese el codigo de su asignatura: ");
						codi = teclado.next();
					}

					if (sys.comprobarCodigo(corr, codi)) {

						System.out.println(sys.desplegarParalelosDisponibles(codi));
						System.out.println("Indique el paralelo en el que desea inscribirse: ");
						String opti = teclado.next();
						while (opti == null) {
							System.out.println("Opcion ingresada no valida. Reintentelo.");
							System.out.println("Indique el paralelo en el que desea inscribirse: ");
							opti = teclado.next();
						}
						if (sys.comprobarParalelo(opti, codi)) {
							if (sys.inscribirAsignatura(corr, codi, opti)) {
								System.out.println("Asignatura inscrita correctamente.");
							} else {
								System.out.println("La asignatura no pudo ser inscrita.");
							}

						} else {
							System.out.println("El paralelo indicado no es valido.");
						}
					}

				} else if (opcion.equalsIgnoreCase("2")) {
					System.out.println("--------------------------------------------");
					System.out.println("Eliminacion de asignaturas");
					System.out.println("--------------------------------------------");
					if (sys.desplegarAsignaturasDisponibles(corr).equalsIgnoreCase("No tiene asignaturas inscritas")) {
						System.out.println("El alumno no tiene asignaturas inscritas.");
					} else {
						System.out.println(sys.desplegarAsignaturasDisponibles(corr));
						System.out.println("Ingrese la asignatura a eliminar: ");
						String op = teclado.next();
						while (op == null) {
							System.out.println("La asignatura indicada no es valida. Reintentelo.");
							System.out.println("Ingrese la asignatura a eliminar: ");
							op = teclado.next();
						}
						if (sys.eliminarAsignatura(corr, op)) {
							System.out.println("Asignatura eliminada correctamente.");
						} else {
							System.out.println("La asignatura indicada no existe en sus inscripciones.");
						}
					}
				}
			} else if (semestre.equals("mitad")) {
				System.out.println("Mitad de semestre.");
				System.out.println("Ingrese la opcion: ");
				System.out.println("1) Eliminar asignaturas.");
				int opc = teclado.nextInt();
				while (opc != 1) {
					System.out.println("Opcion ingresada invalida, reintentelo.");
					System.out.println("Ingrese la opcion: ");
					System.out.println("1) Eliminar asignaturas.");
					opc = teclado.nextInt();

				}
				if (opc == 1) {
					if (sys.desplegarAsignaturasDisponibles(corr).equalsIgnoreCase("No tiene asignaturas inscritas")) {
						System.out.println("El alumno no tiene asignaturas inscritas.");
					} else {
						System.out.println(sys.desplegarAsignaturasDisponibles(corr));
						System.out.println("Ingrese la asignatura a eliminar: ");
						String op = teclado.next();
						while (op == null) {
							System.out.println("La asignatura indicada no es valida. Reintentelo.");
							System.out.println("Ingrese la asignatura a eliminar: ");
							op = teclado.next();
						}
						if (sys.eliminarAsignatura(corr, op)) {
							System.out.println("Asignatura eliminada correctamente.");
						} else {
							System.out.println("La asignatura indicada no existe en sus inscripciones.");
						}
					}
				}
			} else if (semestre.equals("fin")) {
				System.out.println("Cierre de semestre.");
				System.out.println("No hay acciones disponibles.");
			} else {
				System.out.println("Disfrute sus vacaciones.");

			}

		} else if (respuesta.equalsIgnoreCase("profesor")) {
			System.out.println("Bienvenido profesor!");
			System.out.println("Ingrese la fecha!");
			System.out.println("Ingrese el dia: ");
			int dia = teclado.nextInt();
			if (0 > dia || dia > 31) {
				System.out.println("El dia indicado no es valido, reintentelo.");
				System.out.println("Ingrese el dia: ");
				dia = teclado.nextInt();
			}

			System.out.println("Ingrese el mes: ");
			int mes = teclado.nextInt();
			if (0 > mes || mes > 12) {
				System.out.println("El mes indicado no es valido, reintentelo.");
				System.out.println("Ingrese el mes: ");
				mes = teclado.nextInt();

			}
			System.out.println("Ingrese el año: ");
			int año = teclado.nextInt();
			if (año > 2021) {
				System.out.println("El año ingresado no es valido, reintentelo.");
				System.out.println("Ingrese el año: ");
				año = teclado.nextInt();

			}
			Date fecha = new Date(año, mes, dia);
			String semestre = sys.periodo(fecha);

			if (semestre.equals("inicio")) {
				System.out.println("Inicio de semestre.");
				System.out.println("Ingrese la opcion: ");
				System.out.println("1) Chequeo de Alumnos.");
				int opcion = teclado.nextInt();
				while (opcion != 1) {
					System.out.println("la opcion ingresada no es valida. Reintentelo");
					System.out.println("Ingrese la opcion: ");
					System.out.println("1) Chequeo de Alumnos.");
					opcion = teclado.nextInt();
				}
				if (opcion == 1) {
					System.out.println("------------------------------------");
					System.out.println("Chequeo de Alumnos.");
					System.out.println("------------------------------------");
					String paralelos = sys.verParalelos(corr);
					System.out.println(paralelos);
					System.out.println("Ingrese el paralelo: ");
					String op = teclado.next();
					while (op == null) {
						System.out.println("la opcion ingresada no es valida, reinmtentelo.");
						System.out.println("Ingrese el paralelo: ");
						op = teclado.next();
					}
					if (sys.revisarParaleloProfe(op, corr)) {
						sys.desplegarAlumnos(op);
					} else {
						System.out.println("El paralelo indicado no es valido");
					}
				}
			} else if (semestre.equals("mitad")) {
				System.out.println("Mitad de semestre.");
				System.out.println("No tiene opciones disponibles.");

			} else if (semestre.equals("fin")) {
				System.out.println("Cierre de semestre.");
				System.out.println("Ingrese la opcion: ");
				System.out.println("1) Ingresar nota final.");
				int o = teclado.nextInt();
				while (o != 1) {
					System.out.println("la opcion ingresada no es valida. Reintentelo.");
					System.out.println("Ingrese la opcion: ");
					System.out.println("1) Ingresar nota final.");
					o = teclado.nextInt();
				}
				if (o == 1) {
					String asignaturas = sys.verAsignaturasProfesor(corr);
					System.out.println(asignaturas);
					System.out.println("Ingrese el correo del alumno: ");
					String alumno = teclado.next();
					System.out.println("Ingrese la nota del alumno: ");
					Double nota = teclado.nextDouble();
					if (sys.definirNotas(alumno, nota)) {
						System.out.println("Nota ingresada al sistema");
					} else {
						System.out.println("Datos ingresados invalidos.");
					}

				}
			} else {
				System.out.println("Disfrute sus vacaciones.");
			}
		} else if (respuesta.equalsIgnoreCase("admin")) {
			System.out.println("Bienvenido administrador!");
			System.out.println("Ingrese la fecha!");
			System.out.println("Ingrese el dia: ");
			int dia = teclado.nextInt();
			if (0 > dia || dia > 31) {
				System.out.println("El dia indicado no es valido, reintentelo.");
				System.out.println("Ingrese el dia: ");
				dia = teclado.nextInt();
			}

			System.out.println("Ingrese el mes: ");
			int mes = teclado.nextInt();
			if (0 > mes || mes > 12) {
				System.out.println("El mes indicado no es valido, reintentelo.");
				System.out.println("Ingrese el mes: ");
				mes = teclado.nextInt();

			}
			System.out.println("Ingrese el año: ");
			int año = teclado.nextInt();
			if (año > 2021) {
				System.out.println("El año ingresado no es valido, reintentelo.");
				System.out.println("Ingrese el año: ");
				año = teclado.nextInt();

			}
			Date fecha = new Date(año, mes, dia);
			String semestre = sys.periodo(fecha);

			if (semestre.equals("inicio")) {
				System.out.println("Inicio de semestre.");
				System.out.println("No tiene opciones disponibles.");
			} else if (semestre.equals("mitad")) {
				System.out.println("Mitad de semestre.");
				System.out.println("No tiene opciones disponibles.");

			} else if (semestre.equals("fin")) {
				System.out.println("Cierre de semestre.");
				System.out.println("Tenemos " + sys.terminarSistema() + " egresados");

			} else {
				System.out.println("Disfrute sus vacaciones.");
			}
		}
	}

}
