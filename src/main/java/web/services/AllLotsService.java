package web.services;

import db.DAO.LotsDao;
import db.POJO.Lots;
import db.POJO.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//@Component
public class AllLotsService {
    private LotsDao lotsDao = new LotsDao();
    private static final Logger log = Logger.getLogger(AllLotsService.class);
    /**
     * Функция запрашивает все лоты и возвращает список все, у которых дата окончания проведения еще не наступила
     * @return возвращает list лотов
     */
    public List<Lots> getAllLots() {

        List <Lots> lots = lotsDao.getAll();
        for (int i = 0; i < lots.size(); i++) {
            if ((lots.get(i).getAuctionTimeInMs()+lots.get(i).getStartTimeInMs()) < System.currentTimeMillis()){
                lots.remove(i);
            }
        }
        return lots;
    }

}
