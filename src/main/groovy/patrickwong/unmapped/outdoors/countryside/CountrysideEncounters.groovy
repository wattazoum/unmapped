package patrickwong.unmapped.outdoors.countryside

import patrickwong.unmapped.DiceRoller

import com.googlecode.lanterna.gui.Window

public class CountrysideEncounters {
	public static Window getEncounter() {
		List<Window> encounters = new Vector<Window>()
		encounters.add(new BanditRaidWindow())
		encounters.add(new CaravanEscortWindow())
		encounters.add(new GargoyleAmbushWindow())
		encounters.add(new GozeSanWindow())
		encounters.add(new JoustChallengeWindow())
		encounters.add(new JowlEpsteinWindow())
		encounters.add(new PilgrimEscortWindow())
		encounters.add(new PlaguedPeopleWindow())
		encounters.add(new WolfPackWindow())
		
		int rand = DiceRoller.nextInt(encounters.size())
		return encounters.get(rand)
	}
}
