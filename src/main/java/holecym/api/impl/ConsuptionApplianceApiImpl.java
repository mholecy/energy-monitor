package holecym.api.impl;

import holecym.api.AppliancesApi;
import holecym.dao.DataAccessObject;
import holecym.dao.SimpleDao;
import holecym.dao.converters.ConsumptionApplianceConverter;
import holecym.dao.converters.DtoConverter;
import holecym.model.Appliance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Michal on 13. 3. 2017.
 */
public class ConsuptionApplianceApiImpl implements AppliancesApi {

    private static final String SQL_QUERY_FOR_APPLIANCES = "SELECT [Meridlo], [Units], [Parent_ID], [cc], [Path]" +
            " FROM [TEST].[dbo].[tbl_merice] ORDER BY [Meridlo]";

    private SimpleDao<Appliance> dataAccessObject;

    public ConsuptionApplianceApiImpl() {
        dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public List<Appliance> getAppliances() {
        DtoConverter<Appliance> dtoConverter = new ConsumptionApplianceConverter();
        final Set<Appliance> appliances = dataAccessObject
                .getSimpleQuery(SQL_QUERY_FOR_APPLIANCES, dtoConverter);
        return new ArrayList<>(appliances);
    }
}
