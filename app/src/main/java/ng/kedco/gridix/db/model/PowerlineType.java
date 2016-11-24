package ng.kedco.gridix.db.model;


public enum PowerlineType
{
    FEEDER(1, "Feeder"),
    UPRISER(2, "Upriser");

    private final int value;
    private final String text;

    PowerlineType(int value, String text)
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

    public static PowerlineType fromInt(int value)
    {
        switch (value) {
            case 1: return FEEDER;
            case 2: return UPRISER;
            default: return null;
        }
    }
}
