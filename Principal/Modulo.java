package principal;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfaz.BDConnection;

public class Modulo {

	private String nombre;
	private ArrayList<CurvaOriginal> curvas;

	private double alfa;
	private double beta;
	private double gamma;
	private double kappa;


	public Modulo(){
		nombre = "";
		curvas=null;
	}

	public Modulo(String name) throws ClassNotFoundException {
		BDConnection baseDatos = new BDConnection();
		nombre=name;
		for(Object[] elemento : baseDatos.Select("SELECT * FROM MODULO WHERE nombreModulo LIKE '"+name+"';")){

			alfa = (Double.parseDouble(elemento[1].toString()));
			beta = (Double.parseDouble(elemento[2].toString()));
			gamma = (Double.parseDouble(elemento[3].toString()));
			kappa = (Double.parseDouble(elemento[4].toString()));

		}

	}

	public Modulo(String n, double a, double b, double g, double k) throws ClassNotFoundException{

		nombre = n;
		alfa = a;
		beta = b;
		gamma = g;
		kappa = k;
		BDConnection baseDatos = new BDConnection();
		baseDatos.Insert("INSERT INTO Modulo values ('"+n+"', '"+a+"', '"+b+"', '"+g+"', '"+k+"');");

	}
	public Modulo(String n, double a, double b, double g, double k,ArrayList<CurvaOriginal> list){

		nombre = n;
		alfa = a;
		beta = b;
		gamma = g;
		kappa = k;
		curvas = list;


	}
	public Modulo(String name,ArrayList<CurvaOriginal> list) throws ClassNotFoundException{

		this.nombre = name;
		curvas = list;
		BDConnection miBD = new BDConnection();
		Iterator<CurvaOriginal> iter = list.iterator();

		while(iter.hasNext()){
			CurvaOriginal aux = (CurvaOriginal) iter.next();
			double isc = aux.getIsc();
			double voc = aux.getVoc();
			double pmax = aux.getPmax();
			String fecha = aux.getFecha();
			double ff = aux.getFF();
			double ipmax = aux.getIPmax();
			double vpmax = aux.getVPmax();
			miBD.Insert("INSERT INTO MODULO VALUES('"+name+"');");
			miBD.Insert("INSERT INTO CURVAORIGINAL VALUES ('"+name+"','"+fecha+"','"+isc+"','"+voc+"','"+pmax+"','"+ipmax+"','"+vpmax+"','"+ff+"')");
		}

	}

	// ---- Metodo para listar los modulos de la BD

	public static List<Modulo> ListaModulo() throws ClassNotFoundException {
		ArrayList<Modulo> lista = new ArrayList<Modulo>();
		BDConnection baseDatos = new BDConnection();
		for(Object[] elemento : baseDatos.Select("SELECT * FROM MODULO;")){
			Modulo mod = new Modulo();

			mod.setNombre(elemento[0].toString());
			mod.setAlfa(Double.parseDouble(elemento[1].toString()));
			mod.setBeta(Double.parseDouble(elemento[2].toString()));
			mod.setGamma(Double.parseDouble(elemento[3].toString()));
			mod.setKappa(Double.parseDouble(elemento[4].toString()));

			lista.add(mod);
		}
		return lista;
	}
	public  List<String> ListaCampanyas() throws ClassNotFoundException{
		BDConnection miBD = new BDConnection();
		ArrayList<String> lista = new ArrayList<String>();
		
		for(Object[] elemento : miBD.Select("SELECT nombreCampanya FROM campanya where Modulo_nombreModulo = '" + nombre + "';")){
			
			lista.add(elemento[0].toString());
			
			
		}
		return lista;
	}

	//---- Getters

	public String getNombre() {
		return nombre;
	}

	public double getAlfa() {
		return alfa;
	}

	public double getBeta() {
		return beta;
	}

	public double getGamma() {
		return gamma;
	}

	public double getKappa() {
		return kappa;
	}

	// ----- Setters

	public void setNombre(String nombre) throws ClassNotFoundException {
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET NombreModulo ='"+nombre+"' WHERE NombreModulo LIKE'"+this.nombre+"';");
		this.nombre = nombre;
	}

	public void setAlfa(double alfa) throws ClassNotFoundException {
		this.alfa = alfa;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorAlpha='"+alfa+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setBeta(double beta) throws ClassNotFoundException {
		this.beta = beta;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorBeta='"+beta+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setGamma(double gamma) throws ClassNotFoundException {
		this.gamma = gamma;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorGamma='"+gamma+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setKappa(double kappa) throws ClassNotFoundException {
		this.kappa = kappa;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorKappa='"+kappa+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}


	// -----

	public void anyadirCurva (CurvaOriginal c){
		curvas.add(c);
	}

	public void mostrarCurvas(){
		for (curva c : curvas){
			c.mostrarDatos();
		}
	}

	public void setCurva(ArrayList<curva> curvas2) {
		// TODO Auto-generated method stub

	}
}
