package holecym.dao.converters;

import holecym.model.ConsumptionModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static holecym.dao.converters.columns.ColumnName.COLUMN_DATETIME;
import static holecym.dao.converters.columns.ColumnName.COLUMN_FLOAT_VALUE;
import static holecym.dao.converters.columns.ColumnName.COLUMN_NAME;
import static holecym.dao.converters.columns.ColumnName.COLUMN_PARENTID;
import static holecym.dao.converters.columns.ColumnName.COLUMN_UNIT;
import static holecym.utils.DateUtils.getDateFromTimestamp;

public class ConsumptionModelConverter implements DtoConverter<ConsumptionModel> {

    @Override
    public void convert(Set<ConsumptionModel> dtoModelSet, ResultSet resultSet) throws SQLException {

        ConsumptionModel consumptionModel = new ConsumptionModel();
        consumptionModel.setId(resultSet.getInt(COLUMN_PARENTID));
        consumptionModel.setName(resultSet.getString(COLUMN_NAME));
        consumptionModel.setUnit(resultSet.getString(COLUMN_UNIT));
        consumptionModel.setDatetime(getDateFromTimestamp(resultSet.getTimestamp(COLUMN_DATETIME)));
        consumptionModel.setUsage(resultSet.getDouble(COLUMN_FLOAT_VALUE));

        if (consumptionModel.getUsage() > 0) {
            dtoModelSet.add(consumptionModel);
        }
    }
}
