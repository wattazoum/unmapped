package patrickwong.unmapped.town

import patrickwong.unmapped.outdoors.OutsideAction

import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class ExitGateWindow extends Window {
	public ExitGateWindow() {
		super("Town - Exit")
		addComponent(new Label("The gate is heavily guarded, but most of the guards\nare watching for people trying to get into the city\nillegally, not for those trying to leave."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...simply walk out the gate, right past the guards", new OutsideAction()))
		addComponent(new Button("...blend in with the crowd leaving the town"))
		addComponent(new Button("...scale the walls at night when the guards aren't looking"))
		addComponent(new Button("...throw a Potion of Sapkowski to cause a panic, and flee in the chaos"))
		addComponent(new Button("...invoke the power of X on the guards and pass unnoticed"))
		addComponent(new Button("...draw weapons and attack the guards!"))
		addComponent(new Button("...return to the town's main street", new MainStreetAction()))
	}
}
