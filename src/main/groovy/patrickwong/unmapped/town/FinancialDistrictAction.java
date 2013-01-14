package patrickwong.unmapped.town;

import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;

public class FinancialDistrictAction implements Action {

	@Override
	public void doAction() {
		UnmappedMain.getGUI().getActiveWindow().close();
		UnmappedMain.getGUI().showWindow(new FinancialDistrictWindow(), GUIScreen.Position.CENTER);
	}

}
