package martelc.cybertron.domain.transformers;

import martelc.cybertron.domain.criteria.Criterion;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class DecepticonTest {

    @Test
    public void build_withNullCriteriaMap_throwsIllegalArgumentException() {
        Map<Criterion, Integer> nullCriteria = null;

        try {
            new Decepticon.DecepticonBuilder("NullCriteriaDecepticon")
                    .criteria(nullCriteria)
                    .build();

            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);

        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("Criteria");
        }
    }

    @Test
    public void build_withEmptyCriteriaMap_throwsIllegalArgumentException() {
        Map<Criterion, Integer> emptyCriteria = new HashMap<>();

        try {
            new Decepticon.DecepticonBuilder("EmptyCriteriaDecepticon")
                    .criteria(emptyCriteria)
                    .build();

            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);

        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("Criteria");
        }
    }

    @Test
    public void build_withPartialCriteriaMap_throwsIllegalArgumentException() {
        Map<Criterion, Integer> partialCriteria = new HashMap<>();

        partialCriteria.put(Criterion.STRENGTH, 8);
        partialCriteria.put(Criterion.INTELLIGENCE, 9);
        partialCriteria.put(Criterion.SPEED, 2);

        try {
            new Decepticon.DecepticonBuilder("PartialCriteriaDecepticon")
                    .criteria(partialCriteria)
                    .build();

            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);

        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("Criteria");
        }
    }

    @Test
    public void build_withSoundwaveDecepticonCriteriaMap_returnsDecepticon() {
        Map<Criterion, Integer> soundwaveDecepticonCriteria = new HashMap<>();

        soundwaveDecepticonCriteria.put(Criterion.STRENGTH, 8);
        soundwaveDecepticonCriteria.put(Criterion.INTELLIGENCE, 9);
        soundwaveDecepticonCriteria.put(Criterion.SPEED, 2);
        soundwaveDecepticonCriteria.put(Criterion.ENDURANCE, 6);
        soundwaveDecepticonCriteria.put(Criterion.RANK, 7);
        soundwaveDecepticonCriteria.put(Criterion.COURAGE, 5);
        soundwaveDecepticonCriteria.put(Criterion.FIREPOWER, 6);
        soundwaveDecepticonCriteria.put(Criterion.SKILL, 10);

        Transformer soundwaveDecepticon = new Decepticon.DecepticonBuilder("Soundwave")
                .criteria(soundwaveDecepticonCriteria)
                .build();

        assertThat(soundwaveDecepticon).isNotNull();
        assertThat(soundwaveDecepticon).isInstanceOf(Decepticon.class);
    }
}

