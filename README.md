# Score Board
### Football World Cup Score Board library

Entry point is "ScoreBoardService", it has the following contract:  
1) Start game   
* captures Home Team and Away Team       
* start current game with team names 
* set simple incremental ID
2) Finish game  
*  add current game to game storage 
* reset current game  
3) Update score  
* captures home and away teams score   
* update a pair of home and away team score 
* update total score as sum  
4) Get summary by total score  
* returns sorted list of all games in format "HomeTeam 1 - AwayTeam 0"  
* games sorted by descending total score 
* if total score is same - sorted by the most recent

Assumptions:
- only one game at a time possible
- it's not possible to finish game twice
- need to finish current game before start new
- team's name must be not null/blank
- it's possible to update score only after game started  
- score must be only positive   
- next score for team couldn't be less than current  
- if score wasn't updated after start game and before finish - it stored as "0 - 0"  
- get summary returns only all finished games (except current)  

ToDo:
- add Countries enum + country name validation
 



   
