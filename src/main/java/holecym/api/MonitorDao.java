package holecym.api;

import holecym.model.Monitor;

import java.util.List;

/**
 * Created by Michal on 17. 1. 2017.
 */
public interface MonitorDao {

    List<Monitor> getMonitorValues();

    void closeConnections();
}
