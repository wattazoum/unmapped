package patrickwong.unmapped.combat;

import java.util.List;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.equipment.GameItem;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Panel;

public class CombatInventoryWindow extends Window {
	private PlayerCharacter pc;
	private CombatState state;
	private List<GameItem> inventory;
	public CombatInventoryWindow(PlayerCharacter playerCharacter, CombatState combatState) {
		super(playerCharacter.getName() + " - Use an Item");
		this.pc = playerCharacter;
		this.state = combatState;
		inventory = pc.getInventory();
		Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
		Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel centerColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
		for (int i = 0; (i < UnmappedMain.MAX_INVENTORY_SIZE) && (i < inventory.size()); i++) {
			GameItem gi = inventory.get(i);
			if (i < 20) {
				leftColumn.addComponent(new Button(gi.getReadableName(), new CombatViewItemAction(gi, pc, state)));
			} else if (i < 40) {
				centerColumn.addComponent(new Button(gi.getReadableName(), new CombatViewItemAction(gi, pc, state)));
			} else {
				rightColumn.addComponent(new Button(gi.getReadableName(), new CombatViewItemAction(gi, pc, state)));
			}
		}
		mainPanel.addComponent(leftColumn);
		mainPanel.addComponent(centerColumn);
		mainPanel.addComponent(rightColumn);
		addComponent(mainPanel);
		
		addComponent(new Button("Cancel", new Action() {
			@Override
			public void doAction() {
				UnmappedMain.closeCurrent();
				UnmappedMain.showWindow(new CombatDecisionWindow(state, pc));
			}
		}));
	}
}
