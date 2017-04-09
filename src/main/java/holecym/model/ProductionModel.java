package holecym.model;

import holecym.utils.DateUtils;

import java.util.Date;

/**
 * Created by Michal on 6. 3. 2017.
 */
public class ProductionModel implements Comparable<ProductionModel> {

    private String line;
    private Integer unitsAssembled;
    private String appliance;
    private Date date;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Integer getUnitsAssembled() {
        return unitsAssembled;
    }

    public void setUnitsAssembled(Integer unitsAssembled) {
        this.unitsAssembled = unitsAssembled;
    }

    public String getAppliance() {
        return appliance;
    }

    public void setAppliance(String appliance) {
        this.appliance = appliance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductionModel that = (ProductionModel) o;

        if (getLine() != null ? !getLine().equals(that.getLine()) : that.getLine() != null) return false;
        if (getUnitsAssembled() != null ? !getUnitsAssembled().equals(that.getUnitsAssembled()) : that.getUnitsAssembled() != null)
            return false;
        if (getAppliance() != null ? !getAppliance().equals(that.getAppliance()) : that.getAppliance() != null)
            return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getLine() != null ? getLine().hashCode() : 0;
        result = 31 * result + (getUnitsAssembled() != null ? getUnitsAssembled().hashCode() : 0);
        result = 31 * result + (getAppliance() != null ? getAppliance().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductionModel{" +
                "line='" + line + '\'' +
                ", unitsAssembled=" + unitsAssembled +
                ", appliance='" + appliance + '\'' +
                ", date=" + DateUtils.formatDate(date) +
                '}';
    }

    @Override
    public int compareTo(ProductionModel o) {
        if (this.getDate().after(o.getDate())) return 1;
        if (this.getDate().before(o.getDate())) return -1;
        if (this.getDate().equals(o.getDate())) return 0;
        return 0;
    }
}
