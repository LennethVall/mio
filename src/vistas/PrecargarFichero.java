package vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

public class PrecargarFichero extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnPF;
    private JButton btnV;
    private JButton btnSalir;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PrecargarFichero frame = new PrecargarFichero();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PrecargarFichero() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        btnPF = new JButton("Precargar fichero");
        btnPF.setBounds(141, 128, 150, 33);
        btnPF.addActionListener(this);
        contentPane.add(btnPF);

        btnV = new JButton("Visualizar");
        btnV.setBounds(157, 86, 120, 23);
        btnV.addActionListener(this); // ← FALTABA ESTO
        contentPane.add(btnV);
        
        JTextPane txtpnEligeUnaOpcion = new JTextPane();
        txtpnEligeUnaOpcion.setText("Elige una opcion");
        txtpnEligeUnaOpcion.setBounds(74, 26, 150, 20);
        contentPane.add(txtpnEligeUnaOpcion);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(316, 227, 89, 23);
        btnSalir.addActionListener(this);
        contentPane.add(btnSalir);
    }

    public class VentanaTabla extends JFrame {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public VentanaTabla(DefaultTableModel modelo) { 
    		setTitle("Eventos en tabla"); 
    		setSize(600, 400); 
    		setLocationRelativeTo(null);
    		JTable tabla = new JTable(modelo); 
    		JScrollPane scroll = new JScrollPane(tabla);
    		add(scroll); 
    		} 
    	}
    
    private void cerrar() {
		this.dispose();
	}
    @Override
    public void actionPerformed(ActionEvent evento) {

    	if (evento.getSource() == btnPF) {

    	    boolean ok = GF.precargar();

    	    if (ok) {
    	        JOptionPane.showMessageDialog(
    	            this,
    	            "El fichero se ha precargado correctamente.",
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


    	if (evento.getSource() == btnV) {

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
    	    DefaultTableModel modelo = GF.visualizarTabla();
    	    new VentanaTabla(modelo).setVisible(true);
    	}

    	if (evento.getSource() == btnSalir) {
			cerrar();
    	}
}
}
}
