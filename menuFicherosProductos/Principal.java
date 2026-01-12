package menuFicherosProductos;



import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;



public class Principal {
	public static void main(String[] args) throws Exception {
		
		int opc;
		do{
			System.out.println("1.-Altas\n2.-Listado\n3.-Consulta\n4.-Modificación\n5.-Borrado\n6.-Listado ordenado por apellido\n9.-SALIR");
					opc=Util.leerInt();
					switch(opc){
					case 1:
						/* Añade 3 productos al fichero productos.dat
						 * si no existe el fichero, lo crea
						 * el nombre del producto se lo pide al usuario, el stock es aleatorio
						 */
						altaUnProducto();
						break;
					case 2:
						listado();
						break;
					case 3:
						/* Pide al usuario el nombre del producto,
						 * el programa busca el producto en el fichero 
						 * y le muestra por pantalla el stock 
						 * suponemos que solo hay un producto con ese nombre
						 */
						consulta();
						break;
					case 4:
						/* Pide al usuario el nombre del producto,
						 * el programa busca el producto en el fichero 
						 * y le muestra por pantalla el stock.
						 * luego le pide el nuevo stock y guarda el cambio,
						 * suponemos que solo hay un producto con ese nombre
						 * usar un fichero auxiliar
						 */
						modificacion();
						break;
					case 5:
						/* Pide al usuario el nombre del producto,
						 * el programa busca el producto en el fichero,
						 * suponemos que solo hay un producto con ese nombre,
						 * se borra
						 * usar un fichero auxiliar
						 */
						borrado();
						break;
					case 6:
						/*
						 * usar un fichero auxiliar para guardar el ArrayList ordenado
						 */
						ordenarPorNombre();
						break;
					}
		}while (opc!=9);
	}
	
	private static void altaUnProducto() {
		

		

		File refFichero = new File("producto.dat");

		if (!refFichero.exists()) {

		    // Fichero NO existe → lo creamos con cabecera
		    try (ObjectOutputStream productoOStream =
		            new ObjectOutputStream(new FileOutputStream(refFichero))) {

		        System.out.print("Introduce el nombre del producto: ");
		        String nombre = Util.introducirCadena();

		        System.out.print("Introduce el stock del producto: ");
		        int stock = Util.leerInt();

		        productoOStream.writeObject(new Producto(nombre, stock));

		    } catch (IOException e) {
		        System.out.println("Error de escritura: " + e.getMessage());
		    }

		} else {

		    // Fichero SÍ existe → añadimos sin cabecera
		    try (SinCabeceraObjectOutputStream productoOStream =
		            new SinCabeceraObjectOutputStream(new FileOutputStream(refFichero, true))) {

		        System.out.print("Introduce el nombre del producto: ");
		        String nombre = Util.introducirCadena();

		        System.out.print("Introduce el stock del producto: ");
		        int stock = Util.leerInt();

		        productoOStream.writeObject(new Producto(nombre, stock));
		        System.out.println("Producto añadido al fichero existente");

		    } catch (IOException e) {
		        System.out.println("Error de escritura: " + e.getMessage());
		    }}
		}

			
	private static void altaMuchosProductos() {
		

		

		File refFichero = new File("producto.dat");

		if (!refFichero.exists()) {

		    // Fichero NO existe → lo creamos con cabecera
		    try (ObjectOutputStream productoOStream =
		            new ObjectOutputStream(new FileOutputStream(refFichero))) {

		        boolean seguir = true;

		        while (seguir) {

		            System.out.print("Introduce el nombre del producto: ");
		            String nombre = Util.introducirCadena();

		            System.out.print("Introduce el stock del producto: ");
		            int stock = Util.leerInt();

		            productoOStream.writeObject(new Producto(nombre, stock));

		            System.out.println("Si quieres seguir introduciendo productos introduce 1");
		            int continuar = Util.leerInt();

		            if (continuar != 1) {
		                seguir = false;
		            }
		        }

		    } catch (IOException e) {
		        System.out.println("Error de escritura: " + e.getMessage());
		    }

		} else {

		    // Fichero SÍ existe → añadimos sin cabecera
		    try (SinCabeceraObjectOutputStream productoOStream =
		            new SinCabeceraObjectOutputStream(new FileOutputStream(refFichero, true))) {

		        boolean seguir = true;

		        while (seguir) {

		            System.out.print("Introduce el nombre del producto: ");
		            String nombre = Util.introducirCadena();

		            System.out.print("Introduce el stock del producto: ");
		            int stock = Util.leerInt();

		            productoOStream.writeObject(new Producto(nombre, stock));

		            System.out.println("Si quieres seguir introduciendo productos introduce 1");
		            int continuar = Util.leerInt();
		            

		            if (continuar != 1) {
		                seguir = false;
		            }
		        }

		    } catch (IOException e) {
		        System.out.println("Error de escritura: " + e.getMessage());
		    }
		}
	}

	
	private static void listado() {
		
			File refFichero=new File("producto.dat");
			
			// Lee el fichero
			if (!refFichero.exists())
			{
				System.out.println("Fichero no existente");
			}
			else
			{
				try (FileInputStream fileIStream = new FileInputStream(refFichero);
			             ObjectInputStream personaIStream = new ObjectInputStream(fileIStream)) 
				{
					while (true) {
						try {
							Producto aux = (Producto) personaIStream.readObject();
							System.out.println(aux);  
						} catch (EOFException eof) {
							break;
						}
					}

				} catch (Exception e) {
					System.out.println("Error de lectura: "+e.getMessage());
				}
			}
		}

	
	private static void modificacion()  {


		    File fichero = new File("producto.dat");

		    // 1. Leer fichero y volcar a ArrayList
		    ArrayList<Producto> aProductos = new ArrayList<>();

		    if (!fichero.exists()) {
		        System.out.println("Fichero no existente");
		        return;
		    }

		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {

		        while (true) {
		            try {
		                Producto p = (Producto) ois.readObject();
		                aProductos.add(p);
		            } catch (EOFException eof) {
		                break; // fin del fichero
		            }
		        }

		    } catch (Exception e) {
		        System.out.println("Error leyendo fichero: " + e.getMessage());
		        return;
		    }

		    // 2. Buscar producto y modificarlo
		    System.out.println("Introduce nombre del producto a modificar:");
		    String nombreBuscado = Util.introducirCadena();

		    boolean encontrado = false;

		    for (Producto p : aProductos) {
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

		    // 3. Volcar en fichero auxiliar
		    File aux = new File("producto_aux.dat");

		    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(aux))) {

		        for (Producto p : aProductos) {
		            oos.writeObject(p);
		        }

		    } catch (IOException e) {
		        System.out.println("Error escribiendo auxiliar: " + e.getMessage());
		        return;
		    }

