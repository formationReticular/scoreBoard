package ab.demo.scoreboard;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void testCreatedGameHasTeamNamesAndID() {
        int id = 47;
        String homeTeam = "Morocco";
        String awayTeam = "Island";

        Game game = Game.createGame(id, homeTeam, awayTeam);

        Assert.assertEquals(id, game.getId());
        Assert.assertEquals(homeTeam, game.getHomeTeam());
        Assert.assertEquals(awayTeam, game.getAwayTeam());
    }

    @Test
    public void testTotalScoreAfterScoreUpdated() {
        int homeTeamScoreHalf = 1;
        int awayTeamScoreHalf = 2;
        int homeTeamScoreMatch = 3;
        int awayTeamScoreMatch = 2;

        Game game = Game.createGame(59, "Korea", "Canada");
        game.updateGame(homeTeamScoreHalf, awayTeamScoreHalf);
        game.updateGame(homeTeamScoreMatch, awayTeamScoreMatch);

        Assert.assertEquals(homeTeamScoreMatch + awayTeamScoreMatch, game.getTotalScore());
    }

    @Test(expected = GameException.class)
    public void testThrowExceptionWhenTeamNameIsNull() {
        Game.createGame(11, "Chile", null);
    }

    @Test(expected = GameException.class)
    public void testThrowExceptionWhenTeamNameIsBlank() {
        Game.createGame(11, "", "Guatemala");
    }

    @Test(expected = GameException.class)
    public void testThrowExceptionWhenNewScoreIsLess() {
        Game game = Game.createGame(55, "France", "Uzbekistan");
        game.updateGame(1, 3);
        game.updateGame(1, 2);
    }

    @Test(expected = GameException.class)
    public void testThrowExceptionWhenScoreIsNegative() {
        Game game = Game.createGame(55, "Denmark", "Spain");
        game.updateGame(1, -3);
    }
}
