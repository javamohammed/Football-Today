package com.mido.football.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.dao.repository.PlayerRepository;
import com.mido.football.entity.Player;

@Service
@Transactional("transactionManagerJpa")
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	public void save(Player player) {
		playerRepository.save(player);
	}
	
	public List<Player> findAll(){
		return playerRepository.findAll();
	}
	
	public List<Player> findByClubId(int clubId){
		return playerRepository.findByClub_id(clubId);
	}
	
	public Player findById(int id) {
		Player player = null;
		Optional<Player> result =  playerRepository.findById(id);
		if(result != null) {
			player = result.get();
		}
		return player;
	}
}
