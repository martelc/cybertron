package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.ratings.TransformerRatingStrategy;
import martelc.cybertron.domain.transformers.Transformer;

import javax.inject.Inject;
import java.util.Comparator;

public class TransformerRatingComparator implements Comparator<Transformer> {

    private static final int RATING_DIFFERENCE = 1;

    private final TransformerRatingStrategy transformerRatingStrategy;

    @Inject
    public TransformerRatingComparator(TransformerRatingStrategy transformerRatingStrategy) {
        this.transformerRatingStrategy = transformerRatingStrategy;
    }

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        int firstTransformerRating = transformerRatingStrategy.calculateRating(firstTransformer);
        int secondTransformerRating = transformerRatingStrategy.calculateRating(secondTransformer);

        int compareResult = 0;
        if (RATING_DIFFERENCE <= firstTransformerRating - secondTransformerRating) {
            compareResult = 1;
        } else if (RATING_DIFFERENCE <= secondTransformerRating - firstTransformerRating) {
            compareResult = -1;
        }

        return compareResult;
    }
}
