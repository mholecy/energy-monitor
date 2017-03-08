package holecym.model;

import holecym.utils.DateUtils;

import java.util.Date;

/**
 * Created by Michal on 17. 1. 2017.
 */
public class ConsumptionModel {

    private int id;
    private String name;
    private String unit;
    private Date datetime;
    private double usage;

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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsumptionModel consumptionModel = (ConsumptionModel) o;

        if (getId() != consumptionModel.getId()) return false;
        if (Double.compare(consumptionModel.getUsage(), getUsage()) != 0) return false;
        if (getName() != null ? !getName().equals(consumptionModel.getName()) : consumptionModel.getName() != null)
            return false;
        if (getUnit() != null ? !getUnit().equals(consumptionModel.getUnit()) : consumptionModel.getUnit() != null)
            return false;
        return getDatetime() != null ? getDatetime().equals(consumptionModel.getDatetime()) : consumptionModel.getDatetime() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getDatetime() != null ? getDatetime().hashCode() : 0);
        temp = Double.doubleToLongBits(getUsage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ConsumptionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", datetime=" + DateUtils.formatDateTime(datetime) +
                ", usage=" + usage +
                '}';
    }
}
