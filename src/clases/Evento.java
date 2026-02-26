package clases;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    protected int id;
    protected String nombre;
    protected LocalDate fecha;
    protected int aforo;
    protected Idioma idioma;

    public Evento(int id, String nombre, LocalDate fecha, int aforo, Idioma idioma) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.aforo = aforo;
        this.idioma = idioma;
    }

    // Método obligatorio y abstracto
    public abstract void visualizar();

    // *** MÉTODO OBLIGATORIO SEGÚN EL ENUNCIADO ***
    public abstract int calcularDuracionTotal();

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getAforo() {
        return aforo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Nombre: " + nombre +
               ", Fecha: " + fecha +
               ", Aforo: " + aforo +
               ", Idioma: " + idioma;
    }
}
