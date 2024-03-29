package ab.demo.scoreboard;

import org.junit.Assert;
import org.junit.Test;

public class ScoreBoardServiceTest {
    @Test
    public void testSummaryContainsAddedTeams() {
        ScoreBoardService service = new ScoreBoardService();
        String en = "England";
        String de = "Germany";
        String nam = "Namibia";
        String chn = "China";

        service.startGame(en, nam);
        service.finishGame();
        service.startGame(de, chn);
        service.finishGame();

        String summary = service.getSummaryByTotal();
        Assert.assertTrue(summary.contains(en));
        Assert.assertTrue(summary.contains(de));
        Assert.assertTrue(summary.contains(nam));
        Assert.assertTrue(summary.contains(chn));
    }

    @Test
    public void testSummaryContainsLastScore() {
        int lastHomeTeamScore = 4;
        int lastAwayTeamScore = 7;

        ScoreBoardService service = new ScoreBoardService();
        service.startGame("Nicaragua", "Singapore");
        service.updateScore(1, 1);
        service.updateScore(lastHomeTeamScore, lastAwayTeamScore);
        service.finishGame();

        String summary = service.getSummaryByTotal();
        Assert.assertTrue(summary.contains(String.valueOf(lastHomeTeamScore)));
        Assert.assertTrue(summary.contains(String.valueOf(lastAwayTeamScore)));
    }

    @Test
    public void testSummaryContainsGamesInExpectedOrder() {
        ScoreBoardService service = new ScoreBoardService();
        String vnzl = "Venezuela";
        String chl = "Chile";

        service.startGame("Honduras", "Brazil");
        service.updateScore(2, 3);
        service.finishGame();

        service.startGame("Mexico", "Suriname");
        service.updateScore(1, 1);
        service.finishGame();

        service.startGame(vnzl, chl);
        service.updateScore(3, 2);
        service.finishGame();

        String[] summary = service.getSummaryByTotal().split("\n");
        Assert.assertTrue(summary[0].contains(vnzl));
        Assert.assertTrue(summary[0].contains(chl));
    }

    @Test(expected = ScoreBoardException.class)
    public void testOnlyOneCurrentGameCouldBe() {
        ScoreBoardService service = new ScoreBoardService();
        service.startGame("France", "Spain");
        service.startGame("Andorra", "Portugal");
    }

    @Test(expected = ScoreBoardException.class)
    public void testNotPossibleFinishGameTwice() {
        ScoreBoardService service = new ScoreBoardService();
        service.startGame("Moldova", "Albania");
        service.finishGame();
        service.finishGame();
    }

    @Test(expected = ScoreBoardException.class)
    public void testNotPossibleUpdateScoreBeforeGameStarted() {
        ScoreBoardService service = new ScoreBoardService();
        service.updateScore(1, 5);
    }

    @Test
    public void testScoreWasNotUpdatedAndRemainsZero() {
        String zero = "0";
        ScoreBoardService service = new ScoreBoardService();
        service.startGame("Wales", "Norway");
        service.finishGame();
        Assert.assertTrue(service.getSummaryByTotal().contains(zero));
    }

    @Test
    public void testSummaryContainsGamesInFormat() {
        String homeTeam = "Kazakhstan";
        int scoreHome = 3;
        String awayTeam = "Kyrgyzstan";
        int scoreAway = 4;
        String summaryLine = "Kazakhstan 3 - Kyrgyzstan 4";

        ScoreBoardService service = new ScoreBoardService();
        service.startGame(homeTeam, awayTeam);
        service.updateScore(scoreHome, scoreAway);
        service.finishGame();

        Assert.assertEquals(summaryLine, service.getSummaryByTotal());
    }

    @Test
    public void testSummaryContainsOnlyFinishedGames() {
        ScoreBoardService service = new ScoreBoardService();
        service.startGame("Bolivia", "Bosnia");
        service.finishGame();
        service.startGame("Sweden", "Senegal");
        service.finishGame();
        service.startGame("Latvia", "Luxembourg");

        Assert.assertEquals(2, service.getSummaryByTotal().split("\n").length);
    }
}
