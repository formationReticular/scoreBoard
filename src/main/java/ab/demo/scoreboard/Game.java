package ab.demo.scoreboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

class Game {

    private static final Logger log = LoggerFactory.getLogger(Game.class);
    private final int id;
    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private int totalScore;

    private Game(int id, String homeTeam, String awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    static Game createGame(int id, String homeTeam, String awayTeam) {
        log.info("Creating a new game with id: {}, homeTeam: {}, awayTeam: {}", id, homeTeam, awayTeam);
        if (homeTeam == null || awayTeam == null
                || homeTeam.isBlank() || awayTeam.isBlank()) {
            log.error("homeTeam/awayTeam is null/blank");
            throw new GameException("Home team and Away team should be not null/blank");
        }
        return new Game(id, homeTeam, awayTeam);
    }

    void updateGame(int homeTeamScore, int awayTeamScore) {
        log.info("Updating game with homeTeamScore: {}, awayTeamScore: {}", homeTeamScore, awayTeamScore);
        if (homeTeamScore < this.homeTeamScore || awayTeamScore < this.awayTeamScore) {
            log.error("homeTeamScore/awayTeamScore is less than previous");
            throw new GameException("New score value should be more than previous");
        }
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.totalScore = homeTeamScore + awayTeamScore;
    }

    int getTotalScore() {
        return totalScore;
    }

    int getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", totalScore=" + totalScore +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && Objects.equals(homeTeam, game.homeTeam)
                && Objects.equals(awayTeam, game.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeTeam, awayTeam);
    }
}
