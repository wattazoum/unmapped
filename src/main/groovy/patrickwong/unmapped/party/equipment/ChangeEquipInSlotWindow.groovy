package patrickwong.unmapped.party.equipment

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.EquipSlot
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.Equippable

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.component.Panel

public class ChangeEquipInSlotWindow extends Window {
	public static final int MAX_ITEMS_ON_SCREEN = 60;
	
	private PlayerCharacter pc
	private EquipSlot es
	private Action rta
	
	public ChangeEquipInSlotWindow(PlayerCharacter playerCharacter, EquipSlot equipSlot, Action returnAction) {
		super(playerCharacter.name + " changing " + equipSlot.key)
		pc = playerCharacter
		es = equipSlot
		rta = returnAction
		if (es.slot == null) {
			addComponent(new Label("Currently equipped: nothing"))
		} else {
			addComponent(new Label("Currently equipped: " + es.slot.name))
		}
		List<Equippable> possibleItems = pc.getEquippablesFromInventory(es.slotType)
		
		if ((possibleItems == null) || (possibleItems.size() <= 0)) {
			addComponent(new Label("no possible replacements for the " + es.key + " slot"))
		} else {
			Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
			Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
			Panel centerColumn = new Panel(Panel.Orientation.VERTICAL);
			Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
			
			for (int i = 0; (i < MAX_ITEMS_ON_SCREEN) && (i < possibleItems.size()); i++) {
				Equippable gi = possibleItems.get(i);
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
		addComponent(new Button("Unequip", unequipAction))
		addComponent(new Button("Cancel", cancelAction))
	}
	
	private Action genEquipSwapAction(Equippable itemToEquip) {
		def swapAction = {
			if (es.slot != null) {
				pc.addItem(es.slot)
			}
			es.slot = pc.takeItem(itemToEquip.key)
			InterfaceState.nextWindow = new CharacterEquipmentWindow(pc, rta)
			UnmappedMain.closeCurrent()
		} as Action
		return swapAction
	}
	
	def unequipAction = {
		if (es.slot != null) {
			pc.addItem(es.slot)
		}
		es.slot = null
		InterfaceState.nextWindow = new CharacterEquipmentWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def cancelAction = {
		InterfaceState.nextWindow = new CharacterEquipmentWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
}
