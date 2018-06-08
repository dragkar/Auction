package web.services;

import db.DAO.LotsDao;
import db.POJO.Lots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class AllLotsService {

    // private static final Logger log = Logger.getLogger(AllLotsService.class);
     LotsDao lotsDao ;

    public LotsDao getLotsDao() {
        return lotsDao;
    }
    @Autowired
    public void setLotsDao(LotsDao lotsDao) {
        this.lotsDao = lotsDao;
    }



    /**
     * Функция запрашивает все лоты и возвращает список все, у которых дата окончания проведения еще не наступила
     * @return возвращает list лотов
     */
    public List<Lots> getAllLots() throws SQLException {

        List <Lots> lots = lotsDao.getAll();
        for (int i = 0; i < lots.size(); i++) {
            if ((lots.get(i).getAuctionTimeInMs()+lots.get(i).getStartTimeInMs()) < System.currentTimeMillis()){
                lots.remove(i);
            }
        }
        return lots;
    }

}
