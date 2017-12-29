package data;

import java.io.Serializable;
import java.sql.Date;


/**
 * VaskeTid klassen er en klasse der skal fungere som et bindeled imellem Vasketavlen og en reservation.
 * VaskeTavlen skal fyldes op med vasketider, med eller uden reservationer.
 * @author KimdR
 *
 */
public class VaskeTid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8087778595446423144L;
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	private Date dato;
	private VaskeBlok vaskeBlok;
	private Reservation reservation = null;
	

	public VaskeBlok getVaskeBlok() {
		return vaskeBlok;
	}

	public void setVaskeBlok(VaskeBlok vaskeBlok) {
		this.vaskeBlok = vaskeBlok;
	}


	
	public VaskeTid(){
		
	}
	
	public VaskeTid(Date dato, int tavleId, VaskeBlok vaskeBlok){
		this.dato = dato;
		this.vaskeBlok = vaskeBlok;
 
	}

	public Date getDato() {
		return dato;
	}


	public void setDato(Date dato) {
		this.dato = dato;
	}

	
	
}
