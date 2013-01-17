package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class WhoWillWindow extends Window {
	private List<PlayerCharacter> party;
	
	public WhoWillWindow(String actionName, Closure check) {
		super("Who will " + actionName + "?");
		
		party = GameState.getInstance().getParty();
		
		for (PlayerCharacter pc : party) {
			addComponent(new Button(pc.getName(), new CheckAction(pc, check)));
		}
	}
	
	class CheckAction implements Action {
		private PlayerCharacter pc;
		private Closure c;
		public CheckAction(PlayerCharacter pc, Closure c) {
			this.pc = pc;
			this.c = c;
		}
		@Override
		public void doAction() {
			c.call(pc);
		}
	}
}
