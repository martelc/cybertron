package martelc.cybertron.domain.transformers;

import javax.inject.Inject;

public class HubcapAutobot extends Autobot {

    public static class HubcapAutobotBuilder extends AutobotBuilder {

        public HubcapAutobotBuilder() {
            super("Hubcap");
            this.motto = "Weapons can win battles, but words can win wars.";
            this.description = "Has everybody's friendship, nobody's trust. Affable, witty, generous, charming, but " +
                    "a con artist. Living proof of the Cybertronian adage, \"You can't tell an Autobot by its " +
                    "finish.\" Head module equipment receives AM and FM radio, shortwave, UHF, and VHF television " +
                    "signals as weak as .000001 watts. Maximum speed of 90 mph, range 1000 miles.";
            this.criteria(new int[]{4,4,4,4,4,4,4,4});
        }

        @Override
        public HubcapAutobotBuilder getThis() {
            return this;
        }

        public HubcapAutobot build() {
            return new HubcapAutobot(this);
        }
    }

    @Inject
    private HubcapAutobot(HubcapAutobotBuilder builder) {
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
