package patrickwong.unmapped.party

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.Quest

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.component.Panel

public class QuestListWindow extends Window {
	public static final int MAX_QUESTS_ON_SCREEN = 60
	
	public QuestListWindow(Action returnAction) {
		super("Quests")
		
		List<Quest> quests = GameState.getInstance().quests
		if (quests.size() <= 0) {
			addComponent(new Label("(no quests yet)"))
		} else {
			int i = 0
			Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
			Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
			Panel centerColumn = new Panel(Panel.Orientation.VERTICAL);
			Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
			quests.each {
				if (i < 20) {
					leftColumn.addComponent(new Button(it.key, genQuestDetailAction(it, returnAction)))
				} else if (i < 40) {
					centerColumn.addComponent(new Button(it.key, genQuestDetailAction(it, returnAction)))
				} else if (i < MAX_QUESTS_ON_SCREEN) {
					rightColumn.addComponent(new Button(it.key, genQuestDetailAction(it, returnAction)))
				}
				i++
			}
			mainPanel.addComponent(leftColumn);
			mainPanel.addComponent(centerColumn);
			mainPanel.addComponent(rightColumn);
			addComponent(mainPanel);
		}
		addComponent(new Button("Cancel", new PartyMenuAction(returnAction)))
	}
	
	private static Action genQuestDetailAction(Quest quest, Action returnAction) {
		def theAction = {
			InterfaceState.nextWindow = new QuestDetailWindow(quest, returnAction)
			UnmappedMain.closeCurrent()
		} as Action
		return theAction
	}
}
