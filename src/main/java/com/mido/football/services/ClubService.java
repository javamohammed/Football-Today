package com.mido.football.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.dao.repository.ClubRepository;
import com.mido.football.entity.Club;
import com.mido.football.entity.Player;

@Service
@Transactional("transactionManagerJpa")
public class ClubService {

	@Autowired
	private ClubRepository clubRepository;
	
	public void save(Club club) {
		clubRepository.save(club);
	}
	
	public List<Club> findAll(){
		return clubRepository.findAll();
	}
	
	public Club findById(int id) {
		Club club = null;
		Optional<Club> result =  clubRepository.findById(id);
		if(result != null) {
			club = result.get();
		}
		return club;
	}
	public Club findByManager_id(int ManagerId) {
		return clubRepository.findByManager_id(ManagerId);
	}
	
	public List<Club> findByLeague_id(int leagueId) {
		return clubRepository.findByLeague_id(leagueId);
	}
}
