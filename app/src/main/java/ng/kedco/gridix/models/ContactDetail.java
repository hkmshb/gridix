package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 9/26/16.
 */
public abstract class ContactDetail {
    String usage;
    boolean isDefault;

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
