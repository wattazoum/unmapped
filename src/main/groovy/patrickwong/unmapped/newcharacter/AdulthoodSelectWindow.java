package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;

public class AdulthoodSelectWindow extends Window {
	private PlayerCharacter tempCharacter;
	
	public AdulthoodSelectWindow() {
		super("You considered yourself an adult after...");
		tempCharacter = GameState.getInstance().getTempCharacter();
		ActionListBox chooseAdulthoodList = new ActionListBox();
		chooseAdulthoodList.addAction("...taking a life", new KillingAction());
		chooseAdulthoodList.addAction("...losing your virginity", new LoseVirginityAction());
		chooseAdulthoodList.addAction("...going through a war", new WarAction());
		chooseAdulthoodList.addAction("...independence from your parents", new IndependenceAction());
		chooseAdulthoodList.addAction("...graduation", new GraduateAction());
		chooseAdulthoodList.addAction("...having responsibilities", new ResponsibilityAction());
		chooseAdulthoodList.addAction("...a special ritual", new RitualAction());
		
		addComponent(chooseAdulthoodList);
	}
	
	class KillingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setAdulthood("taking a life");
			String[] bonuses = {"STR", "TGH", "END", "AGI", "DEX"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new JobSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class LoseVirginityAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setAdulthood("losing virginity");
			String[] bonuses = {"DEX", "VER", "END", "WIT", "MEM"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new JobSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class WarAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setAdulthood("war");
			String[] bonuses = {"STR", "REF", "END", "AGI", "HLT"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new JobSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class IndependenceAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setAdulthood("independence");
			String[] bonuses = {"VER", "WIT", "REF", "TGH", "HLT"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new JobSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class GraduateAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setAdulthood("graduation");
			String[] bonuses = {"LOG", "MEM", "VER", "DEX", "STR"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new JobSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class ResponsibilityAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setAdulthood("responsibility");
			String[] bonuses = {"LOG", "VER", "MEM", "DEX", "WIT"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new JobSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class RitualAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setAdulthood("ritual");
			String[] bonuses = {"DEX", "END", "HLT", "TGH", "SIX"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new JobSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
}
