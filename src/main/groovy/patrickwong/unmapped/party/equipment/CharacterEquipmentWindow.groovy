package patrickwong.unmapped.party.equipment

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.EquipSlot
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.party.CharacterMenuAction;

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Panel

public class CharacterEquipmentWindow extends Window {
	public static final int MAX_EQUIP_ON_SCREEN = 50
	public CharacterEquipmentWindow(PlayerCharacter pc, Action returnAction) {
		super(pc.name + " - Equipment")
		List<EquipSlot> equipSlots = pc.getEquipment()
		
		Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL)
		Panel leftColumn = new Panel(Panel.Orientation.VERTICAL)
		Panel rightColumn = new Panel(Panel.Orientation.VERTICAL)
		for (int i = 0; (i < MAX_EQUIP_ON_SCREEN) && (i < equipSlots.size()); i++) {
			EquipSlot equipSlot = equipSlots.get(i)
			String displayString = ""
			displayString += equipSlot.key
			displayString += ": "
			if (equipSlot.isFilled()) {
				displayString += equipSlot.slot.name
			} else {
				displayString += "(empty)"
			}
			Action changeEquipAction = new ChangeEquipInSlotAction(pc, equipSlot, returnAction)
			if (i < 25) {
				leftColumn.addComponent(new Button(displayString, changeEquipAction))
			} else {
				rightColumn.addComponent(new Button(displayString, changeEquipAction))
			}
		}
		mainPanel.addComponent(leftColumn)
		mainPanel.addComponent(rightColumn)
		addComponent(mainPanel)
		addComponent(new Button("Cancel", new CharacterMenuAction(pc, returnAction)))
	}
}
