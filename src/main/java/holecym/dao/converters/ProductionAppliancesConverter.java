package holecym.dao.converters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static holecym.dao.converters.columns.ColumnName.COLUMN_VYROBNA_LINKA;

/**
 * Created by Michal on 26. 3. 2017.
 */
public class ProductionAppliancesConverter implements DtoConverter<String> {

    @Override
    public void convert(Set<String> dtoModelSet, ResultSet resultSet) throws SQLException {
        dtoModelSet.add(resultSet.getString(COLUMN_VYROBNA_LINKA));
    }
}
