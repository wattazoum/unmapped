package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
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
	
	private static void endHobbySelect() {
		InterfaceState.nextWindow = new EndChargenWindow();
		UnmappedMain.closeCurrent();
	}
	
	class FightingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("fighting");
			String[] bonuses = {"WIT", "END", "AGI", "DEX", "REF"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"fighting","melee_fighting","melee_defense","melee_evasion","parrying","swords_straight_one"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endHobbySelect();
		}
	}
	
	class CarousingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("carousing");
			String[] bonuses = {"WIT", "VER", "END", "DEX", "MEM"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"socializing","gambling","streetwise","stealth","weaponcatching","intimidate"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endHobbySelect();
		}
	}
	
	class AthleticsAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("athletics");
			String[] bonuses = {"END", "HLT", "AGI", "REF", "STR"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"athletics","climbing","melee_evasion","stealth","weaponcatching","ranged_evasion"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endHobbySelect();
		}
	}
	
	class ReadingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("reading");
			String[] bonuses = {"LOG", "MEM", "SIX", "DEX", "WIT"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"education","legal","exegesis","cooking","illumination","ancient_languages"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endHobbySelect();
		}
	}
	
	class CraftingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("crafting");
			String[] bonuses = {"DEX", "END", "STR", "MEM", "LOG"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"crafting","woodworking","metalworking","glassworking","stoneworking","cooking"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endHobbySelect();
		}
	}
	
	class CookingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("cooking");
			String[] bonuses = {"END", "DEX", "MEM", "HLT", "SIX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"crafting","cooking","gathering","hunting","woodworking","farming"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endHobbySelect();
		}
	}
	
	class RangingAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setHobby("ranging");
			String[] bonuses = {"TGH", "END", "REF", "HLT", "WIT"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"outdoors","woodwise","athletics","climbing","gathering","hunting"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endHobbySelect();
		}
	}
}
