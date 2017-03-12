package martelc.cybertron.domain.transformers;

import martelc.cybertron.domain.rules.TransformerRankRule;
import martelc.cybertron.domain.criteria.Criterion;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Transformer implements Comparable<Transformer>, Comparator<Transformer> {

    protected static abstract class AbstractBuilder {

        private final String name;
        protected final Map<Criterion, Integer> criteria = new HashMap<>(REQUIRED_NUMBER_OF_CRITERION, INITIAL_LOAD_FACTOR);
        String motto = "Transforming the world.";
        String description = "I think, therefore I am.";

        AbstractBuilder(final String name) {
            this.name = name;
        }

        public AbstractBuilder criteria(final Map<Criterion, Integer> criteria) {
            if (null != criteria && !criteria.isEmpty()) {
                this.criteria.putAll(criteria);
            } else {
                this.criteria.clear();
            }
            return getThis();
        }

        public AbstractBuilder criteria(final int[] criteria) {
            if (null != criteria && 0 != criteria.length &&
                    criteria.length == REQUIRED_NUMBER_OF_CRITERION) {

                Criterion[] criteriaKeys = Criterion.values();

                for (int index = 0; index < criteria.length; index++) {
                    this.criteria.put(criteriaKeys[index], criteria[index]);
                }

            } else {
                this.criteria.clear();
            }
            return getThis();
        }

        AbstractBuilder motto(final String motto) {
            this.motto = motto;
            return getThis();
        }

        AbstractBuilder description(final String description) {
            this.description = description;
            return getThis();
        }

        public abstract AbstractBuilder getThis();
        public abstract Transformer build();
    }

    public static final int CRITERIA_VALUE_MINIMUM = 0;
    public static final int CRITERIA_VALUE_MAXIMUM = 10;
    private static final int REQUIRED_NUMBER_OF_CRITERION = Criterion.values().length;
    private static final float INITIAL_LOAD_FACTOR = 0.1f;

    private final Comparator<Transformer> comparator = new TransformerRankRule().reversed();
    private final String name;
    private final Map<Criterion, Integer> criteria = new HashMap<>(REQUIRED_NUMBER_OF_CRITERION, INITIAL_LOAD_FACTOR);
    private final String motto;
    private final String description;

    protected Transformer(final AbstractBuilder builder) {
        this.name = builder.name;
        this.motto = builder.motto;
        this.description= builder.description;
        setCriteria(builder.criteria);
    }

    public String getName() {
        return name;
    }

    public Map<Criterion, Integer> getCriteria() {
        return criteria;
    }

    private void setCriteria(final Map<Criterion, Integer> criteriaToSet) {
        throwIllegalArgumentExceptionIfCriteriaIsNullOrEmpty(criteriaToSet);
        throwIllegalArgumentExceptionIfCriteriaIsPartiallyFull(criteriaToSet);
        throwIllegalArgumentExceptionIfCriteriaContainsInvalidKeysOrValues(criteriaToSet);

        criteria.clear();
        criteria.putAll(criteriaToSet);
    }

    private void throwIllegalArgumentExceptionIfCriteriaIsNullOrEmpty(final Map<Criterion, Integer> criteria) {
        if (null == criteria || criteria.isEmpty()) {
            throw new IllegalArgumentException("Criteria is null or empty.");
        }
    }

    private void throwIllegalArgumentExceptionIfCriteriaIsPartiallyFull(final Map<Criterion, Integer> criteria) {
        if (null == criteria || REQUIRED_NUMBER_OF_CRITERION != criteria.size()) {
            throw new IllegalArgumentException(
                    "Criteria does not have the required number of entries of " + REQUIRED_NUMBER_OF_CRITERION);
        }
    }

    private void throwIllegalArgumentExceptionIfCriteriaContainsInvalidKeysOrValues(
            final Map<Criterion, Integer> criteria) {

        if (null != criteria && REQUIRED_NUMBER_OF_CRITERION == criteria.size()) {
            Criterion[] criteriaKeys = Criterion.values();
            Criterion criteriaKey;
            Integer criteriaValue;

            for (int index = 0; index < REQUIRED_NUMBER_OF_CRITERION; index++) {
                criteriaKey = criteriaKeys[index];
                criteriaValue = criteria.get(criteriaKey);

                if (null == criteriaValue) {
                    throw new IllegalArgumentException("Criteria contains an invalid key " + criteriaKey);
                }

                if (criteriaValue < CRITERIA_VALUE_MINIMUM ||
                        criteriaValue > CRITERIA_VALUE_MAXIMUM) {
                    throw new IllegalArgumentException(
                            "Criteria " + criteriaKey + " contains an invalid value " + criteriaValue);
                }
            }

        } else {
            throw new IllegalArgumentException("Criteria is not the correct size.");
        }
    }

    @Override
    public int compareTo(Transformer otherTransformer) {
        int reverseOrder = -1;

        return reverseOrder * this.criteria.get(Criterion.RANK).compareTo(
                otherTransformer.criteria.get(Criterion.RANK));
    }

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        return comparator.compare(firstTransformer, secondTransformer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, criteria, motto, description);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (!(object instanceof Transformer))
            return false;

        Transformer transformer = (Transformer) object;
        return name.equals(transformer.name) &&
                criteria.equals(transformer.criteria) &&
                motto.equals(transformer.motto) &&
                description.equals(transformer.description);
    }

    @Override
    public String toString() {
        return name;
    }
}
