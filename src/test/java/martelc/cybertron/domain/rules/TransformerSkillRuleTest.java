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

public class TransformerSkillRuleTest {

    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final TransformerSkillRule transformerSkillRuleUnderTest =
            new TransformerSkillRule();

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

        soundwaveDecepticonCriteria.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MAXIMUM);
        bluestreakAutobotCriteri.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MINIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerSkillRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSecondTransformerWinningComparison_returnsSecondTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MINIMUM);
        bluestreakAutobotCriteri.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MAXIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerSkillRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withNeitherTransformerWinningComparison_returnsTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MINIMUM);
        bluestreakAutobotCriteri.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MINIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerSkillRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withFirstTransformerWinningComparisonByExactlyTheSkillDifference_returnsFirstTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.SKILL,
                Transformer.CRITERIA_VALUE_MINIMUM + TransformerSkillRule.SKILL_DIFFERENCE);
        bluestreakAutobotCriteri.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MINIMUM);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerSkillRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSecondTransformerWinningComparisonByExactlyTheSkillDifference_returnsSecondTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        soundwaveDecepticonCriteria.put(Criterion.SKILL, Transformer.CRITERIA_VALUE_MINIMUM);
        bluestreakAutobotCriteri.put(Criterion.SKILL,
                Transformer.CRITERIA_VALUE_MINIMUM + TransformerSkillRule.SKILL_DIFFERENCE);

        when(mockSoundwaveDecepticon.getCriteria()).thenReturn(soundwaveDecepticonCriteria);
        when(mockBluestreakAutobot.getCriteria()).thenReturn(bluestreakAutobotCriteri);
        when(mockSoundwaveDecepticon.compareTo(mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerSkillRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }
}
