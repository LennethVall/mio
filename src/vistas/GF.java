package vistas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import clases.Idioma;
import clases.Teatro;
import clases.Concierto;

public class GF {

    public static boolean precargar() {

        File fichero = new File("evento.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            
            Teatro primerT = new Teatro(1002, "bilbo",LocalDate.of(2025, 11, 4),2000, 2,Idioma.INGLES);
            Teatro segundoT = new Teatro(1003,"vitoria",LocalDate.of(2025, 5, 2), 1400, 2, Idioma.INGLES );
            Concierto primerC = new Concierto( 1004, "bilbo","Suplex", LocalDate.of(2025, 5, 2), 1400, Idioma.INGLES);
            
            oos.writeObject(primerT);
            oos.writeObject(segundoT);
            oos.writeObject(primerC);

            return true;

        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
            return false;
        }
    }
    public static void visualizar() {
    	
    }
}
