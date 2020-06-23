package com.mido.football.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.dao.repository.LineUpRepository;
import com.mido.football.entity.LineUp;

@Service
@Transactional("transactionManagerJpa")
public class LineUpService {

	@Autowired
	private LineUpRepository lineUpRepository;
	
	public void save(LineUp lineUp) {
		lineUpRepository.save(lineUp);
		
	}
	
	public LineUp findByMatch_id(int MatchId) {
		return lineUpRepository.findByMatch_id(MatchId);
	}
	
	public LineUp findById(int id) {
		LineUp lineUp = null;
		Optional<LineUp> result =  lineUpRepository.findById(id);
		if(result != null) {
			lineUp = result.get();
		}
		return lineUp;
	}
}
