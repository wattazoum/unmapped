package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;

public class HobbySelectWindow extends Window {
	private PlayerCharacter tempCharacter;
	
	public HobbySelectWindow() {
		super("The fates allowed you a hobby of...");
		tempCharacter = GameState.getInstance().getTempCharacter();
		ActionListBox chooseHobbyList = new ActionListBox();
		chooseHobbyList.addAction("...fighting", new FightingAction());
		chooseHobbyList.addAction("...carousing", new CarousingAction());
		chooseHobbyList.addAction("...athletics", new AthleticsAction());
		chooseHobbyList.addAction("...reading", new ReadingAction());
		chooseHobbyList.addAction("...crafting", new CraftingAction());
		chooseHobbyList.addAction("...cooking", new CookingAction());
		chooseHobbyList.addAction("...ranging", new RangingAction());
		
		addComponent(chooseHobbyList);
	}
	
	class FightingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("fighting");
			String[] bonuses = {"WIT", "END", "AGI", "DEX", "REF"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new EndChargenWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class CarousingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("carousing");
			String[] bonuses = {"WIT", "VER", "END", "DEX", "MEM"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new EndChargenWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class AthleticsAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("athletics");
			String[] bonuses = {"END", "HLT", "AGI", "REF", "STR"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new EndChargenWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class ReadingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("reading");
			String[] bonuses = {"LOG", "MEM", "SIX", "DEX", "WIT"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new EndChargenWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class CraftingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("crafting");
			String[] bonuses = {"DEX", "END", "STR", "MEM", "LOG"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new EndChargenWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class CookingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("cooking");
			String[] bonuses = {"END", "DEX", "MEM", "HLT", "SIX"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new EndChargenWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class RangingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("ranging");
			String[] bonuses = {"TGH", "END", "REF", "HLT", "WIT"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new EndChargenWindow(), GUIScreen.Position.CENTER);
		}
	}
}
