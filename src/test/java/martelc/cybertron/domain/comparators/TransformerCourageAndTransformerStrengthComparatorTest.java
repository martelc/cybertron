package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerCourageAndTransformerStrengthComparatorTest {

    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final TransformerCourageComparator mockTransformerCourageComparator = Mockito.mock(TransformerCourageComparator.class);
    private final TransformerStrengthComparator mockTransformerStrengthComparator = Mockito.mock(TransformerStrengthComparator.class);

    private final TransformerCourageAndStrengthComparator transformerCourageAndStrengthComparator =
            new TransformerCourageAndStrengthComparator(
                    mockTransformerCourageComparator, mockTransformerStrengthComparator);

    @Test
    public void compare_withCourageComparatorReturnsNeitherTransformerAndStrengthComparatorReturnsNeitherTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleComparator.NEITHER_OPPONENT;

        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthComparator.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsFirstTransformerAndStrengthComparatorReturnsNeitherTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleComparator.NEITHER_OPPONENT;

        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.FIRST_OPPONENT);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthComparator.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsNeitherTransformerAndStrengthComparatorReturnsFirstTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleComparator.NEITHER_OPPONENT;

        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.FIRST_OPPONENT);

        int declaredWinner = transformerCourageAndStrengthComparator.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsSecondTransformerAndStrengthComparatorReturnsNeitherTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleComparator.NEITHER_OPPONENT;

        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.SECOND_OPPONENT);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthComparator.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsNeitherTransformerAndStrengthComparatorReturnsSecondTransformer_returnsNeitherTransformer() {
        int expectedWinner = TransformerBattleComparator.NEITHER_OPPONENT;

        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                TransformerBattleComparator.SECOND_OPPONENT);

        int declaredWinner = transformerCourageAndStrengthComparator.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsFirstTransformerAndStrengthComparatorReturnsFirstTransformer_returnsFirstTransformer() {
        int expectedWinner = TransformerBattleComparator.FIRST_OPPONENT;

        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthComparator.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withCourageComparatorReturnsSecondTransformerAndStrengthComparatorReturnsSecondTransformer_returnsSecondTransformer() {
        int expectedWinner = TransformerBattleComparator.SECOND_OPPONENT;

        when(mockTransformerCourageComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);
        when(mockTransformerStrengthComparator.compare(mockSoundwaveDecepticon, mockBluestreakAutobot)).thenReturn(
                expectedWinner);

        int declaredWinner = transformerCourageAndStrengthComparator.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

}
