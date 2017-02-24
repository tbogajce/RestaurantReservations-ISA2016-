package netgloo.models;

public class TablePrint {

	
	public Long generalTableID;
	public boolean occupied;
	public int positionX;
	public int positionY;
	public int areaSizeX;
	public int areaSizeY;
	public Long resTableID;
	public Long areaID;
	//public int guestsOrder;
	
	
	
	
	
	
	/*
	public int getGuestsOrder() {
		return guestsOrder;
	}
	public void setGuestsOrder(int guestsOrder) {
		this.guestsOrder = guestsOrder;
	}*/
	
	public Long getResTableID() {
		return resTableID;
	}
	public void setResTableID(Long resTableID) {
		this.resTableID = resTableID;
	}
	public Long getAreaID() {
		return areaID;
	}
	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}
	public Long getGeneralTableID() {
		return generalTableID;
	}
	public void setGeneralTableID(Long generalTableID) {
		this.generalTableID = generalTableID;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public int getAreaSizeX() {
		return areaSizeX;
	}
	public void setAreaSizeX(int areaSizeX) {
		this.areaSizeX = areaSizeX;
	}
	public int getAreaSizeY() {
		return areaSizeY;
	}
	public void setAreaSizeY(int areaSizeY) {
		this.areaSizeY = areaSizeY;
	}
	public TablePrint(Long generalTableID, boolean occupied, int positionX, int positionY, int areaSizeX,
			int areaSizeY, Long resTableID, Long areaID/*, int guestsOrder*/) {
		super();
		this.generalTableID = generalTableID;
		this.occupied = occupied;
		this.positionX = positionX;
		this.positionY = positionY;
		this.areaSizeX = areaSizeX;
		this.areaSizeY = areaSizeY;
		this.resTableID=resTableID;
		this.areaID=areaID;
		/*this.guestsOrder=guestsOrder;*/
	}
	
	
	
	
	public TablePrint()
	{
		super();
	}
	
	
	
	
}
