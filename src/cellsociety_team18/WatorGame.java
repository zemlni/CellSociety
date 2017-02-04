package cellsociety_team18;

import java.io.File;
import wator.*;

public class WatorGame extends Game {

	private int fishReproductionTime;
	private int sharkReproductionTime;
	private int originalEnergy;
	private int energyEarned;

	// lots of similar code with method in game. will need to extract method
	// from there.
	@Override
	public void setup() {
		setName("wator");
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		setupBasicInfo(xmlFile);
	}

	/*
	 * if we do it this way we will get too many sharks and they will take over
	 */
	@Override
	public State getRandomState(Cell cell) {
		int rand = (int) Math.random() * 3;
		if (rand == 0)
			return new EmptyState(cell);
		if (rand == 1)
			return new FishState(cell, fishReproductionTime);
		return new SharkState(cell, originalEnergy, energyEarned, sharkReproductionTime);
	}

}
