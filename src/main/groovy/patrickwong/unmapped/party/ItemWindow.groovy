package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.Equippable
import patrickwong.unmapped.model.equipment.GameItem
import patrickwong.unmapped.model.equipment.Grippable

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class ItemWindow extends Window {
	private GameItem gi;
	private PlayerCharacter pc;
	private Action rta;
	
	public ItemWindow(GameItem item, PlayerCharacter owner, Action returnAction) {
		super(item.getReadableName());
		this.gi = item
		this.pc = owner
		this.rta = returnAction
		boolean givingPossible = (GameState.getInstance().getParty().size() > 1)
		
		addComponent(new Label(gi.getDesc()))
		if (gi instanceof Equippable) {
			addComponent(new Button("Equip", new PutItemInSlotAction(pc, gi, rta)));
		}
		if (gi.usableInField()) {
			addComponent(new Button("Use", new UseItemAction(gi, pc)));
		}
		if (gi.isStackable() && (gi.stackSize > 1)) {
			addComponent(new Button("Drop one", dropOneAction))
			addComponent(new Button("Drop all", dropAllAction))
			if (givingPossible) {
				addComponent(new Button("Give one", giveOneAction))
				addComponent(new Button("Give all", giveAllAction))
			}
		} else {
			addComponent(new Button("Drop", dropAllAction))
			if (givingPossible) {
				addComponent(new Button("Give", giveAllAction))
			}
		}
		addComponent(new Button("Cancel", cancelAction));
	}
	
	def dropOneAction = {
		pc.removeItem(gi.key)
		InterfaceState.nextWindow = new ItemWindow(gi, pc, rta)
		UnmappedMain.closeCurrent()
	} as Action

	def dropAllAction = {
		pc.removeItemStack(gi.key)
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def giveOneAction = {
		InterfaceState.nextWindow = new GiveOneWindow(pc, gi, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def giveAllAction = {
		InterfaceState.nextWindow = new GiveAllWindow(pc, gi, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	def cancelAction = {
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
}
