package ng.kedco.gridix.db.dao;

import java.util.List;
import ng.kedco.gridix.db.model.Powerline;
import ng.kedco.gridix.db.model.PowerlineType;


public interface IPowerlineDao
{
    boolean addPowerline(Powerline powerline);

    Powerline fetchById(int id);

    Powerline fetchByCode(String code);

    Powerline fetchByAltCode(String code);

    List<Powerline> fetchPowerlines();

    List<Powerline> fetchByType(PowerlineType type);

}
