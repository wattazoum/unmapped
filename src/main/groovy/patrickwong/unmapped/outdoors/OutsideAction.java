package patrickwong.unmapped.outdoors;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class OutsideAction implements Action {

	@Override
	public void doAction() {
		InterfaceState.nextWindow = new OutsideWindow();
		UnmappedMain.closeCurrent();
	}

}
