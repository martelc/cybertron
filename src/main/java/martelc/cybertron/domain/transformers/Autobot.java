package martelc.cybertron.domain.transformers;

public class Autobot extends Transformer {

    protected static class AutobotBuilder extends AbstractBuilder {

        AutobotBuilder(String name) {
            super(name);
            this.motto = "Death to decepticons.";
        }

        @Override
        public AutobotBuilder getThis() {
            return this;
        }

        public Autobot build() {
            return new Autobot(this);
        }
    }

    protected Autobot(AutobotBuilder builder) {
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
