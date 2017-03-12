package martelc.cybertron.domain.rules;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransformerBattleRuleChain {

    private final TransformerNameRule transformerNameRule;
    private final TransformerCourageAndStrengthRule transformerCourageAndStrengthRule;
    private final TransformerSkillRule transformerSkillRule;
    private final TransformerRatingRule transformerRatingRule;

    @Inject
    public TransformerBattleRuleChain(
            TransformerNameRule transformerNameRule,
            TransformerCourageAndStrengthRule transformerCourageAndStrengthRule,
            TransformerSkillRule transformerSkillRule,
            TransformerRatingRule transformerRatingRule) {

        this.transformerNameRule = transformerNameRule;
        this.transformerCourageAndStrengthRule = transformerCourageAndStrengthRule;
        this.transformerSkillRule = transformerSkillRule;
        this.transformerRatingRule = transformerRatingRule;
    }


    List<Comparator> provideOrderedComparators() {
        List<Comparator> orderedComparator = new ArrayList<>();

        orderedComparator.add(transformerNameRule);
        orderedComparator.add(transformerCourageAndStrengthRule);
        orderedComparator.add(transformerSkillRule);
        orderedComparator.add(transformerRatingRule);

        return orderedComparator;
    }

}
