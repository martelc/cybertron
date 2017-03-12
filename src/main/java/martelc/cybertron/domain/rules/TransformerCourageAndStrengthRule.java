package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.transformers.Transformer;

import javax.inject.Inject;
import java.util.Comparator;

public class TransformerCourageAndStrengthRule implements Comparator<Transformer> {

    private final TransformerCourageRule transformerCourageRule;
    private final TransformerStrengthRule transformerStrengthRule;

    @Inject
    public TransformerCourageAndStrengthRule(
            TransformerCourageRule transformerCourageRule,
            TransformerStrengthRule transformerStrengthRule) {

        this.transformerCourageRule = transformerCourageRule;
        this.transformerStrengthRule = transformerStrengthRule;
    }


    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        int courageCompareResult = transformerCourageRule.compare(firstTransformer, secondTransformer);
        int strengthCompareResult = transformerStrengthRule.compare(firstTransformer, secondTransformer);

        int compareResult = 0;
        if (courageCompareResult == strengthCompareResult) {
            compareResult = courageCompareResult;
        }

        return compareResult;
    }
}
