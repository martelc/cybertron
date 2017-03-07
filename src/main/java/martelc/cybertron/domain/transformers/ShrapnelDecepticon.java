package martelc.cybertron.domain.transformers;

public class ShrapnelDecepticon extends Decepticon {

    public static class ShrapnelDecepticonBuilder extends DecepticonBuilder {

        public ShrapnelDecepticonBuilder() {
            super("Shrapnel");
            this.motto = "Control electricity and you control the world.";
            this.description = "he noise of war and the screams of his foes are music to this loathsome Insecticon's " +
                    "audio-modules... has piercing battle cry... can be heard 8 miles away. In insect mode can use " +
                    "antennae to control almost any electrical device. In robot mode can attract lightning bolts to " +
                    "antennae and shoot them out hands. Grenade launcher shoots 30 pound steel balls that splinter " +
                    "into razor-sharp spikes. Insulation can stop his electrical blasts.";
            this.criteria(new int[]{9,6,4,7,6,6,8,9});
        }

        @Override
        public ShrapnelDecepticonBuilder getThis() {
            return this;
        }

        public ShrapnelDecepticon build() {
            return new ShrapnelDecepticon(this);
        }
    }

    private ShrapnelDecepticon(ShrapnelDecepticonBuilder builder) {
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
