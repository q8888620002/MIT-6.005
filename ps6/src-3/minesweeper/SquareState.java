package minesweeper;
/*
 * Enum of the State of a square
 */
public enum SquareState {
	/*
	 * States of a square
	 */
	FLAGGED,
	DUG,
	UNTOUCHED;
	
	/**
	 * @return the String representation of the ENUM type
	 */
	@Override
	public String toString() {
		switch (this) {
		case FLAGGED:
			return "flagged";
		case DUG:
			return "dug";
		case UNTOUCHED:
				return "untouched";
		default:
			return "undefined";
		}
	}
}
