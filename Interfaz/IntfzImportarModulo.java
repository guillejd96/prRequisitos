package interfaz;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.ImportarModulo;
import principal.Modulo;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;

public class IntfzImportarModulo extends JFrame {
	

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSubirArchivo;
	private JButton btnCancelar;
	private JButton btnBuscarArchivo;
	private String file;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public  void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					IntfzImportarModulo frame = new IntfzImportarModulo();
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
	public IntfzImportarModulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setTitle("PVTRANSLATOR");
		
		textField = new JTextField();
		textField.setBounds(10, 216, 357, 23);
		contentPane.add(textField);
		textField.setColumns(10);
//-----BOTON BUSCAR ARCHIVO
		btnBuscarArchivo = new JButton("Buscar Archivo");
		btnBuscarArchivo.setBounds(377, 216, 154, 23);
		contentPane.add(btnBuscarArchivo);
		btnBuscarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//QUE HACE EL BOTON
				
				
				final JFileChooser fc = new JFileChooser();
				int response = fc.showOpenDialog(rootPane);
				if(response == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile().toString();
					textField.setText(file);
				}
			}
		});
//-----BOTON SUBIR ARCHIVO
		btnSubirArchivo = new JButton("Subir Archivo");
		btnSubirArchivo.setBounds(10, 266, 130, 23);
		contentPane.add(btnSubirArchivo);
		btnSubirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//QUE HACE EL BOTON
				if(!textField.getText().equals("")) {
					try {
						Modulo mod =ImportarModulo.importarModulo(textField.getText());
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(null, "El archivo se ha subido a la bd");
				}else {
					JOptionPane.showMessageDialog(null, "Introduzca archivo","Error!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
//-----BOTON CANCELAR
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(401, 266, 130, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().isEmpty()) {
					//QUE HACE EL BOTON
					int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Mensaje",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						IntfzPantallaInicio pi = new IntfzPantallaInicio();
						pi.setVisible(true);
						dispose();
					}
					
				}else {
					//si no habia ningun texto sale directamente
					IntfzPantallaInicio pi = new IntfzPantallaInicio();
					pi.setVisible(true);
					dispose();
				}
				
			}
		});
//-----TEXTO INFORMATIVO
		JTextPane txtpnContenido = new JTextPane();
		txtpnContenido.setBackground(UIManager.getColor("Button.background"));
		txtpnContenido.setText("bla bla bla");
		txtpnContenido.setBounds(10, 84, 357, 118);
		contentPane.add(txtpnContenido);
//-----titulo
		lblNewLabel = new JLabel("IMPORTAR MÓDULO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 521, 62);
		contentPane.add(lblNewLabel);
		btnBuscarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ACCION AL PULSAR EL BOTON
				
				
			}
		});
		
		
		
	}
}
