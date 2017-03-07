package martelc.cybertron.domain.transformers;

public class OptimusPrimeAutobot extends Autobot {

    public static class OptimusPrimeAutobotBuilder extends AutobotBuilder {

        public OptimusPrimeAutobotBuilder() {
            super("Optimus Prime");
            this.motto = "Freedom is the right of all sentient beings.";
            this.description = "Optimus Prime is the largest, strongest and wisest of all Autobots. Feels his role " +
                    "is the protection of all life, including Earth-life. Fights unceasingly to defeat the " +
                    "Decepticons. Splits into three autonomous modules: 1) Optimus Prime... the brain center known " +
                    "as the Commander; 2) Roller, the Autobot scout car... a spy who operates up to 1200 miles away; " +
                    "and 3) Autobot Headquarters... the combat deck equipped with a versatile mechanic/artillery " +
                    "robot. Injury to one module is felt by the other two.";
            this.criteria(new int[]{10,10,8,10,10,10,8,10});
        }

        @Override
        public OptimusPrimeAutobotBuilder getThis() {
            return this;
        }

        public OptimusPrimeAutobot build() {
            return new OptimusPrimeAutobot(this);
        }
    }

    private OptimusPrimeAutobot(OptimusPrimeAutobotBuilder builder) {
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
