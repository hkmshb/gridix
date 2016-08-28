package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 8/27/16.
 */
public abstract class StationEquipment extends Equipment {
    private String installedStationId;

    public String getInstalledStationId() {
        return installedStationId;
    }

    public void setInstalledStationId(String installedStationId) {
        this.installedStationId = installedStationId;
    }
}
