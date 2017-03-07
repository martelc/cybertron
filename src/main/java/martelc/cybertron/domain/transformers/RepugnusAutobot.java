package martelc.cybertron.domain.transformers;

public class RepugnusAutobot extends Autobot {

    public static class RepugnusAutobotBuilder extends AutobotBuilder {

        public RepugnusAutobotBuilder() {
            super("Repugnus");
            this.motto = "No job is too disgusting to disgust me.";
            this.description = "He has a personality as repellent as his looks - been kicked out of the Autobots " +
                    "many times for insubordination only to be asked back since he's always willing to undertake " +
                    "missions too low-down and dirty for anyone else to consider. In robot mode, carries venom " +
                    "laser that slows cerebro impulses and paralyzes on impact. In creature mode, has infra-red and " +
                    "x-ray vision; can emit colors and stroboscopic effect with eyes. Claws contain chemical, " +
                    "electromagnetic and audio sensors; can rip through almost any substance.";
            this.criteria(new int[]{5,9,2,9,6,10,2,10});
        }

        @Override
        public RepugnusAutobotBuilder getThis() {
            return this;
        }

        public RepugnusAutobot build() {
            return new RepugnusAutobot(this);
        }
    }

    private RepugnusAutobot(RepugnusAutobotBuilder builder) {
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
