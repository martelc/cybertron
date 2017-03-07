package martelc.cybertron.domain.transformers;

public class Decepticon extends Transformer {

    protected static class DecepticonBuilder extends AbstractBuilder {

        DecepticonBuilder(String name) {
            super(name);
            this.motto = "Death to autobots.";
        }

        @Override
        public DecepticonBuilder getThis() {
            return this;
        }

        public Decepticon build() {
            return new Decepticon(this);
        }
    }

    Decepticon(DecepticonBuilder builder) {
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
