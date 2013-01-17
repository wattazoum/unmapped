package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;

import com.googlecode.lanterna.gui.Action;
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
	
	private static void endGenderSelect() {
		InterfaceState.nextWindow = new BackgroundSelectWindow();
		UnmappedMain.closeCurrent();
	}
	
	class ChooseMaleAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("male");
			endGenderSelect();
		}
	}
	
	class ChooseFemaleAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("female");
			endGenderSelect();
		}
	}
	
	class ChoosePluralAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("plural");
			endGenderSelect();
		}
	}
	
	class ChooseNeuterAction implements Action {
		@Override
		public void doAction() {
			GameState.getInstance().getTempCharacter().setGender("neuter");
			endGenderSelect();
		}
	}
}
