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
					 
					IntfzCurvaCorregida frame = new IntfzCurvaCorregida(cc);
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
	public IntfzCurvaCorregida(CurvaCorregida cc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//evita cerrar el proyecto entero
		setBounds(100, 100, 450, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 curva = cc;
		
		JLabel lblCurvaCorregida = new JLabel("CURVA CORREGIDA");
		lblCurvaCorregida.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurvaCorregida.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCurvaCorregida.setBounds(10, 11, 414, 45);
		contentPane.add(lblCurvaCorregida);
		
		//CONSTRUCCION DE LA TABLA
		String[] columnName = {"N","Tensión(V)","Corriente(A)","Potencia(W)"};
		
		Object [] [] data= new Object [curva.getPts().size()] [columnName.length];//array de objetos
		int i = 0; //indice sobre el que construiremos la tabla
		for( parIV pts : curva.getPts()) {
			data[i][0] = i+1; 			  		//indice
			data[i][1] = Math.floor(pts.getVoltaje() * 100000) / 100000;		//tension
			data[i][2] = Math.floor(pts.getIntensidad() * 100000) / 100000;
			double pot = pts.getVoltaje()*pts.getIntensidad();	//potencia
			data[i][3] = Math.floor(pot * 100000) / 100000;
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

