package patrickwong.unmapped.combat

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.equipment.GameItem
import patrickwong.unmapped.model.PlayerCharacter

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
		UnmappedMain.closeCurrent()
		UnmappedMain.showWindow(new CombatViewItemWindow(gi, pc, state))
	}

}
