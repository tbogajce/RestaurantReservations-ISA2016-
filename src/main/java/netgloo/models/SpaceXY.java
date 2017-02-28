package netgloo.models;

public class SpaceXY {
	
	int spaceX;
	
	int spaceY;

	public int getSpaceX() {
		return spaceX;
	}

	public void setSpaceX(int spaceX) {
		this.spaceX = spaceX;
	}

	public int getSpaceY() {
		return spaceY;
	}

	public void setSpaceY(int spaceY) {
		this.spaceY = spaceY;
	}

	public SpaceXY(int spaceX, int spaceY) {
		super();
		this.spaceX = spaceX;
		this.spaceY = spaceY;
	}
	
	
	public SpaceXY()
	{
		super();
	}

}
