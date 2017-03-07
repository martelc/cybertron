package martelc.cybertron.crosscutting;

import com.google.inject.AbstractModule;
import martelc.cybertron.domain.battles.OncePerCombaticonBattleStrategy;
import martelc.cybertron.domain.battles.BattleStrategy;
import martelc.cybertron.domain.ratings.FiveCriterionTransformerRatingStrategy;
import martelc.cybertron.domain.ratings.TransformerRatingStrategy;

public class DomainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BattleStrategy.class).to(OncePerCombaticonBattleStrategy.class);
        bind(TransformerRatingStrategy.class).to(FiveCriterionTransformerRatingStrategy.class);
    }
}