		    // 4. Borrar original y renombrar
		    if (fichero.delete()) {
		        aux.renameTo(fichero);
		        System.out.println("Producto modificado correctamente");
		    } else {
		        System.out.println("Error al reemplazar el fichero");
		    }
		}

	
	

	private static void consulta()  {
File refFichero=new File("producto.dat");
		
		// Lee el fichero
		if (!refFichero.exists())
		{
			System.out.println("Fichero no existente");
		}
		else
		{
			//pido usuario nombre del producto a buscar
			System.out.println ("introduce nombre de producto a buscar");
			String nombreBuscado = Util.introducirCadena();
			try (FileInputStream fileIStream = new FileInputStream(refFichero);
		             ObjectInputStream productoIStream = new ObjectInputStream(fileIStream)) 
			{
				while (true) {
					try {
						Producto aux = (Producto) productoIStream.readObject();
						if (aux.getNombre().equalsIgnoreCase(nombreBuscado))
						{
							System.out.println(aux);  
						break;
						}
					
					} catch (EOFException eof) {
						System.out.println("producto no existe");	
					}
				}

			} catch (Exception e) {
				System.out.println("Error de lectura: "+e.getMessage());
			}
		}
	}   
	
	
	private static void borrado()  {
		File fichero = new File("producto.dat");
		 // 1. Leer fichero y volcar a ArrayList
	    ArrayList<Producto> aProductos = new ArrayList<>();

	    if (!fichero.exists()) {
	        System.out.println("Fichero no existente");
	        return;
	    }

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {

	        while (true) {
	            try {
	                Producto p = (Producto) ois.readObject();
	                aProductos.add(p);
	            } catch (EOFException eof) {
	                break; // fin del fichero
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error leyendo fichero: " + e.getMessage());
	        return;
	    }

	    // 2. Buscar producto y borrar
	    System.out.println("Introduce nombre del producto a borrar:");
	    String nombreBuscado = Util.introducirCadena();

	    boolean encontrado = false;

	    Iterator<Producto> it = aProductos.iterator();

	    while (it.hasNext()) {
	        Producto p = it.next();
	        if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
	            System.out.println("Producto encontrado y eliminado: " + p);
	            it.remove(); 
	            encontrado = true;
	            break;
	        }
	    }

	    if (!encontrado) {
	        System.out.println("Producto no encontrado");
	        return;
	    }


	    // 3. Volcar en fichero auxiliar
	    File aux = new File("producto_aux.dat");

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(aux))) {

	        for (Producto p : aProductos) {
	            oos.writeObject(p);
	        }

	    } catch (IOException e) {
	        System.out.println("Error escribiendo auxiliar: " + e.getMessage());
	        return;
	    }

	    // 4. Borrar original y renombrar
	    if (fichero.delete()) {
	        aux.renameTo(fichero);
	        System.out.println("Producto modificado correctamente");
	    } else {
	        System.out.println("Error al reemplazar el fichero");
	    }
	}


		
	
	private static void ordenarPorNombre() {

	    File fichero = new File("producto.dat");
	    ArrayList<Producto> aProductos = new ArrayList<>();

	    if (!fichero.exists()) {
	        System.out.println("Fichero no existente");
	        return;
	    }

	    // 1. Leer fichero
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {

	        while (true) {
	            try {
	                Producto p = (Producto) ois.readObject();
	                aProductos.add(p);
	            } catch (EOFException eof) {
	                break;
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error leyendo fichero: " + e.getMessage());
	        return;
	    }

	    // 2. Ordenar usando Comparable
	    Collections.sort(aProductos);

	    // 3. Volcar en fichero auxiliar
	    File aux = new File("producto_aux.dat");

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(aux))) {

	        for (Producto p : aProductos) {
	            oos.writeObject(p);
	        }

	    } catch (IOException e) {
	        System.out.println("Error escribiendo auxiliar: " + e.getMessage());
	        return;
	    }

	    // 4. Borrar original y renombrar
	    if (fichero.delete()) {
	        aux.renameTo(fichero);
	        System.out.println("Productos ordenados correctamente");
	    } else {
	        System.out.println("Error al reemplazar el fichero");
	    }
	}


}