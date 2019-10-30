package map.structure;

import util.Position;

public class Field {

    // x and y Coordinates
    private int xCoord;
    private int yCoord;
    private int sizeInPx;

    // Enum for Field type
    private FieldDescription fieldDescription;

    //Constructor
    public Field(int sizeInPx, int xCoord, int yCoord, FieldDescription fieldDescription) {
    	this.sizeInPx=sizeInPx;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.fieldDescription = fieldDescription;
    }

    //Constructor only for dummy objects
    public Field() {
    	this.sizeInPx=0;
        this.xCoord = 0;
        this.yCoord = 0;
        this.fieldDescription = FieldDescription.FREE;
    }

    /**
     *
     * returns the y-Position of the Field in the map
     */
    public int getFieldPositionY() {

        return yCoord;
    }

    /**
     *
     * returns the x-Positon of the Field in the map
     */
    public int getFieldPositionX() {

        return xCoord;
    }

    /**
     * returns the real x-Coordinate
     *
     */
    public int getPixelCoordX() {
    	return xCoord*sizeInPx;
    }

    /**
     * returns the real y-Coordinate
     *
     */
    public int getPixelCoordY() {
    	return yCoord*sizeInPx*-1;
    }

    public FieldDescription getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(FieldDescription fieldDescription){
        this.fieldDescription = fieldDescription;
    }
    
    public Position getSpawnPoint() {
    	return new Position(getPixelCoordX()+(sizeInPx/2), getPixelCoordY()-(sizeInPx/2));
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldDescription == null) ? 0 : fieldDescription.hashCode());
		result = prime * result + sizeInPx;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (fieldDescription != other.fieldDescription)
			return false;
		if (sizeInPx != other.sizeInPx)
			return false;
		if (xCoord != other.xCoord)
			return false;
		if (yCoord != other.yCoord)
			return false;
		return true;
	}
}
