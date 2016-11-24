package ng.kedco.gridix.db.model;


public enum StationType
{
    TRANSMISSION(1, "Transmission"),
    INJECTION(2, "Injection"),
    DISTRIBUTION(3, "Distribution");

    private final int value;
    private final String text;

    StationType(int value, String text)
    {
        this.value = value;
        this.text = text;
    }

    public int value()
    {
        return this.value;
    }

    @Override
    public String toString()
    {
        return this.text;
    }

    public static StationType fromInt(int value)
    {
        switch (value) {
            case 1: return TRANSMISSION;
            case 2: return INJECTION;
            case 3: return DISTRIBUTION;
            default: return null;
        }
    }
}
