package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportarModulo {

	public ImportarModulo(){
	}

	public Modulo importarModulo(String file) throws FileNotFoundException, ClassNotFoundException{

		Scanner sc = new Scanner(new File(file));

		String nom = sc.nextLine(); // Saco el nombre del modulo (linea 1)
		for(int i=0;i<13;i++){ // Itero 14 veces para irme a la linea 15 del fichero
			sc.nextLine();
		}

		String a = sc.nextLine(); // Saco el valor de alpha (linea 15)
		double al = Double.parseDouble(a);
		sc.nextLine();
		String b = sc.nextLine(); // Saco el valor de beta (linea 17)
		double be = Double.parseDouble(b);
		sc.nextLine();
		String g = sc.nextLine(); // Saco el valor de gamma (linea 19)
		double ga = Double.parseDouble(g);
		sc.nextLine();
		String k = sc.nextLine(); // Saco el valor de kappa (linea 21)
		double ka = Double.parseDouble(k);


		Modulo mod = new Modulo(nom, al, be, ga, ka);

		sc.close();
		return mod;
	}
}
