package martelc.cybertron.domain.game;

import martelc.cybertron.domain.battles.BattleStrategy;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class GameTest {

    private final BluestreakAutobot mockBluestreakAutobot = Mockito.mock(BluestreakAutobot.class);
    private final OptimusPrimeAutobot mockOptimusPrimeAutobot = Mockito.mock(OptimusPrimeAutobot.class);
    private final SoundwaveDecepticon mockSoundwaveDecepticon = Mockito.mock(SoundwaveDecepticon.class);
    private final PredakingDecepticon mockPredakingDecepticon = Mockito.mock(PredakingDecepticon.class);
    private final Team mockAutobotTeam = Mockito.mock(Team.class);
    private final Team mockDecepticonTeam = Mockito.mock(Team.class);
    private final TransformerBattleRuleRoot mockTransformerBattleRuleRoot =
            Mockito.mock(TransformerBattleRuleRoot.class);
    private final BattleStrategy mockBattleStrategy = Mockito.mock(BattleStrategy.class);
    private final Game gameUnderTest = new Game(mockBattleStrategy);

    private final List<Transformer> autobotTeamCombaticons = new ArrayList<>();
    private final List<Transformer> decepticonTeamCombaticons = new ArrayList<>();
    private final Set<Transformer> combaticons = new HashSet<>();

    @Before
    public void setup() {
        autobotTeamCombaticons.clear();
        decepticonTeamCombaticons.clear();
        combaticons.clear();
    }

    @Test
    public void battleTeams_withTwoCombaticonsPerTeam_succeedsWithoutThrowingException() {
        combaticons.add(mockBluestreakAutobot);
        combaticons.add(mockOptimusPrimeAutobot);
        combaticons.add(mockSoundwaveDecepticon);
        combaticons.add(mockPredakingDecepticon);

        autobotTeamCombaticons.add(mockBluestreakAutobot);
        autobotTeamCombaticons.add(mockOptimusPrimeAutobot);
        decepticonTeamCombaticons.add(mockSoundwaveDecepticon);
        decepticonTeamCombaticons.add(mockPredakingDecepticon);

        when(mockAutobotTeam.getCombaticons()).thenReturn(autobotTeamCombaticons);
        when(mockDecepticonTeam.getCombaticons()).thenReturn(decepticonTeamCombaticons);
        when(mockTransformerBattleRuleRoot.compare(
                mockBluestreakAutobot, mockSoundwaveDecepticon)).thenReturn(
                TransformerBattleRuleRoot.FIRST_OPPONENT);
        when(mockTransformerBattleRuleRoot.compare(
                mockOptimusPrimeAutobot, mockPredakingDecepticon)).thenReturn(
                TransformerBattleRuleRoot.FIRST_OPPONENT);
        when(mockBattleStrategy.executeBattle(mockAutobotTeam, mockDecepticonTeam)).thenReturn(2);

        try {
            gameUnderTest.play(combaticons);
        } catch (Exception e) {
            fail("Game.play failed unexpectedly.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void battleTeams_withEmptyCombaticons_throwsIllegalArgumentException() {
        combaticons.clear();
        gameUnderTest.play(combaticons);
    }

    @Test(expected = IllegalArgumentException.class)
    public void battleTeams_withNullCombaticonsPerTeam_throwsIllegalArgumentException() {
        final Set<Transformer> nullCombaticons = null;
        gameUnderTest.play(nullCombaticons);
    }
}
