package holecym.model;

import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Michal on 6. 3. 2017.
 */
public class Production {
    private Map<Appliance, TreeSet<ProductionModel>> data;
    private Map<String, Integer> totalProducedItems;

    public Map<Appliance, TreeSet<ProductionModel>> getData() {
        return data;
    }

    public void setData(Map<Appliance, TreeSet<ProductionModel>> data) {
        this.data = data;
    }

    public Map<String, Integer> getTotalProducedItems() {
        return totalProducedItems;
    }

    public void setTotalProducedItems(Map<String, Integer> totalUsage) {
        this.totalProducedItems = totalUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) return false;
        return getTotalProducedItems() != null ? getTotalProducedItems().equals(that.getTotalProducedItems()) : that.getTotalProducedItems() == null;
    }

    @Override
    public int hashCode() {
        int result = getData() != null ? getData().hashCode() : 0;
        result = 31 * result + (getTotalProducedItems() != null ? getTotalProducedItems().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Production{" +
                "data=" + data +
                ", totalProducedItems=" + totalProducedItems +
                '}';
    }
}
