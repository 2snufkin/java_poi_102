import java.util.List;
import java.util.ArrayList;

public class Container {
    private String name;
    private String description;
    private List<Enceinte> enceintes;

    public Container(String name) {
        this.name = name;
        this.enceintes = new ArrayList<>();
    }




// Getters and setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Enceinte> getEnceintes() {
        return enceintes;
    }

    public void setEnceintes(List<Enceinte> enceintes) {
        this.enceintes = enceintes;
    }

    // Example of adding containers
    public void addEnceinte(Enceinte enceinte) {
        this.enceintes.add(enceinte);
    }




}
