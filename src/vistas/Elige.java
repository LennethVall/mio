package vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Elige extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JRadioButton rbCarga;
    private JRadioButton rbVisualiza;
    private JButton jbAceptar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Elige frame = new Elige();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Elige() {
        setTitle("Elige una opcion");
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        rbCarga = new JRadioButton("Carga");
        rbCarga.setBounds(56, 90, 109, 23);
        contentPane.add(rbCarga);

        rbVisualiza = new JRadioButton("Visualiza");
        rbVisualiza.setBounds(269, 90, 109, 23);
        contentPane.add(rbVisualiza);

        rbVisualiza.setSelected(true);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbCarga);
        grupo.add(rbVisualiza);

        jbAceptar = new JButton("Aceptar");
        jbAceptar.setBounds(162, 212, 89, 23);
        jbAceptar.addActionListener(this); 
        contentPane.add(jbAceptar);
        
        JLabel lblNewLabel = new JLabel("Selecciona una opcion");
        lblNewLabel.setBounds(146, 54, 141, 14);
        contentPane.add(lblNewLabel);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (rbCarga.isSelected()) {
            System.out.println("Cargando...");
            if (evento.getSource() == jbAceptar) {

        	    boolean ok = GF.precargar();

        	    if (ok) {
        	        JOptionPane.showMessageDialog(
        	            this,
        	            "El fichero se ha cargado correctamente.",
        	            "Éxito",
        	            JOptionPane.INFORMATION_MESSAGE
        	        );
        	    } else {
        	        JOptionPane.showMessageDialog(
        	            this,
        	            "No se ha podido precargar el fichero.",
        	            "Error",
        	            JOptionPane.ERROR_MESSAGE
        	        );
        	    }
            }
        } else if (rbVisualiza.isSelected()) {
            System.out.println("Comprobando archivo...");
            File fichero = new File("gure.dat");

    	    if (!fichero.exists() || fichero.length() == 0) {
    	        JOptionPane.showMessageDialog(
    	            this,
    	            "No hay datos que visualizar.",
    	            "Aviso",
    	            JOptionPane.WARNING_MESSAGE
    	        );
    	        return;
    	    }

    	    // Si hay datos → abrir tabla
    	    @SuppressWarnings("unused")
			DefaultTableModel modelo = GF.visualizarTabla();
    	    
    	}
        }
    }


