package principal;

import java.util.ArrayList;

import interfaz.BDConnection;

public class Campanya {

	private Modulo mod; // Modulo al que pertenece la campanya

	private ArrayList<CurvaOriginal> curvas; // Curvas que tiene la campanya

	private String nombreCampanya;

	public Campanya(String n,Modulo m,ArrayList<CurvaOriginal> c) throws ClassNotFoundException{
		this.nombreCampanya=n;
		this.mod=m;
		this.curvas=c;

		BDConnection miBD = new BDConnection();
		miBD.Insert("insert into campanya values ('"+nombreCampanya+"','"+mod.getNombre()+"');");
	}

	public Campanya(String n,Modulo m) throws ClassNotFoundException{
		this.nombreCampanya=n;
		this.mod=m;
		curvas = new ArrayList<>();

		BDConnection miBD = new BDConnection();
		miBD.Insert("insert into campanya values ('"+nombreCampanya+"','"+mod.getNombre()+"');");
	}

	public ArrayList<CurvaOriginal> getCurvas(){
		return curvas;
	}

	public int addNewCurva(CurvaOriginal c){
		if(curvas.contains(c)){
			return 0; // Devuelve un 0 si ya esta en la lista
		}
		else {
			curvas.add(c);
			return 1; // Devuelve un 1 si no esta en la lista
		}
	}

}
