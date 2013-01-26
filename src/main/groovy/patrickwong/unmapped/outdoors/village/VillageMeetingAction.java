package patrickwong.unmapped.outdoors.village;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class VillageMeetingAction implements Action {
	private String vt;
	public VillageMeetingAction(String villageType) {
		vt = villageType;
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new VillageMeetingWindow(vt);
		UnmappedMain.closeCurrent();
	}

}
