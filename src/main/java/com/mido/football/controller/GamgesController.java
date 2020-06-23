package com.mido.football.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mido.football.entity.Club;
import com.mido.football.entity.Country;
import com.mido.football.entity.League;
import com.mido.football.entity.LineUp;
import com.mido.football.entity.Manager;
import com.mido.football.entity.Match;
import com.mido.football.entity.Player;
import com.mido.football.service.country.CountryService;
import com.mido.football.service.league.LeagueService;
import com.mido.football.services.ClubService;
import com.mido.football.services.LineUpService;
import com.mido.football.services.MatchService;
import com.mido.football.services.PlayerService;
import com.mido.football.validation.Fixture;
import com.mido.football.validation.FixtureGenerator;
import com.mido.football.validation.Result;

@Controller
public class GamgesController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private PlayerService playerService; 
	
	@Autowired
	private LineUpService lineUpService; 
	
	@Value("${games.home}")
	private String routePath;
	
	@GetMapping("${games.home}")
	public String gamesIndex(Model model) {
		Match  match = new Match();
		List<Country> countries= countryService.getAllCountriesNames();
		List<League> leagues= leagueService.list();
		model.addAttribute("leagues", leagues);
		model.addAttribute("countries", countries);
		model.addAttribute("label", "Games");
		model.addAttribute("match", match);
		return "games/index";
	}
	
	@GetMapping("${leagues.standing}")
	public String gameStanding(@PathVariable int leagueId, Model model) {
		
		model.addAttribute("list", this.calcPoints(leagueId));
		model.addAttribute("matches_league", "matches_league");
		return "games/standing";
	}
	
	
	private List<Result> sortTeams(List<Result> list){
		for (int i = 0; i < list.size(); i++) {
			for (int j = i+1; j < list.size(); j++) {
				if((list.get(i).getPoints() < list.get(j).getPoints())) {
					Result temp1 = list.get(i);
					Result temp2 = list.get(j);
					list.set(i, temp2);
					list.set(j, temp1);
				}
				if((list.get(i).getPoints() == list.get(j).getPoints()) &&  (list.get(i).getDiff() < list.get(j).getDiff())) {
					Result temp1 = list.get(i);
					Result temp2 = list.get(j);
					list.set(i, temp2);
					list.set(j, temp1);
				}
			}
		}
		return list;
	}
	
	private List<Result> calcPoints(int leagueId){
		 List<Match> matchs = matchService.findByLeague_id(leagueId);
		 List<Club> clubs = clubService.findByLeague_id(leagueId);
		 List<Result> list = new  ArrayList<Result>();
		 for (Club club: clubs) {
			 list.add(new Result(club.getName()));
		 }
		 for (Match match : matchs) {
			 
			 if(match.getResult().equalsIgnoreCase("-") || match.getResult() == null  || match.getResult().equalsIgnoreCase("null")) {
				 continue;
			 }
				int goals_local = Integer.parseInt(match.getResult().split("-")[0]);
				int goals_visitor = Integer.parseInt(match.getResult().split("-")[1]);
				for (Result result : list) {
					if(match.getLocal().getName().equalsIgnoreCase(result.getName())) {
						int i = list.indexOf(result);
						result.setGoals(goals_local);
						result.setGoalsReceived(goals_visitor);
						
						if(goals_local > goals_visitor) {
							result.setPoints(3);
						}
						if(goals_local == goals_visitor) {
							result.setPoints(1);
						}
						list.set(i, result);
					}
					
					if(match.getVisitor().getName().equalsIgnoreCase(result.getName())) {
						int i = list.indexOf(result);
						result.setGoals(goals_visitor);
						result.setGoalsReceived(goals_local);
						if(goals_visitor > goals_local) {
							result.setPoints(3);
						}
						if(goals_local == goals_visitor) {
							result.setPoints(1);
						}
						list.set(i, result);
					}
				}
			}
		return sortTeams(list);
	}
	
	@GetMapping("${games.match}")
	public String matchFill(@PathVariable int matchId, Model model) {
		String user = "admin";
		Match  match = matchService.findById(matchId);
		
		model.addAttribute("label", "Games");
		model.addAttribute("match", match);
		/*
		 * Add after security*/
		if(user.equalsIgnoreCase("admin")) {
		List<Player> locals = playerService.findByClubId(match.getLocal().getId());
		List<Player> visitors = playerService.findByClubId(match.getVisitor().getId());
		model.addAttribute("locals", locals);
		model.addAttribute("visitors", visitors);
			return "games/match";
		}
		LineUp lineUp = lineUpService.findByMatch_id(matchId);
		
		if(lineUp != null) {
			List<String> teamL = this.getLineUpTeam(lineUp.getPlayerTeam_1().split("#"));
			List<String> teamV =this.getLineUpTeam(lineUp.getPlayerTeam_2().split("#"));
			model.addAttribute("teamL", teamL);
			model.addAttribute("teamV", teamV);
			
			String resultL = match.getResult().split("-")[0];
			String resultV = match.getResult().split("-")[1];
			model.addAttribute("resultL", resultL);
			model.addAttribute("resultV", resultV);
			
			List<String> teamLG = this.getGoalsTeam(lineUp.getPlayerTeam_1Goals().split("#"), resultL);
			List<String> teamVG = this.getGoalsTeam(lineUp.getPlayerTeam_2Goals().split("#"), resultV);
			model.addAttribute("teamLG", teamLG);
			model.addAttribute("teamVG", teamVG);
			
		}
		return "games/public_result";
		
	}
	
	private List<String> getGoalsTeam(String[] lineupL, String result){
		List<String> goals = new ArrayList<String>();
		if(!result.equalsIgnoreCase("0")) {
			for (int i = 0; i < lineupL.length; i++) {
				if(!lineupL[i].equalsIgnoreCase("null")) {
					goals.add(lineupL[i].split(",")[1]);
				}
			}
		}
		
		return goals;
	}
	
	private List<String> getLineUpTeam(String[] lineupL){
		List<String> team = new ArrayList<String>();
		for (int i = 0; i < lineupL.length; i++) {
			if(!lineupL[i].equalsIgnoreCase("null")) {
				team.add(lineupL[i].split(",")[1]);
			}
		}
		return team;
	}

	@PostMapping("${games.rm}")
	public String SaveResultAndDayMatch(@PathVariable int leagueId, HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
            String parameterName = params.nextElement();
            String[] pars= parameterName.split("#");
            Match match = matchService.findById(Integer.parseInt(pars[0]));
            if(pars[1].equalsIgnoreCase("daymatch")) {
            	System.out.println("daymatch="+request.getParameter(parameterName));
            	match.setDayMatch(request.getParameter(parameterName));
            }
            if(pars[1].equalsIgnoreCase("result")) {
            	match.setResult(request.getParameter(parameterName));
            }
            System.out.println(match);
            matchService.save(match);
        }
		return "redirect: /football/leagues/matches/"+leagueId;
	}
	
	@PostMapping("${games.match.save}")
	public String gameSaveMatch(@PathVariable int matchId, HttpServletRequest request) {
		Match  match = matchService.findById(matchId);
		LineUp lineUp = new LineUp();
		lineUp.setMatch(match);
		lineUp.setDay(match.getDayMatch());
		int local = match.getLocal().getId();
		int visitor = match.getVisitor().getId();
		int local_goals = 0;
		int visitor_goals = 0;
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
            String parameterName = params.nextElement();
            String[] pars= parameterName.split("#");
            
            if(Integer.parseInt(pars[2]) == local && pars[3].equalsIgnoreCase("lineup")) {
            	String lineup1 = lineUp.getPlayerTeam_1() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_1(lineup1);
            }
            if(Integer.parseInt(pars[2]) == visitor && pars[3].equalsIgnoreCase("lineup")) {
            	String lineup1 = lineUp.getPlayerTeam_2() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_2(lineup1);
            }
            
            if(Integer.parseInt(pars[2]) == local && pars[3].equalsIgnoreCase("goal")) {
            	String lineup1 = lineUp.getPlayerTeam_1Goals() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_1Goals(lineup1);
            	local_goals++;
            }
            if(Integer.parseInt(pars[2]) == visitor && pars[3].equalsIgnoreCase("goal")) {
            	String lineup1 = lineUp.getPlayerTeam_2Goals() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_2Goals(lineup1);
            	visitor_goals ++;
            }
            
            if(Integer.parseInt(pars[2]) == local && pars[3].equalsIgnoreCase("yellow")) {
            	String lineup1 = lineUp.getPlayerTeam_1YellowCards() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_1YellowCards(lineup1);
            }
            if(Integer.parseInt(pars[2]) == visitor && pars[3].equalsIgnoreCase("yellow")) {
            	String lineup1 = lineUp.getPlayerTeam_2YellowCards() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_2YellowCards(lineup1);
            }
            
            if(Integer.parseInt(pars[2]) == local && pars[3].equalsIgnoreCase("red")) {
            	String lineup1 = lineUp.getPlayerTeam_1RedCards() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_1RedCards(lineup1);
            }
            if(Integer.parseInt(pars[2]) == visitor && pars[3].equalsIgnoreCase("red")) {
            	String lineup1 = lineUp.getPlayerTeam_2RedCards() +"#"+pars[0]+","+pars[1];
            	lineUp.setPlayerTeam_2RedCards(lineup1);
            }
            
           // System.out.println(pars[0]+','+pars[1]+','+pars[2]+','+pars[3]);
        }
		String result = local_goals + "-"+visitor_goals;
		match.setResult(result);
		lineUpService.save(lineUp);
		matchService.save(match);
		//System.out.println(result);
		//System.out.println(lineUp);
		return "redirect: /football/leagues/matches/"+match.getLeague().getId();
	}
	
	@PostMapping("${games.save}")
	public String generateWeeks(@Valid @ModelAttribute("Match")  Match match, BindingResult result,Model model) {
		if (result.hasErrors()) {
			
			List<Country> countries= countryService.getAllCountriesNames();
			model.addAttribute("countries", countries);
			model.addAttribute("label", "Games");
			model.addAttribute("match", match);
			return "games/index";
	      }
		List<Club> clubs = clubService.findByLeague_id(match.getLeague().getId());
		int season = Calendar.getInstance().get(Calendar.YEAR);
		this.GenarateMatchs(clubs, season+"", match.getLeague().getId());
		System.out.println(clubs.size()+"--"+ season);
		return "redirect:"+routePath;
	}
	
	
	private void GenarateMatchs(List<Club> clubs, String season, int league_id) {
		
		int size = (clubs.size() - 2) * 2;
		List<String> teams = this.getTeams(clubs);
		FixtureGenerator<String> fixtureGenerator = new FixtureGenerator();
		//List<Match> matchs = new ArrayList<Match>();
		League league = new League();
		league.setId(league_id);
		
		List<List<Fixture<String>>> rounds = fixtureGenerator.getFixtures(teams, true);
		for(int i=0; i<size; i++){
		    List<Fixture<String>> round = rounds.get(i);
		    for(Fixture<String> fixture: round){
		    	Match match = new Match();
				match.setWeek(""+(i+1));
				match.setLeague(league);
				match.setSeason(season);
		    	match.setLocal(new Club(Integer.parseInt(fixture.getHomeTeam())));
		    	match.setVisitor(new Club(Integer.parseInt(fixture.getAwayTeam())));
		    	//match.visitor = fixture.getAwayTeam();
		    	//matchs.add(match);
		    	matchService.save(match);
		    }
		    
		}
		
		/*
		for (Match m : matchs) {
			System.out.println(m.getWeek()+" "+m.getSeason()+" "+m.getLocal().getId()+" "+m.getVisitor().getId());
		}*/
		
	}
	
	private List<String> getTeams(List<Club> clubs){
		List<String> teams = new ArrayList<String>();
		for (Club cl: clubs) {
			if(cl.getId() != 5) {
				teams.add(cl.getId()+"");
			}
			
		}
		return teams;
		
	}
}
