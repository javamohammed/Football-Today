package com.mido.football.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mido.football.entity.League;
import com.mido.football.services.MatchService;

@Component
public class LeagueAlreadyGeneratedValidator implements ConstraintValidator<LeagueAlreadyGenerated, League> {
	
	@Autowired
	private  MatchService matchService;

	@Override
	public boolean isValid(League league, ConstraintValidatorContext theConstraintValidatorContext) {

		return matchService.leagueAlready(league.getId());
	}

}
