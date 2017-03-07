package martelc.cybertron.domain.game;

import martelc.cybertron.domain.battles.BattleStrategy;
import martelc.cybertron.domain.battles.OncePerCombaticonBattleStrategy;
import martelc.cybertron.domain.comparators.TransformerBattleComparator;
import martelc.cybertron.domain.comparators.TransformerBattleComparatorChain;
import martelc.cybertron.domain.comparators.TransformerCourageAndStrengthComparator;
import martelc.cybertron.domain.comparators.TransformerCourageComparator;
import martelc.cybertron.domain.comparators.TransformerNameComparator;
import martelc.cybertron.domain.comparators.TransformerRatingComparator;
import martelc.cybertron.domain.comparators.TransformerSkillComparator;
import martelc.cybertron.domain.comparators.TransformerStrengthComparator;
import martelc.cybertron.domain.exceptions.OptimusPrimeMeetsPredakingException;
import martelc.cybertron.domain.ratings.FiveCriterionTransformerRatingStrategy;
import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.BrainstormAutobot;
import martelc.cybertron.domain.transformers.GalvatronDecepticon;
import martelc.cybertron.domain.transformers.HubcapAutobot;
import martelc.cybertron.domain.transformers.MegatronDecepticon;
import martelc.cybertron.domain.transformers.MetroplexAutobot;
import martelc.cybertron.domain.transformers.OptimusPrimeAutobot;
import martelc.cybertron.domain.transformers.PredakingDecepticon;
import martelc.cybertron.domain.transformers.RepugnusAutobot;
import martelc.cybertron.domain.transformers.ShrapnelDecepticon;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import martelc.cybertron.domain.transformers.Transformer;
import martelc.cybertron.domain.transformers.VortexDecepticon;
import martelc.cybertron.domain.transformers.WheeljackAutobot;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.junit.Assert.assertFalse;

public class GameIT {

    private final Transformer optimusPrimeAutobot = new OptimusPrimeAutobot.OptimusPrimeAutobotBuilder().build();
    private final Transformer bluestreakAutobot = new BluestreakAutobot.BluestreakAutobotBuilder().build();
    private final Transformer brainstormAutobot = new BrainstormAutobot.BrainstormAutobotBuilder().build();
    private final Transformer hubcapAutoboot = new HubcapAutobot.HubcapAutobotBuilder().build();
    private final Transformer metroplexAutobot = new MetroplexAutobot.MetroplexAutobotBuilder().build();
    private final Transformer repugnusAutobot = new RepugnusAutobot.RepugnusAutobotBuilder().build();
    private final Transformer wheeljackAutobot = new WheeljackAutobot.WheeljackAutobotBuilder().build();

    private final Transformer predakingDecepticon = new PredakingDecepticon.PredakingDecepticonBuilder().build();
    private final Transformer soundwaveDecepticon = new SoundwaveDecepticon.SoundwaveDecepticonBuilder().build();
    private final Transformer galvatronDecepticon = new GalvatronDecepticon.GalvatronDecepticonBuilder().build();
    private final Transformer megatronDecepticon = new MegatronDecepticon.MegatronDecepticonBuilder().build();
    private final Transformer shrapnelDecepticon = new ShrapnelDecepticon.ShrapnelDecepticonBuilder().build();
    private final Transformer vortexDecepticon = new VortexDecepticon.VortexDecepticonBuilder().build();

    private final TransformerBattleComparator transformerBattleComparator = new TransformerBattleComparator(
            new TransformerBattleComparatorChain(
                    new TransformerNameComparator(),
                    new TransformerCourageAndStrengthComparator(
                            new TransformerCourageComparator(),
                            new TransformerStrengthComparator()),
                    new TransformerSkillComparator(),
                    new TransformerRatingComparator(
                            new FiveCriterionTransformerRatingStrategy())
            ));

    private final BattleStrategy battleStrategy = new OncePerCombaticonBattleStrategy(transformerBattleComparator);
    private final Game game = new Game(battleStrategy);

    private final Set<Transformer> combaticons = new HashSet<>();

    @Before
    public void setup() {
        combaticons.clear();
    }

