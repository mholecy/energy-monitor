package holecym.api;

import holecym.model.Measures;
import holecym.model.Monitor;

import java.util.List;

/**
 * Created by Michal on 17. 1. 2017.
 */
public interface MeasuresDao {

    List<Measures> getMeasureValues();

    void closeConnections();
}
