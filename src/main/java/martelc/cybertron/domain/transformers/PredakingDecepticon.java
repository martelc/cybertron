package martelc.cybertron.domain.transformers;

public class PredakingDecepticon extends Decepticon {

    public static class PredakingDecepticonBuilder extends DecepticonBuilder {

        public PredakingDecepticonBuilder() {
            super("Predaking");
            this.motto = "Destroy first, ask questions later.";
            this.description = "The closest thing to a perfect fighting machine that the Decepticons have. As a " +
                    "warrior he has no equal; as a weapon he has no restraints. His actions result from seemingly " +
                    "savage, animal instinct. Can lift 500 tons; reacts to any movement he sees within .002 seconds. " +
                    "Can generate protective electric field. Wields powerful x-ray laser cannon. Each foot houses " +
                    "twin mortar shell launchers. No known weaknesses.";
            this.criteria(new int[]{10,5,3,8,7,9,9,8});
        }

        @Override
        public PredakingDecepticonBuilder getThis() {
            return this;
        }

        public PredakingDecepticon build() {
            return new PredakingDecepticon(this);
        }
    }

    private PredakingDecepticon(PredakingDecepticonBuilder builder) {
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
