package ab.demo.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardService {

    private Game currentGame;
    private final List<Game> games = new ArrayList<>();

    public void startGame(String homeTeam, String awayTeam) {
        currentGame = Game.createGame(obtainID(), homeTeam, awayTeam);
    }

    public void finishGame() {
        games.add(currentGame);
        currentGame = null;
    }

    public void updateScore(byte homeTeamScore, byte awayTeamScore) {
        currentGame.updateGame(homeTeamScore, awayTeamScore);
    }

    public String getSummaryByTotal() {
        List<Game> sorted = new ArrayList<>(games);
        sorted.sort(Comparator.comparing(Game::getTotalScore).thenComparing(Game::getId));
        return sorted.stream().map(this::printGame).collect(Collectors.joining("\n"));
    }

    private int obtainID() {
        return games.isEmpty() ? 1 : games.size() + 1;
    }

    private String printGame(Game game) {
        return String.format("%s %d - %s %d", game.getHomeTeam(), game.getHomeTeamScore(),
                game.getAwayTeam(), game.getAwayTeamScore());
    }
}
