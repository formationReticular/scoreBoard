# Score Board
Football World Cup Score Board library

Entry point is "ScoreBoardService", has following contract:  
1) Start game (captures Home Team and Away Team)  
start current game with team names, set simple incremental ID
2) Finish game  
 add cuurent game to game storage, reset current game  
3) Update score  
update pair of home and away team score, update total score as sum  
4) Get summary by total score  
returns sorted list of all games in format "HomeTeam 1 - AwayTeam 0"  
games sorted by descending total score, if total score is same - sorted by the most recent


   