    @Test
    public void play_withExampleGame_returnsOneBattleWithDecepticonsWinning() {
        combaticons.add(hubcapAutoboot);
        combaticons.add(bluestreakAutobot);
        combaticons.add(soundwaveDecepticon);

        game.play(combaticons);

        int numberOfBattles = game.getNumberOfBattles();
        Team autobotTeam = game.getAutobotTeam();
        Team decepticonTeam = game.getDecepticonTeam();

        assertThat(numberOfBattles).isEqualTo(1);
        assertThat(autobotTeam.getCombaticons().size()).isEqualTo(1);
        assertThat(decepticonTeam.getCombaticons().size()).isEqualTo(1);
        assertThat(autobotTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(1);
        assertThat(decepticonTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(0);
        assertThat(autobotTeam.getCombaticons().contains(hubcapAutoboot));
        assertThat(decepticonTeam.getCombaticons().contains(soundwaveDecepticon));
    }

    @Test
    public void play_withOptimusPrimeInTheGame_returnsOneBattleWithAutobotsWinning() {
        combaticons.add(hubcapAutoboot);
        combaticons.add(optimusPrimeAutobot);
        combaticons.add(soundwaveDecepticon);

        game.play(combaticons);

        int numberOfBattles = game.getNumberOfBattles();
        Team autobotTeam = game.getAutobotTeam();
        Team decepticonTeam = game.getDecepticonTeam();

        assertThat(numberOfBattles).isEqualTo(1);
        assertThat(autobotTeam.getCombaticons().size()).isEqualTo(2);
        assertThat(decepticonTeam.getCombaticons().size()).isEqualTo(0);
        assertThat(autobotTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(0);
        assertThat(decepticonTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(1);
        assertThat(autobotTeam.getCombaticons().contains(hubcapAutoboot));
        assertThat(autobotTeam.getCombaticons().contains(optimusPrimeAutobot));
        assertFalse(decepticonTeam.getCombaticons().contains(soundwaveDecepticon));
    }

    @Test
    public void play_withPredakingInTheGame_returnsOneBattleWithDecepticonsWinning() {
        combaticons.add(hubcapAutoboot);
        combaticons.add(bluestreakAutobot);
        combaticons.add(predakingDecepticon);

        game.play(combaticons);

        int numberOfBattles = game.getNumberOfBattles();
        Team autobotTeam = game.getAutobotTeam();
        Team decepticonTeam = game.getDecepticonTeam();

        assertThat(numberOfBattles).isEqualTo(1);
        assertThat(autobotTeam.getCombaticons().size()).isEqualTo(1);
        assertThat(decepticonTeam.getCombaticons().size()).isEqualTo(1);
        assertThat(autobotTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(1);
        assertThat(decepticonTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(0);
        assertThat(autobotTeam.getCombaticons().contains(hubcapAutoboot));
        assertFalse(autobotTeam.getCombaticons().contains(bluestreakAutobot));
        assertThat(autobotTeam.getCombaticons().contains(predakingDecepticon));
    }

    @Test
    public void play_withOptimusPrimeAndPredakingInTheGame_throwsOptimusPrimeMeetsPredakingException() {
        combaticons.add(hubcapAutoboot);
        combaticons.add(optimusPrimeAutobot);
        combaticons.add(predakingDecepticon);

        try {
            game.play(combaticons);
            failBecauseExceptionWasNotThrown(OptimusPrimeMeetsPredakingException.class);

        } catch (OptimusPrimeMeetsPredakingException e) {
            Team autobotTeam = game.getAutobotTeam();
            Team decepticonTeam = game.getDecepticonTeam();
            int numberOfBattles = game.getNumberOfBattles();

            assertThat(numberOfBattles).isEqualTo(0);
            assertThat(autobotTeam.getCombaticons().isEmpty());
            assertThat(decepticonTeam.getCombaticons().isEmpty());
            assertThat(autobotTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(0);
            assertThat(decepticonTeam.getNumberOfTeamCombaticonsDestroyed()).isEqualTo(0);
        }
    }

    @Test
    public void play_withoutOptimusPrimeAndPredaking_returnsOneBattleWithDecepticonsWinning() {
        combaticons.add(bluestreakAutobot);
        combaticons.add(brainstormAutobot);
        combaticons.add(hubcapAutoboot);
        combaticons.add(metroplexAutobot);
        combaticons.add(repugnusAutobot);
        combaticons.add(wheeljackAutobot);

        combaticons.add(galvatronDecepticon);
        combaticons.add(megatronDecepticon);
        combaticons.add(shrapnelDecepticon);
        combaticons.add(soundwaveDecepticon);
        combaticons.add(vortexDecepticon);

        game.play(combaticons);

        int numberOfBattles = game.getNumberOfBattles();
        Team autobotTeam = game.getAutobotTeam();
        Team decepticonTeam = game.getDecepticonTeam();
        int numberOfRemainingCombaticons = autobotTeam.getCombaticons().size() +
                decepticonTeam.getCombaticons().size();
        int numberOfCombaticonsDifference = combaticons.size() - numberOfRemainingCombaticons;

        assertThat(numberOfBattles).isEqualTo(5);
        assertThat(numberOfCombaticonsDifference).isEqualTo(numberOfBattles);
    }
}
