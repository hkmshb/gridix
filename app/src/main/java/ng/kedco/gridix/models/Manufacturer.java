package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 8/27/16.
 */
public class Manufacturer extends BaseEntity {
    private String name;
    private String email;
    private String location;
    private String url;
    private boolean local;

    public Manufacturer(String name, String email, String location, String url, boolean local) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.url = url;
        this.local = local;
    }

    public Manufacturer(String name, String email, String location) {
        this.name = name;
        this.email = email;
        this.location = location;
    }

    public Manufacturer(String name, String email, String location, String url) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }
}
