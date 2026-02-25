package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeMap;

import clases.Concierto;

import clases.Evento;
import clases.Idioma;

import clases.SinCabeceraObjectOutputStream;
import clases.Teatro;

import clases.Utilidades;

public class Main {

	public static void main(String[] args) {

		File fichEvento = new File("evento.dat");
		File fichEstadistica = new File("estadisticas.dat");

		int opcion;

		do {
			mostrarMenu();
			opcion = Utilidades.leerInt("Elige opción: ");

			try {
				switch (opcion) {
				case 1:
					nuevoEvento(fichEvento);
					break;
				case 2:
					visualizar();
					break;
				case 3:
					añadirCancionAConcierto();
					break;
				case 4:
					eliminarEventoPorNombre(fichEvento);
					break;
				case 5:
					mostrar(fichEvento);
					break;
				case 0:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción no válida");
				}
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}

		} while (opcion != 0);
	}

	private static void mostrarMenu() {
		System.out.println("\n--- MENÚ ---");
		System.out.println("1. Insertar nuevo evento");
		System.out.println("2. Visualizar eventos");
		System.out.println("3. Añadir cancion a un concierto");
		System.out.println("4. Eliminae un evento dado su nombre");
		System.out.println("5. Mostrar");
		System.out.println("0. Salir");
	}

	private static TreeMap<String, Evento> cargarEvento(File fichEvento) {
		TreeMap<String, Evento> canciones = new TreeMap<>();

		if (!fichEvento.exists() || fichEvento.length() == 0) {
			return canciones;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento))) {
			while (true) {
				try {
					Evento e = (Evento) ois.readObject();
					canciones.put(e.getNombre(), e);
				} catch (EOFException eof) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error leyendo archivo: " + e.getMessage());
			return new TreeMap<>();
		}

		return canciones;
	}

	public static void nuevoEvento(File fichEvento) {

		System.out.println("Introduce nombre del evento:");
		String nombre = Utilidades.introducirCadena();

		if (fichEvento.exists() && fichEvento.length() > 0) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento))) {
				while (true) {
					try {
						Evento e = (Evento) ois.readObject();
						if (e.getNombre().equals(nombre)) {
							System.out.println("Ya existe un motor con ese ID.");
							return;
						}
					} catch (EOFException eof) {
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Error leyendo motores: " + e.getMessage());
				return;
			}
		}

		System.out.println("Introduce id:");
		int id = Utilidades.leerInt();

		System.out.println("Tipo de evento:");
		System.out.println("1. Concierto");
		System.out.println("2. Teatro");
		int tipo = Utilidades.leerInt();

		Evento nuevo = null;

		if (tipo == 1) {
			System.out.println("Introduce nombre del grupo");
			int nombreGrupo = Utilidades.leerInt();

			System.out.println("Introduce la fecha del evento (aaaa-mm-dd):");
			String fecha = Utilidades.introducirCadena();

			LocalDate fechaReparacion;
			try {
				fechaReparacion = LocalDate.parse(fecha);
			} catch (Exception e) {
				System.out.println("Fecha no válida.");
				return;
			}

			System.out.println("Introduce aforo");
			int aforo = Utilidades.leerInt();

			System.out.println("Idioma (INGLES / ESPAÑOL):");
			String combStr = Utilidades.introducirCadena().toUpperCase();
			Idioma idm;

			if (combStr.equals("INGLES")) {
				idm = Idioma.INGLES;
			} else if (combStr.equals("ESPAÑOL")) {
				idm = Idioma.ESPAÑOL;
			} else {
				System.out.println("Idioma no válido.");
				return;
			}

			nuevo = new Concierto(id, nombreGrupo, fecha, aforo, idm);

		
		}
		// no me coge la opcion de tipo 2 con nada
		}else if (tipo == 2) {

		
			Evento nuevo = null;System.out.println("Introduce id:");
			int id = Utilidades.leerInt();System.out.println("Introduce el numero de actos");
			int actos = Utilidades.leerInt();

			System.out.println("Introduce la fecha del evento (aaaa-mm-dd):");
			String fecha = Utilidades.introducirCadena();

			LocalDate fechaReparacion;
			try
			{
				fechaReparacion = LocalDate.parse(fecha);
				}catch(Exception e)	{
					
					System.out.println("Fecha no válida.");
					return;
				}

			System.out.println("Introduce aforo");
			int aforo = Utilidades.leerInt();

			System.out.println("Idioma (INGLES / ESPAÑOL):");
			String combStr = Utilidades.introducirCadena().toUpperCase();
			Idioma idm;

				if(combStr.equals("INGLES"))
				{
					idm = Idioma.INGLES;
				}else if(combStr.equals("ESPAÑOL"))	{
					idm = Idioma.ESPAÑOL;
				}else{
					System.out.println("Idioma no válido.");
					return;
				}

			 nuevo = new Teatro(id,actos,fecha,aforo,idm);

		}else{
			System.out.println("Opción no válida.");
			return;
			}
	
