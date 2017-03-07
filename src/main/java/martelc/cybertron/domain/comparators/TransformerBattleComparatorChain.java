package martelc.cybertron.domain.comparators;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransformerBattleComparatorChain {

    private final TransformerNameComparator transformerNameComparator;
    private final TransformerCourageAndStrengthComparator transformerCourageAndStrengthComparator;
    private final TransformerSkillComparator transformerSkillComparator;
    private final TransformerRatingComparator transformerRatingComparator;

    @Inject
    public TransformerBattleComparatorChain(
            TransformerNameComparator transformerNameComparator,
            TransformerCourageAndStrengthComparator transformerCourageAndStrengthComparator,
            TransformerSkillComparator transformerSkillComparator,
            TransformerRatingComparator transformerRatingComparator) {

        this.transformerNameComparator = transformerNameComparator;
        this.transformerCourageAndStrengthComparator = transformerCourageAndStrengthComparator;
        this.transformerSkillComparator = transformerSkillComparator;
        this.transformerRatingComparator = transformerRatingComparator;
    }


    List<Comparator> provideOrderedComparators() {
        List<Comparator> orderedComparator = new ArrayList<>();

        orderedComparator.add(transformerNameComparator);
        orderedComparator.add(transformerCourageAndStrengthComparator);
        orderedComparator.add(transformerSkillComparator);
        orderedComparator.add(transformerRatingComparator);

        return orderedComparator;
    }

}
