package patrickwong.unmapped.combat

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.GameItem

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class CombatViewItemWindow extends Window {
	private GameItem gi
	private PlayerCharacter pc
	private CombatState state
	public CombatViewItemWindow(GameItem gameItem, PlayerCharacter playerCharacter, CombatState combatState) {
		super(gameItem.getReadableName())
		this.gi = gameItem
		this.pc = playerCharacter
		this.state = combatState
		
		addComponent(new Label(gi.getDesc()))
		if (gi.usableInCombat()) {
			addComponent(new Button("Use", combatUseItemAction))
		}
		addComponent(new Button("Cancel", cancelAction))
	}
	
	def combatUseItemAction = {
		CombatDecision cd = new CombatDecision(pc.getName())
		cd.setClosure {
			return gi.useInCombat(pc, state)
		}
		Closure toNextDecision = CombatProcessUtil.genToNextDecisionClosure()
		toNextDecision(cd, state)
	} as Action
	
	def cancelAction = {
		InterfaceState.nextWindow = new CombatInventoryWindow(pc, state)
		UnmappedMain.closeCurrent()
	} as Action
}
