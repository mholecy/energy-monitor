package holecym.model;

/**
 * Created by Michal on 17. 1. 2017.
 */
public class Measures {

    private int id;
    private String name;
    private String unit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measures measures = (Measures) o;

        if (id != measures.id) return false;
        if (!name.equals(measures.name)) return false;
        return unit.equals(measures.unit);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + unit.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Measures{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
