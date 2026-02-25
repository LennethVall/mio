package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.GF;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PrecargarFichero extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPF;
	private JLabel lblStatus;
	private JButton btnV;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrecargarFichero frame = new PrecargarFichero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrecargarFichero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		btnPF = new JButton ("precargar fichero");
		btnPF.setBounds(141, 128, 135, 33);
		btnPF.addActionListener (this);
		contentPane.add(btnPF);
		
		lblStatus = new JLabel("TEXTO PRUEVA");
		lblStatus.setBounds(178, 206, 46, 14);
		contentPane.add(lblStatus);
		
		btnV = new JButton("Visualizar");
		btnV.setBounds(157, 86, 89, 23);
		contentPane.add(btnV);
	}
	
		
		
		
	
		@Override
		public void actionPerformed(ActionEvent evento) {
			
			if (evento.getSource() == btnPF); 
				
			if (evento.getSource() == btnV);
				
		}
	}
