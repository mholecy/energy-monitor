package holecym;

import holecym.api.MeasuresDao;
import holecym.api.MonitorDao;
import holecym.dao.MeasuresDaoImpl;
import holecym.dao.MonitorDaoImpl;
import holecym.model.Measures;
import holecym.model.Monitor;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Michal on 5. 1. 2017.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Properties properties = System.getProperties();
        properties.setProperty("db.connection", "jdbc:sqlserver://HP-PROBOOK\\SQLEXPRESS");
        properties.setProperty("db.username", "mholecy");
        properties.setProperty("db.password", "kleopatra");

        MonitorDao monitorDao = new MonitorDaoImpl();
        List<Monitor> monitorData = monitorDao.getMonitorValues();
        for (Monitor monitor : monitorData) {
            System.out.println(monitor);
        }
        monitorDao.closeConnections();

        MeasuresDao measuresDao = new MeasuresDaoImpl();
        List<Measures> measureValues = measuresDao.getMeasureValues();
        for (Measures measures : measureValues) {
            System.out.println(measures);
        }
        measuresDao.closeConnections();

    }
}
