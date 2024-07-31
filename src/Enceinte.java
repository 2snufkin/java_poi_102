import java.util.List;
import java.util.ArrayList;

public class Enceinte {
    private String name;
    private String alias;

    private Integer position;

    private String color;
    private List<Enceinte> subEnceintes;
    private List<Boite> boites;

    public Enceinte(String name, String alias, String color,  Integer position) {
        this.name = name;
        this.alias = alias;
        this.color = color;
        this.subEnceintes = new ArrayList<>();
        this.position = position;
        this.boites = new ArrayList<>();
    }

    public List<Boite> getBoites() {
        return boites;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setBoites(List<Boite> boites) {
        this.boites = boites;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
// Getters and setters

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

    public List<Enceinte> getSubEnceintes() {
        return subEnceintes;
    }

    public void setSubEnceintes(List<Enceinte> subEnceintes) {
        this.subEnceintes = subEnceintes;
    }

    // Example of adding containers
    public void addSubEnceinte(Enceinte enceinte) {
        this.subEnceintes.add(enceinte);
    }

    public void addBoite(Boite boite) {
        this.boites.add(boite);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------" + "\n");
        sb.append("Enceinte: ").append(name)
                .append(" (Position: ").append(position).append(")\n");

        // Add details for each Boite
        if (boites != null && !boites.isEmpty()) {
            sb.append("  Boites:\n");
            for (Boite boite : boites) {
                sb.append("    - ").append(boite.getName())
                        .append(" (Position: ").append(boite.getPosition()).append(")\n");
            }
        } else {
            sb.append("  No Boites\n");
        }

        // Optionally, add details for subEnceintes
        if (subEnceintes != null && !subEnceintes.isEmpty()) {
            sb.append("  Sub-Enceintes:\n");
            for (Enceinte subEnceinte : subEnceintes) {
                sb.append("Name " + subEnceinte.getName() + "\n");
                sb.append("Alias " + subEnceinte.getAlias()+ "\n");
                sb.append("Position " + subEnceinte.getPosition()+ "\n");
                sb.append("*****************");
            }
        } else {
            sb.append("  No Sub-Enceintes\n");
        }

        return sb.toString();
    }

}
