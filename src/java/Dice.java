package java;

public class Dice {
	private int sides;

	public Dice(int sidesToBeSet) {
		sides = sidesToBeSet;
	}

	public int rollDice(int sidesToBeSet) {

		return (1 + (int) (Math.random() * sides));
	}
}