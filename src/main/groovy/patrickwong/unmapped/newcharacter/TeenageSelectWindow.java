package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
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
	
	private static void endTeenage() {
		InterfaceState.nextWindow = new AdulthoodSelectWindow();
		UnmappedMain.closeCurrent();
	}
	
	class HerdingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("herding");
			String[] bonuses = {"VER", "REF", "END", "HLT", "MEM"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"farming","cooking","gathering","woodwise","bows","ranged_fighting"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endTeenage();
		}
	}
	
	class ExploitedAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("exploited");
			String[] bonuses = {"END", "REF", "VER", "TGH", "DEX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"etiquette","streetwise","gambling","socializing","performing","melee_evasion"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endTeenage();
		}
	}
	
	class FightingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("fighting");
			String[] bonuses = {"STR", "TGH", "AGI", "DEX", "END"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"fighting","melee_fighting","melee_defense","ranged_fighting","ranged_defense","unarmed"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endTeenage();
		}
	}
	
	class SchoolAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("school");
			String[] bonuses = {"DEX", "MEM", "LOG", "VER", "STR"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"education","ancient_languages","legal","alchemy","etiquette","glassworking"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endTeenage();
		}
	}
	
	class StudyReligionAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("studying religion");
			String[] bonuses = {"WIT", "MEM", "DEX", "VER", "SIX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"education","exegesis","supernatural","meditating","etiquette","exorcism"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endTeenage();
		}
	}
	
	class PartyingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("partying");
			String[] bonuses = {"WIT", "REF", "DEX", "VER", "END"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"socializing","streetwise","gambling","climbing","missile_catching","performing"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endTeenage();
		}
	}
	
	class WanderingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setTeenage("wandering");
			String[] bonuses = {"END", "HLT", "REF", "MEM", "SIX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"outdoors","woodwise","hunting","gathering","ranged_fighting","ranged_defense"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endTeenage();
		}
	}
}
