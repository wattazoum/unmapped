package patrickwong.unmapped.outdoors.countryside

import patrickwong.unmapped.outdoors.OutsideAction

import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class PilgrimEscortWindow extends Window {
	public PilgrimEscortWindow() {
		super("Countryside - Lost Pilgrims")
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...(debug) leave", new OutsideAction()))
	}
}
