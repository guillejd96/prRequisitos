package principal;

import java.util.List;

public class CurvaCorregida implements curva {
    List<parIV> pts;

    public List<parIV> getPts() {
		return pts;
	}

	public void setPts(List<parIV> pts) {
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

