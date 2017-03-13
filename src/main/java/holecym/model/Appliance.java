package holecym.model;

/**
 * Created by Michal on 13. 3. 2017.
 */
public class Appliance {

    private int id;
    private String name;
    private String unit;
    private String cc;
    private String path;

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

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appliance appliance = (Appliance) o;

        if (getId() != appliance.getId()) return false;
        if (getName() != null ? !getName().equals(appliance.getName()) : appliance.getName() != null) return false;
        if (getUnit() != null ? !getUnit().equals(appliance.getUnit()) : appliance.getUnit() != null) return false;
        if (getCc() != null ? !getCc().equals(appliance.getCc()) : appliance.getCc() != null) return false;
        return getPath() != null ? getPath().equals(appliance.getPath()) : appliance.getPath() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getCc() != null ? getCc().hashCode() : 0);
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " [" + unit + "]";
    }
}
