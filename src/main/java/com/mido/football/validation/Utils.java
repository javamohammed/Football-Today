package com.mido.football.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

	public static List<String> foots(){
		List<String> ff = new ArrayList<String>();
		//right and left
		ff.add("Left");
		ff.add("Right");
		return ff;
		
	}
	
	public static List<Position> positions(){
		List<Position> pp = new ArrayList<Position>(Arrays.asList(new Position("GK","goal keeper") , new Position("DF","defender") , new Position("CB","Center Back") , new Position("SW","Sweeper Libero") , new Position("RB","Right Back") , new Position("LB","Left Back") , new Position("RWB","Right Wingback") , new Position("LWB","Left Wingback") , new Position("MF","Midfielder") , new Position("DM","Defensive midfielder") , new Position("CM","Center midfielder") , new Position("AM","Attacking midfielder") , new Position("LM","Left Wide midfielder") , new Position("RM","Right Wide midfielder") , new Position("FW","Forward") , new Position("ST","Striker") , new Position("RF","Right Forward") , new Position("CF","Center Forward") , new Position("LF","Left Forward") , new Position("SS","Secondary striker") , new Position("RW","Left winger") , new Position("LW","Right winger") ));
		return pp;
		
	}
}