// intento escribir añadiendo debajo en el fichero, me pide crear el fichero o la constante fichEvento
	try(
	SinCabeceraObjectOutputStream oos = new SinCabeceraObjectOutputStream(new FileOutputStream(fichEvento, true)))
	{
		//creo el nuevo teatro antes de escribir el objeto
	
	oos.writeObject(nuevo);

		}catch(IOException e)
		{
		System.out.println("Error escribiendo evento: " + e.getMessage());
		return;
		}

	System.out.println("Evento añadido correctamente.");

	public static void visualizar() {
	    	// no he sabido hacerlo
	    }

	public static void añadirCancionAConcierto() {
	    	//no he sabido hacerlo
	    }
		// no comprendo por que me da error en el int de la declaracion del metodo 
	public static int eliminarEventoPorNombre(File fichEvento) {
	    	TreeMap<String, Evento> mapaEvento = cargarEvento(fichEvento);
			

			if (mapaEvento.isEmpty()) {
				System.out.println("No hay motores.");
				return -1;
			}

			System.out.println("Introduce el nombre del evento a borrar:");
			String nombreBuscado = Utilidades.introducirCadena();

			boolean encontrado = false;
			String nombreEventoEliminado = null;

			Iterator<Evento> itMotores = mapaEvento.values().iterator();
			while (itMotores.hasNext()) {
				Evento e = itMotores.next();
				if (e.getNombre().equalsIgnoreCase(nombreBuscado)) {
					nombreEventoEliminado = e.getNombre();
					itMotores.remove();
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				System.out.println("Marca no encontrada.");
				return -1;
			}

			

			if (!guardarEvento(fichEvento, mapaEvento)) {
				return -1;
			}
			

			System.out.println("Evento eliminado.");
			return 1;
		}
	// no comprendo por qu eme da error en el boolean de la declaracion del metodo
	private static boolean guardarEvento(File fichEvento, TreeMap<String, Evento> mapaEvento) {
			File aux = new File("Evento_aux.dat");

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(aux))) {
				for (Evento e : mapaEvento.values()) {
					oos.writeObject(e);
				}
			} catch (Exception e) {
				System.out.println("Error guardando reparaciones: " + e.getMessage());
				return false;
			}

			if (fichEvento.exists()) {
				fichEvento.delete();
			}
			aux.renameTo(fichEvento);
			return true;
		}
	//no comprendo por que da error en void de la declaracion del metodo
	public static void mostrar(File fichEvento) {

	    		TreeMap<String, Evento> mapaEvento = cargarEvento(fichEvento);

	    		if (mapaEvento.isEmpty()) {
	    			System.out.println("No hay eventos que mostrar.");
	    			return;
	    		}

	    		for (Evento e : mapaEvento.values()) {
	    			System.out.println(e);
	    			System.out.println("idioma " + e.getIdioma());
	    			System.out.println("-------------------------");
	    		}
	    	}
}}
