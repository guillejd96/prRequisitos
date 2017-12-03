package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import principal.CurvaCorregida;
import principal.parIV;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

public class IntfzCurvaCorregida extends JFrame {

	private JPanel contentPane;
	//curva
	private static CurvaCorregida curva;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public  void newScreen(CurvaCorregida cc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 curva = cc;
					 
					IntfzCurvaCorregida frame = new IntfzCurvaCorregida();
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
	public IntfzCurvaCorregida() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//evita cerrar el proyecto entero
		setBounds(100, 100, 450, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCurvaCorregida = new JLabel("CURVA CORREGIDA");
		lblCurvaCorregida.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurvaCorregida.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCurvaCorregida.setBounds(10, 11, 414, 45);
		contentPane.add(lblCurvaCorregida);
		
		//CONSTRUCCION DE LA TABLA
		String[] columnName = {"N","TensiÃ³n(V)","Corriente(A)","Potencia(W)"};
		
		Object [] [] data= new Object [curva.getPts().size()] [columnName.length];//array de objetos
		int i = 0; //indice sobre el que construiremos la tabla
		for( parIV pts : curva.getPts()) {
			data[i][0] = i+1; 			  		//indice
			data[i][1] = pts.getVoltaje();		//tension
			data[i][2] = pts.getIntensidad();	//corriente
			data[i][3] = pts.getVoltaje()*pts.getIntensidad();	//potencia
			i++;
		}
		
		table = new JTable(data,columnName);
		table.setPreferredScrollableViewportSize(new Dimension(100,100));
		table.setFillsViewportHeight(true);
		table.setBounds(45, 87, 336, 163);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 67, 414, 405);
		contentPane.add(scrollPane);
		
		
		
		
		
		 
	}
}

