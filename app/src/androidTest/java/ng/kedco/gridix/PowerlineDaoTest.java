package ng.kedco.gridix;

import android.test.AndroidTestCase;

import java.util.List;

import ng.kedco.gridix.db.Db;
import ng.kedco.gridix.db.dao.IPowerlineDao;
import ng.kedco.gridix.db.model.Powerline;
import ng.kedco.gridix.db.model.PowerlineType;
import ng.kedco.gridix.db.model.Voltage;
import ng.kedco.gridix.db.schema.PowerlineSchema;


public class PowerlineDaoTest extends BaseDaoTest
{
    private IPowerlineDao dao = null;

    public void setUp()
    {
        super.setUp();
        this.dao = db.getPowerlineDao();
        this.db.getWritableDB().execSQL(
            "DELETE FROM " + PowerlineSchema.TABLE_NAME + ";");
    }

    public void testAddPowerline()
    {
        assertCount(PowerlineSchema.TABLE_NAME, 0);
        assertTrue(addPowerline("0901000401", "F301", "Club 33KV Feeder"));
        assertCount(PowerlineSchema.TABLE_NAME, 1);
    }

    public void testAddMultiplePowerlines()
    {
        assertCount(PowerlineSchema.TABLE_NAME, 0);
        assertTrue(addPowerline("0901000401", "F301", "Club 33KV Feeder"));
        assertTrue(addPowerline("0901000402", "F302", "Jahun 33KV Feeder"));
        assertTrue(addPowerline("0901000403", "F303", "Gwarzo 33KV Feeder"));
        assertCount(PowerlineSchema.TABLE_NAME, 3);
    }

    public void testFetchPowerlineByCode()
    {
        assertCount(PowerlineSchema.TABLE_NAME, 0);
        assertTrue(addPowerline("0901000401", "F301", "Club 33KV Feeder"));

        Powerline powerline = dao.fetchByCode("0901000401");
        assertNotNull(powerline);

        assertEquals("0901000401", powerline.code());
        assertEquals("F301", powerline.altCode());
        assertEquals("Club 33KV Feeder", powerline.name());
    }

    public void testFetchPowerlineByAltCode()
    {
        assertCount(PowerlineSchema.TABLE_NAME, 0);
        assertTrue(addPowerline("0901000401", "F301", "Club 33KV Feeder"));

        Powerline powerline = dao.fetchByAltCode("F301");
        assertNotNull(powerline);

        assertEquals("0901000401", powerline.code());
        assertEquals("F301", powerline.altCode());
        assertEquals("Club 33KV Feeder", powerline.name());
    }

    public void testFetchPowerlineByType()
    {
        assertCount(PowerlineSchema.TABLE_NAME, 0);
        assertTrue(addPowerline("0901000401", "F301", "Club 33KV Feeder"));
        assertTrue(addPowerline("0901000402", "F302", "Jahun 33KV Feeder"));
        assertTrue(addPowerline("0901000501", "F101", "Garu 11KV Feeder", PowerlineType.UPRISER));
        assertCount(PowerlineSchema.TABLE_NAME, 3);

        List<Powerline> powerlines = dao.fetchByType(PowerlineType.FEEDER);
        assertNotNull(powerlines);
        assertEquals(2, powerlines.size());

        powerlines = dao.fetchByType(PowerlineType.UPRISER);
        assertNotNull(powerlines);
        assertEquals(1, powerlines.size());
    }

    private boolean addPowerline(String code, String altCode, String name)
    {
        return addPowerline(code, altCode, name, PowerlineType.FEEDER);
    }

    private boolean addPowerline(String code, String altCode, String name, PowerlineType type)
    {
        Voltage voltage_ratio = Voltage.MVOLTH;
        return dao.addPowerline(new Powerline(
            Integer.parseInt(code), code, altCode, name,
            type, voltage_ratio, 0, true));
    }
}
