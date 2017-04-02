package holecym.api;

import holecym.model.Appliance;
import holecym.model.Consumption;
import holecym.model.Production;

import java.time.LocalDateTime;

/**
 * Created by Michal on 5. 3. 2017.
 */
public interface EnergyMonitorApi {

    /**
     * Funkcia na vyrátanie nameranej spotreby jedného alebo viacerých
     * meracích prístrojov za vybrané obdobie (s presnosťou na sekundy).
     *
     * @param dateFrom  Odkedy sa bude ratat spotreba
     * @param dateTo    Dokedy sa bude ratat spotreba
     * @param appliance Spotrebic, alebo viacero spotrebicov pre ktore sa bude ratat spotreba
     * @return
     */
    Consumption getConsumptionData(LocalDateTime dateFrom, LocalDateTime dateTo, Appliance... appliance);

    /**
     * Funkcia na vyrátanie množstva vyrobených jednotiek jednej alebo viacerých výrobných liniek v rámci
     * vybraného obdobia (s presnosťou na hodiny).
     *
     * @param dateFrom     Odkedy sa bude ratat pocet
     * @param dateTo       Dokedy sa bude ratat pocet
     * @param appliance Vyrobna linka, alebo viacero liniek pre ktore sa bude ratat pocet
     * @return
     */
    Production getProducedItemsCountData(LocalDateTime dateFrom, LocalDateTime dateTo, Appliance... appliance);
}
