package holecym.dao;

import holecym.api.MonitorDao;
import holecym.model.Monitor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 17. 1. 2017.
 */
public class MonitorDaoImpl extends JdbcConnection implements MonitorDao {

    private static final String COLUMN_DATETIME = "Datetimestamp";
    private static final String COLUMN_FLOATVALUE = "floatvalue";
    private static final String COLUMN_PARENTID = "parentid";

    public MonitorDaoImpl() {
        super();
    }

    @Override
    public List<Monitor> getMonitorValues() {
        final List<Monitor> monitorList = new ArrayList<>();
        getMonitorDataResultSet();
        try {
            while (resultSet.next()) {
                Monitor monitor = new Monitor();
                monitor.setDatetime(resultSet.getDate(COLUMN_DATETIME));
                monitor.setFloatValue(resultSet.getDouble(COLUMN_FLOATVALUE));
                monitor.setParentId(resultSet.getInt(COLUMN_PARENTID));

                monitorList.add(monitor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monitorList;
    }

    private void getMonitorDataResultSet() {
        try {
            final String sql = "SELECT TOP (100) [parentid]," +
                    " [floatvalue], [Datetimestamp] FROM [TEST].[dbo].[tbl_monitor] " +
                    "WHERE [parentid] = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 3);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
