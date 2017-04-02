package holecym.dao.converters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Michal on 25. 3. 2017.
 */
public interface DtoConverter<T> {

    void convert(Set<T> dtoModelSet, ResultSet resultSet) throws SQLException;
}
