package patrickwong.unmapped.outdoors.village;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class VillageBlacksmithAction implements Action {
	private String villageType;
	
	public VillageBlacksmithAction(String villageType) {
		this.villageType = villageType;
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new VillageBlacksmithWindow(villageType);
		UnmappedMain.closeCurrent();
	}

}
