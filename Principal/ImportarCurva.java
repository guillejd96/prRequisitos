package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaz.BDConnection;

public class ImportarCurva {

	public ImportarCurva(){

	}

	public CurvaOriginal importarCurva(String file) throws FileNotFoundException, ClassNotFoundException{
		// NO ESTA ACABADO

		Scanner sc = new Scanner(new File(file));
		Scanner auxSC;
		sc.useDelimiter(" ");

		String moduleName = sc.nextLine(); // -- Modulo
		auxSC= new Scanner(moduleName);
		auxSC.next();
		moduleName = auxSC.next();
		moduleName += auxSC.next();
		auxSC.close();
		System.out.println(moduleName);

		String campanya=sc.nextLine(); // -- Campaña
		auxSC = new Scanner(campanya);
		auxSC.next();
		campanya = auxSC.next();
		System.out.println(campanya);
		auxSC.close();

		String fecha = sc.nextLine(); // -- Fecha
		auxSC = new Scanner(fecha);
		auxSC.next();
		fecha = auxSC.next();
		auxSC.close();
		System.out.println(fecha);

		String hora = sc.nextLine(); // -- Hora
		auxSC = new Scanner(hora);
		auxSC.next();
		hora = auxSC.next();
		System.out.println(hora);


		IterarHasta(sc, new String(), "Isc:");

		double isc = sc.nextDouble(); // ISC
		System.out.println("ISC: "+isc);
		IterarHasta(sc, new String(), "Voc:");
		double voc = sc.nextDouble(); // VOC
		System.out.println("VOC: "+voc);
		IterarHasta(sc, new String(), "Pmax:");
		double pmax = sc.nextDouble(); // PMAX
		System.out.println("PMAX: "+pmax);
		IterarHasta(sc, new String(), "IPmax:");
		double ipmax = sc.nextDouble(); // IPMAX
		System.out.println("IPMAX: "+ipmax);
		IterarHasta(sc, new String(), "VPmax:");
		double vpmax = sc.nextDouble(); // VPMAX
		System.out.println("VPMAX: "+vpmax);
		IterarHasta(sc, new String(), "FF:");
		double ff = sc.nextDouble(); // FF
		System.out.println("FF: "+ff);



		IterarHasta(sc, new String(), "IV:");

		int nPuntos = sc.nextInt(); // Numero de puntos

		System.out.println("Numero de puntos: "+nPuntos);

		IterarHasta(sc, new String(), "1");
		ArrayList<parIV> pares = new ArrayList<>();

		for(int i=0;i<nPuntos;i++){
			double vo = sc.nextDouble(); // Voltaje
			double in = sc.nextDouble(); // Intensidad
			double po = sc.nextDouble(); // Potencia
			System.out.println((i+1)+" V: "+vo+" In: "+in);
			sc.next();
			parIV p = new parIV(in, vo);
			pares.add(p);
		}

		CurvaOriginal auxCurva = new CurvaOriginal(isc, voc, pmax,ipmax, vpmax, ff, pares, fecha);

		BDConnection miBD = new BDConnection();
		List<Object[]> auxM = miBD.Select("SELECT * FROM MODULO WHERE NOMBREMODULO='"+moduleName+"'");

		if(auxM.isEmpty()) { // Si el modulo al que pertenece la curva no esta en la BD
			Modulo auxModule = new Modulo(moduleName); // Creamos el modulo con solo el nombre
		}

		sc.close();
		auxSC.close();
		return auxCurva;
	}

	private void IterarHasta(Scanner sc,String aux,String s){
		while(!aux.equals(s)){
			aux=sc.nextLine();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		ImportarCurva im = new ImportarCurva();
		CurvaOriginal cv = im.importarCurva("IV_I-53 946309_20170822_152424.xls");
	}

}
