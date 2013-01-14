package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.model.GameState;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;

public class GenderSelectWindow extends Window {
	
	public GenderSelectWindow() {
		super("Your gender is...");
		ActionListBox chooseGenderList = new ActionListBox();
		chooseGenderList.addAction("...male", new ChooseMaleAction());
		chooseGenderList.addAction("...female", new ChooseFemaleAction());
		chooseGenderList.addAction("...plural", new ChoosePluralAction());
		chooseGenderList.addAction("...neuter", new ChooseNeuterAction());
		addComponent(chooseGenderList);
	}
	
	class ChooseMaleAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("male");
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new BackgroundSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class ChooseFemaleAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("female");
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new BackgroundSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class ChoosePluralAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("plural");
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new BackgroundSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class ChooseNeuterAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("neuter");
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new BackgroundSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
}
