package patrickwong.unmapped.party.equipment

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.party.CharacterMenuAction;

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class CharacterGripWindow extends Window {
	private PlayerCharacter pc
	private Action rta
	
	public CharacterGripWindow(PlayerCharacter playerCharacter, Action returnAction) {
		super(playerCharacter.name + " - Weapons and Shields")
		pc = playerCharacter
		rta = returnAction
		
		addComponent(new Button("Right Hand: " + pc.rightHand.getReadableName(), handRightAction))
		addComponent(new Button("Left Hand: " + pc.leftHand.getReadableName(), handLeftAction))
		addComponent(new Button("Ranged: " + pc.rangedWeapon.getReadableName(), handRangedAction))
		addComponent(new Button("Cancel", new CharacterMenuAction(pc, returnAction)))
	}
	
	def handRightAction = {
		InterfaceState.nextWindow = new HandRightWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def handLeftAction = {
		InterfaceState.nextWindow = new HandLeftWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def handRangedAction = {
		InterfaceState.nextWindow = new HandRangedWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
}
