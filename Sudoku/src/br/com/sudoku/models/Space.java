package br.com.sudoku.models;

public class Space {
	
	Integer actual;
	private final boolean fixed;
	private final int expected;
	
	public Space(int spected, boolean fixed) {
		super();
		this.fixed = fixed;
		this.expected = spected;
		
		if (fixed) {
			this.actual = expected;
        }
	}

	public void setActual(Integer actual) {
		if (fixed)return;
	
		this.actual = actual;
	}
	
	public void clearSpace() {
		this.actual = null;
	}


	public Integer getActual() {
		return actual;
	}

	public boolean isFixed() {
		return fixed;
	}

	public int getExpected() {
		return expected;
	}
	
	
}
