package martelc.cybertron.domain.game;

import martelc.cybertron.domain.battles.BattleStrategy;
import martelc.cybertron.domain.comparators.TransformerBattleComparator;
import martelc.cybertron.domain.transformers.Autobot;
import martelc.cybertron.domain.transformers.Decepticon;
import martelc.cybertron.domain.transformers.Transformer;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private final BattleStrategy battleStrategy;
    private final Team autobotTeam;
    private final Team decepticonTeam;
    private int numberOfBattles = 0 ;

    @Inject
    Game(BattleStrategy battleStrategy) {
        this.battleStrategy = battleStrategy;
        this.autobotTeam = new Team("Autobot");
        this.decepticonTeam = new Team("Decepticon");
    }

    Team getAutobotTeam() {
        return autobotTeam;
    }

    Team getDecepticonTeam() {
        return decepticonTeam;
    }

    int getNumberOfBattles() {
        return numberOfBattles;
    }

    public void play(Set<Transformer> combaticons) {
        throwExceptionIfCombaticonsIsNullOrEmpty(combaticons);
        divideCombaticonsIntoTeams(combaticons);
        battleTeams();
    }

    private void throwExceptionIfCombaticonsIsNullOrEmpty(Set<Transformer> combaticons) {
        if (null == combaticons || combaticons.isEmpty()) {
            throw new IllegalArgumentException("Combaticons is null or empty.");
        }
    }

    private void divideCombaticonsIntoTeams(Set<Transformer> combaticons) {
        List<Transformer> autbotTransformers = combaticons
                .stream()
                .filter(t -> t instanceof Autobot)
                .collect(Collectors.toList());

        List<Transformer> decepticonTransformers = combaticons
                .stream()
                .filter(t -> t instanceof Decepticon)
                .collect(Collectors.toList());

        autobotTeam.addCombaticons(autbotTransformers);
        decepticonTeam.addCombaticons(decepticonTransformers);
    }

    private void battleTeams() {
        numberOfBattles += battleStrategy.executeBattle(autobotTeam,decepticonTeam);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        appendBattles(stringBuilder);
        appendTeams(stringBuilder);

        return stringBuilder.toString();
    }

    private void appendBattles(StringBuilder stringBuilder) {
        stringBuilder.append('\n');
        stringBuilder.append(numberOfBattles);
        stringBuilder.append(" battle");

        if (numberOfBattles != 1) {
            stringBuilder.append('s');
        }
    }

    private void appendTeams(StringBuilder stringBuilder) {
        int compareResult = autobotTeam.compareTo(decepticonTeam);
        Team winningTeam;
        Team losingTeam;

        if (TransformerBattleComparator.FIRST_OPPONENT == compareResult) {
            winningTeam = autobotTeam;
            losingTeam = decepticonTeam;
        } else {
            winningTeam = decepticonTeam;
            losingTeam = autobotTeam;
        }

        stringBuilder.append("\nWinning team ");
        stringBuilder.append(winningTeam);

        stringBuilder.append("\nSurvivors from the losing team ");
        stringBuilder.append(losingTeam);
    }
}
