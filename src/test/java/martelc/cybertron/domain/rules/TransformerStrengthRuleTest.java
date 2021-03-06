package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.criteria.Criterion;
import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import martelc.cybertron.domain.transformers.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerStrengthRuleTest {

    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final TransformerStrengthRule transformerStrengthRuleUnderTest =
            new TransformerStrengthRule();

    private final Map<Criterion, Integer> soundwaveDecepticonCriteria = new HashMap<>();
    private final Map<Criterion, Integer> bluestreakAutobotCriteri = new HashMap<>();

    @Before
    public void setup() {
        soundwaveDecepticonCriteria.clear();
        bluestreakAutobotCriteri.clear();
    }

    @Test
    public void compare_withFirstTransformerWinningComparison_returnsFirstTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MAXIMUM);
        bluestreakAutobotCriteri.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MINIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerStrengthRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSecondTransformerWinningComparison_returnsSecondTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MINIMUM);
        bluestreakAutobotCriteri.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MAXIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerStrengthRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withNeitherTransformerWinningComparison_returnsTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MINIMUM);
        bluestreakAutobotCriteri.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MINIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerStrengthRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withFirstTransformerWinningComparisonByExactlyTheStrengthDifference_returnsFirstTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.STRENGTH,
                Transformer.CRITERIA_VALUE_MINIMUM + TransformerStrengthRule.STRENGTH_DIFFERENCE);
        bluestreakAutobotCriteri.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MINIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerStrengthRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSecondTransformerWinningComparisonByExactlyTheStrengthDifference_returnsSecondTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.STRENGTH, Transformer.CRITERIA_VALUE_MINIMUM);
        bluestreakAutobotCriteri.put(Criterion.STRENGTH,
                Transformer.CRITERIA_VALUE_MINIMUM + TransformerStrengthRule.STRENGTH_DIFFERENCE);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerStrengthRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }
}
