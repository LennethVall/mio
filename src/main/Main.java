package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.TreeMap;

import clases.Cancion;
import clases.Concierto;
import clases.Evento;
import clases.Idioma;
import clases.SinCabeceraObjectOutputStream;
import clases.Teatro;
import clases.Utilidades;

public class Main {

    private static final File FICH_EVENTO = new File("gure.dat");
    private static final File FICH_ESTADISTICAS = new File("estadisticas.dat");

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = Utilidades.leerInt("Elige opción (0-5): ", 0, 5);

            switch (opcion) {
                case 1:
                    nuevoEvento(FICH_EVENTO);
                    break;
                case 2:
                    visualizarEventos(FICH_EVENTO);
                    break;
                case 3:
                    anadirCancion(FICH_EVENTO);
                    break;
                case 4:
                    eliminarEventoPorNombre(FICH_EVENTO);
                    break;
                case 5:
                    generarEstadisticas(FICH_EVENTO);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ ---");
        System.out.println("1. Insertar nuevo evento");
        System.out.println("2. Visualizar eventos");
        System.out.println("3. Añadir canción a un concierto");
        System.out.println("4. Eliminar un evento dado su nombre");
        System.out.println("5. Mostrar estadísticas por idioma");
        System.out.println("0. Salir");
    }

