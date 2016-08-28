package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 8/27/16.
 */
public abstract class LineEquipment extends  Equipment {
    private String installedLineId;

    public String getInstalledLineId() {
        return installedLineId;
    }

    public void setInstalledLineId(String installedLineId) {
        this.installedLineId = installedLineId;
    }
}
