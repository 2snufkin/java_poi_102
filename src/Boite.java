public class Boite {
    private String name;

    private Integer position;
    private String alias;
    private String color;

    public Boite(String name, String alias, String color, Integer position) {
        this.position = position;
        this.name = name;
        this.alias = alias;
        this.color = color;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
