package cellsociety_team18;

import java.io.File;



public class WatorGame extends Game{

	
	//lots of similar code with method in game. will need to extract method from there.
	@Override
	public void setup(){
		setName("wator");
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		setupBasicInfo(xmlFile);
		//not sure this is needed, will remove in the future
		State[] states = {
				new EmptyState(),
				new FishState(),
				new SharkState()
		};
		setStates(states);
	}
	/*
	 * if we do it this way we will get too many sharks and they will take over
	 * */
	@Override
	public State getRandomState(Cell cell) {
		int rand = (int)Math.random()*3;
		if (rand == 0)
			return new EmptyState(cell);
		if (rand == 1)
			return new FishState(cell);
		return new SharkState(cell);
	}

}
