 batting = load '/datastore/lahman591-csv/Batting.csv' using PigStorage(',');
 players = FOREACH batting GENERATE $0 as playerID, $1 as year, $8 as runs;
 grp_by_player= GROUP players BY playerID;
 GRP_YR_PLAYER= FOREACH grp_by_player GENERATE group as playerID,players.year as play_yr,players.runs as player_run; 
 max_runs_player_bag = FOREACH grp_by_player GENERATE group as playerID,MAX(players.runs) as max_runs_player;
 max_player_year= JOIN max_runs_player_bag BY (playerID,max_runs_player) LEFT OUTER,players BY (playerID,runs);
 
 final_result_player_run= FOREACH max_player_year GENERATE $0 as playerID,$3 as year,$1 as run;
 
 final_result_player_run_order = ORDER final_result_player_run by playerID,year;
 
 
 sp_player = filter players by playerID == 'zuverge01';
 
 

 
