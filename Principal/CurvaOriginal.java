package principal;

import java.util.ArrayList;
import java.util.List;

import interfaz.BDConnection;

public class CurvaOriginal implements curva {
    private List<parIV> pts;
    
    private int idCurva;
	
	private String modName;
    
    private double Isc;
    private double Voc;
    private double Pmax;
    private double IPmax;
    private double VPmax;
    private double FF;
    
    private double temp;
    private double irr;
    
    private String fecha;

   public CurvaOriginal(double isc, double voc, double pmax,double ipmax, double vpmax, double ff, List<parIV> PTS,String date,double t,double i,String mod) throws ClassNotFoundException {

		this.fecha = date;
		this.FF = ff;
		this.Pmax=pmax;
		this.IPmax = ipmax;
		this.Isc = isc;
		this.pts = PTS;
		this.Voc = voc;
		this.VPmax = vpmax;
		this.temp = t;
		this.irr = i;
	   this.modName=mod;
	   int idCurva = nuevoID();
		BDConnection baseDatos = new BDConnection();
		//baseDatos.Insert("INSERT INTO CURVAORIGINAL VALUES("+nuevoID()+",'"+date+"',"+isc+","+voc+","+pmax+","+ipmax+","+vpmax+","+ff+","+t+","+i+",'"+mod+"');");
		baseDatos.Insert("INSERT INTO CURVAORIGINAL VALUES("+idCurva+",'"+date+"',"+isc+","+voc+","+pmax+","+ipmax+","+vpmax+","+ff+","+t+","+i+",'"+mod+"');");
		//hay que dar de alta en la base de datos a los puntos
		for(parIV par : PTS) {
			baseDatos.Insert("INSERT INTO ParIV VALUES ("+nuevoIDiv()+", "+par.getIntensidad()+", "+par.getVoltaje()+", "+idCurva+") ;");
		}

	}
    
    
    	public CurvaOriginal(int i) throws ClassNotFoundException {
		// dado un id carga el objeto de la base de datos;
    	idCurva = i;
    	
		BDConnection baseDatos = new BDConnection();
		for(Object[] elemento : baseDatos.Select("SELECT * FROM curvaOriginal WHERE idCurva = "+i+";")){

			this.fecha = elemento[1].toString();
			this.Isc = Double.parseDouble(elemento[2].toString());
			this.Voc = Double.parseDouble(elemento[3].toString());
			this.Pmax = Double.parseDouble(elemento[4].toString());
			this.IPmax = Double.parseDouble(elemento[5].toString());
			this.VPmax = Double.parseDouble(elemento[6].toString());
			this.FF = Double.parseDouble(elemento[7].toString());
			this.temp = Double.parseDouble(elemento[8].toString());
			this.irr = Double.parseDouble(elemento[9].toString());
			this.modName = elemento[10].toString();
		}
		//obtener los puntos de la curva
		List<parIV> list = new ArrayList<parIV>();
		for(Object[] elemento : baseDatos.Select("SELECT * FROM parIV WHERE curvaOriginal_idCurva = "+i+" ;")) {
			parIV piv = new parIV(Double.parseDouble(elemento[1].toString())
									, Double.parseDouble(elemento[2].toString()));
			list.add(piv);
		}
		this.pts = list;
	}

    
	public double getTemp() {
		return temp;
	}


	public void setTemp(double temp) {
		this.temp = temp;
	}


	public double getIrr() {
		return irr;
	}


	public void setIrr(double irr) {
		this.irr = irr;
	}


	public List<parIV> getPts() {
		return pts;
	}

	public void setPts(List<parIV> pts) {
		this.pts = pts;
	}

	public double getIsc() {
		return Isc;
	}

	public void setIsc(double isc) {
		Isc = isc;
	}

	public double getVoc() {
		return Voc;
	}

	public void setVoc(double voc) {
		Voc = voc;
	}

	public double getPmax() {
		return Pmax;
	}

	public void setPmax(double pmax) {
		Pmax = pmax;
	}

	public double getIPmax() {
		return IPmax;
	}

	public void setIPmax(double iPmax) {
		IPmax = iPmax;
	}

	public double getVPmax() {
		return VPmax;
	}

	public void setVPmax(double vPmax) {
		VPmax = vPmax;
	}

	public double getFF() {
		return FF;
	}

	public void setFF(double fF) {
		FF = fF;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void mostrarDatos(){
        int i = 0;
        for (parIV p : pts){
            System.out.println("PARIV" + i + "[Intensidad = " + p.getIntensidad() + "] ; [Voltaje = " + p.getVoltaje() + "]");
            i++;
        }
    }


	public int getIdCurva() {
		return idCurva;
	}


	public void setIdCurva(int idCurva) {
		this.idCurva = idCurva;
	}
	
	private int nuevoID() throws ClassNotFoundException{
		BDConnection baseDatos = new BDConnection();
		List<Object[]> listaID = baseDatos.Select("SELECT IDCURVA FROM CURVAORIGINAL;");
		int max = 0;
		for(Object[] aux : listaID){
			if(max < (int) aux[0]){
				max = (int) aux[0];
			}
		}
		return (max+1);
	}
	private int nuevoIDiv() throws ClassNotFoundException{
		BDConnection baseDatos = new BDConnection();
		List<Object[]> listaID = baseDatos.Select("SELECT iDParIV FROM ParIV;");
		int max = 0;
		for(Object[] aux : listaID){
			if(max < (int) aux[0]){
				max = (int) aux[0];
			}
		}
		return (max+1);
	}
}

