package patrickwong.unmapped.town.slumencounters

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain

import com.googlecode.lanterna.gui.Action

public class SlumencounterLowlivesAction implements Action {
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new SlumencounterLowlivesWindow()
		UnmappedMain.closeCurrent()
	}
}
