package patrickwong.unmapped.party

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action

public class MoveUpAction implements Action {
		private PlayerCharacter pc;
		public MoveUpAction(PlayerCharacter pc) {
			this.pc = pc;
		}
		@Override
		public void doAction() {
			int originalOrder = pc.getOrder();
			int newOrder = originalOrder - 1;
			List<PlayerCharacter> party = GameState.getInstance().getParty();
			PlayerCharacter charToSwap = party.get(newOrder);
			
			party.remove(pc);
			party.remove(charToSwap);
			
			pc.setOrder(newOrder);
			charToSwap.setOrder(originalOrder);
			
			party.add(pc);
			party.add(charToSwap);
			party = party.sort();
			GameState.getInstance().setParty(party)
			UnmappedMain.closeCurrent()
			UnmappedMain.showWindow(new CharacterMenuWindow(pc.getName()))
		}
	}
