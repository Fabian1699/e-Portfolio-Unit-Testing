package map.structure;

public enum FieldDescription {

    FREE("Free"),
    TOWER("Tower"),
    PATH("Path");

    private String label;

    FieldDescription(String label) {

        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

}
