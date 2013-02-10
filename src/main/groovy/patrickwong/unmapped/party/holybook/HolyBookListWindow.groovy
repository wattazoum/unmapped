package patrickwong.unmapped.party.holybook

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.HolyBook;
import patrickwong.unmapped.party.PartyMenuAction

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.component.Panel

public class HolyBookListWindow extends Window {
	private static final int MAX_ITEMS_ON_SCREEN = 60
	private Action rta
	
	public HolyBookListWindow(Action returnAction) {
		super("Holy Books")
		rta = returnAction
		
		Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
		Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel centerColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
		
		int i = 0
		GameState.getInstance().holyBooks.each {
			HolyBook hb = HolyBook.holyBooks.get(it)
			if (i < 20) {
				leftColumn.addComponent(new Button(hb.shortName, genHolyBookDetailAction(hb)))
			} else if (i < 40) {
				centerColumn.addComponent(new Button(hb.shortName, genHolyBookDetailAction(hb)))
			} else if (i < MAX_ITEMS_ON_SCREEN) {
				rightColumn.addComponent(new Button(hb.shortName, genHolyBookDetailAction(hb)))
			}
			i++
		}
		mainPanel.addComponent(leftColumn);
		mainPanel.addComponent(centerColumn);
		mainPanel.addComponent(rightColumn);
		addComponent(mainPanel);
		
		if (i <= 0) {
			addComponent(new Label("(you don't have any holy books yet)"))
		}
		if (i >= MAX_ITEMS_ON_SCREEN) {
			addComponent(new Label("too many holy books to list, this is a bug"))
		}
		addComponent(new Button("OK", new PartyMenuAction(rta)))
	}
	
	private Action genHolyBookDetailAction(HolyBook hb) {
		def theAction = {
			InterfaceState.nextWindow = new HolyBookDetailWindow(hb, rta)
			UnmappedMain.closeCurrent()
		} as Action
		return theAction
	}
}
