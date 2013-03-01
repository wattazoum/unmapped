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

public class RaubritterSiegeWindow extends Window {
	private int wr
	public RaubritterSiegeWindow(int wavesRemaining) {
		super("Raubritter Tower - Besieging")
		wr = wavesRemaining
		UnmappedMain.log.debug("waves remaining in siege: $wr")
		if (wr > 0) {
			addComponent(new Label("While the raubritter sits safe in his tower,\nreinforcements are coming to break the siege from behind."))
			addComponent(new Label("You decide to..."))
			addComponent(new Button("...fight them and maintain the siege", siegeBattleAction))
			addComponent(new Button("...break the siege and run away", runAwayAction))
		} else {
			addComponent(new Label("You have defeated all the reinforcements,\nand food is running low in the tower.\nThe raubritter himself sallies forth with his entourage!"))
			addComponent(new Label("You decide to..."))
			addComponent(new Button("...fight him!", mainBattleAction))
			addComponent(new Button("...run away!", runAwayAction))
		}
	}
	
	Action siegeBattleAction = {
		CombatState state = new CombatStateRaubritterSiege(wr)
		InterfaceState.nextWindow = new CombatWindow(state)
		UnmappedMain.closeCurrent()
	} as Action
	
	Action runAwayAction = {
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "Running away", "The raubritter and his men laugh at you.\n'Not so heroic, eh?' they taunt.")
		InterfaceState.nextWindow = new OutsideWindow()
		UnmappedMain.closeCurrent()
	} as Action
	
	Action mainBattleAction = {
		CombatState state = new CombatStateRaubritterMain(false, true)
		InterfaceState.nextWindow = new CombatWindow(state)
		UnmappedMain.closeCurrent()
	} as Action
}
