package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerCourageAndTransformerStrengthRuleTest {

    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final TransformerCourageRule mockTransformerCourageRule = Mockito.mock(TransformerCourageRule.class);
    private final TransformerStrengthRule mockTransformerStrengthRule = Mockito.mock(TransformerStrengthRule.class);

    private final TransformerCourageAndStrengthRule transformerCourageAndStrengthRule =
            new TransformerCourageAndStrengthRule(
                    mockTransformerCourageRule, mockTransformerStrengthRule);

    @Test
    public void compare_withCourageComparatorReturnsNeitherTransformerAndStrengthComparatorReturnsNeitherTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthRule.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsFirstTransformerAndStrengthComparatorReturnsNeitherTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.FIRST_OPPONENT);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthRule.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsNeitherTransformerAndStrengthComparatorReturnsFirstTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.FIRST_OPPONENT);

        int declaredWinner = transformerCourageAndStrengthRule.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsSecondTransformerAndStrengthComparatorReturnsNeitherTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.SECOND_OPPONENT);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthRule.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsNeitherTransformerAndStrengthComparatorReturnsSecondTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.SECOND_OPPONENT);

        int declaredWinner = transformerCourageAndStrengthRule.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsFirstTransformerAndStrengthComparatorReturnsFirstTransformer_returnsFirstTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthRule.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsSecondTransformerAndStrengthComparatorReturnsSecondTransformer_returnsSecondTransformer() {
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthRule.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

}
