package holecym.api.impl;

import holecym.api.AppliancesApi;
import holecym.dao.AppliancesDao;
import holecym.model.Appliance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Michal on 13. 3. 2017.
 */
public class AppliancesApiImpl implements AppliancesApi {

    private static final String SQL_QUERY_FOR_APPLIANCES = "SELECT [Meridlo], [Units], [Parent_ID], [cc], [Path]" +
            " FROM [TEST].[dbo].[tbl_merice] ORDER BY [Meridlo]";

    private AppliancesDao appliancesDao;

    public AppliancesApiImpl() {
        appliancesDao = new AppliancesDao();
    }

    @Override
    public List<Appliance> getAppliances() {
        final Set<Appliance> appliances = appliancesDao.getAppliances(SQL_QUERY_FOR_APPLIANCES);
        return new ArrayList<>(appliances);
    }
}
