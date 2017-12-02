package principal;

public class Canal {
	private double valorMedio;
	private double valorInicial;
	private double valorFinal;
	private int id;
	private String nombre;
	private String medida;
//-------Constructor
	public Canal(double vm, double vi,double vf, int i, String name, String med) {
		if(i < 0) {
			throw new RuntimeException("Valor del identificador negativo");
		}
		valorMedio = vm;
		valorInicial = vi;
		valorFinal = vf;
		id = i;
		nombre = name;
		medida = med;
	}
//------Getters
	public double getValorMedio() {
		return valorMedio;
	}
	public double getValorInicial() {
		return valorInicial;
	}
	public double getValorFinal() {
		return valorFinal;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	
	public String getMedida() {
		return medida;
	}
	//-------Setters
	public void setValorMedio(double valorMedio) {
		this.valorMedio = valorMedio;
	}
	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}
	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}
	public void setId(int i) {
		if(i < 0) {
			throw new RuntimeException("Valor negativo");
		}
		this.id = i;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	
}

