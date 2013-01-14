package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.mainmenu.MainMenuAction;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;
import patrickwong.unmapped.town.TavernAction;
import patrickwong.unmapped.town.TavernWindow;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class EndChargenWindow extends Window {
	private GameState gameState;
	
	public EndChargenWindow() {
		super("You say you are...");
		gameState = GameState.getInstance();
		PlayerCharacter currentCharacter = gameState.getTempCharacter();
		
		addComponent(new Label(currentCharacter.summaryString()));
		
		if (GameState.getInstance().getGameInProgress()) {
			addComponent(new Button("Add this character to the party", new ConfirmCharacterAction()));
			addComponent(new Button("Cancel", new TavernAction()));
		} else {
			addComponent(new Button("Adventure!", new ConfirmCharacterAction()));
			addComponent(new Button("Cancel", new MainMenuAction()));
		}
	}
	
	class ConfirmCharacterAction implements Action {
		@Override
		public void doAction() {
			gameState.addToParty(gameState.getTempCharacter());
			UnmappedMain.getGUI().getActiveWindow().close();
			UnmappedMain.getGUI().showWindow(new TavernWindow(), GUIScreen.Position.CENTER);
		}
	}
}
