package patrickwong.unmapped.mainmenu;

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.MusicManager
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.newcharacter.NewCharacterWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.GUIScreen
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class MainMenuWindow extends Window {
	
	public MainMenuWindow() {
		super("Main Menu");
		
		boolean isGameInProgress = GameState.getInstance().getGameInProgress();
		if (!isGameInProgress) {
			MusicManager.play("mainmenu");
		}
		
		if (isGameInProgress) {
			addComponent(new Button("Resume Game", GameState.getInstance().currentLocationAction()));
		}
		addComponent(new Button("New Game", newGameAction));
		addComponent(new Button("Load Game (not implemented)", loadGameAction));
		if (isGameInProgress) {
			addComponent(new Button("Save Game (not implemented)", saveGameAction));
		}
		addComponent(new Button("Quit", quitGameAction));
	}
	
	def newGameAction = {
		GameState.wipeGameState()
		InterfaceState.nextWindow = new NewCharacterWindow()
		UnmappedMain.closeCurrent()
	} as Action
	
	def loadGameAction = {
		InterfaceState.nextWindow = new LoadGameWindow()
		UnmappedMain.closeCurrent()
	} as Action
	
	def saveGameAction = {
		InterfaceState.nextWindow = new SaveGameWindow()
		UnmappedMain.closeCurrent()
	} as Action
	
	def quitGameAction = {
		InterfaceState.endGame = true
		UnmappedMain.closeCurrent()
	} as Action
}
