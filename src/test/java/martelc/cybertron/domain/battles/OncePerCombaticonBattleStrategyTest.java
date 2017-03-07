package martelc.cybertron.domain.battles;

import martelc.cybertron.domain.battles.BattleStrategy;
import martelc.cybertron.domain.battles.OncePerCombaticonBattleStrategy;
import martelc.cybertron.domain.comparators.TransformerBattleComparator;
import martelc.cybertron.domain.game.Team;
import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.OptimusPrimeAutobot;
import martelc.cybertron.domain.transformers.PredakingDecepticon;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import martelc.cybertron.domain.transformers.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class OncePerCombaticonBattleStrategyTest {

    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final OptimusPrimeAutobot mockOptimusPrimeAutobot = Mockito.mock(OptimusPrimeAutobot.class);
    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final PredakingDecepticon mockPredakingDecepticon = Mockito.mock(PredakingDecepticon.class);
    private final Team mockAutobotTeam = Mockito.mock(Team.class);
    private final Team mockDecepticonTeam = Mockito.mock(Team.class);
    private final TransformerBattleComparator mockTransformerBattleComparator =
            Mockito.mock(TransformerBattleComparator.class);
    private final BattleStrategy battleStrategyUnderTest = new OncePerCombaticonBattleStrategy(mockTransformerBattleComparator);

    private final List<Transformer> autobotTeamCombaticons = new ArrayList<>();
    private final List<Transformer> decepticonTeamCombaticons = new ArrayList<>();

    @Before
    public void setup() {
        autobotTeamCombaticons.clear();
        decepticonTeamCombaticons.clear();
    }

    @Test
    public void battleTeams_withOneCombaticonPerTeam_returnsOneBattle() {
        int expectedNumberOfBattles = 1;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);

        when(mockAutobotTeam.getCombaticons()).thenReturn(autobotTeamCombaticons);
        when(mockDecepticonTeam.getCombaticons()).thenReturn(decepticonTeamCombaticons);
        when(mockTransformerBattleComparator.compare(
                mockBluestreakAutobot, mockSoundwaveDecepticon)).thenReturn(
                        TransformerBattleComparator.FIRST_OPPONENT);

        int numberOfBattles = battleStrategyUnderTest.executeBattle(mockAutobotTeam, mockDecepticonTeam);

        assertThat(numberOfBattles).isEqualTo(expectedNumberOfBattles);
    }

    @Test
    public void battleTeams_withTwoCombaticonsInFirstTeamAndOneCombaticonInSecondTeam_returnsOneBattle() {
        int expectedNumberOfBattles = 1;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        autobotTeamCombaticons.add(mockOptimusPrimeAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);

        when(mockAutobotTeam.getCombaticons()).thenReturn(autobotTeamCombaticons);
        when(mockDecepticonTeam.getCombaticons()).thenReturn(decepticonTeamCombaticons);
        when(mockTransformerBattleComparator.compare(
                mockBluestreakAutobot, mockSoundwaveDecepticon)).thenReturn(
                TransformerBattleComparator.FIRST_OPPONENT);

        int numberOfBattles = battleStrategyUnderTest.executeBattle(mockAutobotTeam, mockDecepticonTeam);

        assertThat(numberOfBattles).isEqualTo(expectedNumberOfBattles);
    }

    @Test
    public void battleTeams_withOneCombaticonsInFirstTeamAndTwoCombaticonsInSecondTeam_returnsOneBattle() {
        int expectedNumberOfBattles = 1;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);
        decepticonTeamCombaticons.add(mockPredakingDecepticon);

        when(mockAutobotTeam.getCombaticons()).thenReturn(autobotTeamCombaticons);
        when(mockDecepticonTeam.getCombaticons()).thenReturn(decepticonTeamCombaticons);
        when(mockTransformerBattleComparator.compare(
                mockBluestreakAutobot, mockSoundwaveDecepticon)).thenReturn(
                TransformerBattleComparator.FIRST_OPPONENT);

        int numberOfBattles = battleStrategyUnderTest.executeBattle(mockAutobotTeam, mockDecepticonTeam);

        assertThat(numberOfBattles).isEqualTo(expectedNumberOfBattles);
    }

    @Test
    public void battleTeams_withTwoCombaticonsPerTeam_returnsTwoBattles() {
        int expectedNumberOfBattles = 2;

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        autobotTeamCombaticons.add(mockOptimusPrimeAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);
        decepticonTeamCombaticons.add(mockPredakingDecepticon);

        when(mockAutobotTeam.getCombaticons()).thenReturn(autobotTeamCombaticons);
        when(mockDecepticonTeam.getCombaticons()).thenReturn(decepticonTeamCombaticons);
        when(mockTransformerBattleComparator.compare(
                mockBluestreakAutobot, mockSoundwaveDecepticon)).thenReturn(
                TransformerBattleComparator.FIRST_OPPONENT);
        when(mockTransformerBattleComparator.compare(
                mockOptimusPrimeAutobot, mockPredakingDecepticon)).thenReturn(
                TransformerBattleComparator.FIRST_OPPONENT);

        int numberOfBattles = battleStrategyUnderTest.executeBattle(mockAutobotTeam, mockDecepticonTeam);

        assertThat(numberOfBattles).isEqualTo(expectedNumberOfBattles);
    }

}
