package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

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

public class IntfzPantallaInicio extends JFrame {

	private JLayeredPane contentPane;
	private JLabel lblLogoPyromik;
	private JLabel lblPvtranslator;
	private JButton btnListaModulo;
	private JButton btnImportarModulo;
	private JButton btnImportarCurva;
	//SLOTS DE MODULOS
	private Modulo mod1 = new Modulo();
	private Modulo mod2 = new Modulo();
	private Modulo mod3 = new Modulo();
	private Modulo mod4 = new Modulo();
	
	private JPanel panelModulo;
	private JLabel ListaModuloTitulo;
	private JButton button;
	private JSeparator separator;
	private JLabel modulo1_tit;
	private JLabel modulo1_nametxt;
	private JLabel modulo1_name = new JLabel("");
	private JButton btnModulo1;
	private JSeparator separator_1;
	private JLabel modulo2_tit;
	private JLabel modulo2_nametxt;
	private JLabel modulo2_name = new JLabel("");
	private JButton btnModulo2;
	private JSeparator separator_2;
	private JLabel modulo3_tit;
	private JLabel modulo3_nametxt;
	private JLabel modulo3_name = new JLabel("");
	private JButton btnModulo3;
	private JSeparator separator_3;
	private JLabel modulo4_tit;
	private JLabel modulo4_nametxt;
	private JLabel modulo4_name= new JLabel("");
	private JButton btnModulo4;
	/**
	 * Launch the application.
	 */
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
		
		mod1 = new Modulo();
		mod2 = new Modulo();
		mod3 = new Modulo();
		mod4 = new Modulo();
		
		cargaDatos();		//cargamos los datos en los slots
		
		this.setTitle("PVTRANSLATOR");
		Image logoPyromik = new ImageIcon(this.getClass().getResource("/Images/pyromikLogo.jpeg")).getImage();
		
		JPanel panelInicial = new JPanel();
		panelInicial.setBounds(0, 0, 529, 292);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		//------TEXTO DE ABAJO		
		JTextPane txtpnBalBlaBla = new JTextPane();
		txtpnBalBlaBla.setBounds(97, 180, 347, 128);
		panelInicial.add(txtpnBalBlaBla);
		txtpnBalBlaBla.setBackground(UIManager.getColor("Button.background"));
		txtpnBalBlaBla.setText("BAL BLA BLA");
		//-----BOTON LISTA MODULOS
		btnListaModulo = new JButton("Lista Módulos");
		btnListaModulo.setBounds(97, 126, 134, 23);
		btnListaModulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				panelInicial.setVisible(false);
				panelModulo.setVisible(true);
				
			}
		});
		panelInicial.add(btnListaModulo);
		//------BOTON IMPORTAR MODULOS
		btnImportarModulo = new JButton("Importar Modulo");
		btnImportarModulo.setBounds(285, 126, 134, 23);
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
		btnImportarCurva.setBounds(285, 156, 134, 23);
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
		lblPvtranslator.setBounds(110, 11, 251, 85);
		panelInicial.add(lblPvtranslator);
		lblPvtranslator.setFont(new Font("Tahoma", Font.PLAIN, 35));
		//-----IMAGEN LOGO
		lblLogoPyromik = new JLabel("");
		lblLogoPyromik.setBounds(10, 6, 90, 90);
		panelInicial.add(lblLogoPyromik);
		lblLogoPyromik.setIcon(new ImageIcon(logoPyromik));
