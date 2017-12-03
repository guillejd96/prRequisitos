package principal;

import java.util.ArrayList;
import java.util.List;

public class Metodo {

	public Metodo(){

	}

	public static CurvaCorregida Metodo1(double alfa,double beta,double res,double kappa,double isc,double g1,double g2,double t1,double t2,List<parIV> pares){
		ArrayList<parIV> newPares = new ArrayList<>();

		for(parIV p : pares){
			double i2 = p.getIntensidad() + isc * ( (g2/g1) - 1 ) + alfa * (t2-t1); // Formula para I2[i]
			double v2 = p.getVoltaje() - res * (i2-p.getIntensidad()) - kappa * i2 * (t2-t1) + beta * (t2-t1); // Formula para V2[i]
			parIV newP = new parIV(i2, v2);
			newPares.add(newP);
		}

		CurvaCorregida cc = new CurvaCorregida(newPares);

		return cc;
	}

}
