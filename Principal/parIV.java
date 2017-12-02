package principal;

public class parIV {
    private double intensidad;
    private double voltaje;
    
    //generar constructor
    public parIV(double i, double v) {
    	intensidad = i;
    	voltaje = v;
    }
	public double getIntensidad() {
		return intensidad;
	}
	public void setIntensidad(double intensidad) {
		this.intensidad = intensidad;
	}
	public double getVoltaje() {
		return voltaje;
	}
	public void setVoltaje(double voltaje) {
		this.voltaje = voltaje;
	}
}

