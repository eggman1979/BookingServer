package data;

import java.io.Serializable;


public class BoligForening implements Serializable {
	 private static final long serialVersionUID = 1L; 

	private String navn;
	private  int id;
	private int antalTavler;

	public BoligForening(String navn,  int antalTavler){
		this.navn = navn;
		this.antalTavler = antalTavler;
	}
	public BoligForening(){}

	public BoligForening(String navn, int antalTavler, int id) {
		this.antalTavler = antalTavler;
		this.id = id;
		this.navn = navn;
	}
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getId() {
		return id;
	}
	

	public int getAntalTavler() {
		return antalTavler;
	}
	
	public void setAntalTavler(int antalTavler) {
		this.antalTavler = antalTavler;
	}

	
}
