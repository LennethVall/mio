package menuFicherosProductos;

import java.io.Serializable;

public  class Producto implements Serializable, Comparable <Producto>{ 

	   

	public Producto(String nombre,  int stock) {
		super();
		this.nombre = nombre;
		
		this.stock = stock;
	}





	private String nombre; 
   
    private int stock; 

  

       public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getStock() {
		return stock;
	}





	public void setStock(int stock) {
		this.stock = stock;
	}





	@Override
	public String toString() {
		return "Producto{nombre='" + nombre + "', stock=" + stock + "}"; }





	@Override
	public int compareTo(Producto otro) {
		// TODO Auto-generated method stub
		return this.nombre.compareToIgnoreCase(otro.nombre);
	}



}