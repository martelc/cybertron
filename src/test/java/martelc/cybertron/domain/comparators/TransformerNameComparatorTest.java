package martelc.cybertron.domain.comparators;

import martelc.cybertron.domain.exceptions.OptimusPrimeMeetsPredakingException;
import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.OptimusPrimeAutobot;
import martelc.cybertron.domain.transformers.PredakingDecepticon;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerNameComparatorTest {

    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final OptimusPrimeAutobot mockOptimusPrimeAutobot = Mockito.mock(OptimusPrimeAutobot.class);
    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final PredakingDecepticon mockPredakingDecepticon = Mockito.mock(PredakingDecepticon.class);

    private TransformerNameComparator transformerNameComparatorUnderTest = new TransformerNameComparator();

    @Test
    public void compare_withFirstTransformerAndSecondTransformerNotInNameComparator_returnsNeitherTransformer() {
        String nameNotInNameComparator = "NameNotInNameComparator";
        when(mockSoundwaveDecepticon.getName()).thenReturn(nameNotInNameComparator);
        when(mockBluestreakAutobot.getName()).thenReturn(nameNotInNameComparator);

        int declaredWinner = transformerNameComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(TransformerBattleComparator.NEITHER_OPPONENT);
    }

    @Test
    public void compare_withFirstTransformerNameInComparator_returnsFirstTransformer() {
        int expectedWinner = TransformerBattleComparator.FIRST_OPPONENT;

        String nameInNameComparator = "Optimus Prime";
        String nameNotInNameComparator = "NameNotInNameComparator";

        when(mockOptimusPrimeAutobot.getName()).thenReturn(nameInNameComparator);
        when(mockSoundwaveDecepticon.getName()).thenReturn(nameNotInNameComparator);

        int declaredWinner = transformerNameComparatorUnderTest.compare(
                mockOptimusPrimeAutobot, mockSoundwaveDecepticon);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compare_withSecondTransformerNameInComparator_returnsSecondTransformer() {
        int expectedWinner = TransformerBattleComparator.SECOND_OPPONENT;

        String nameNotInNameComparator = "NameNotInNameComparator";
        String nameInNameComparator = "Optimus Prime";

        when(mockSoundwaveDecepticon.getName()).thenReturn(nameNotInNameComparator);
        when(mockOptimusPrimeAutobot.getName()).thenReturn(nameInNameComparator);

        int declaredWinner = transformerNameComparatorUnderTest.compare(
                mockSoundwaveDecepticon, mockOptimusPrimeAutobot);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test(expected=OptimusPrimeMeetsPredakingException.class)
    public void compare_withBothTransformerNamesInComparator_throws() {
        String firstNameInNameComparator = "Optimus Prime";
        String secondNameInNameComparator = "Predaking";

        when(mockOptimusPrimeAutobot.getName()).thenReturn(firstNameInNameComparator);
        when(mockPredakingDecepticon.getName()).thenReturn(secondNameInNameComparator);

        transformerNameComparatorUnderTest.compare(
                mockOptimusPrimeAutobot, mockPredakingDecepticon);
    }

}
