package data;

public class VaskeBlok {

	private int boligforeningId;
	private int blokID;
	private int startTid;


	public VaskeBlok(int boligforeningId, int blokID, int startTid){
		this.boligforeningId = boligforeningId;
		this.blokID = blokID;
		this.startTid = startTid;
	}


	public int getBoligforeningId() {
		return boligforeningId;
	}


	public void setTavleID(int boligforeningId) {
		this.boligforeningId = boligforeningId;
	}


	public int getBlokID() {
		return blokID;
	}
	public void setBlokID(int blokID) {
		this.blokID = blokID;
	}
	public int getStartTid() {
		return startTid;
	}
	public void setStartTid(int startTid) {
		this.startTid = startTid;
	}
}
