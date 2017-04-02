package holecym.dao.converters;

import holecym.model.ProductionModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static holecym.dao.converters.columns.ColumnName.COLUMN_DATE;
import static holecym.dao.converters.columns.ColumnName.COLUMN_LINE;
import static holecym.dao.converters.columns.ColumnName.COLUMN_POCET_JEDNOTIEK;
import static holecym.dao.converters.columns.ColumnName.COLUMN_VYROBNA_LINKA;
import static holecym.utils.DateUtils.getDateFromTimestamp;

/**
 * Created by Michal on 26. 3. 2017.
 */
public class ProductionModelConverter implements DtoConverter<ProductionModel> {

    @Override
    public void convert(Set<ProductionModel> dtoModelSet, ResultSet resultSet) throws SQLException {

        ProductionModel productionModel = new ProductionModel();

        productionModel.setLine(resultSet.getString(COLUMN_LINE));
        productionModel.setUnitsAssembled(resultSet.getLong(COLUMN_POCET_JEDNOTIEK));
        productionModel.setAppliance(resultSet.getString(COLUMN_VYROBNA_LINKA));
        productionModel.setDate(getDateFromTimestamp(resultSet.getTimestamp(COLUMN_DATE)));

        dtoModelSet.add(productionModel);
    }
}
