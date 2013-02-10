package patrickwong.unmapped.party.equipment

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.EquipSlot
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.Equippable
import patrickwong.unmapped.model.equipment.Grippable

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class PutItemInSlotWindow extends Window {
	private PlayerCharacter pc
	private Equippable gi
	private Action rta
	
	public PutItemInSlotWindow(PlayerCharacter playerCharacter, Equippable item, Action returnAction) {
		super(playerCharacter.name + " equipping " + item.getReadableName())
		pc = playerCharacter
		gi = item
		rta = returnAction
		
		if (gi instanceof Grippable) {
			Grippable gripItem = (Grippable)gi
			if (gripItem.melee) {
				String rightHandString = "Right Hand: " + pc.rightHand.getReadableName()
				String leftHandString = "Left Hand: " + pc.leftHand.getReadableName()
				if (gripItem.twoHanded) {
					addComponent(new Label(rightHandString))
					addComponent(new Label(leftHandString))
					addComponent(new Button("Equip to both hands", equipToBothHandsAction))
				} else {
					addComponent(new Button(rightHandString, equipToRightHandAction))
					addComponent(new Button(leftHandString, equipToLeftHandAction))
				}
			}
			if (gripItem.ranged) {
				String rangedHandString = "Ranged: " + pc.rangedWeapon.getReadableName()
				addComponent(new Button(rangedHandString))
			}
			
		} else {
			List<EquipSlot> possibleSlots = pc.getPossibleSlots(gi)
			for (EquipSlot equipSlot : possibleSlots) {
				String displayString = equipSlot.key + ": "
				if (equipSlot.slot == null) {
					displayString += "(empty)"
				} else {
					displayString += equipSlot.slot.getReadableName()
				}
				addComponent(new Button(displayString, genEquipItemAction(equipSlot)))
			}
		}
		addComponent(new Button("Cancel", cancelAction))
	}
	
	private Action genEquipItemAction(EquipSlot equipSlot) {
		def theAction = {
			pc.equipItemFromInventory(gi.key, equipSlot.key)
			InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
			UnmappedMain.closeCurrent()
		} as Action
		return theAction
	}
	
	def equipToRightHandAction = {
		pc.unequipRightHand()
		pc.rightHand = pc.takeItem(gi.key)
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def equipToLeftHandAction = {
		pc.unequipLeftHand()
		pc.leftHand = pc.takeItem(gi.key)
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def equipToBothHandsAction = {
		pc.unequipRightHand()
		pc.unequipLeftHand()
		pc.rightHand = pc.takeItem(gi.key)
		pc.leftHand = pc.rightHand
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def equipToRangedHandAction = {
		pc.unequipRangedWeapon()
		pc.rangedWeapon = pc.takeItem(gi.key)
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	}
	
	def cancelAction = {
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
}
