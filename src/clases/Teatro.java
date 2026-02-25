package clases;

import java.io.Serializable;
import java.time.LocalDate;

public class Teatro extends Evento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int numeroActos;

	public Teatro(int id, String nombre, LocalDate fecha, int aforo, int numerActos, Idioma idioma) {
		super(id, nombre, fecha, aforo, idioma);
		this.numeroActos=numeroActos;
	}

			
	
	


	/**
	 * @return the numeroActos
	 */
	public int getNumeroActos() {
		return numeroActos;
	}

	/**
	 * @param numeroActos the numeroActos to set
	 */
	public void setNumeroActos(int numeroActos) {
		this.numeroActos = numeroActos;
	}

	@Override
	public String toString() {
		return "Teatro [numeroActos=" + numeroActos + ", id=" + id + ", nombre=" + nombre + ", fecha=" + fecha
				+ ", aforo=" + aforo + ", getNumeroActos()=" + getNumeroActos() + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getFecha()=" + getFecha() + ", getAforo()=" + getAforo()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	
	
}
