package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerBattleRuleRootTest {

    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final TransformerCourageRule mockTransformerCourageRule =
            Mockito.mock(TransformerCourageRule.class);
    private final TransformerStrengthRule mockTransformerStrengthRule =
            Mockito.mock(TransformerStrengthRule.class);
    private final TransformerBattleRuleChain mockTransformerBattleRuleChain =
            Mockito.mock(TransformerBattleRuleChain.class);

    private final TransformerBattleRuleRoot transformerBattleRuleRootUnderTest =
            new TransformerBattleRuleRoot(mockTransformerBattleRuleChain);

    @Test
    public void compare_withEmptyBattleComparatorChain_returnsNeitherTransformer() {
        List<Comparator> emptyChainOfComparators = new ArrayList<>();
        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(emptyChainOfComparators);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(TransformerBattleRuleRoot.NEITHER_OPPONENT);
    }

    @Test
    public void compare_withSingleBattleComparatorThatComparesToNeitherTransformer_returnsNeitherTransformer() {
        List<Comparator> chainWithASingleComparator = new ArrayList<>();
        chainWithASingleComparator.add(mockTransformerCourageRule);
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithASingleComparator);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSingleBattleComparatorThatComparesToFirstTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithASingleComparator = new ArrayList<>();
        chainWithASingleComparator.add(mockTransformerCourageRule);
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithASingleComparator);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSingleBattleComparatorThatComparesToSecondTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithASingleComparator = new ArrayList<>();
        chainWithASingleComparator.add(mockTransformerCourageRule);
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithASingleComparator);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithBothComparingToNeitherTransformer_returnsNeitherTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToNeitherTransformerAndSecondComparingToFirstTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.NEITHER_OPPONENT);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToFirstTransformerAndSecondComparingToNeitherTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.NEITHER_OPPONENT);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToNeitherTransformerAndSecondComparingToSecondTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.NEITHER_OPPONENT);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToSecondTransformerAndSecondComparingToNeitherTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.NEITHER_OPPONENT);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToFirstTransformerAndSecondComparingToFirstTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToFirstTransformerAndSecondComparingToSecondTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.SECOND_OPPONENT);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToSecondTransformerAndSecondComparingToFirstTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleRuleRoot.FIRST_OPPONENT);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToSecondTransformerAndSecondComparingToSecondTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageRule);
        chainWithTwoComparators.add(mockTransformerStrengthRule);
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        when(mockTransformerBattleRuleChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthRule.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleRuleRootUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }
}
