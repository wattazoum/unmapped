package patrickwong.unmapped.party.equipment

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.Grippable

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.component.Panel

public class HandRightWindow extends Window {
	public static final int MAX_ITEMS_ON_SCREEN = 60;
	private PlayerCharacter pc
	private Action rta
	
	public HandRightWindow(PlayerCharacter playerCharacter, Action returnAction) {
		super (playerCharacter.name + " equipping right hand")
		pc = playerCharacter
		rta = returnAction
		addComponent(new Label("Currently equipped: " + playerCharacter.rightHand.getReadableName()))
		
		List<Grippable> possibleItems = pc.getMeleeGrippablesFromInventory()
		if ((possibleItems == null) || (possibleItems.size() <= 0)) {
			addComponent(new Label("no possible replacements for the right hand"))
		} else {
			Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
			Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
			Panel centerColumn = new Panel(Panel.Orientation.VERTICAL);
			Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
			for (int i = 0; (i < MAX_ITEMS_ON_SCREEN) && (i < possibleItems.size()); i++) {
				Grippable gi = possibleItems.get(i);
				if (i < 20) {
					leftColumn.addComponent(new Button(gi.getReadableName(), genEquipSwapAction(gi)))
				} else if (i < 40) {
					centerColumn.addComponent(new Button(gi.getReadableName(), genEquipSwapAction(gi)))
				} else {
					rightColumn.addComponent(new Button(gi.getReadableName(), genEquipSwapAction(gi)))
				}
			}
			mainPanel.addComponent(leftColumn);
			mainPanel.addComponent(centerColumn);
			mainPanel.addComponent(rightColumn);
			addComponent(mainPanel);
			
			if (possibleItems.size() > MAX_ITEMS_ON_SCREEN) {
				addComponent(new Label("(too many items to list)"));
			}
		}
		if (!(pc.rightHand.key.equalsIgnoreCase("right_fist"))) {
			addComponent(new Button("Unequip", unequipAction))
		}
		addComponent(new Button("Cancel", cancelAction))
	}
	
	def unequipAction = {
		pc.unequipRightHand()
		InterfaceState.nextWindow = new CharacterGripWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def cancelAction = {
		InterfaceState.nextWindow = new CharacterGripWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	private Action genEquipSwapAction(Grippable gi) {
		def swapAction = {
			pc.unequipRightHand()
			Grippable itemToEquip = pc.takeItem(gi.key)
			if (gi.twoHanded) {
				pc.unequipLeftHand()
				pc.leftHand = itemToEquip
			}
			pc.rightHand = itemToEquip
			InterfaceState.nextWindow = new CharacterGripWindow(pc, rta)
			UnmappedMain.closeCurrent()
		} as Action
		return swapAction
	}
}