    // 1) Insertar nuevo evento (SIN estructuras de datos)
    private static void nuevoEvento(File fichEvento) {

        System.out.println("Introduce el nombre del evento:");
        String nombreEvento = Utilidades.introducirCadena();

        // 1) Comprobar si el nombre ya existe (SIN estructuras de datos)
        boolean existe = false;

        if (fichEvento.exists() && fichEvento.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento))) {
                while (true) {
                    try {
                        Evento e = (Evento) ois.readObject();
                        if (e.getNombre().equalsIgnoreCase(nombreEvento)) {
                            existe = true;
                            break;
                        }
                    } catch (EOFException eof) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error leyendo fichero: " + e.getMessage());
                return;
            }
        }

        if (existe) {
            System.out.println("Ya existe un evento con ese nombre.");
            return;
        }

        // 2) Generar ID secuencial (SIN estructuras de datos)
        int nuevoId = 1;

        if (fichEvento.exists() && fichEvento.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento))) {
                while (true) {
                    try {
                        Evento e = (Evento) ois.readObject();
                        if (e.getId() >= nuevoId) {
                            nuevoId = e.getId() + 1;
                        }
                    } catch (EOFException eof) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error generando ID: " + e.getMessage());
                return;
            }
        }

        // 3) Pedir tipo de evento
        System.out.println("Tipo de evento:");
        System.out.println("1. Concierto");
        System.out.println("2. Teatro");
        int tipo = Utilidades.leerInt(1, 2);

        // 4) Pedir fecha
        System.out.println("Introduce la fecha del evento (dd/MM/yyyy):");
        LocalDate fecha = Utilidades.leerFechaDMA();

        if (fecha.isBefore(LocalDate.now())) {
            System.out.println("ERROR: La fecha no puede ser anterior a hoy.");
            return;
        }

        // 5) Pedir aforo
        System.out.println("Introduce aforo:");
        int aforo = Utilidades.leerInt(1, 100000);

        // 6) Pedir idioma
        System.out.println("Idioma (EUSKERA / CASTELLANO / INGLES):");
        String idiomaStr = Utilidades.introducirCadena("EUSKERA", "CASTELLANO", "INGLES");
        Idioma idioma = Idioma.valueOf(idiomaStr.toUpperCase());

        Evento nuevo = null;

        // 7) Crear el evento según tipo
        if (tipo == 1) {
            System.out.println("Introduce nombre del grupo:");
            String nombreGrupo = Utilidades.introducirCadena();

            nuevo = new Concierto(nuevoId, nombreEvento, fecha, aforo, idioma, nombreGrupo);

        } else {
            System.out.println("Introduce número de actos:");
            int actos = Utilidades.leerInt(1, 50);

            nuevo = new Teatro(nuevoId, nombreEvento, fecha, aforo, actos, idioma);
        }

        // 8) Escribir en fichero (append)
   
        try {
            ObjectOutputStream oos;

            if (fichEvento.exists() && fichEvento.length() > 0) {
                oos = new SinCabeceraObjectOutputStream(new FileOutputStream(fichEvento, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(fichEvento));
            }

            oos.writeObject(nuevo);
            oos.close();

        } catch (IOException e) {
            System.out.println("Error escribiendo evento: " + e.getMessage());
            return;
        }

        System.out.println("Evento añadido correctamente.");

    }

    // 2) Visualizar eventos (SIN estructuras de datos)
    private static void visualizarEventos(File fichEvento) {

        // 1) Comprobar si el fichero existe o está vacío
        if (!fichEvento.exists() || fichEvento.length() == 0) {
            System.out.println("No hay eventos para mostrar.");
            return;
        }

        System.out.println("\n--- LISTADO DE EVENTOS ---");

        // 2) Lectura secuencial SIN estructuras de datos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento))) {

            while (true) {
                try {
                    Evento e = (Evento) ois.readObject();

                    // 3) Mostrar cada evento usando su método visualizar()
                    e.visualizar();
                    System.out.println("-----------------------------");

                } catch (EOFException eof) {
                    // Fin del fichero → salir del bucle
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error leyendo eventos: " + e.getMessage());
        }
    }

    

    // 3) Añadir canción a un concierto (SIN estructuras de datos)
    private static void anadirCancion(File fichEvento) {

        if (!fichEvento.exists() || fichEvento.length() == 0) {
            System.out.println("No hay eventos registrados.");
            return;
        }

        System.out.println("Introduce el nombre del concierto:");
        String nombreBuscado = Utilidades.introducirCadena();

        File temp = new File("temp.dat");
        boolean encontrado = false;

        try (
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))
        ) {

            while (true) {
                try {
                    Evento e = (Evento) ois.readObject();

                    // ¿Es el concierto buscado?
                    if (e instanceof Concierto && e.getNombre().equalsIgnoreCase(nombreBuscado)) {
                        encontrado = true;

                        // Pedir datos de la canción
                        System.out.println("Título de la canción:");
                        String titulo = Utilidades.introducirCadena();

                        System.out.println("Duración en minutos:");
                        int duracion = Utilidades.leerInt(1, 1000);

                        // Añadir canción
                        Concierto c = (Concierto) e;
                        c.addCancion(new Cancion(titulo, duracion));

                        // Guardar el concierto modificado
                        oos.writeObject(c);

                    } else {
                        // Guardar el evento tal cual
                        oos.writeObject(e);
                    }

                } catch (EOFException eof) {
                    break;
                }
            }

        } catch (Exception ex) {
            System.out.println("Error modificando el fichero: " + ex.getMessage());
            return;
        }

        // Si no se encontró el concierto
        if (!encontrado) {
            System.out.println("El concierto no existe.");
            temp.delete();
            return;
        }

        // Reemplazar el fichero original
        fichEvento.delete();
        temp.renameTo(fichEvento);

        System.out.println("Canción añadida correctamente.");
    }


    // 4) Eliminar evento por nombre (CON estructuras de datos)
    private static void eliminarEventoPorNombre(File fichEvento) {

        if (!fichEvento.exists() || fichEvento.length() == 0) {
            System.out.println("No hay eventos registrados.");
            return;
        }

        System.out.println("Introduce el nombre del evento a eliminar:");
        String nombreBuscado = Utilidades.introducirCadena();

        TreeMap<String, Evento> mapa = new TreeMap<>();
        boolean encontrado = false;

        // 1) Cargar todos los eventos en el TreeMap
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento))) {

            while (true) {
                try {
                    Evento e = (Evento) ois.readObject();
                    mapa.put(e.getNombre().toLowerCase(), e);
                } catch (EOFException eof) {
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error leyendo el fichero: " + e.getMessage());
            return;
        }

        // 2) Intentar eliminar
        if (mapa.remove(nombreBuscado.toLowerCase()) != null) {
            encontrado = true;
        }

        if (!encontrado) {
            System.out.println("El evento no existe.");
            return;
        }

        // 3) Guardar el TreeMap actualizado en un fichero temporal
        File temp = new File("temp.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))) {

            for (Evento e : mapa.values()) {
                oos.writeObject(e);
            }

        } catch (Exception e) {
            System.out.println("Error escribiendo el fichero: " + e.getMessage());
            return;
        }

        // 4) Reemplazar el fichero original
        fichEvento.delete();
        temp.renameTo(fichEvento);

        System.out.println("Evento eliminado correctamente.");
    }


    // 5) Estadísticas por idioma (CON estructuras de datos)
    private static void generarEstadisticas(File fichEvento) {

        if (!fichEvento.exists() || fichEvento.length() == 0) {
            System.out.println("No hay eventos registrados.");
            return;
        }

        // Mapa ordenado por nombre del idioma
        TreeMap<String, Integer> contadorIdiomas = new TreeMap<>();

        // Inicializar contadores
        contadorIdiomas.put("CASTELLANO", 0);
        contadorIdiomas.put("EUSKERA", 0);
        contadorIdiomas.put("INGLES", 0);

        // 1) Leer todos los eventos del fichero
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichEvento))) {

            while (true) {
                try {
                    Evento e = (Evento) ois.readObject();
                    String idioma = e.getIdioma().toString().toUpperCase();

                    // Incrementar contador
                    contadorIdiomas.put(idioma, contadorIdiomas.get(idioma) + 1);

                } catch (EOFException eof) {
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error leyendo eventos: " + e.getMessage());
            return;
        }

        // 2) Mostrar estadísticas por pantalla
        System.out.println("\n--- ESTADÍSTICAS POR IDIOMA ---");
        for (String idioma : contadorIdiomas.keySet()) {
            System.out.println(idioma + ": " + contadorIdiomas.get(idioma));
        }

        // 3) Crear estadísticas.dat (eliminar si existe)
        File estad = new File("estadisticas.dat");
        if (estad.exists()) {
            estad.delete();
        }

        // 4) Guardar estadísticas en el fichero
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(estad))) {

            for (String idioma : contadorIdiomas.keySet()) {
                String linea = idioma + ": " + contadorIdiomas.get(idioma);
                oos.writeObject(linea);
            }

        } catch (Exception e) {
            System.out.println("Error escribiendo estadísticas: " + e.getMessage());
            return;
        }

        System.out.println("Estadísticas generadas correctamente.");
    }

}
