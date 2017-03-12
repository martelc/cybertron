package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.criteria.Criterion;
import martelc.cybertron.domain.transformers.Transformer;

import java.util.Comparator;

public class TransformerStrengthRule implements Comparator<Transformer> {

    static final int STRENGTH_DIFFERENCE = 3;

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        Integer firstTransformerStrength = firstTransformer.getCriteria().get(Criterion.STRENGTH);
        Integer secondTransformerStrength = secondTransformer.getCriteria().get(Criterion.STRENGTH);

        int compareResult = 0;

        if (STRENGTH_DIFFERENCE <= firstTransformerStrength - secondTransformerStrength ||
            STRENGTH_DIFFERENCE <= secondTransformerStrength - firstTransformerStrength) {
            compareResult = firstTransformerStrength.compareTo(secondTransformerStrength);
        }


        return compareResult;
    }
}
