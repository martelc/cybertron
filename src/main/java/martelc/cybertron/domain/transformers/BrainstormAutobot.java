package martelc.cybertron.domain.transformers;

public class BrainstormAutobot extends Autobot {

    public static class BrainstormAutobotBuilder extends AutobotBuilder {

        public BrainstormAutobotBuilder() {
            super("Brainstorm");
            this.motto = "The strongest tyrant cannot crush freedom of thought.";
            this.description = "So full of ideas that he often begins disclosing a new one before he finishes " +
                    "explaining an old one. Worked with the mysterious Nebulan medical doctor, Arcana, to whom he's " +
                    "binary-bonded, to devise technology that created the Headmasters. In jet mode, maximum speed: " +
                    "5200 mph. Range: 8000 miles. In robot mode, carries high-energy photon pulse cannons. Sometimes " +
                    "overheats and shorts out sections of his cerebro-circuitry.";
            this.criteria(new int[]{5,8,9,6,7,9,7,8});
        }

        @Override
        public BrainstormAutobotBuilder getThis() {
            return this;
        }

        public BrainstormAutobot build() {
            return new BrainstormAutobot(this);
        }
    }

    private BrainstormAutobot(BrainstormAutobotBuilder builder) {
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
