package patrickwong.unmapped.outdoors.village;

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain

import com.googlecode.lanterna.gui.Action

public class VillageAction implements Action {
	private static final List<String> villageTypes = [
		"normal",
		"lillith_elder",
		"lillith_blacksmith",
		"lillith_priest",
		"lillith_all",
		"baphomet_elder",
		"baphomet_blacksmith",
		"baphomet_priest",
		"baphomet_all",
		"samael_elder",
		"samael_blacksmith",
		"samael_priest",
		"samael_all",
		"akumasama_elder",
		"akumasama_blacksmith",
		"akumasama_priest",
		"akumasama_all"
	]
	private String villageType
	
	public VillageAction() {
		int rand = DiceRoller.nextInt(villageTypes.size())
		villageType = villageTypes.get(rand)
	}
	
	public VillageAction(String villageType) {
		this.villageType = villageType
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new VillageWindow(villageType)
		UnmappedMain.closeCurrent()
	}

}
