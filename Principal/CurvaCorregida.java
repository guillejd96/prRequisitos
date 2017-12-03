package principal;

import java.util.ArrayList;

public class CurvaCorregida implements curva {

	ArrayList<parIV> pts;

	public CurvaCorregida(ArrayList<parIV> pares){
		pts=pares;
	}

	public ArrayList<parIV> getPts() {
		return pts;
	}

	public void setPts(ArrayList<parIV> pts) {
		this.pts = pts;
	}

	public void mostrarDatos(){
		int i = 0;
		for (parIV p : pts){
			System.out.println("PARIV" + i + "[Intensidad = " + p.getIntensidad() + "] ; [Voltaje = " + p.getVoltaje());
			i++;
		}
	}

}
