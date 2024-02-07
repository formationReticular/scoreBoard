package ab.demo.scoreboard;

import java.util.Objects;

class Game {
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
        if (homeTeam == null || awayTeam == null
                || homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new GameException("Home team and Away team should be not null/blank");
        }
        return new Game(id, homeTeam, awayTeam);
    }

    void updateGame(int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore < this.homeTeamScore || awayTeamScore < this.awayTeamScore
                || homeTeamScore < 0 || awayTeamScore < 0) {
            throw new GameException("New score value could be less than previous or negative");
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
                '}';
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
