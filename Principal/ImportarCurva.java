package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import interfaz.BDConnection;

public class ImportarCurva {

	public ImportarCurva(){

	}

	public static CurvaOriginal importarCurva(String file) throws FileNotFoundException, ClassNotFoundException{

		Scanner sc = new Scanner(new File(file));
		Scanner auxSC;
		sc.useDelimiter(" ");

		String moduleName = sc.nextLine(); // -- Modulo
		auxSC= new Scanner(moduleName);
		auxSC.next();
		moduleName = auxSC.next();
		moduleName += " "+auxSC.next();
		auxSC.close();
		//System.out.println(moduleName);

		String campanya=sc.nextLine(); // -- Campaña
		auxSC = new Scanner(campanya);
		auxSC.next();
		campanya = auxSC.next();
		//System.out.println(campanya);
		auxSC.close();

		String fecha = sc.nextLine(); // -- Fecha
		auxSC = new Scanner(fecha);
		auxSC.next();
		fecha = auxSC.next();
		auxSC.close();
		//System.out.println(fecha);

		String hora = sc.nextLine(); // -- Hora
		auxSC = new Scanner(hora);
		auxSC.next();
		hora = auxSC.next();
		auxSC.close();
		//System.out.println(hora);

		sc.nextLine();
		sc.nextLine();


		String aux = sc.nextLine(); // -- ISC
		auxSC = new Scanner(aux);
		auxSC.next();
		double isc = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(isc);

		aux = sc.nextLine(); // -- VOC
		auxSC = new Scanner(aux);
		auxSC.next();
		double voc = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(voc);

		aux = sc.nextLine(); // -- PMAX
		auxSC = new Scanner(aux);
		auxSC.next();
		double pmax = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(pmax);

		aux = sc.nextLine(); // -- IPMAX
		auxSC = new Scanner(aux);
		auxSC.next();
		double ipmax = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(ipmax);

		aux = sc.nextLine(); // -- VPMAX
		auxSC = new Scanner(aux);
		auxSC.next();
		double vpmax = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(vpmax);

		aux = sc.nextLine(); // -- FF
		auxSC = new Scanner(aux);
		auxSC.next();
		double ff = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(ff);

		for(int k=0;k<9;k++){ // Itero hasta la linea 22
			sc.nextLine();
		}

		aux = sc.nextLine(); // -- TEMPERATURA AMBIENTE
		auxSC = new Scanner(aux);
		auxSC.next();
		auxSC.next();
		double temp = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(temp);

		sc.nextLine(); // Itero hasta la linea 25
		sc.nextLine();

		aux = sc.nextLine(); // -- IRRADIANCIA
		auxSC = new Scanner(aux);
		auxSC.next();
		auxSC.next();
		double irr = auxSC.nextDouble();
		auxSC.close();
		//System.out.println(irr);

		for(int k=0;k<9;k++){ // Itero hasta la linea 35
			sc.nextLine();
		}

		aux = sc.nextLine(); // -- NUMERO DE PUNTOS
		auxSC = new Scanner(aux);
		auxSC.next();
		auxSC.next();
		auxSC.next();
		auxSC.next();
		auxSC.next();
		int nPuntos = auxSC.nextInt();
		auxSC.close();
		//System.out.println(nPuntos);

		ArrayList<parIV> pares = new ArrayList<>();

		sc.nextLine();
		aux = sc.nextLine();

		for(int i=0;i<nPuntos-1;i++){ // -- PUNTOS
			auxSC = new Scanner(aux);
			auxSC.next();
			double vo = auxSC.nextDouble();
			double in = auxSC.nextDouble();
			//System.out.println(vo+" "+in);
			aux = sc.nextLine();
			auxSC.close();
			parIV p = new parIV(in,vo);
			pares.add(p);
		}

		auxSC = new Scanner(aux); // -- ULTIMO PUNTO
		auxSC.next();
		double vo = auxSC.nextDouble();
		double in = auxSC.nextDouble();
		//System.out.println(vo+" "+in);
		parIV p = new parIV(in, vo);
		pares.add(p);
		auxSC.close();
		sc.close();
		
		JOptionPane.showMessageDialog(null,moduleName+" "+isc+" "+ voc+" "+pmax+" "+ipmax+" "+vpmax+" "+ff+" "+fecha+" "+temp+" "+irr);

		
		BDConnection miBD = new BDConnection();
		List<Object[]> auxM = miBD.Select("SELECT * FROM MODULO WHERE nombreModulo LIKE '"+moduleName+"' ;");
		JOptionPane.showMessageDialog(null,String.valueOf(auxM.isEmpty()));
		
		if(auxM.isEmpty()) { // Si el modulo al que pertenece la curva no esta en la BD
			Modulo auxModule = new Modulo(moduleName,0,0,0,0); // Creamos el modulo con solo el nombre
		}
		CurvaOriginal auxCurva = new CurvaOriginal(isc, voc, pmax,ipmax, vpmax, ff, pares, fecha,temp,irr,moduleName);
		
		
		return auxCurva;
	}

}


