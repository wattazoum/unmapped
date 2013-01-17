package patrickwong.unmapped.combat

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.GameItem

import com.googlecode.lanterna.gui.Action

class CombatViewItemAction implements Action {
	private GameItem gi;
	private PlayerCharacter pc;
	private CombatState state;
	public CombatViewItemAction(GameItem gi, PlayerCharacter pc, CombatState state) {
		this.gi = gi
		this.pc = pc
		this.state = state
	}
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new CombatViewItemWindow(gi, pc, state)
		UnmappedMain.closeCurrent()
	}

}
