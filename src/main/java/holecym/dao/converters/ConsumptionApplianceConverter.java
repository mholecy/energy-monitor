package holecym.dao.converters;

import holecym.model.Appliance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static holecym.dao.converters.columns.ColumnName.COLUMN_CC;
import static holecym.dao.converters.columns.ColumnName.COLUMN_NAME;
import static holecym.dao.converters.columns.ColumnName.COLUMN_PARENT_ID;
import static holecym.dao.converters.columns.ColumnName.COLUMN_PATH;
import static holecym.dao.converters.columns.ColumnName.COLUMN_UNIT;


/**
 * Created by Michal on 26. 3. 2017.
 */
public class ConsumptionApplianceConverter implements DtoConverter<Appliance> {

    @Override
    public void convert(Set<Appliance> dtoModelSet, ResultSet resultSet) throws SQLException {

        Appliance appliance = new Appliance();
        appliance.setName(resultSet.getString(COLUMN_NAME));
        appliance.setUnit(resultSet.getString(COLUMN_UNIT));
        appliance.setId(resultSet.getInt(COLUMN_PARENT_ID));
        appliance.setCc(resultSet.getString(COLUMN_CC));
        appliance.setPath(resultSet.getString(COLUMN_PATH));

        dtoModelSet.add(appliance);
    }
}
