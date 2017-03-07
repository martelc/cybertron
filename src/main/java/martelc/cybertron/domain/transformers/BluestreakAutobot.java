package martelc.cybertron.domain.transformers;

import javax.inject.Inject;

public class BluestreakAutobot extends Autobot {

    public static class BluestreakAutobotBuilder extends AutobotBuilder {

        public BluestreakAutobotBuilder() {
            super("Bluestreak");
            this.motto = "I never met a Decepticon I didn't dislike.";
            this.description = "Bluestreak often talks incessantly and inanely. Lightens the situation for all " +
                    "Autobots with his good-natured manner. Despite formidable weaponry and blazing speed, he hates " +
                    "war. Haunted by memory of Decepticons destroying his home-city. Fires bombs up to 8.3 miles and " +
                    "lightning-like 80,000 volt beam up to 12 miles of limited accuracy. Often inhibited by his " +
                    "disdain for combat.";
            this.criteria(new int[]{6,6,7,9,5,2,9,7});
        }

        @Override
        public BluestreakAutobotBuilder getThis() {
            return this;
        }

        public BluestreakAutobot build() {
            return new BluestreakAutobot(this);
        }
    }

    @Inject
    private BluestreakAutobot(BluestreakAutobotBuilder builder) {
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
