package martelc.cybertron.domain.transformers;

public class GalvatronDecepticon extends Decepticon {

    public static class GalvatronDecepticonBuilder extends DecepticonBuilder {

        public GalvatronDecepticonBuilder() {
            super("Galvatron");
            this.motto = "My power is everything; defeat is absurd!";
            this.description = "Galvatron is a cold-hearted robotic villain. Determined to lead the Decepticons. " +
                    "Possesses enough strength to pulverize an Autobot into scrap metal. Unconquerable. Arrogant and " +
                    "compassionless. Plots against his allies, thus weakening his position. In robot mode, he " +
                    "carries a laser that emits chemically-produced, direct-current electricity";
            this.criteria(new int[]{10,9,9,10,9,9,9,10});
        }

        @Override
        public GalvatronDecepticonBuilder getThis() {
            return this;
        }

        public GalvatronDecepticon build() {
            return new GalvatronDecepticon(this);
        }
    }

    private GalvatronDecepticon(GalvatronDecepticonBuilder builder) {
        super(builder);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

}
