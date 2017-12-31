package principal;

import java.util.Map.Entry;
import java.util.TreeMap;

public class Metodo {

	public Metodo(){

	}

	public static CurvaCorregida Metodo1(double alfa,double beta,double res,double kappa,double isc,double g1,double g2,double t1,double t2,CurvaOriginal co) throws ClassNotFoundException{
		String in="",vo="";
		double pmax=0,ipmax=0,vpmax=0,isc2=0,voc2=0,difISC=Double.MAX_VALUE,difVOC=Double.MAX_VALUE;
		TreeMap<Double,Double> puntos = co.getPts();
		for(Entry<Double, Double> aux : puntos.entrySet()){
			double i2 = aux.getValue() + isc * ( (g2/g1) - 1 ) + alfa * (t2-t1); // Formula para I2[i]
			double v2 = aux.getKey() - res * (i2-aux.getValue()) - kappa * i2 * (t2-t1) + beta * (t2-t1); // Formula para V2[i]
			in+=i2+";";
			vo+=v2+";";
			if(pmax<(i2*v2)){ // Saca los valores de IPMAX,VPMAX,PMAX
				pmax = i2*v2;
				ipmax=i2;
				vpmax=v2;
			}

			double auxI = Math.abs(i2);
			if(difISC>auxI) { // Saca la VOC
				difISC=auxI;
				voc2 = v2;
			}
			double auxV = Math.abs(v2);
			if(difVOC>auxV) { // Saca la ISC
				difVOC=auxV;
				isc2=i2;
			}

		}

		double ff = 100 * (pmax/(isc2*voc2)); // Saca el FF


		CurvaCorregida cc = new CurvaCorregida(co,isc2,voc2,pmax,ipmax,vpmax,ff,in,vo); // NULL PORQUE NO SE DE DONDE SACAR LA CURVA ORIGINAL

		return cc;
	}

}
