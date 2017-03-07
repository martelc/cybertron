package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.criteria.Criterion;
import martelc.cybertron.domain.transformers.Transformer;

import java.util.Comparator;

public class TransformerRankComparator implements Comparator<Transformer> {

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {

        return firstTransformer.getCriteria().get(Criterion.RANK)
                .compareTo(secondTransformer.getCriteria().get(Criterion.RANK));
    }

}
