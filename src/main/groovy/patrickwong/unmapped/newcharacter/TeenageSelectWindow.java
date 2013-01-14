package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;

public class TeenageSelectWindow extends Window {
	
	private PlayerCharacter tempCharacter;
	
	public TeenageSelectWindow() {
		super("Your teen years were spent...");
		tempCharacter = GameState.getInstance().getTempCharacter();
		
		ActionListBox chooseTeenageList = new ActionListBox();
		chooseTeenageList.addAction("...herding animals", new HerdingAction());
		chooseTeenageList.addAction("...being exploited", new ExploitedAction());
		chooseTeenageList.addAction("...fighting", new FightingAction());
		chooseTeenageList.addAction("...in a school", new SchoolAction());
		chooseTeenageList.addAction("...studying to be a priest(ess)", new StudyReligionAction());
		chooseTeenageList.addAction("...attending parties", new PartyingAction());
		chooseTeenageList.addAction("...wandering homelessly", new WanderingAction());
		
		addComponent(chooseTeenageList);
	}
	
	class HerdingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("herding");
			String[] bonuses = {"VER", "REF", "END", "HLT", "MEM"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new AdulthoodSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class ExploitedAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("exploited");
			String[] bonuses = {"END", "REF", "VER", "TGH", "DEX"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new AdulthoodSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class FightingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("fighting");
			String[] bonuses = {"STR", "TGH", "AGI", "DEX", "END"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new AdulthoodSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class SchoolAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("school");
			String[] bonuses = {"DEX", "MEM", "LOG", "VER", "STR"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new AdulthoodSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class StudyReligionAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("studying religion");
			String[] bonuses = {"WIT", "MEM", "DEX", "VER", "SIX"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new AdulthoodSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class PartyingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("partying");
			String[] bonuses = {"WIT", "REF", "DEX", "VER", "END"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new AdulthoodSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
	
	class WanderingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("wandering");
			String[] bonuses = {"END", "HLT", "REF", "MEM", "SIX"};
			tempCharacter.statsAddExp(bonuses, 100);
			getOwner().getActiveWindow().close();
			getOwner().showWindow(new AdulthoodSelectWindow(), GUIScreen.Position.CENTER);
		}
	}
}
