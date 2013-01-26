package patrickwong.unmapped.party

import patrickwong.unmapped.model.Quest

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class QuestDetailWindow extends Window {
	public QuestDetailWindow(Quest quest, Action returnAction) {
		super("Quest - " + quest.key)
		quest.phases.each {
			addComponent(new Label(it.description))
		}
		addComponent(new Button("OK", new QuestListAction(returnAction)))
	}
}
