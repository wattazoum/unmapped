package patrickwong.unmapped.combat

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.enemy.Enemy
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.GUIScreen
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.GUIScreen.Position
import com.googlecode.lanterna.gui.component.Button

public class CombatDecisionWindow extends Window {
	
	private GameState gs
	private CombatState state
	private PlayerCharacter pc
	CombatDecision cd
	
	public CombatDecisionWindow(CombatState combatState, PlayerCharacter playerCharacter) {
		super("Combat - " + playerCharacter.getName())
		
		gs = GameState.getInstance()
		state = combatState
		pc = playerCharacter
		cd = new CombatDecision(pc.getName())
		
		state.removeDecision(pc.getName())
		
		addComponent(new Button("Attack", attackAction))
		addComponent(new Button("Defend", defendAction))
		addComponent(new Button("Special Action", specialAction))
		addComponent(new Button("Use an Item", useItemAction))
		addComponent(new Button("Pass", passAction))
		addComponent(new Button("Assess the situation", assessAction))
		
		if (state.currentCharacter > 0) {
			addComponent(new Button("Previous", previousAction))
		}
		
		def cancelAction = {
			UnmappedMain.closeCurrent()
			UnmappedMain.showWindow(new CombatWindow(state))
		} as Action
		addComponent(new Button("Cancel", cancelAction))
	}
	
	def attackAction = {
		cd.setClosure {
			String action = ""
			Combatant target = DefaultCombatUtil.pcChooseTarget(state)
			if (target == null) {
				action += pc.getName() + pc.getAttackVerb() + "thin air"
				return action
			}
			action = DefaultCombatUtil.doAttack(pc, target)
			return action
		}
		CombatProcessUtil.toNextDecision(cd, state)
	} as Action
	
	def defendAction = {
		cd.setClosure {
			String action = pc.getName() + " defends (not implemented)"
			return action
		}
		CombatProcessUtil.toNextDecision(cd, state)
	} as Action

	def specialAction = {
		cd.setClosure {
			String action = pc.getName() + " does a special action (not implemented)"
			return action
		}
		CombatProcessUtil.toNextDecision(cd, state)
	} as Action

	def useItemAction = {
		UnmappedMain.closeCurrent()
		UnmappedMain.showWindow(new CombatInventoryWindow(pc, state))
	} as Action
	
	def passAction = {
		cd.setClosure {
			String action = pc.getName() + " passes"
			return action
		}
		CombatProcessUtil.toNextDecision(cd, state)
	} as Action

	def assessAction = {
		UnmappedMain.showWindow(new CombatStatusWindow(state, true));
	} as Action
	
	def previousAction = {
		state.currentCharacter -= 1
		UnmappedMain.closeCurrent()
		UnmappedMain.showWindow(new CombatDecisionWindow(state, gs.getCharacter(state.getCurrentCharacter())))
	} as Action
}
