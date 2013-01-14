package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;

public class CharacterMenuAction implements Action {
	
	private String charName;
	
	public CharacterMenuAction(String charName) {
		this.charName = charName;
	}
	
	@Override
	public void doAction() {
		UnmappedMain.getGUI().getActiveWindow().close();
		UnmappedMain.getGUI().showWindow(new CharacterMenuWindow(charName), GUIScreen.Position.CENTER);
	}

}
