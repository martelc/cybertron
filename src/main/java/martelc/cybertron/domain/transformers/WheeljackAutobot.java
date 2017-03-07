package martelc.cybertron.domain.transformers;

public class WheeljackAutobot extends Autobot {

    public static class WheeljackAutobotBuilder extends AutobotBuilder {

        public WheeljackAutobotBuilder() {
            super("Wheeljack");
            this.motto = "Never do what your enemy expects you to do.";
            this.description = "Wheeljack is the mad scientist of the Autobots. Always inventing new weapons and " +
                    "gadgets. Most adept at driving while in car mode. Likes to show off his stunts. Flying range " +
                    "of 800 miles using solid-fuel rockets in arms. Shoots magnetic inducer, shrapnel-needle and " +
                    "gyro-inhibitor shells from his shoulder cannons. He is his own worst enemy. Often injured " +
                    "while experimenting with new weapons.";
            this.criteria(new int[]{7,9,7,5,8,9,7,10});
        }

        @Override
        public WheeljackAutobotBuilder getThis() {
            return this;
        }

        public WheeljackAutobot build() {
            return new WheeljackAutobot(this);
        }
    }

    private WheeljackAutobot(WheeljackAutobotBuilder builder) {
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
