package clases;

import java.io.Serializable;

public class Cancion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nombreCancion;
	public String interprete;

	public Cancion(String nombreCancion, String nombreGrupo, String interprete) {
		this.nombreCancion = nombreCancion;
		this.interprete = interprete;
	}

	/**
	 * @return the nombreCancion
	 */
	public String getNombreCancion() {
		return nombreCancion;
	}

	/**
	 * @param nombreCancion the nombreCancion to set
	 */
	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}

	/**
	 * @return the nombreGrupo
	 */
	public String getNombreGrupo() {
		return interprete;
	}

	/**
	 * @param nombreGrupo the nombreGrupo to set
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.interprete = nombreGrupo;
	}

	@Override
	public String toString() {
		return "Cancion [nombreCancion=" + nombreCancion + ", nombreGrupo=" + interprete + ", getNombreCancion()="
				+ getNombreCancion() + ", getNombreGrupo()=" + getNombreGrupo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
