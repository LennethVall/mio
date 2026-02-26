package clases;

import java.io.Serializable;

public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private int duracion; // en minutos

    public Cancion(String titulo, int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Canción: " + titulo + " (" + duracion + " min)";
    }
}
