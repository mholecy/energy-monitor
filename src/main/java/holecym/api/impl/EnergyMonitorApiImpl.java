package holecym.api.impl;

import holecym.api.EnergyMonitorApi;
import holecym.dao.ConsumptionDao;
import holecym.model.Appliance;
import holecym.model.Consumption;
import holecym.model.ConsumptionModel;
import holecym.model.Production;
import holecym.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Michal on 5. 3. 2017.
 */
public class EnergyMonitorApiImpl implements EnergyMonitorApi {

    private static final String SQL_CONSUMPTION_QUERY_TEMPLATE =
            "SELECT [MON].[Datetime]\n" +
                    ",[MON].[odometervalue]\n" +
                    ",[MON].[Datetimestamp]\n" +
                    ",[MER].[Meridlo]\n" +
                    ",[MER].[Units]\n" +
                    ",[MON].[parentid]\n" +
                    "FROM [TEST].[dbo].[tbl_monitor] [MON]\n" +
                    "LEFT OUTER JOIN [TEST].[dbo].[tbl_merice] [MER]\n" +
                    "ON [MON].[parentid] = [MER].[Parent_ID]\n";

    private ConsumptionDao consumptionDao;

    public EnergyMonitorApiImpl() {
        consumptionDao = new ConsumptionDao();
    }

    @Override
    public Consumption getConsumptionData(LocalDateTime dateFrom, LocalDateTime dateTo, Appliance... appliances) {
        final Consumption consumption = new Consumption();
        final List<String> queries = new ArrayList<>();

        for (Appliance appliance : appliances) {
            final String sqlQuery = getSqlQueryForComsumptionPerAppliance(dateFrom, dateTo, appliance);
            queries.add(sqlQuery);
        }

        final Map<Appliance, Set<ConsumptionModel>> measureValues = consumptionDao.getMeasureValues(queries, appliances);
        consumption.setData(measureValues);
        consumption.setTotalUsage(getTotalConsumption(measureValues, appliances));
        return consumption;
    }

    @Override
    public Production getProducedItemsCountData(LocalDateTime dateFrom, LocalDateTime dateTo, String... assembleLine) {
        return null;
    }

    private String getSqlQueryForComsumptionPerAppliance(LocalDateTime dateFrom, LocalDateTime dateTo, Appliance appliance) {
        StringBuilder stringBuilder = new StringBuilder(SQL_CONSUMPTION_QUERY_TEMPLATE);
        stringBuilder.append("WHERE [MON].[Datetimestamp]  BETWEEN '")
                .append(DateUtils.formatDateTime(dateFrom)).append("' ")
                .append("AND '")
                .append(DateUtils.formatDateTime(dateTo)).append("'\n")
                .append("AND ").append("[MER].[Meridlo] = '").append(appliance.getName()).append("' ")
                .append("AND ").append("[MER].[Path] = '").append(appliance.getPath()).append("' ")
                .append("AND ").append("[MER].[Units] = '").append(appliance.getUnit()).append("'\n")
                .append("AND [MON].[odometervalue] > 0.0");
        return stringBuilder.toString();
    }

    private Map<String, Double> getTotalConsumption(Map<Appliance, Set<ConsumptionModel>> measuresData, Appliance[] appliances) {
        final Map<String, Double> result = new HashMap<>();
        double initialValue;
        for (Appliance appliance : appliances) {

        }
        return null;
    }
}
