package clases;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Evento implements Serializable{

	
	
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	protected int id;
	protected String nombre;
	protected LocalDate fecha;
	protected int aforo;
	private Idioma idioma;

	public Evento (int id, String nombre, LocalDate fecha, int aforo, Idioma idioma) {
		this.id=id;
		this.nombre=nombre;
		this.fecha=fecha;
		this.aforo=aforo;
		this.idioma=idioma;
		
}

	
	public void visualizar() {
		
	}


	/**
	 * @return the serialVersionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * @param serialVersionuid the serialVersionuid to set
	 */
	public static void setSerialversionuid(long serialVersionuid) {
		serialVersionUID = serialVersionuid;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the nombreEvento
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombreEvento the nombreEvento to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	/**
	 * @return the aforo
	 */
	public int getAforo() {
		return aforo;
	}


	/**
	 * @param aforo the aforo to set
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}


	/**
	 * @return the idioma
	 */
	public Idioma getIdioma() {
		return idioma;
	}


	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}


	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombreEvento=" + nombre + ", fecha=" + fecha + ", aforo=" + aforo
				+ ", idioma=" + idioma + ", getId()=" + getId() + ", getNombre()=" + getNombre()
				+ ", getFecha()=" + getFecha() + ", getAforo()=" + getAforo() + ", getIdioma()=" + getIdioma()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}

	
	
