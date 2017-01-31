package holecym.model;

import java.util.Date;

/**
 * Created by Michal on 17. 1. 2017.
 */
public class Monitor {

    private Date datetime;
    private int parentId;
    private double floatValue;

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public double getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(double floatValue) {
        this.floatValue = floatValue;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "datetime=" + datetime +
                ", parentId=" + parentId +
                ", floatValue=" + floatValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monitor monitor = (Monitor) o;

        if (parentId != monitor.parentId) return false;
        if (Double.compare(monitor.floatValue, floatValue) != 0) return false;
        return datetime != null ? datetime.equals(monitor.datetime) : monitor.datetime == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = datetime != null ? datetime.hashCode() : 0;
        result = 31 * result + parentId;
        temp = Double.doubleToLongBits(floatValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
