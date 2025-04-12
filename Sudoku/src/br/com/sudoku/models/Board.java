package br.com.sudoku.models;

import java.util.Collection;
import java.util.List;

import Enums.GameStatusEnum;

public class Board {
	
	private final List<List<Space>> spaces; 
	
	public Board(List<List<Space>> spaces) {
		super();
		this.spaces = spaces;
	}
	
	public List<List<Space>> getSpaces() {
		return spaces;
	}
	
	public GameStatusEnum getStatus() {
		if(spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && s.getActual() != null)) {
			return GameStatusEnum.NON_STARTED;
			
		}
		
		return spaces.stream().flatMap(Collection::stream).allMatch(s -> s.getActual() != null) ? GameStatusEnum.COMPLETE : GameStatusEnum.INCOMPLETE;
	}
	
	public boolean hasError() {
		
		if (getStatus() == GameStatusEnum.NON_STARTED) {
			return false;
		}
		
		return spaces.stream().flatMap(Collection::stream)
				.anyMatch(s -> s.getActual() != null && s.getExpected() != s.getActual());
	}
	
	public boolean changeValue(final int row, final int col, final int value) {
		
		if (spaces.get(row).get(col).isFixed()) {
			return false;
		}
		
		spaces.get(row).get(col).setActual(value);
		
		return true;
		
	}
	
	public boolean clearValue(final int row, final int col) {
		var space  = spaces.get(row).get(col);
		
		if (space.isFixed()) {
			return false;
		}
		
		space.clearSpace();
		
		return true;
	}
	
	public void Reset() {
		spaces.stream().flatMap(Collection::stream).forEach(s -> s.clearSpace());
	}	
	
	
	public boolean isFinished() {
		
		return !hasError() && getStatus().equals(GameStatusEnum.COMPLETE);
		
	}
}
