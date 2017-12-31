package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import principal.CurvaCorregida;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class IntfzCurvaCorregida extends JFrame {

	private JPanel contentPane;
	//curva
	private static CurvaCorregida curva;
	private JTable table;
	private ChartPanel panelGrafico;
	private XYSeries seriesA = new XYSeries("par i-v");

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
		setBounds(100, 100, 450, 834);
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
		String[] columnName = {"N","TensiÃ³n(V)","Corriente(A)","Potencia(W)"};
		
		Object [] [] data= new Object [curva.getPts().size()] [columnName.length];//array de objetos
		int i = 0;
		for( Entry<Double, Double> pt : curva.getPts().entrySet()){
			//parte de la tabla
			double pot = pt.getKey()*pt.getValue();	//potencia
			data[i][0] = i+1;
			data[i][1] = Math.floor(pt.getKey() * 100000) / 100000;
			data[i][2] = Math.floor(pt.getValue() * 100000) / 100000;
			data[i][3] = pot;
			
			i++;
			
			//parte grafica
			seriesA.add(pt.getKey(), pt.getValue());
		}
		
		table = new JTable(data,columnName);
		table.setPreferredScrollableViewportSize(new Dimension(100,100));
		table.setFillsViewportHeight(true);
		table.setBounds(45, 87, 336, 163);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 354, 414, 431);
		contentPane.add(scrollPane);
//-------REPRSENTACION GRAFICA
		 XYSeriesCollection datasetA = new XYSeriesCollection();
	        datasetA.addSeries(seriesA);

	        JFreeChart chartA = ChartFactory.createXYLineChart(
	                "curvaFoltovoltaica", // Título
	                "Corriente (A)", // Etiqueta Coordenada X
	                "Tension (V)", // Etiqueta Coordenada Y
	                datasetA, // Datos
	                PlotOrientation.VERTICAL,
	                false, // Muestra la leyenda de los productos (Producto A)
	                false,
	                false
	        );
			
		
		panelGrafico = new ChartPanel(chartA);
		panelGrafico.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelGrafico.setBackground(UIManager.getColor("Button.focus"));
		panelGrafico.setBounds(10, 67, 414, 276);
		contentPane.add(panelGrafico);
		
		
		
		
		
		 
	}
}

