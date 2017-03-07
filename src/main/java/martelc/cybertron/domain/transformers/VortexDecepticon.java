package martelc.cybertron.domain.transformers;

public class VortexDecepticon extends Decepticon {

    public static class VortexDecepticonBuilder extends DecepticonBuilder {

        public VortexDecepticonBuilder() {
            super("Vortex");
            this.motto = "I'm Vortex, fly me - if you dare!";
            this.description = "Gives a ride to remember - in your nightmares! Takes Autobots on dizzying, " +
                    "death-defying flights to scare information out of them. As helicopter, goes 300 mph with a " +
                    "range of 1200 miles. Whirls rotor blades to create 200-300 mph wind funnels. Uses " +
                    "semi-automatic glue gun. Combines with fellow Combaticons to form \"Bruticus.\"";
            this.criteria(new int[]{4,9,6,5,6,6,7,8});
        }

        @Override
        public VortexDecepticonBuilder getThis() {
            return this;
        }

        public VortexDecepticon build() {
            return new VortexDecepticon(this);
        }
    }

    private VortexDecepticon(VortexDecepticonBuilder builder) {
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
