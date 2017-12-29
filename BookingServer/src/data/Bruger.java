package data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "bruger") 
public class Bruger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241553341318566499L;
	private int brugerID;
	private int boligForeningID;
	private int ANTAL_NOEGLER;
	private int brugteNoegler;
	private String navn;

/**
 * En general konstrukt�r der skaber objekter til afsendelse
 * @param brugerID
 * @param boligForeningID
 * @param navn
 * @param ANTAL_NOEGLER
 */
	public Bruger(int brugerID, int boligForeningID ,String navn, int ANTAL_NOEGLER){
		this.brugerID = brugerID;
		this.boligForeningID = boligForeningID;
		this.ANTAL_NOEGLER = ANTAL_NOEGLER;
		this.navn = navn;
		}

	public Bruger(){ }
	
	/**
	 * Konstrukt�r der bruges til at oprette objekter til lagring i database
	 * @param boligForeningID
	 * @param navn
	 * @param ANTAL_NOEGLER
	 */
	public Bruger(int boligForeningID ,String navn, int ANTAL_NOEGLER){
		this.boligForeningID = boligForeningID;
		this.ANTAL_NOEGLER = ANTAL_NOEGLER;
		this.navn = navn;
	}

	public int getBrugerID() {
		return brugerID;
	}

	@XmlElement 
	public void setBrugerID(int brugerID) {
		this.brugerID = brugerID;
	}


	public int getBoligForeningID() {
		return boligForeningID;
	}

	@XmlElement 
	public void setBoligForeningID(int boligForeningID) {
		this.boligForeningID = boligForeningID;
	}


	public int getBrugteNoegler() {
		return brugteNoegler;
	}

	@XmlElement 
	public void setBrugteNoegler(int brugteNoegler) {
		this.brugteNoegler = brugteNoegler;
	}


	public String getNavn() {
		return navn;
	}

	@XmlElement 
	public void setNavn(String navn) {
		this.navn = navn;
	}


	public int getANTAL_NOEGLER() {
		return ANTAL_NOEGLER;
	}
}