//------PANEL DE MODULO
		panelModulo = new JPanel();
		panelModulo.setBounds(0, 0, 529, 292);
		contentPane.add(panelModulo);
		panelModulo.setLayout(null);
		panelModulo.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ListaModuloTitulo = new JLabel("LISTA M\u00D3DULOS");
		ListaModuloTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ListaModuloTitulo.setBounds(10, 11, 414, 42);
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
		
		separator = new JSeparator();
		separator.setBounds(10, 64, 509, 3);
		panelModulo.add(separator);
		//-----MODULO1
		modulo1_tit = new JLabel("M\u00D3DULO 1");
		modulo1_tit.setFont(new Font("Tahoma", Font.BOLD, 11));
		modulo1_tit.setBounds(20, 78, 69, 14);
		panelModulo.add(modulo1_tit);
		
		modulo1_nametxt = new JLabel("NOMBRE:");
		modulo1_nametxt.setBounds(99, 78, 69, 14);
		panelModulo.add(modulo1_nametxt);
		
		
		modulo1_name.setBounds(178, 78, 203, 14);
		panelModulo.add(modulo1_name);
		
		btnModulo1 = new JButton("Elegir");
		btnModulo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntfzModuloElegido me;
				try {
					me = new IntfzModuloElegido(mod1.getNombre());
					me.newScreen(mod1.getNombre());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		if(modulo1_name.getText().equals("")) {			//si no hay módulo este es desactivado
			btnModulo1.setEnabled(false);
		}
		btnModulo1.setBounds(430, 74, 89, 23);
		panelModulo.add(btnModulo1);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 103, 509, 3);
		panelModulo.add(separator_1);
		//-----MODULO2
		modulo2_tit = new JLabel("M\u00D3DULO 2");
		modulo2_tit.setFont(new Font("Tahoma", Font.BOLD, 11));
		modulo2_tit.setBounds(20, 117, 69, 14);
		panelModulo.add(modulo2_tit);
		
		modulo2_nametxt = new JLabel("NOMBRE:");
		modulo2_nametxt.setBounds(99, 117, 69, 14);
		panelModulo.add(modulo2_nametxt);
		
		
		modulo2_name.setBounds(178, 117, 203, 14);
		panelModulo.add(modulo2_name);
		
		btnModulo2 = new JButton("Elegir");
		btnModulo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntfzModuloElegido me;
				try {
					me = new IntfzModuloElegido(mod2.getNombre());
					me.newScreen(mod2.getNombre());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		if(modulo2_name.getText().equals("")) {			//si no hay módulo este es desactivado
			btnModulo2.setEnabled(false);
		}
		btnModulo2.setBounds(430, 113, 89, 23);
		panelModulo.add(btnModulo2);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(10, 142, 509, 3);
		panelModulo.add(separator_2);
		//-----MODULO3
		modulo3_tit = new JLabel("M\u00D3DULO 3");
		modulo3_tit.setFont(new Font("Tahoma", Font.BOLD, 11));
		modulo3_tit.setBounds(20, 156, 69, 14);
		panelModulo.add(modulo3_tit);
		
		modulo3_nametxt = new JLabel("NOMBRE:");
		modulo3_nametxt.setBounds(99, 156, 69, 14);
		panelModulo.add(modulo3_nametxt);
		
		
		modulo3_name.setBounds(178, 156, 203, 14);
		panelModulo.add(modulo3_name);
		
		btnModulo3 = new JButton("Elegir");
		btnModulo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntfzModuloElegido me;
				try {
					me = new IntfzModuloElegido(mod3.getNombre());
					me.newScreen(mod3.getNombre());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		if(modulo3_name.getText().equals("")) {			//si no hay módulo este es desactivado
			btnModulo3.setEnabled(false);
		}
		
		btnModulo3.setBounds(430, 152, 89, 23);
		panelModulo.add(btnModulo3);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(10, 181, 509, 3);
		panelModulo.add(separator_3);
		//-----MODULO4
		modulo4_tit = new JLabel("M\u00D3DULO 4");
		modulo4_tit.setFont(new Font("Tahoma", Font.BOLD, 11));
		modulo4_tit.setBounds(20, 195, 69, 14);
		panelModulo.add(modulo4_tit);
		
		modulo4_nametxt = new JLabel("NOMBRE:");
		modulo4_nametxt.setBounds(99, 195, 69, 14);
		panelModulo.add(modulo4_nametxt);
		
		
		modulo4_name.setBounds(178, 195, 203, 14);
		panelModulo.add(modulo4_name);
		
		btnModulo4 = new JButton("Elegir");
		btnModulo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntfzModuloElegido me;
				try {
					me = new IntfzModuloElegido(mod3.getNombre());
					me.newScreen(mod3.getNombre());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		if(modulo4_name.getText().equals("")) {			//si no hay módulo este es desactivado
			btnModulo4.setEnabled(false);
		}
		btnModulo4.setBounds(430, 191, 89, 23);
		panelModulo.add(btnModulo4);
		
		
		panelInicial.setVisible(true);
		panelModulo.setVisible(false);

		
	}
	private void cargaDatos() {
		List<Modulo> slotsModulo = new ArrayList<Modulo>();
		slotsModulo.add(mod1);slotsModulo.add(mod2);slotsModulo.add(mod3);
		slotsModulo.add(mod4);
		List<JLabel> listaNombres = new ArrayList<JLabel>();	//actualizará el nombre del módulo si existe
		listaNombres.add(modulo1_name);listaNombres.add(modulo2_name);
		listaNombres.add(modulo3_name);listaNombres.add(modulo4_name);
		
		try {
			List<Modulo> modulosEnSistema = Modulo.ListaModulo();
			if(modulosEnSistema != null) {			//si hay modulos en el sistema
				Iterator<Modulo> itSlot = slotsModulo.iterator();
				Iterator<JLabel> itNombre = listaNombres.iterator();
				
				for(Modulo mod : modulosEnSistema) {
					String name = mod.getNombre();
					
					itSlot.next().setNombre(name);
					JLabel a = itNombre.next();
					a.setText(name);
				}
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
		}
		
	}//end cargaDatos()

		
	}

