package martelc.cybertron.domain.transformers;

public class MetroplexAutobot extends Autobot {

    public static class MetroplexAutobotBuilder extends AutobotBuilder {

        public MetroplexAutobotBuilder() {
            super("Metroplex");
            this.motto = "Vigilance is the foundation on which victories are built.";
            this.description = "Startlingly versatile, staggeringly strong, the Autobots's last line of defense... " +
                    "a mighty instrument of titanic destructive force. Extremely modest about his achievements... " +
                    "berates himself for not doing more. In robot mode, he can lift 70,000 tons, has " +
                    "shoulder-mounted twin high-energy maser cannons and omni-directional receiving and transmitting " +
                    "antenna. In city mode, has helipad and fully equipped repair bays that can handle four vehicles " +
                    "at once. Left rear tower transforms into tank, Slammer, who has rocket-propelled mortar cannon. " +
                    "Scamper is sports car with side-mounted electro-blasters; transforms into robot, uses " +
                    "high-energy particle beam pistol. Six-Gun is small robot, has ion-pulse rifles for arms, twin " +
                    "surface-to-air guided missile launchers on back, acetylene pistol. In battle station mode, uses " +
                    "all these weapons and twin disruptor rays, laser lances, powerful anti-matter projectors.";
            this.criteria(new int[]{10,8,2,9,8,10,10,9});
        }

        @Override
        public MetroplexAutobotBuilder getThis() {
            return this;
        }

        public MetroplexAutobot build() {
            return new MetroplexAutobot(this);
        }
    }

    private MetroplexAutobot(MetroplexAutobotBuilder builder) {
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
