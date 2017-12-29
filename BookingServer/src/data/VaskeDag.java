package data;

import java.util.List;

public class VaskeDag {

	List<VaskeTid> vasketider;


	int antalBlokke;
	
	public VaskeDag(int antalBlokke, List<VaskeTid> vasketider){
		this.antalBlokke = antalBlokke;
		this.vasketider = vasketider;
	}
	
	
	public boolean isVaskeDagLedig(){
		for (VaskeTid vaskeTid : vasketider) {
			if(vaskeTid.getReservation() != null){
				return true;
			}
		}
		return false;
	}
	
	public List<VaskeTid> getVasketider() {
		return vasketider;
	}


	public void setVasketider(List<VaskeTid> vasketider) {
		this.vasketider = vasketider;
	}


	public int getAntalBlokke() {
		return antalBlokke;
	}


	public void setAntalBlokke(int antalBlokke) {
		this.antalBlokke = antalBlokke;
	}

}
