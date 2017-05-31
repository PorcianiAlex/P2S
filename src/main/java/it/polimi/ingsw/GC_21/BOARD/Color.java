package it.polimi.ingsw.GC_21.BOARD;

public enum Color {
Green, Yellow, Red, Blue, Neutral;
	
	 @Override
	  public String toString() {
	    switch(this) {
	      case Red: return "R";
	      case Blue: return "B";
	      case Green: return "G";
	      case Neutral: return "N";
	      default: return "//";
	    }
	  }
}
