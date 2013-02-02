package patrickwong.unmapped.mainmenu;

import groovy.io.FileType
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.XmlImporter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class LoadGameWindow extends Window {
	public static final int MAX_ITEMS_ON_SCREEN = 60;
	public LoadGameWindow() {
		super("Load Game");
		File saveDir = new File("save")
		saveDir.mkdir()
		if (saveDir.list().length <= 0) {
			addComponent(new Label("(no saved games)"))
		} else {
			saveDir.eachFile(FileType.FILES) {
				String apparentName = it.getName().replace(".xml", "")
				addComponent(new Button(apparentName, genLoadGameAction(it)))
			}
		}
		addComponent(new Button("Cancel", new MainMenuAction()));
	}
	
	private Action genLoadGameAction(File saveFile) {
		def theAction = {
			XmlImporter.importXml(saveFile.getText())
			GameState.getInstance().currentLocationAction().doAction()
		} as Action
		return theAction
	}
}
