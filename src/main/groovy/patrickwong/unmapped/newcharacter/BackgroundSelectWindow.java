package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;

public class BackgroundSelectWindow extends Window {
	private PlayerCharacter tempCharacter;
	
	BackgroundSelectWindow() {
		super("You were born to a family of...");
		tempCharacter = GameState.getInstance().getTempCharacter();
		
		ActionListBox chooseBackgroundList = new ActionListBox();
		chooseBackgroundList.addAction("...low slaves", new LowSlaveAction());
		chooseBackgroundList.addAction("...high slaves", new HighSlaveAction());
		chooseBackgroundList.addAction("...servants", new ServantAction());
		chooseBackgroundList.addAction("...tenants", new TenantAction());
		chooseBackgroundList.addAction("...landowners", new LandownerAction());
		chooseBackgroundList.addAction("...nobility", new NobilityAction());
		chooseBackgroundList.addAction("...travellers", new TravellersAction());
		
		addComponent(chooseBackgroundList);
	}
	
	private static void endBackground() {
		InterfaceState.nextWindow = new ChildhoodSelectWindow();
		UnmappedMain.closeCurrent();
	}
	
	class LowSlaveAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setBackground("low slave");
			String[] bonuses = {"STR", "TGH", "END", "HLT", "VER"};
			tempCharacter.statsAddExp(bonuses, 200);
			endBackground();
		}
	}
	
	class HighSlaveAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setBackground("high slave");
			String[] bonuses = {"STR", "VER", "END", "HLT", "TGH"};
			tempCharacter.statsAddExp(bonuses, 200);
			endBackground();
		}
	}
	
	class ServantAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setBackground("servant");
			String[] bonuses = {"DEX", "AGI", "END", "HLT", "VER"};
			tempCharacter.statsAddExp(bonuses, 200);
			endBackground();
		}
	}
	
	class TenantAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setBackground("tenant");
			String[] bonuses = {"STR", "LOG", "END", "REF", "DEX"};
			tempCharacter.statsAddExp(bonuses, 200);
			endBackground();
		}
	}
	
	class LandownerAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setBackground("landowner");
			String[] bonuses = {"LOG", "MEM", "VER", "REF", "END"};
			tempCharacter.statsAddExp(bonuses, 200);
			endBackground();
		}
	}
	
	class NobilityAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setBackground("nobility");
			String[] bonuses = {"WIT", "REF", "VER", "AGI", "DEX"};
			tempCharacter.statsAddExp(bonuses, 200);
			endBackground();
		}
	}
	
	class TravellersAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setBackground("traveller");
			String[] bonuses = {"TGH", "END", "HLT", "REF", "LOG"};
			tempCharacter.statsAddExp(bonuses, 200);
			endBackground();
		}
	}
}
