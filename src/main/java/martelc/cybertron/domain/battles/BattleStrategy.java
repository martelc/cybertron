package martelc.cybertron.domain.battles;

import martelc.cybertron.domain.game.Team;

public interface BattleStrategy {

    int executeBattle(Team autobotTeam, Team decepticonTeam);

}
