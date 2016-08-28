package ng.kedco.gridix.models;

import java.util.ArrayList;

/**
 * Created by shaibujnr on 8/17/16.
 */

public class InjectionStation extends PowerStation {
    private boolean isPublic;

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
