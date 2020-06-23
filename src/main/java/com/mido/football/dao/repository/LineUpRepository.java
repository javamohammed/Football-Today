package com.mido.football.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mido.football.entity.LineUp;

public interface LineUpRepository extends JpaRepository<LineUp, Integer> {

	public LineUp findByMatch_id(int MatchId);
}
