package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.transformers.Transformer;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.Iterator;

public class TransformerBattleComparator implements Comparator<Transformer> {
    public static final int FIRST_OPPONENT = 1;
    public static final int SECOND_OPPONENT = -1;
    public static final int NEITHER_OPPONENT = 0;

    private final TransformerBattleComparatorChain transformerBattleComparatorChain;

    @Inject
    public TransformerBattleComparator(TransformerBattleComparatorChain transformerBattleComparatorChain) {
        this.transformerBattleComparatorChain = transformerBattleComparatorChain;
    }

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        Iterator<Comparator> comparatorIterator = transformerBattleComparatorChain.provideOrderedComparators().iterator();

        return compareUntilANonzeroComparisonResult(firstTransformer, secondTransformer, comparatorIterator);
    }

    private int compareUntilANonzeroComparisonResult(Transformer firstTransformer, Transformer secondTransformer, Iterator<Comparator> comparatorIterator) {
        Comparator<Transformer> currentComparator;
        int currentCompareResult = 0;
        int compareResult = 0;

        while (0 == currentCompareResult && comparatorIterator.hasNext()) {
            currentComparator = comparatorIterator.next();
            currentCompareResult = currentComparator.compare(firstTransformer, secondTransformer);

            if (0 != currentCompareResult) {
                compareResult = currentCompareResult;
            }
        }

        return compareResult;
    }
}
