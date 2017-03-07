package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerBattleComparatorTest {

    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final TransformerCourageComparator mockTransformerCourageComparator =
            Mockito.mock(TransformerCourageComparator.class);
    private final TransformerStrengthComparator mockTransformerStrengthComparator =
            Mockito.mock(TransformerStrengthComparator.class);
    private final TransformerBattleComparatorChain mockTransformerBattleComparatorChain =
            Mockito.mock(TransformerBattleComparatorChain.class);

    private final TransformerBattleComparator transformerBattleComparatorUnderTest =
            new TransformerBattleComparator(mockTransformerBattleComparatorChain);

    @Test
    public void compare_withEmptyBattleComparatorChain_returnsNeitherTransformer() {
        List<Comparator> emptyChainOfComparators = new ArrayList<>();
        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(emptyChainOfComparators);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(TransformerBattleComparator.NEITHER_OPPONENT);
    }

    @Test
    public void compare_withSingleBattleComparatorThatComparesToNeitherTransformer_returnsNeitherTransformer() {
        List<Comparator> chainWithASingleComparator = new ArrayList<>();
        chainWithASingleComparator.add(mockTransformerCourageComparator);
        int expectedWinner = TransformerBattleComparator.NEITHER_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithASingleComparator);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSingleBattleComparatorThatComparesToFirstTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithASingleComparator = new ArrayList<>();
        chainWithASingleComparator.add(mockTransformerCourageComparator);
        int expectedWinner = TransformerBattleComparator.FIRST_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithASingleComparator);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSingleBattleComparatorThatComparesToSecondTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithASingleComparator = new ArrayList<>();
        chainWithASingleComparator.add(mockTransformerCourageComparator);
        int expectedWinner = TransformerBattleComparator.SECOND_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithASingleComparator);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithBothComparingToNeitherTransformer_returnsNeitherTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.NEITHER_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToNeitherTransformerAndSecondComparingToFirstTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.FIRST_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.NEITHER_OPPONENT);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToFirstTransformerAndSecondComparingToNeitherTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.FIRST_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.NEITHER_OPPONENT);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToNeitherTransformerAndSecondComparingToSecondTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.SECOND_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.NEITHER_OPPONENT);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToSecondTransformerAndSecondComparingToNeitherTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.SECOND_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.NEITHER_OPPONENT);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }


    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToFirstTransformerAndSecondComparingToFirstTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.FIRST_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToFirstTransformerAndSecondComparingToSecondTransformer_returnsFirstTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.FIRST_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.SECOND_OPPONENT);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToSecondTransformerAndSecondComparingToFirstTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.SECOND_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.FIRST_OPPONENT);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withTwoBattleComparatorsWithFirstComparingToSecondTransformerAndSecondComparingToSecondTransformer_returnsSecondTransformer() {
        List<Comparator> chainWithTwoComparators = new ArrayList<>();
        chainWithTwoComparators.add(mockTransformerCourageComparator);
        chainWithTwoComparators.add(mockTransformerStrengthComparator);
        int expectedWinner = TransformerBattleComparator.SECOND_OPPONENT;

        when(mockTransformerBattleComparatorChain.provideOrderedComparators()).thenReturn(chainWithTwoComparators);
        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerBattleComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }
}
