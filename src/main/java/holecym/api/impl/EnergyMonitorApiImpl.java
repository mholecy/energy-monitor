package holecym.api.impl;

import holecym.api.EnergyMonitorApi;
import holecym.dao.ConsumptionDao;
import holecym.model.Consumption;
import holecym.model.ConsumptionModel;
import holecym.model.Production;
import holecym.utils.DateUtils;

import java.util.*;

/**
 * Created by Michal on 5. 3. 2017.
 */
public class EnergyMonitorApiImpl implements EnergyMonitorApi {

    private static final String SQL_CONSUMPTION_QUERY_TEMPLATE =
            "SELECT TOP (1000) [MON].[Datetime]\n" +
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
    public Consumption getConsumptionData(Date dateFrom, Date dateTo, String... appliances) {
        final Consumption consumption = new Consumption();
        final List<String> queries = new ArrayList<>();

        for (String appliance : appliances) {
            final String sqlQuery = getSqlQueryForComsumptionPerAppliance(dateFrom, dateTo, appliance);
            queries.add(sqlQuery);
        }
        final Map<String, Set<ConsumptionModel>> measureValues = consumptionDao.getMeasureValues(queries, appliances);
        consumption.setData(measureValues);
        consumption.setTotalUsage(getTotalConsumption(measureValues, appliances));
        return consumption;
    }

    @Override
    public Production getProducedItemsCountData(Date dateFrom, Date dateTo, String... assembleLine) {
        return null;
    }

    private String getSqlQueryForComsumptionPerAppliance(Date dateFrom, Date dateTo, String appliance) {
        StringBuilder stringBuilder = new StringBuilder(SQL_CONSUMPTION_QUERY_TEMPLATE);
        stringBuilder.append("WHERE [MON].[Datetimestamp]  BETWEEN '")
                .append(DateUtils.formatDateTime(dateFrom)).append("' ")
                .append("AND '")
                .append(DateUtils.formatDateTime(dateTo)).append("'\n")
                .append("AND ")
                .append("[MER].[Meridlo] = '").append(appliance).append("'\n")
                .append("AND [MON].[odometervalue] > 0.0");
        return stringBuilder.toString();
    }

    private Map<String, Double> getTotalConsumption(Map<String, Set<ConsumptionModel>> measuresData, String[] appliances) {
        final Map<String, Double> result = new HashMap<>();
        double initialValue;
        for (String appliance : appliances) {

        }
        return null;
    }
}
