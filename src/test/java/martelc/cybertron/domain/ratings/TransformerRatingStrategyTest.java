package martelc.cybertron.domain.ratings;

import martelc.cybertron.domain.criteria.Criterion;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import martelc.cybertron.domain.transformers.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerRatingStrategyTest {

    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);

    private final TransformerRatingStrategy transformerRatingStrategyUnderTest =
            new FiveCriterionTransformerRatingStrategy();

    private final Map<Criterion, Integer> soundwaveDecepticonCriteria = new HashMap<>();

    @Before
    public void setup() {
        soundwaveDecepticonCriteria.clear();
    }

    @Test
    public void calculate_withAllRatingCriteriaMaximized_returnsCalculatedSum() {
        soundwaveDecepticonCriteria.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MAXIMUM);
        soundwaveDecepticonCriteria.put(Criterion.INTELLIGENCE, Transformer.CRITERIA_VALUE_MAXIMUM);
        soundwaveDecepticonCriteria.put(Criterion.SPEED, Transformer.CRITERIA_VALUE_MAXIMUM);
        soundwaveDecepticonCriteria.put(Criterion.ENDURANCE, Transformer.CRITERIA_VALUE_MAXIMUM);
        soundwaveDecepticonCriteria.put(Criterion.FIREPOWER, Transformer.CRITERIA_VALUE_MAXIMUM);

        int expectedRating = 5 * Transformer.CRITERIA_VALUE_MAXIMUM;

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);

        int calculatedRating = transformerRatingStrategyUnderTest.calculateRating(mockSoundwaveDecepticon);

        assertThat(calculatedRating).isEqualTo(expectedRating);
    }


    @Test
    public void calculate_withAllRatingCriteriaMinimized_returnsCalculatedSum() {
        soundwaveDecepticonCriteria.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MINIMUM);
        soundwaveDecepticonCriteria.put(Criterion.INTELLIGENCE, Transformer.CRITERIA_VALUE_MINIMUM);
        soundwaveDecepticonCriteria.put(Criterion.SPEED, Transformer.CRITERIA_VALUE_MINIMUM);
        soundwaveDecepticonCriteria.put(Criterion.ENDURANCE, Transformer.CRITERIA_VALUE_MINIMUM);
        soundwaveDecepticonCriteria.put(Criterion.FIREPOWER, Transformer.CRITERIA_VALUE_MINIMUM);

        int expectedRating = 5 * Transformer.CRITERIA_VALUE_MINIMUM;

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);

        int calculatedRating = transformerRatingStrategyUnderTest.calculateRating(mockSoundwaveDecepticon);

        assertThat(calculatedRating).isEqualTo(expectedRating);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculate_withPartialRatingCriteria_throwsIllegalArgumentException() {
        soundwaveDecepticonCriteria.put(Criterion.SPEED, Transformer.CRITERIA_VALUE_MAXIMUM);
        soundwaveDecepticonCriteria.put(Criterion.ENDURANCE, Transformer.CRITERIA_VALUE_MAXIMUM);
        soundwaveDecepticonCriteria.put(Criterion.FIREPOWER, Transformer.CRITERIA_VALUE_MAXIMUM);

        int expectedRating = 3 * Transformer.CRITERIA_VALUE_MAXIMUM;

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);

        int calculatedRating = transformerRatingStrategyUnderTest.calculateRating(mockSoundwaveDecepticon);

        assertThat(calculatedRating).isEqualTo(expectedRating);
    }
}
