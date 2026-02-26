package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeMap;

public class Concierto extends Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreGrupo;
    private TreeMap<String, Cancion> canciones;

    public Concierto(int id, String nombre, LocalDate fecha, int aforo, Idioma idioma, String nombreGrupo) {
        super(id, nombre, fecha, aforo, idioma);
        this.nombreGrupo = nombreGrupo;
        this.canciones = new TreeMap<>(); // lista ORDENADA
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public TreeMap<String, Cancion> getCanciones() {
        return canciones;
    }

    public void addCancion(Cancion c) {
        canciones.put(c.getTitulo(), c);
    }

    @Override
    public int calcularDuracionTotal() {
        int total = 15; // extra obligatorio
        for (Cancion c : canciones.values()) {
            total += c.getDuracion();
        }
        return total;
    }

    @Override
    public void visualizar() {
        System.out.println("=== CONCIERTO ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Aforo: " + aforo);
        System.out.println("Idioma: " + idioma);
        System.out.println("Grupo: " + nombreGrupo);
        System.out.println("Canciones:");
        for (Cancion c : canciones.values()) {
            System.out.println("   - " + c);
        }
    }

    @Override
    public String toString() {
        return "Concierto | ID: " + id +
               ", Nombre: " + nombre +
               ", Fecha: " + fecha +
               ", Aforo: " + aforo +
               ", Idioma: " + idioma +
               ", Grupo: " + nombreGrupo +
               ", Canciones: " + canciones.size();
    }
}
