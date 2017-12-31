package principal;

import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import interfaz.BDConnection;

public class CurvaCorregida implements curva {

	private CurvaOriginal origen; // Almaceno a que curva esta asociada esta curva corregida

	private int idCurvaCorregida;
	private double isc;
	private double voc;
	private double pmax;
	private double ipmax;
	private double vpmax;
	private double ff;
	private TreeMap<Double,Double> puntos;

	public CurvaCorregida(CurvaOriginal c,double i,double v,double pm,double ip,double vp,double f,String intens,String volt) throws ClassNotFoundException{
		this.origen=c; // c es la clave primaria de CurvaOriginal (fechaHoraCurva)
		this.isc = i;
		this.voc=v;
		this.pmax=pm;
		this.ipmax=ip;
		this.vpmax=vp;
		this.ff=f;

		int nuevoId = nuevoID(c.getFechaHora());
		
		// Creacion del Map 
		puntos = new TreeMap<>();

		Scanner sIn = new Scanner(intens);
		Scanner sVo = new Scanner(volt);
		sIn.useDelimiter(";");
		sVo.useDelimiter(";");

		while(sVo.hasNext()){
			double aux1 = Double.parseDouble(sIn.next());
			double aux2 = Double.parseDouble(sVo.next());
			puntos.put(aux2, aux1);
		}

		sIn.close();
		sVo.close();

		BDConnection miBD = new BDConnection();
		miBD.Insert("insert into curvaCorregida values ("+nuevoId+","+isc+","+voc+","+pmax+","+ipmax+","+vpmax+","+ff+",'"+intens+"','"+volt+"','"+c.getFechaHora()+"','"+c.getCampName()+"','"+c.getModName()+"');");
	}
	public CurvaCorregida(int id) throws ClassNotFoundException{
		BDConnection miBD = new BDConnection();
		String in= "";//string con intensidades
		String vo= "";//string con voltajes
		for(Object[] elemento : miBD.Select("SELECT * FROM curvaCorregida WHERE idCurvaCorregida = "+id+" ;")){
			this.idCurvaCorregida = Integer.parseInt(elemento[0].toString());
			this.isc = Double.parseDouble(elemento[1].toString());
			this.voc = Double.parseDouble(elemento[2].toString());
			this.pmax = Double.parseDouble(elemento[3].toString());
			this.ipmax = Double.parseDouble(elemento[4].toString());
			this.vpmax = Double.parseDouble(elemento[5].toString());
			this.ff = Double.parseDouble(elemento[6].toString());
			in = elemento[7].toString();
			vo = elemento[8].toString();
			this.origen = new CurvaOriginal(elemento[7].toString(),elemento[9].toString());//el origen se accede con su hora y el nombre del modulo
		}
		// Creacion del Map 
		puntos = new TreeMap<>();

		Scanner sIn = new Scanner(in);
		Scanner sVo = new Scanner(vo);
		
		sIn.useDelimiter(";");
		sVo.useDelimiter(";");

		while(sVo.hasNext()){
			
			double c = Double.parseDouble(sIn.next());
			double v = Double.parseDouble(sVo.next());
			
			puntos.put(v, c);
		}
		

		sIn.close();
		sVo.close();
	}
	
	public int getIdCurvaCorregida() {
		return idCurvaCorregida;
	}
	public double getIsc() {
		return isc;
	}
	public double getVoc() {
		return voc;
	}
	public double getPmax() {
		return pmax;
	}
	public double getIpmax() {
		return ipmax;
	}
	public double getVpmax() {
		return vpmax;
	}
	public double getFf() {
		return ff;
	}

	public TreeMap<Double,Double> getPts() {
		return puntos;
	}

	public void setPts(String in,String vo) {
		Scanner sIn = new Scanner(in);
		Scanner sVo = new Scanner(vo);
		sIn.useDelimiter(";");
		sVo.useDelimiter(";");

		while(sVo.hasNext()){
			double c = Double.parseDouble(sIn.next());
			double v = Double.parseDouble(sVo.next());
			puntos.put(v, c);
		}

		sIn.close();
		sVo.close();
	}

	public void mostrarDatos(){
		Set<Entry<Double,Double>> set = puntos.entrySet();
		int i=0;
		for(Entry<Double,Double> aux : set){
			System.out.println("Curva Original: "+origen.getFechaHora()+" Campanya: "+origen.getCampName()+" Modulo: "+origen.getModName());
			System.out.println("PARIV" + i + "[Intensidad = " + aux.getValue() + "] ; [Voltaje = " + aux.getKey() + "]");
			i++;
		}
	}

	private int nuevoID(String s) throws ClassNotFoundException{
		BDConnection miBD = new BDConnection();
		int max=0;
		for(Object[] tupla : miBD.Select("Select idCurvaCorregida from curvaCorregida where curvaOriginal_fechaHoraCurva like '"+s+"';")){
			int aux = (int) tupla[0];
			if (max<aux) max=aux;
		}
		return max+1;
	}

}
