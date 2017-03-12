package martelc.cybertron.domain.game;

import martelc.cybertron.domain.rules.TransformerBattleRuleRoot;
import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.OptimusPrimeAutobot;
import martelc.cybertron.domain.transformers.PredakingDecepticon;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import martelc.cybertron.domain.transformers.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {

    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final OptimusPrimeAutobot mockOptimusPrimeAutobot = Mockito.mock(OptimusPrimeAutobot.class);
    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final PredakingDecepticon mockPredakingDecepticon = Mockito.mock(PredakingDecepticon.class);
    private final Team autobotTeamUnderTest = new Team("Autobot");
    private final Team decepticonTeamUnderTest = new Team("Decepticon");

    private final List<Transformer> autobotTeamCombaticons = new ArrayList<>();
    private final List<Transformer> decepticonTeamCombaticons = new ArrayList<>();

    @Before
    public void setup() {
        autobotTeamCombaticons.clear();
        decepticonTeamCombaticons.clear();
    }

    @Test
    public void compareTo_withOneLessCombaticonInFirstTeamThanSecondTeam_returnsSecondTeam() {
        int expectedWinner = TransformerBattleRuleRoot.SECOND_OPPONENT;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        autobotTeamCombaticons.add(mockOptimusPrimeAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);
        decepticonTeamCombaticons.add(mockPredakingDecepticon);

        autobotTeamUnderTest.addCombaticons(autobotTeamCombaticons);
        decepticonTeamUnderTest.addCombaticons(decepticonTeamCombaticons);

        Iterator<Transformer> autobotIterator = autobotTeamUnderTest.getCombaticons().iterator();
        if (autobotIterator.hasNext()) {
            autobotIterator.next();
            autobotIterator.remove();
            autobotTeamUnderTest.incrementNumberOfTeamCombaticonsDestroyed();
        }

        int declaredWinner = autobotTeamUnderTest.compareTo(decepticonTeamUnderTest);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compareTo_withOneMoreCombaticonInFirstTeamCombaticonsDestroyedInSecondTeam_returnsFirstTeam() {
        int expectedWinner = TransformerBattleRuleRoot.FIRST_OPPONENT;
        int expectedNumberOfDestroyedCombaticons = 1;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        autobotTeamCombaticons.add(mockOptimusPrimeAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);
        decepticonTeamCombaticons.add(mockPredakingDecepticon);

        autobotTeamUnderTest.addCombaticons(autobotTeamCombaticons);
        decepticonTeamUnderTest.addCombaticons(decepticonTeamCombaticons);

        Iterator<Transformer> decepticonIterator = decepticonTeamUnderTest.getCombaticons().iterator();
        int numberOfDecepticonsToDestroy = expectedNumberOfDestroyedCombaticons;
        while (decepticonIterator.hasNext() && numberOfDecepticonsToDestroy > 0) {
            decepticonIterator.next();
            decepticonIterator.remove();
            decepticonTeamUnderTest.incrementNumberOfTeamCombaticonsDestroyed();
            numberOfDecepticonsToDestroy--;
        }

        int declaredWinner = autobotTeamUnderTest.compareTo(decepticonTeamUnderTest);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
        assertThat(decepticonTeamUnderTest.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(expectedNumberOfDestroyedCombaticons);
    }

    @Test
    public void compareTo_withZeroCombaticonDestroyedFromFirstTeamAndZeroCombaticonsDestroyedInSecondTeam_returnsNeitherTeam() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        autobotTeamCombaticons.add(mockOptimusPrimeAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);
        decepticonTeamCombaticons.add(mockPredakingDecepticon);

        autobotTeamUnderTest.addCombaticons(autobotTeamCombaticons);
        decepticonTeamUnderTest.addCombaticons(decepticonTeamCombaticons);

        int declaredWinner = autobotTeamUnderTest.compareTo(decepticonTeamUnderTest);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compareTo_withAllCombaticonsDestroyedOnEachTeam_returnsNeitherTeam() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        autobotTeamCombaticons.add(mockOptimusPrimeAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);
        decepticonTeamCombaticons.add(mockPredakingDecepticon);

        autobotTeamUnderTest.addCombaticons(autobotTeamCombaticons);
        decepticonTeamUnderTest.addCombaticons(decepticonTeamCombaticons);

        Iterator<Transformer> autobotIterator = autobotTeamUnderTest.getCombaticons().iterator();
        while (autobotIterator.hasNext()) {
            autobotIterator.next();
            autobotIterator.remove();
            autobotTeamUnderTest.incrementNumberOfTeamCombaticonsDestroyed();
        }

        Iterator<Transformer> decepticonIterator = decepticonTeamUnderTest.getCombaticons().iterator();
        while (decepticonIterator.hasNext()) {
            decepticonIterator.next();
            decepticonIterator.remove();
            decepticonTeamUnderTest.incrementNumberOfTeamCombaticonsDestroyed();
        }

        int declaredWinner = autobotTeamUnderTest.compareTo(decepticonTeamUnderTest);

        assertThat(declaredWinner).isEqualTo(expectedWinner);
    }

    @Test
    public void compareTo_withZeroCombaticonsOnEachTeam_returnsNeitherTeam() {
        int expectedWinner = TransformerBattleRuleRoot.NEITHER_OPPONENT;

        int declaredWinner = autobotTeamUnderTest.compareTo(decepticonTeamUnderTest);

        assertThat(declaredWinner).isEqualTo(expectedWinner);

    }

}
