package map.structure;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import util.Position;


public class MapStructure {
	private Map<String, Field> fields = new HashMap<String, Field>();
	private static final int sizeField = 80;
	private static final int rows = 6;
	private static final int columns = 11;
    private static final Position[] path = new Position[]{
            new Position(0,2), new Position(1,2), new Position(1,1),
            new Position(1,0), new Position(2,0), new Position(3,0),
            new Position(4,0), new Position(4,1), new Position(4,2),
            new Position(3,2), new Position(3,3), new Position(3,4),
            new Position(4,4), new Position(5,4), new Position(6,4),
            new Position(6,3), new Position(6,2), new Position(7,2),
            new Position(8,2), new Position(9,2), new Position(10,2)};

    // Constructor
    public MapStructure(){
        FieldDescription[][] fieldDescription = createPath();
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
            	Position pos = new Position(x,y);
                fields.put(pos.toString(), new Field(sizeField, x, y, fieldDescription[x][y]));
            }
        }
    }

    // sets the FieldDescription for the Map (says which Field belongs to the Path and which not)
    private FieldDescription[][] createPath(){

        // There is only one fix Path with the following Coordinates
        FieldDescription[][] fieldDescription = new FieldDescription[columns][rows];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                for (Position pos : path){
                    if(pos.equals(new Position(i,j))) {
                        fieldDescription[i][j] = FieldDescription.PATH;
                        break;
                    } else {
                        fieldDescription[i][j] = FieldDescription.FREE;
                    }
                }
            }
        }
        return fieldDescription;
    }
    
    public List<Field> getFields(){
    	return new ArrayList<Field>(fields.values());
    }

    /**
     *
     * @param progress
     * @return Field where the enemy should move to
     */
    public Field getFieldForEnemy(int progress){
        return fields.get(path[progress].toString());
    }
    
    public Field getField(Position pos) {
    	return fields.get(pos.toString());
    }
    
    public int getSizeField() {
    	return sizeField;
    }
}
