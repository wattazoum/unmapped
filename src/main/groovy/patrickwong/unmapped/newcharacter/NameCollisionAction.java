package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.dialog.MessageBox;

public class NameCollisionAction implements Action {
	private String charName;
	public NameCollisionAction(String charName) {
		this.charName = charName;
	}
	@Override
	public void doAction() {
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Name conflict!", "There is already a character named " + charName + " in the party.");
	}

}
