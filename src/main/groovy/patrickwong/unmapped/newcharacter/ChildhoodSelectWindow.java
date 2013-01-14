package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;

public class ChildhoodSelectWindow extends Window {
	private PlayerCharacter tempCharacter;
	
	public ChildhoodSelectWindow() {
		super("Your childhood consisted of...");
		tempCharacter = GameState.getInstance().getTempCharacter();
		
		ActionListBox chooseChildhoodList = new ActionListBox();
		chooseChildhoodList.addAction("...starving", new StarvingAction());
		chooseChildhoodList.addAction("...begging", new BeggingAction());
		chooseChildhoodList.addAction("...stealing", new StealingAction());
		chooseChildhoodList.addAction("...working", new WorkingAction());
		chooseChildhoodList.addAction("...learning", new LearningAction());
		chooseChildhoodList.addAction("...playing", new PlayingAction());
		chooseChildhoodList.addAction("...wrestling", new WrestlingAction());
		
		addComponent(chooseChildhoodList);
	}
	
	class StarvingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("starving");
			String[] bonuses = {"STR", "TGH", "END", "HLT", "WIT"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new TeenageSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class BeggingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("begging");
			String[] bonuses = {"VER", "REF", "END", "HLT", "WIT"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new TeenageSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class StealingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("stealing");
			String[] bonuses = {"DEX", "AGI", "END", "HLT", "MEM"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new TeenageSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class WorkingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("working");
			String[] bonuses = {"DEX", "TGH", "END", "VER", "MEM"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new TeenageSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class LearningAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("learning");
			String[] bonuses = {"LOG", "MEM", "VER", "DEX", "STR"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new TeenageSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class PlayingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("playing");
			String[] bonuses = {"AGI", "DEX", "STR", "HLT", "REF"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new TeenageSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class WrestlingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("wrestling");
			String[] bonuses = {"STR", "TGH", "END", "HLT", "REF"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new TeenageSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
}
