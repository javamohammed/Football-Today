package com.mido.football.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mido.football.entity.Player;

public interface PlayerRepository  extends JpaRepository<Player, Integer>{

	public List<Player> findByClub_id(int clubId);
}
