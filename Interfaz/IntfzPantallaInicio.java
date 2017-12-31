package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import principal.Modulo;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class IntfzPantallaInicio extends JFrame {

	private JLayeredPane contentPane;
	private JLabel lblLogoPyromik;
	private JLabel lblPvtranslator;
	private JButton btnListaModulo;
	private JButton btnImportarModulo;
	private JButton btnImportarCurva;

	private JPanel panelModulo;
	private JPanel panelEditar;
	private JLabel ListaModuloTitulo;
	private JButton button;
	private JButton Elegir;
	/**
	 * Launch the application.
	 */
	
	private JList<String> list;
	private JTextField textField_nombre;
	private JTextField textField_alpha;
	private JTextField textField_beta;
	private JTextField textField_gamma;
	private JTextField textField_kappa;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IntfzPantallaInicio frame = new IntfzPantallaInicio();
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
	public IntfzPantallaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 331);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		this.setTitle("PVTRANSLATOR");
		Image logoPyromik = new ImageIcon(this.getClass().getResource("/Images/pyromikLogo.jpg")).getImage();
		
				JPanel panelInicial = new JPanel();
				panelInicial.setBounds(0, 0, 529, 292);
				contentPane.add(panelInicial);
				panelInicial.setLayout(null);
				//------TEXTO DE ABAJO		
				JTextPane txtpnBalBlaBla = new JTextPane();
				txtpnBalBlaBla.setBounds(188, 81, 161, 23);
				panelInicial.add(txtpnBalBlaBla);
				txtpnBalBlaBla.setBackground(UIManager.getColor("Button.background"));
				txtpnBalBlaBla.setText("Bienvenido a PVTranslator");
				//-----BOTON LISTA MODULOS
				btnListaModulo = new JButton("Lista Módulos");
				btnListaModulo.setBounds(200, 117, 134, 23);
				btnListaModulo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {


						panelInicial.setVisible(false);
						panelModulo.setVisible(true);

					}
				});
				panelInicial.add(btnListaModulo);
				//------BOTON IMPORTAR MODULOS
				btnImportarModulo = new JButton("Importar Modulo");
				btnImportarModulo.setBounds(200, 165, 134, 23);
				btnImportarModulo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						IntfzImportarModulo im = new IntfzImportarModulo();
						im.newScreen();
						dispose();
					}
				});
				panelInicial.add(btnImportarModulo);
				//------BOTON IMPORTAR CURVA
				btnImportarCurva = new JButton("Importar curva");
				btnImportarCurva.setBounds(200, 212, 134, 23);
				btnImportarCurva.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						IntfzImportarCurva im = new IntfzImportarCurva();
						im.newScreen();
						dispose();
					}
				});
				panelInicial.add(btnImportarCurva);
				
						//-----TITULO
						lblPvtranslator = new JLabel("PVTRANSLATOR");
						lblPvtranslator.setBounds(134, 6, 291, 85);
						panelInicial.add(lblPvtranslator);
						lblPvtranslator.setFont(new Font("Times New Roman", Font.PLAIN, 35));
						//-----IMAGEN LOGO
						lblLogoPyromik = new JLabel("");
						lblLogoPyromik.setBounds(10, 6, 90, 90);
						panelInicial.add(lblLogoPyromik);
						lblLogoPyromik.setIcon(new ImageIcon(logoPyromik));
						
						
								panelInicial.setVisible(true);
		//------PANEL DE MODULO
		panelModulo = new JPanel();
		panelModulo.setBounds(0, 0, 529, 292);
		contentPane.add(panelModulo);
		panelModulo.setLayout(null);
		panelModulo.setBorder(new EmptyBorder(5, 5, 5, 5));

		ListaModuloTitulo = new JLabel("LISTADO DE M\u00D3DULOS ");
		ListaModuloTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ListaModuloTitulo.setBounds(10, 0, 414, 42);
		panelModulo.add(ListaModuloTitulo);

		button = new JButton("Atr\u00E1s");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModulo.setVisible(false);
				panelInicial.setVisible(true);
			}
		});
		button.setBounds(10, 258, 89, 23);
		panelModulo.add(button);
		

		Elegir = new JButton("Elegir");
		Elegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (list.getSelectedValue() != null) {
						IntfzModuloElegido me = new IntfzModuloElegido(list.getSelectedValue());
						me.newScreen(list.getSelectedValue());
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//-------
			}
		});


		Elegir.setBounds(430, 258, 89, 23);
		panelModulo.add(Elegir);
				
				list = new JList<String>();
				list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				list.setBounds(20, 46, 475, 180);
				list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);    //solo se puede seleccionar uno
				//creamos el modelo
		        DefaultListModel<String> modeloCamp = new DefaultListModel<String>();
		        try {
					for(Modulo mod : Modulo.ListaModulo()) {
						modeloCamp.addElement(mod.getNombre());
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        list.setModel(modeloCamp);
				panelModulo.add(list);
				
				JButton btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(list.getSelectedValue() != null) {
							try {
								
								Modulo mod = new Modulo(list.getSelectedValue());
								textField_nombre.setText(mod.getNombre());
								textField_alpha.setText(String.valueOf(mod.getAlfa()));
								textField_beta.setText(String.valueOf(mod.getBeta()));
								textField_gamma.setText(String.valueOf(mod.getGamma()));
								textField_kappa.setText(String.valueOf(mod.getKappa()));
								
								panelModulo.setVisible(false);
								panelEditar.setVisible(true);
								
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				btnEditar.setBounds(326, 258, 89, 23);
				panelModulo.add(btnEditar);
				
				panelEditar = new JPanel();
				panelEditar.setBounds(0, 0, 529, 292);
				contentPane.add(panelEditar);
				panelEditar.setLayout(null);
				
				textField_nombre = new JTextField();
				textField_nombre.setBounds(238, 44, 86, 20);
				panelEditar.add(textField_nombre);
				textField_nombre.setColumns(10);
				
				textField_alpha = new JTextField();
				textField_alpha.setBounds(238, 75, 86, 20);
				panelEditar.add(textField_alpha);
				textField_alpha.setColumns(10);
				
				textField_beta = new JTextField();
				textField_beta.setBounds(238, 106, 86, 20);
				panelEditar.add(textField_beta);
				textField_beta.setColumns(10);
				
				textField_gamma = new JTextField();
				textField_gamma.setBounds(238, 137, 86, 20);
				panelEditar.add(textField_gamma);
				textField_gamma.setColumns(10);
				
				textField_kappa = new JTextField();
				textField_kappa.setBounds(238, 168, 86, 20);
				panelEditar.add(textField_kappa);
				textField_kappa.setColumns(10);
				
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(182, 47, 46, 14);
				panelEditar.add(lblNombre);
				
				JLabel lblAlpha = new JLabel("Alpha:");
				lblAlpha.setBounds(182, 78, 46, 14);
				panelEditar.add(lblAlpha);
				
				JLabel lblBeta = new JLabel("Beta:");
				lblBeta.setBounds(182, 109, 46, 14);
				panelEditar.add(lblBeta);
				
				JLabel lblGamma = new JLabel("Gamma:");
				lblGamma.setBounds(182, 140, 46, 14);
				panelEditar.add(lblGamma);
				
				JLabel lblKappa = new JLabel("Kappa:");
				lblKappa.setBounds(182, 171, 46, 14);
				panelEditar.add(lblKappa);
				
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setBounds(295, 258, 89, 23);
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(list.getSelectedValue() != null) {
							try {
								
								Modulo mod = new Modulo(list.getSelectedValue());
								mod.setNombre(textField_nombre.getText());
								mod.setAlfa(Double.parseDouble(textField_alpha.getText()));
								mod.setBeta(Double.parseDouble(textField_beta.getText()));
								mod.setGamma(Double.parseDouble(textField_gamma.getText()));
								mod.setKappa(Double.parseDouble(textField_kappa.getText()));
								
								panelModulo.setVisible(true);
								panelEditar.setVisible(false);
								
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				panelEditar.add(btnGuardar);
				
				
				JButton btnAtrs = new JButton("Atr\u00E1s");
				btnAtrs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						panelModulo.setVisible(true);
						panelEditar.setVisible(false);
					}
				});
				btnAtrs.setBounds(139, 258, 89, 23);
				panelEditar.add(btnAtrs);
				
				
		panelModulo.setVisible(false);
		panelEditar.setVisible(false);


	}
}

