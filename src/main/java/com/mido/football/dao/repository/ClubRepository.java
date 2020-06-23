package com.mido.football.dao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mido.football.entity.Club;
public interface ClubRepository extends JpaRepository<Club, Integer> {

	public Club findByManager_id(int ManagerId);
	public List<Club> findByLeague_id(int leagueId);
}
