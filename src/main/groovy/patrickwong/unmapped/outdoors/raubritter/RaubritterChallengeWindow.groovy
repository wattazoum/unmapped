package patrickwong.unmapped.outdoors.raubritter

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatState
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.outdoors.OutsideWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class RaubritterChallengeWindow extends Window {
	private int wr
	public RaubritterChallengeWindow(int wavesRemaining) {
		super("Raubritter Tower - Challenge")
		wr = wavesRemaining
		UnmappedMain.log.debug("waves remaining: $wr")
		if (wavesRemaining > 0) {
			addComponent(new Label("The raubritter did not consider you worthy of direct combat.\nHis trusted men come down to fight you instead."))
			addComponent(new Label("You decide to..."))
			addComponent(new Button("...fight them head-on", challengeBattleAction))
			addComponent(new Button("...run away", runAwayAction))
		} else {
			addComponent(new Label("With no more men left to man the walls, the raubritter has no choice but\nto come down and fight you himself."))
			addComponent(new Label("You decide to..."))
			addComponent(new Button("...meet him in battle!", challengeBattleAction))
			addComponent(new Button("...run away!", runAwayAction))
		}
	}
	
	Action challengeBattleAction = {
		CombatState state = new CombatStateRaubritterChallenge(wr)
		InterfaceState.nextWindow = new CombatWindow(state)
		UnmappedMain.closeCurrent()
	} as Action
	
	Action runAwayAction = {
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "The raubritter and his men laugh at you.\n'What happened to all that talk?' they taunt.")
		InterfaceState.nextWindow = new OutsideWindow()
		UnmappedMain.closeCurrent()
	} as Action
	
	Action mainBattleAction = {
		CombatState state = new CombatStateRaubritterMain(false, true)
		InterfaceState.nextWindow = new CombatWindow(state)
		UnmappedMain.closeCurrent()
	} as Action
}
