package martelc.cybertron.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import martelc.cybertron.crosscutting.DomainModule;
import martelc.cybertron.domain.game.Game;
import martelc.cybertron.domain.transformers.BluestreakAutobot;
import martelc.cybertron.domain.transformers.HubcapAutobot;
import martelc.cybertron.domain.transformers.SoundwaveDecepticon;
import martelc.cybertron.domain.transformers.Transformer;

import java.util.HashSet;
import java.util.Set;

public class Application {

    private static Injector injector;

    public static void main(String[] args) {
        initializeApplication();
        playGame();
    }

    private static void initializeApplication() {
        injector = Guice.createInjector(new DomainModule());
    }

    private static void playGame() {
        Set<Transformer> combaticons = buildCombaticons();
        Game game = injector.getInstance(Game.class);
        game.play(combaticons);
        System.out.println(game);
    }

    private static Set<Transformer> buildCombaticons() {
        Transformer soundwaveDecepticon = injector.getInstance(SoundwaveDecepticon.class);
        Transformer bluestreakAutobot = injector.getInstance(BluestreakAutobot.class);
        Transformer hubcapAutobot = injector.getInstance(HubcapAutobot.class);

        Set<Transformer> combaticons = new HashSet<>();
        combaticons.add(soundwaveDecepticon);
        combaticons.add(bluestreakAutobot);
        combaticons.add(hubcapAutobot);

        return combaticons;
    }
}
