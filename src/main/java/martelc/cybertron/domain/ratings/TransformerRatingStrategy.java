package martelc.cybertron.domain.ratings;

import martelc.cybertron.domain.transformers.Transformer;

public interface TransformerRatingStrategy {

    int calculateRating(Transformer transformer);

}
