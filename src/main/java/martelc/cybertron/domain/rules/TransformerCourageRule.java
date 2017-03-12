package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.criteria.Criterion;
import martelc.cybertron.domain.transformers.Transformer;

import java.util.Comparator;

public class TransformerCourageRule implements Comparator<Transformer> {

    static final int COURAGE_DIFFERENCE = 4;

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        Integer firstTransformerCourage = firstTransformer.getCriteria().get(Criterion.COURAGE);
        Integer secondTransformerCourage = secondTransformer.getCriteria().get(Criterion.COURAGE);

        int compareResult = 0;
        if (COURAGE_DIFFERENCE <= firstTransformerCourage - secondTransformerCourage ||
                COURAGE_DIFFERENCE <= secondTransformerCourage - firstTransformerCourage) {
            compareResult = firstTransformerCourage.compareTo(secondTransformerCourage);
        }

        return compareResult;
    }
}
