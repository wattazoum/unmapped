package patrickwong.unmapped.combat

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.HolyBook
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class WhichBookWindow extends Window {
	private PlayerCharacter pc
	private CombatState cs
	
	public WhichBookWindow(PlayerCharacter playerCharacter, CombatState state) {
		super(playerCharacter.name + " invoking a book")
		pc = playerCharacter
		cs = state
		
		List<HolyBook> books = GameState.getInstance().getBooksForCombat()
		books.each {
			addComponent(new Button(it.shortName, genInvokeBookAction(it)))
		}
		
		addComponent(new Button("Cancel", cancelAction))
	}
	
	private Action genInvokeBookAction(HolyBook hb) {
		Action theAction = {
			CombatDecision cd = new CombatDecision(pc.getName())
			cd.setClosure {
				return hb.useInCombat(pc, cs)
			}
			Closure toNextDecision = CombatProcessUtil.genToNextDecisionClosure()
			toNextDecision(cd, cs)
		} as Action
		return theAction
	}
	
	Action cancelAction = {
		InterfaceState.nextWindow = new CombatDecisionWindow(cs, pc)
		UnmappedMain.closeCurrent()
	} as Action
}
