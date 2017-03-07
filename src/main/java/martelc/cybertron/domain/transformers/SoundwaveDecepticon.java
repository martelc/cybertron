package martelc.cybertron.domain.transformers;

import javax.inject.Inject;

public class SoundwaveDecepticon extends Decepticon {

    public static class SoundwaveDecepticonBuilder extends DecepticonBuilder {

        public SoundwaveDecepticonBuilder() {
            super("Soundwave");
            this.motto = "Cries and screams are music to my ears.";
            this.description = "It is said Soundwave can hear a fly sneeze. Uses anything he hears for blackmail to " +
                    "advance his status. Opportunist. Despised by all other Decepticons. Sensors can detect even " +
                    "lowest energy radio transmissions. Able to read minds by monitoring electrical brain impulses. " +
                    "Acts as radio link for others. Locates and identifies Autobots, then informs Decepticons. " +
                    "Carries a concussion blaster gun. Often target of retaliation by his comrades.";
            this.criteria(new int[]{8,9,2,6,7,5,6,10});
        }

        @Override
        public SoundwaveDecepticonBuilder getThis() {
            return this;
        }

        public SoundwaveDecepticon build() {
            return new SoundwaveDecepticon(this);
        }
    }

    @Inject
    private SoundwaveDecepticon(SoundwaveDecepticonBuilder builder) {
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
