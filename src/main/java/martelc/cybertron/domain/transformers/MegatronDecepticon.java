package martelc.cybertron.domain.transformers;

public class MegatronDecepticon extends Decepticon {

    public static class MegatronDecepticonBuilder extends DecepticonBuilder {

        public MegatronDecepticonBuilder() {
            super("Megatron");
            this.motto = "Peace through tyranny.";
            this.description = "Megatron combines brute strength, military cunning, ruthlessness and terror. Aches " +
                    "to return to Cybertron to complete the Decepticon conquest, but only after destroying all " +
                    "Autobots on Earth. Plans to possess all of Earth's resources. Incredibly powerful and i" +
                    "ntelligent. Fires particle beam cannon. Can link up interdimensionally to a black hole and " +
                    "draw anti-matter from it for use as a weapon. No known weakness.";
            this.criteria(new int[]{10,10,4,8,10,9,10,9});
        }

        @Override
        public MegatronDecepticonBuilder getThis() {
            return this;
        }

        public MegatronDecepticon build() {
            return new MegatronDecepticon(this);
        }
    }

    private MegatronDecepticon(MegatronDecepticonBuilder builder) {
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
