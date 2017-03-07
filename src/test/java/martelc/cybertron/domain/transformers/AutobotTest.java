package martelc.cybertron.domain.transformers;

import martelc.cybertron.domain.criteria.Criterion;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class AutobotTest {

    @Test
    public void build_withNullCriteriaMap_throwsIllegalArgumentException() {
        Map<Criterion, Integer> nullCriteria = null;

        try {
            new Autobot.AutobotBuilder("NullCriteriaAutobot")
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
            new Autobot.AutobotBuilder("EmptyCriteriaAutobot")
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
            new Autobot.AutobotBuilder("PartialCriteriaAutobot")
                    .criteria(partialCriteria)
                    .build();

            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);

        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("Criteria");
        }
    }

    @Test
    public void build_withBluestreakAutobotCriteriaMap_returnsAutobot() {
        Map<Criterion, Integer> bluestreakAutobotCriteria = new HashMap<>();

        bluestreakAutobotCriteria.put(Criterion.STRENGTH, 6);
        bluestreakAutobotCriteria.put(Criterion.INTELLIGENCE, 6);
        bluestreakAutobotCriteria.put(Criterion.SPEED, 7);
        bluestreakAutobotCriteria.put(Criterion.ENDURANCE, 9);
        bluestreakAutobotCriteria.put(Criterion.RANK, 5);
        bluestreakAutobotCriteria.put(Criterion.COURAGE, 2);
        bluestreakAutobotCriteria.put(Criterion.FIREPOWER, 9);
        bluestreakAutobotCriteria.put(Criterion.SKILL, 7);

        Transformer bluestreakAutobot = new Autobot.AutobotBuilder("Bluestreak")
                .criteria(bluestreakAutobotCriteria)
                .build();

        assertThat(bluestreakAutobot).isNotNull();
        assertThat(bluestreakAutobot).isInstanceOf(Autobot.class);
    }

    @Test
    public void build_withHubcapAutobotCriteriaAutobotMap_returnsAutobot() {
        Map<Criterion, Integer> hubcapAutobotCriteria = new HashMap<>();

        hubcapAutobotCriteria.put(Criterion.STRENGTH, 4);
        hubcapAutobotCriteria.put(Criterion.INTELLIGENCE, 4);
        hubcapAutobotCriteria.put(Criterion.SPEED, 4);
        hubcapAutobotCriteria.put(Criterion.ENDURANCE, 4);
        hubcapAutobotCriteria.put(Criterion.RANK, 4);
        hubcapAutobotCriteria.put(Criterion.COURAGE, 4);
        hubcapAutobotCriteria.put(Criterion.FIREPOWER, 4);
        hubcapAutobotCriteria.put(Criterion.SKILL, 4);

        Transformer hubcapAutobot = new Autobot.AutobotBuilder("Hubcap")
                .criteria(hubcapAutobotCriteria)
                .build();

        assertThat(hubcapAutobot).isNotNull();
        assertThat(hubcapAutobot).isInstanceOf(Autobot.class);
    }
}
