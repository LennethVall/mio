package vistas;

import java.io.*;
import java.time.LocalDate;

import clases.Idioma;
import clases.Teatro;
import clases.Concierto;

public class GF {

    public static boolean precargar() {

        File fichero = new File("gure.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            
            Teatro primerT = new Teatro(1002, "bilbo", LocalDate.of(2025, 11, 4), 2000, 2, Idioma.INGLES);
            Teatro segundoT = new Teatro(1003, "vitoria", LocalDate.of(2025, 5, 2), 1400, 2, Idioma.INGLES);

            Concierto primerC = new Concierto(
                1004,"bilbo",LocalDate.of(2025, 5, 2), 1400, Idioma.INGLES, "Suplex");

            oos.writeObject(primerT);
            oos.writeObject(segundoT);
            oos.writeObject(primerC);

            return true;

        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
            return false;
        }
    }

    public static javax.swing.table.DefaultTableModel visualizarTabla() {

        String[] columnas = {"ID", "Nombre", "Fecha", "Aforo", "Idioma", "Tipo"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0);

        File fichero = new File("gure.dat");

        if (!fichero.exists() || fichero.length() == 0) {
            return modelo; // tabla vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {

            while (true) {
                try {
                    Object obj = ois.readObject();

                    if (obj instanceof clases.Evento e) {

                        String tipo = (e instanceof clases.Concierto) ? "Concierto" : "Teatro";

                        modelo.addRow(new Object[]{
                            e.getId(), e.getNombre(), e.getFecha(), e.getAforo(), e.getIdioma(), tipo });
                    }

                } catch (EOFException eof) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;
    }

    
}
