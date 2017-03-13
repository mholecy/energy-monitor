package holecym.model;

import java.util.Map;
import java.util.Set;

/**
 * Created by Michal on 6. 3. 2017.
 */
public class Consumption {

    private Map<Appliance, Set<ConsumptionModel>> data;
    private Map<String, Double> totalUsage;

    public Map<Appliance, Set<ConsumptionModel>> getData() {
        return data;
    }

    public void setData(Map<Appliance, Set<ConsumptionModel>> data) {
        this.data = data;
    }

    public Map<String, Double> getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(Map<String, Double> totalUsage) {
        this.totalUsage = totalUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consumption that = (Consumption) o;

        if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) return false;
        return getTotalUsage() != null ? getTotalUsage().equals(that.getTotalUsage()) : that.getTotalUsage() == null;
    }

    @Override
    public int hashCode() {
        int result = getData() != null ? getData().hashCode() : 0;
        result = 31 * result + (getTotalUsage() != null ? getTotalUsage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "data=" + data +
                ", totalUsage=" + totalUsage +
                '}';
    }
}
