package holecym.api.impl;

import holecym.api.EnergyMonitorApi;
import holecym.dao.DataAccessObject;
import holecym.dao.converters.ConsumptionModelConverter;
import holecym.dao.converters.DtoConverter;
import holecym.dao.converters.ProductionModelConverter;
import holecym.model.Appliance;
import holecym.model.Consumption;
import holecym.model.ConsumptionModel;
import holecym.model.Production;
import holecym.model.ProductionModel;
import holecym.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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

    private static final String SQL_PRODUCTION_QUERY_TEMPLATE =
            "SELECT [Segment]\n" +
                    ",[Dátum]\n" +
                    ",[Linka]\n" +
                    ",[Stroj]\n" +
                    ",[pocet_kusov]\n" +
                    ",[vyr_linka]\n" +
                    ",[Dátum1]\n" +
                    "FROM [TEST].[dbo].[Data$]\n";

    private DataAccessObject<ConsumptionModel> consumptionDao;
    private DataAccessObject<ProductionModel> productionDao;

    public EnergyMonitorApiImpl() {
        consumptionDao = new DataAccessObject<>();
        productionDao = new DataAccessObject<>();
    }

    @Override
    public Consumption getConsumptionData(LocalDateTime dateFrom, LocalDateTime dateTo,
                                          Appliance... appliances) {
        final Consumption consumption = new Consumption();
        final List<String> queries = new ArrayList<>();

        for (Appliance appliance : appliances) {
            final String sqlQuery = getSqlQueryForComsumptionPerAppliance(dateFrom, dateTo, appliance);
            queries.add(sqlQuery);
        }

        DtoConverter<ConsumptionModel> dtoConverter = new ConsumptionModelConverter();
        final Map<Appliance, TreeSet<ConsumptionModel>> measureValues = consumptionDao.getQuery
                (queries, appliances, dtoConverter);
        consumption.setData(measureValues);
        consumption.setTotalUsage(getTotalConsumption(measureValues));
        return consumption;
    }

    @Override
    public Production getProducedItemsCountData(LocalDateTime dateFrom, LocalDateTime dateTo,
                                                Appliance... appliances) {
        final Production production = new Production();
        final List<String> queries = new ArrayList<>();

        for (Appliance appliance : appliances) {
            final String sqlQuery = getSqlQueryForProductionPerAppliance(dateFrom, dateTo, appliance);
            queries.add(sqlQuery);
        }

        DtoConverter<ProductionModel> dtoConverter = new ProductionModelConverter();
        final Map<Appliance, TreeSet<ProductionModel>> measureValues = productionDao.getQuery
                (queries, appliances, dtoConverter);
        production.setData(measureValues);
        production.setTotalProducedItems(getTotalProducedItems(measureValues));
        return production;
    }

    private String getSqlQueryForProductionPerAppliance(LocalDateTime dateFrom, LocalDateTime dateTo,
                                                        Appliance appliance) {
        final StringBuilder stringBuilder = new StringBuilder(SQL_PRODUCTION_QUERY_TEMPLATE);
        stringBuilder.append(" WHERE [Dátum1] BETWEEN '")
                .append(DateUtils.formatDateTime(dateFrom)).append("' ")
                .append("AND '")
                .append(DateUtils.formatDateTime(dateTo)).append("'\n")
                .append("AND ").append("[vyr_linka] = '").append(appliance.getName()).append("'");
        return stringBuilder.toString();
    }

    private String getSqlQueryForComsumptionPerAppliance(LocalDateTime dateFrom, LocalDateTime dateTo,
                                                         Appliance appliance) {
        final StringBuilder stringBuilder = new StringBuilder(SQL_CONSUMPTION_QUERY_TEMPLATE);
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

    private Map<String, Double> getTotalConsumption(Map<Appliance, TreeSet<ConsumptionModel>> measuresData) {
        final Map<String, Double> result = new HashMap<>();

        measuresData.forEach((Appliance key, TreeSet<ConsumptionModel> consumptionModels) -> {
            if (consumptionModels != null && !consumptionModels.isEmpty()) {
                final ConsumptionModel first = consumptionModels.first();
                final ConsumptionModel last = consumptionModels.last();
                final double totalConsumption = last.getUsage() - first.getUsage();
                result.put(key.toString(), totalConsumption);
            }
        });
        return result;
    }

    private Map<String, Integer> getTotalProducedItems(
            Map<Appliance, TreeSet<ProductionModel>> measuresData) {

        final Map<String, Integer> result = new HashMap<>();
        measuresData.forEach((Appliance key, TreeSet<ProductionModel> productionModels) -> {
            if (productionModels != null && !productionModels.isEmpty()) {
                final int sum = productionModels.stream().mapToInt(ProductionModel::getUnitsAssembled).sum();
                result.put(key.toString(), sum);
            }
        });
        return result;
    }
}
