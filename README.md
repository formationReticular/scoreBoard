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
* returns all games joined in one String  

Assumptions:
- only one game at a time possible
- need to finish current game before start new
- it's not possible to finish game twice
- team's name must be not null/blank
- it's possible to update score only after game started  
- score must be only positive   
- next score for team couldn't be less than current  
- if score wasn't updated after start game and before finish - it stored as "0 - 0"  
- get summary returns only all finished games (except current)    
- get summary returns one String with all games  

ToDo:
- add Countries enum + country name validation
- optionally - return summary in both formats: String and List  
 



   
