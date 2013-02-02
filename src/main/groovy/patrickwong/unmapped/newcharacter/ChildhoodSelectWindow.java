package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
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
	
	private static void endChildhood() {
		InterfaceState.nextWindow = new TeenageSelectWindow();
		UnmappedMain.closeCurrent();
	}
	
	class StarvingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("starving");
			String[] bonuses = {"STR", "TGH", "END", "HLT", "WIT"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"gathering","hunting","streetwise","gambling","stealth","praying"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endChildhood();
		}
	}
	
	class BeggingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("begging");
			String[] bonuses = {"VER", "REF", "END", "HLT", "WIT"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"gathering","intimdate","streetwise","gambling","stealth","short_weapons"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endChildhood();
		}
	}
	
	class StealingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("stealing");
			String[] bonuses = {"DEX", "AGI", "END", "HLT", "MEM"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"gathering","climbing","streetwise","gambling","stealth","short_weapons"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endChildhood();
		}
	}
	
	class WorkingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("working");
			String[] bonuses = {"DEX", "TGH", "END", "VER", "MEM"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"farming","cooking","axes","streetwise","woodworking","metalworking"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endChildhood();
		}
	}
	
	class LearningAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("learning");
			String[] bonuses = {"LOG", "MEM", "VER", "DEX", "STR"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"legal","stoneworking","etiquette","ancient_languages","swords_straight_one","alchemy"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endChildhood();
		}
	}
	
	class PlayingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("playing");
			String[] bonuses = {"AGI", "DEX", "STR", "HLT", "REF"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"streetwise","intimdate","unarmed","gambling","stealth","climbing"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endChildhood();
		}
	}
	
	class WrestlingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setChildhood("wrestling");
			String[] bonuses = {"STR", "TGH", "END", "HLT", "REF"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"unarmed","weaponcatching","melee_fighting","melee_evasion","ranged_evasion","intimidate"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endChildhood();
		}
	}
}
