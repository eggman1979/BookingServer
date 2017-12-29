package data;

import java.io.Serializable;

public class VaskeTavle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6977445431778097356L;
	private int tavleID;
	private int boligForeningID;
	private int antalBlokkePerDag;

	public VaskeTavle(){};

	public VaskeTavle(int tavleID, int boligForeningID, int antalBlokkePerDag){
		this.tavleID = tavleID;
		this.boligForeningID = boligForeningID;
		this.antalBlokkePerDag = antalBlokkePerDag;	
	}

	public int getTavleID() {
		return tavleID;
	}

	public void setTavleID(int tavleID) {
		this.tavleID = tavleID;
	}

	public int getBoligForeningID() {
		return boligForeningID;
	}

	public void setBoligForeningID(int boligForeningID) {
		this.boligForeningID = boligForeningID;
	}


	public int getAntalBlokkePrDag() {
		return antalBlokkePerDag;
	}

	public void setAntalBlokkePrDag(int nyAntalBlokke){
		this.antalBlokkePerDag = nyAntalBlokke;
	}
}

