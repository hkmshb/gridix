package ng.kedco.gridix.enums;

/**
 * Created by shaibujnr on 9/23/16.
 */
public enum FragmentTags {
    TRANSMISSION{
        @Override
        public String toString() {
            return "transmission_fragment_tag";
        }
    },

    INJECTION{
        @Override
        public String toString() {
            return "injection_fragment_tag";
        }
    },

    DISTRIBUTION{
        @Override
        public String toString() {
            return "distribution_fragment_tag";
        }
    },

    FEEDERTT{
      @Override
        public String toString(){return "feeder33_fragment_tag";}
    },

    FEEDERELV{
        @Override
        public String toString() {
            return "feeder11_fragment_tag";
        }
    }


}
