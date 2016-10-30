package ng.kedco.gridix.db.model;


public enum Voltage
{
    LVOLT(1, "0.415KV"),
    MVOLTL(2, "11KV"),
    MVOLTH(3, "33KV"),
    HVOLTL(4, "132KV"),
    HVOLTH(5, "330KV");

    private final int value;
    private final String text;

    Voltage(int value, String text)
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

    public static Voltage fromInt(int value)
    {
        switch (value) {
            case 1: return LVOLT;
            case 2: return MVOLTL;
            case 3: return MVOLTH;
            case 4: return HVOLTL;
            case 5: return HVOLTH;
            default: return null;
        }
    }

    public enum Ratio
    {
        MVOLTL_LVOLT(1, "11/0.415KV"),
        MVOLTH_LVOLT(2, "33/0.415KV"),
        MVOLTH_MVOLTL(3, "33/11KV"),
        HVOLTL_MVOLTL(4, "132/11KV"),
        HVOLTL_MVOLTH(5, "132/33KV"),
        HVOLTH_HVOLTL(6, "330/132KV");

        private final int value;
        private final String text;

        Ratio(int value, String text)
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

        public static Ratio fromInt(int value)
        {
            switch (value) {
                case 1: return MVOLTL_LVOLT;
                case 2: return MVOLTH_LVOLT;
                case 3: return MVOLTH_MVOLTL;
                case 4: return HVOLTL_MVOLTL;
                case 5: return HVOLTL_MVOLTH;
                case 6: return HVOLTH_HVOLTL;
                default: return null;
            }
        }
    }
}
