package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.dialog.MessageBox;

public class NewCharacterAction implements Action {

	@Override
	public void doAction() {
		if (GameState.getInstance().getParty().size() >= UnmappedMain.MAX_PARTY_SIZE) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Full Party!", "You cannot have more than " + UnmappedMain.MAX_PARTY_SIZE + " people in your party.\nAny more and people will start feeling neglected.");
		} else {
			UnmappedMain.getGUI().getActiveWindow().close();
			UnmappedMain.getGUI().showWindow(new NewCharacterWindow(), GUIScreen.Position.CENTER);
		}
	}

}
