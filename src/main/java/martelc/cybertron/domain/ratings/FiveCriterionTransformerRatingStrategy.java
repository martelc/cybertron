package martelc.cybertron.domain.ratings;

import martelc.cybertron.domain.criteria.Criterion;
import martelc.cybertron.domain.transformers.Transformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FiveCriterionTransformerRatingStrategy implements TransformerRatingStrategy {

    private static final Set<Criterion> OVERALL_RATING_CRITERION = new HashSet<>(
            Arrays.asList(
                    Criterion.STRENGTH,
                    Criterion.INTELLIGENCE,
                    Criterion.SPEED,
                    Criterion.ENDURANCE,
                    Criterion.FIREPOWER));

    @Override
    public int calculateRating(Transformer transformer) {
        return sumOverallRating(transformer.getCriteria());
    }

    private int sumOverallRating(Map<Criterion, Integer> criteria) {
        int sum = 0;

        for (Criterion currentCriterion : OVERALL_RATING_CRITERION) {
            throwExceptionIfCriterionIsMissing(criteria, currentCriterion);

            sum += criteria.get(currentCriterion);
        }

        return sum;
    }

    private void throwExceptionIfCriterionIsMissing(Map<Criterion, Integer> criteria, Criterion currentCriterion) {
        if (!criteria.containsKey(currentCriterion)) {
            throw new IllegalArgumentException(currentCriterion.name() + "is missing from transformer criteria.");
        }
    }
}
