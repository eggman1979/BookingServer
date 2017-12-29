package data;

import java.io.Serializable;


public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3827982888483436674L;
	private int brugerID;
	public int getBrugerID() {
		return brugerID;
	}

	public void setBrugerID(int brugerID) {
		this.brugerID = brugerID;
	}

	public int getVaskeBlokID() {
		return vaskeBlokID;
	}

	public void setVaskeBlokID(int vaskeBlokID) {
		this.vaskeBlokID = vaskeBlokID;
	}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	private long dato;
	private int vaskeBlokID;
	private int boligforeningID;
	private int reservationID;
	private int tavleID;
	
	private long tilfoejetDato; 

	
	

	public Reservation(){};
	
	public Reservation(int brugerID , long dato, int vaskeBlokID, int boligforeningID, int tavleID, long tilfoejetDato) {
		this.brugerID = brugerID;
		this.dato = dato;
		this.vaskeBlokID = vaskeBlokID;
		this.boligforeningID = boligforeningID;
		this.tavleID = tavleID;
		this.tilfoejetDato = tilfoejetDato;
	}

	public Reservation(int reservationID, int brugerID , long dato, int vaskeBlokID,int boligforeningID, int tavleID, long tilfoejetDato) {
		this.reservationID = reservationID;
		this.brugerID = brugerID;
		this.dato = dato;
		this.vaskeBlokID = vaskeBlokID;
		this.boligforeningID = boligforeningID;
		this.tavleID = tavleID;
		this.tilfoejetDato = tilfoejetDato;
	}


	public void setBruger(int brugerID) {
		this.brugerID = brugerID;
	}
	public long getDato() {
		return dato;
	}
	public void setDato(long dato) {
		this.dato = dato;
	}
	public int getvaskeBlokID() {
		return vaskeBlokID;
	}
	public void setvaskeBlokID(int vaskeBlokID) {
		this.vaskeBlokID = vaskeBlokID;
	}
	public int getTavleID() {
		return tavleID;
	}
	public void setTavleID(int tavleID) {
		this.tavleID = tavleID;
	}
	public int getBoligforeningID() {
		return boligforeningID;
	}

	public void setBoligforeningID(int boligforeningID) {
		this.boligforeningID = boligforeningID;
	}
	
	public long getTilfoejetDato() {
		return tilfoejetDato;
	}

	public void setTilfoejet_dato(long tilfoejetDato) {
		this.tilfoejetDato = tilfoejetDato;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Reservation)){
			return false;
		}
		Reservation newRes = (Reservation) obj;
		if(newRes.boligforeningID == this.boligforeningID && this.getDato() == newRes.getDato() && this.getvaskeBlokID() == newRes.getVaskeBlokID() && this.getTavleID() == newRes.getTavleID()){
			return true;
		}
		
		return false;
	}

}
