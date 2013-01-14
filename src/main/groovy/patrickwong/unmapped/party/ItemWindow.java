package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.equipment.Equippable;
import patrickwong.unmapped.equipment.GameItem;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class ItemWindow extends Window {
	private PlayerCharacter pc;
	public ItemWindow(GameItem gi, PlayerCharacter owner) {
		super(gi.getReadableName());
		this.pc = owner;
		addComponent(new Label(gi.getDesc()));
		if (gi.usableInField()) {
			addComponent(new Button("Use", new UseItemAction(gi, pc)));
		}
		if (gi instanceof Equippable) {
			addComponent(new Button("Equip"));
		}
		addComponent(new Button("Cancel", new Action() {
			@Override
			public void doAction() {
				UnmappedMain.closeCurrent();
				UnmappedMain.showWindow(new CharacterInventoryWindow(pc));
			}
		}));
	}
}
