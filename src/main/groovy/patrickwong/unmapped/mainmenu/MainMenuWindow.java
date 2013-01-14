package patrickwong.unmapped.mainmenu;

import patrickwong.unmapped.MusicManager;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.newcharacter.NewCharacterWindow;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;

public class MainMenuWindow extends Window {
	
	public MainMenuWindow() {
		super("Main Menu");
		
		boolean isGameInProgress = GameState.getInstance().getGameInProgress();
		if (!isGameInProgress) {
			MusicManager.getInstance().play("mainmenu");
		}
		
		if (isGameInProgress) {
			addComponent(new Button("Resume Game", GameState.getInstance().currentLocationAction()));
		}
		
		addComponent(new Button("New Game", new Action() {
			@Override
			public void doAction() {
				getOwner().getActiveWindow().close();
				GameState.wipeGameState();
				getOwner().showWindow(new NewCharacterWindow(), GUIScreen.Position.CENTER);
			}
		}));
		
		addComponent(new Button("Load Game", new Action() {
			@Override
			public void doAction() {
				getOwner().getActiveWindow().close();
				getOwner().showWindow(new LoadGameWindow(), GUIScreen.Position.CENTER);
			}
		}));
		
		if (isGameInProgress) {
			addComponent(new Button("Save Game", new Action() {
				@Override
				public void doAction() {
					getOwner().getActiveWindow().close();
					getOwner().showWindow(new SaveGameWindow(), GUIScreen.Position.CENTER);
				}
			}));
		}
		
		addComponent(new Button("Quit", new Action() {
			@Override
			public void doAction() {
				getOwner().getActiveWindow().close();
			}
		}));
	}
}
