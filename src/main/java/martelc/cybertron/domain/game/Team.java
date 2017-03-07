package martelc.cybertron.domain.game;

import martelc.cybertron.domain.transformers.Transformer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Team implements Comparable<Team>{

    private final String name;
    private final List<Transformer> combaticons = new ArrayList<>();
    private int numberOfTeamCombaticonsDestroyed = 0;

    Team(String name) {
        this.name = name;
    }

    void addCombaticons(List<Transformer> combaticons) {
        throwExceptionIfCombaticonsIsNullOrEmpty(combaticons);

        this.combaticons.clear();
        this.combaticons.addAll(combaticons);
    }

    void incrementNumberOfTeamCombaticonsDestroyed() {
        numberOfTeamCombaticonsDestroyed++;
    }

    public List<Transformer> getCombaticons() {
        return combaticons;
    }

    int getNumberOfTeamCombaticonsDestroyed() {
        return numberOfTeamCombaticonsDestroyed;
    }

    public void destroyCombaticon(Iterator<Transformer> transformerToDestroy) {
        transformerToDestroy.remove();
        numberOfTeamCombaticonsDestroyed++;
    }

    @Override
    public String toString() {
        return "(" +
                name +
                "): " +
                combaticons
                        .stream()
                        .map(Transformer::getName)
                        .collect(Collectors.joining(", "));
    }

    @Override
    public int compareTo(Team otherTeam) {
        int reverseOrder = -1;

        return reverseOrder * new Integer(this.getNumberOfTeamCombaticonsDestroyed())
                .compareTo(otherTeam.getNumberOfTeamCombaticonsDestroyed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(combaticons);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (!(object instanceof Team))
            return false;

        Team team = (Team) object;
        return combaticons.equals(team.combaticons);

    }

    private void throwExceptionIfCombaticonsIsNullOrEmpty(List<Transformer> combaticons) {
        if (null == combaticons || combaticons.isEmpty()) {
            throw new IllegalArgumentException("Combaticons is null or empty.");
        }
    }
}
