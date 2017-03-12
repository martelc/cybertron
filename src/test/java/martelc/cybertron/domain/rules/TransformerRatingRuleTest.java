package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.ratings.TransformerRatingStrategy;
import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TransformerRatingRuleTest {

    private final TransformerRatingStrategy mockTransformerRatingStrategy = Mockito.mock(TransformerRatingStrategy.class);
    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final TransformerRatingRule transformerRatingRuleUnderTest = new TransformerRatingRule(mockTransformerRatingStrategy);

    @Test
    public void compare_withFirstTransformerWithLowerRating_returnsSecondTransformer() {
        int mockSoundwaveDecepticonRating = 31;
        int mockBluestreakAutobotRating = 37;

        when(mockTransformerRatingStrategy.calculateRating(mockSoundwaveDecepticon))
                .thenReturn(mockSoundwaveDecepticonRating);
        when(mockTransformerRatingStrategy.calculateRating(mockBluestreakAutobot))
                .thenReturn(mockBluestreakAutobotRating);

        int declaredWinner = transformerRatingRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(TransformerBattleRuleRoot.SECOND_OPPONENT);
    }

    @Test
    public void compare_withFirstTransformerWithHigherRating_returnsFirstTransformer() {
        int mockBluestreakAutobotRating = 37;
        int mockSoundwaveDecepticonRating = 31;

        when(mockTransformerRatingStrategy.calculateRating(mockBluestreakAutobot))
                .thenReturn(mockBluestreakAutobotRating);
        when(mockTransformerRatingStrategy.calculateRating(mockSoundwaveDecepticon))
                .thenReturn(mockSoundwaveDecepticonRating);

        int declaredWinner = transformerRatingRuleUnderTest.compare(
                mockBluestreakAutobot, mockSoundwaveDecepticon);

        assertThat(declaredWinner).isEqualTo(TransformerBattleRuleRoot.FIRST_OPPONENT);
    }

    @Test
    public void compare_withFirstTransformerWithSameRatingAsSecondTransformer_returnsNeitherTransformer() {
        int mockBluestreakAutobotRating = 31;
        when(mockTransformerRatingStrategy.calculateRating(mockSoundwaveDecepticon))
                .thenReturn(mockBluestreakAutobotRating);
        when(mockTransformerRatingStrategy.calculateRating(mockBluestreakAutobot))
                .thenReturn(mockBluestreakAutobotRating);

        int declaredWinner = transformerRatingRuleUnderTest.compare(
                mockSoundwaveDecepticon, mockBluestreakAutobot);

        assertThat(declaredWinner).isEqualTo(TransformerBattleRuleRoot.NEITHER_OPPONENT);
    }

}
