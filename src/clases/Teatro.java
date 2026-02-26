package clases;

import java.io.Serializable;
import java.time.LocalDate;

public class Teatro extends Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numeroActos;

    public Teatro(int id, String nombre, LocalDate fecha, int aforo, int numeroActos, Idioma idioma) {
        super(id, nombre, fecha, aforo, idioma);
        this.numeroActos = numeroActos;
    }

    public int getNumeroActos() {
        return numeroActos;
    }

    public void setNumeroActos(int numeroActos) {
        this.numeroActos = numeroActos;
    }

    @Override
    public void visualizar() {
        System.out.println("=== OBRA DE TEATRO ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Aforo: " + aforo);
        System.out.println("Idioma: " + idioma);
        System.out.println("Número de actos: " + numeroActos);
    }

    @Override
    public int calcularDuracionTotal() {
        return numeroActos * 20;
    }

    @Override
    public String toString() {
        return "Teatro | ID: " + id +
               ", Nombre: " + nombre +
               ", Fecha: " + fecha +
               ", Aforo: " + aforo +
               ", Idioma: " + idioma +
               ", Actos: " + numeroActos;
    }
}
