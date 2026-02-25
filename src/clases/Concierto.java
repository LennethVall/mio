package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeMap;

public class Concierto extends Evento implements Serializable {

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	public String nombreGrupo;
	private TreeMap<String, Evento> canciones = new TreeMap<>();

	public Concierto(int id,String nombre, String nombreGrupo, LocalDate localDate, int aforo, Idioma idioma) {
		super(id, nombre, localDate, aforo, idioma);
		this.nombreGrupo = nombreGrupo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param serialversionuid the serialversionuid to set
	 */
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	/**
	 * @return the nombreGrupo
	 */
	public String getNombreGrupo() {
		return nombreGrupo;
	}

	/**
	 * @param nombreGrupo the nombreGrupo to set
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	/**
	 * @return the canciones
	 */
	public TreeMap<String, Evento> getCanciones() {
		return canciones;
	}

	/**
	 * @param canciones the canciones to set
	 */
	public void setCanciones(TreeMap<String, Evento> canciones) {
		this.canciones = canciones;
	}

	@Override
	public String toString() {
		return "Concierto [nombreGrupo=" + nombreGrupo + ", canciones=" + canciones + ", id=" + id + ", nombre="
				+ nombre + ", fecha=" + fecha + ", aforo=" + aforo + ", getNombreGrupo()=" + getNombreGrupo()
				+ ", getCanciones()=" + getCanciones() + ", getId()=" + getId() + ", getNombre()=" + getNombre()
				+ ", getFecha()=" + getFecha() + ", getAforo()=" + getAforo() + ", getIdioma()=" + getIdioma()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}