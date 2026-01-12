package menuFicherosProductos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import operacionesConFicheroDeObjetos.Persona;

public class Metodos {

	
	public static void modificarProductos() {
		
		ArrayList <Producto> Producto = new ArrayList<>();
		if (!fichero.exist()) {
			System.out.println("el fichero no existe");
			return;
		}
		else
		{
			try (FileInputStream fileIStream = new FileInputStream(refFichero);
		             ObjectInputStream personaIStream = new ObjectInputStream(fileIStream)) 
			{
				while (true) {
					try {
						Producto aux = (Producto) productoIStream.readObject();
						System.out.println(aux);  
					} catch (EOFException eof) {
						break;
					}
				}

			} catch (Exception e) {
				System.out.println("Error de lectura: "+e.getMessage());
			}
	
		System.out.println("Introduce nombre del producto a modificar:");
	    String nombreBuscado = Util.introducirCadena();

	    boolean encontrado = false;

	    for (Producto p : aProducto) {
	        if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
	            System.out.println("Producto encontrado: " + p);

	            System.out.println("Introduce nuevo stock:");
	            int nuevoStock = Util.leerInt();
	            p.setStock(nuevoStock);

	            encontrado = true;
	            break;
	        }	
	    }

	    if (!encontrado) {
	        System.out.println("Producto no encontrado");
	        return;
	    }
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(aux))) {

	        for (Producto p : aProducto) {
	            oos.writeObject(p);
	        }
}
	}
}
	
}