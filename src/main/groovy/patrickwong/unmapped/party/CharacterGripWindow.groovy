package patrickwong.unmapped.party

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class CharacterGripWindow extends Window {
	private PlayerCharacter pc
	
	public CharacterGripWindow(PlayerCharacter playerCharacter, Action returnAction) {
		super(playerCharacter.name + " - Weapons and Shields")
		pc = playerCharacter
		
		addComponent(new Button(pc.rightHand.name))
		addComponent(new Button(pc.leftHand.name))
		addComponent(new Button("Cancel", new CharacterMenuAction(pc, returnAction)))
	}
}
