package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.criteria.Criterion;
import martelc.cybertron.domain.transformers.Transformer;

import java.util.Comparator;

public class TransformerSkillComparator implements Comparator<Transformer> {

    static final int SKILL_DIFFERENCE = 3;

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        Integer firstTransformerSkill = firstTransformer.getCriteria().get(Criterion.SKILL);
        Integer secondTransformerSkill= secondTransformer.getCriteria().get(Criterion.SKILL);

        int compareResult = 0;
        if (SKILL_DIFFERENCE <= Math.abs(firstTransformerSkill - secondTransformerSkill)) {
            compareResult = firstTransformerSkill.compareTo(secondTransformerSkill);
        }

        return compareResult;
    }
}
