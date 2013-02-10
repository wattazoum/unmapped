package patrickwong.unmapped.party.holybook

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.HolyBook
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Border
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.component.Panel
import com.googlecode.lanterna.gui.dialog.MessageBox

public class HolyBookDetailWindow extends Window {
	private HolyBook hb
	private Action rta
	public HolyBookDetailWindow(HolyBook holyBook, Action returnAction) {
		super(holyBook.fullName)
		hb = holyBook
		rta = returnAction
		Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
		Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
		
		leftColumn.addComponent(new Label(hb.description))
		mainPanel.addComponent(leftColumn);
		if (hb.usableInField()) {
			rightColumn.setBorder(new Border.Standard())
			rightColumn.setTitle("Invoke its power")
			GameState.getInstance().party.each {
				rightColumn.addComponent(new Button(it.name, genInvokeBookAction(it)))
			}
			mainPanel.addComponent(rightColumn);
		}
		addComponent(mainPanel);
		
		addComponent(new Button("OK", new HolyBookListAction(rta)))
	}
	
	private Action genInvokeBookAction(PlayerCharacter pc) {
		def theAction = {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " invokes the " + hb.fullName, hb.useInField(pc))
			InterfaceState.nextWindow = new HolyBookDetailWindow(hb, rta)
			UnmappedMain.closeCurrent()
		} as Action
		return theAction
	}
}
