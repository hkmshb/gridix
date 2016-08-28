package ng.kedco.gridix.models;

/**
 * Created by shaibujnr on 8/17/16.
 */

public class DistributionSubstation extends Station{
    private boolean isPublic;
    private int upriserCount;

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getUpriserCount() {
        return upriserCount;
    }

    public void setUpriserCount(int upriserCount) {
        this.upriserCount = upriserCount;
    }
}
