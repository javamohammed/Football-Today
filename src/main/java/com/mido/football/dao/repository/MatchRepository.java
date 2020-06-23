package com.mido.football.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mido.football.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {
	public List<Match> findByLeague_id(int leagueId);

}
