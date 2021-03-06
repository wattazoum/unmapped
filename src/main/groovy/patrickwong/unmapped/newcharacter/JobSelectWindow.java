package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;

public class JobSelectWindow extends Window {
	private PlayerCharacter tempCharacter;
	
	public JobSelectWindow() {
		super("Your first steady job was...");
		tempCharacter = GameState.getInstance().getTempCharacter();
		ActionListBox chooseJobList = new ActionListBox();
		chooseJobList.addAction("...farming", new FarmerAction());
		chooseJobList.addAction("...fishing", new FisherAction());
		chooseJobList.addAction("...prostitution", new SexAction());
		chooseJobList.addAction("...as a priest(ess)", new PriestAction());
		chooseJobList.addAction("...scrivening", new ScribeAction());
		chooseJobList.addAction("...performing", new PerformerAction());
		chooseJobList.addAction("...banditry", new BanditAction());
		
		addComponent(chooseJobList);
	}
	
	private static void endJobSelect() {
		InterfaceState.nextWindow = new HobbySelectWindow();
		UnmappedMain.closeCurrent();
	}
	
	class FarmerAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setFirstJob("farmer");
			String[] bonuses = {"STR", "TGH", "END", "MEM", "SIX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"crafting","farming","gathering","cooking","woodwise","ranged_fighting"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endJobSelect();
		}
	}
	
	class FisherAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setFirstJob("fisher");
			String[] bonuses = {"STR", "REF", "DEX", "MEM", "HLT"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"outdoors","farming","gathering","cooking","woodwise","hunting"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endJobSelect();
		}
	}
	
	class SexAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setFirstJob("prostitute");
			String[] bonuses = {"VER", "WIT", "MEM", "END", "DEX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"socializing","streetwise","etiquette","gambling","performing","hunting"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endJobSelect();
		}
	}
	
	class PriestAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setFirstJob("priest");
			String[] bonuses = {"MEM", "LOG", "VER", "DEX", "SIX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"supernatural","exegesis","illumination","meditating","praying","fourdimensional"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endJobSelect();
		}
	}
	
	class ScribeAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setFirstJob("scribe");
			String[] bonuses = {"LOG", "DEX", "AGI", "MEM", "SIX"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"education","legal","exegesis","alchemy","ancient_languages","meditating"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endJobSelect();
		}
	}
	
	class PerformerAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setFirstJob("performer");
			String[] bonuses = {"DEX", "AGI", "VER", "WIT", "REF"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"socializing","throwing_weapons","etiquette","ranged_fighting","performing","ranged_evasion"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endJobSelect();
		}
	}
	
	class BanditAction implements Action {
		@Override
		public void doAction() {
			tempCharacter.setFirstJob("bandit");
			String[] bonuses = {"DEX", "TGH", "AGI", "REF", "WIT"};
			tempCharacter.statsAddExp(bonuses, 200);
			String[] skillBonuses = {"ranged_fighting","bows","blowguns","stealth","athletics","woodwise"};
			tempCharacter.skillsAddExp(skillBonuses, 100);
			endJobSelect();
		}
	}
}
