package patrickwong.unmapped.outdoors.raubritter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class RaubritterEnterAction implements Action {

	@Override
	public void doAction() {
		InterfaceState.nextWindow = new RaubritterEnterWindow();
		UnmappedMain.closeCurrent();
	}

}
