package martelc.cybertron.domain.battles;

import martelc.cybertron.domain.rules.TransformerBattleRuleRoot;
import martelc.cybertron.domain.exceptions.OptimusPrimeMeetsPredakingException;
import martelc.cybertron.domain.game.Team;
import martelc.cybertron.domain.transformers.Transformer;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Iterator;

public class OncePerCombaticonBattleStrategy implements BattleStrategy {

    private final TransformerBattleRuleRoot comparator;

    @Inject
    public OncePerCombaticonBattleStrategy(TransformerBattleRuleRoot comparator) {
        this.comparator = comparator;
    }

    @Override
    public int executeBattle(Team autobotTeam, Team decepticonTeam) {
        orderTeamCombaticons(autobotTeam, decepticonTeam);

        int numberOfBattles;

        try {
            numberOfBattles = battleOncePerTeamCombaticon(autobotTeam, decepticonTeam);

        } catch (OptimusPrimeMeetsPredakingException e) {
            autobotTeam.getCombaticons().clear();
            decepticonTeam.getCombaticons().clear();

            throw e;
        }

        return numberOfBattles;
    }

    private void orderTeamCombaticons(Team autobotTeam, Team decepticonTeam) {
        Collections.sort(autobotTeam.getCombaticons());
        Collections.sort(decepticonTeam.getCombaticons());
    }

    private int battleOncePerTeamCombaticon(Team autobotTeam, Team decepticonTeam) {
        int numberOfBattles = 0;

        Iterator<Transformer> autobotIterator = autobotTeam.getCombaticons().iterator();
        Iterator<Transformer> decepticonIterator = decepticonTeam.getCombaticons().iterator();

        while (autobotIterator.hasNext() && decepticonIterator.hasNext()) {
            numberOfBattles++;

            compareAndDestroyCombaticons(autobotTeam, decepticonTeam, autobotIterator, decepticonIterator);
        }

        return numberOfBattles;
    }

    private void compareAndDestroyCombaticons(
            Team autobotTeam,
            Team decepticonTeam,
            Iterator<Transformer> autobotIterator,
            Iterator<Transformer> decepticonIterator) {

        Transformer autobot = autobotIterator.next();
        Transformer decepticon = decepticonIterator.next();

        int compareResult = comparator.compare(autobot, decepticon);


        if (TransformerBattleRuleRoot.NEITHER_OPPONENT == compareResult ||
                TransformerBattleRuleRoot.SECOND_OPPONENT == compareResult) {
            autobotTeam.destroyCombaticon(autobotIterator);
        }

        if (TransformerBattleRuleRoot.NEITHER_OPPONENT == compareResult ||
                TransformerBattleRuleRoot.FIRST_OPPONENT == compareResult) {
            decepticonTeam.destroyCombaticon(decepticonIterator);
        }
    }

}
