package ab.demo.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardService {

    private Game currentGame;
    private final List<Game> games = new ArrayList<>();

    public void startGame(String homeTeam, String awayTeam) {
        if (currentGame != null) {
            throw new ScoreBoardException("Need to finish current game first");
        }
        currentGame = Game.createGame(obtainID(), homeTeam, awayTeam);
    }

    public void finishGame() {
        if (currentGame == null) {
            throw new ScoreBoardException("There is no current game to finish");
        }
        games.add(currentGame);
        currentGame = null;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        if (currentGame == null) {
            throw new ScoreBoardException("Need to start a new game first");
        }
        currentGame.updateGame(homeTeamScore, awayTeamScore);
    }

    public String getSummaryByTotal() {
        List<Game> sorted = new ArrayList<>(games);
        sorted.sort(Comparator.comparing(Game::getTotalScore)
                .thenComparing(Game::getId).reversed());
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
