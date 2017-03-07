package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.transformers.Transformer;

import javax.inject.Inject;
import java.util.Comparator;

public class TransformerCourageAndStrengthComparator implements Comparator<Transformer> {

    private final TransformerCourageComparator transformerCourageComparator;
    private final TransformerStrengthComparator transformerStrengthComparator;

    @Inject
    public TransformerCourageAndStrengthComparator(
            TransformerCourageComparator transformerCourageComparator,
            TransformerStrengthComparator transformerStrengthComparator) {

        this.transformerCourageComparator = transformerCourageComparator;
        this.transformerStrengthComparator = transformerStrengthComparator;
    }


    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        int courageCompareResult = transformerCourageComparator.compare(firstTransformer, secondTransformer);
        int strengthCompareResult = transformerStrengthComparator.compare(firstTransformer, secondTransformer);

        int compareResult = 0;
        if (courageCompareResult == strengthCompareResult) {
            compareResult = courageCompareResult;
        }

        return compareResult;
    }
}
