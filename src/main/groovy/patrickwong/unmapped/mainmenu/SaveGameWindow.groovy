package patrickwong.unmapped.mainmenu;

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.XmlExporter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.component.TextBox
import com.googlecode.lanterna.terminal.TerminalSize

public class SaveGameWindow extends Window {
	private TextBox gameNameBox;
	public SaveGameWindow() {
		super("Save Game");
		
		addComponent(new Label("Game name:"));
		gameNameBox = new TextBox();
		gameNameBox.setPreferredSize(new TerminalSize(30, 1));
		gameNameBox.setText(GameState.getInstance().getGameName());
		addComponent(gameNameBox);
		addComponent(new Button("Save", saveAction));
		addComponent(new Button("Cancel", new MainMenuAction()));
	}
	
	def saveAction = {
		String gameName = gameNameBox.getText()
		GameState.getInstance().setGameName(gameName)
		UnmappedMain.log.debug("saving game $gameName")
		File saveDir = new File("save")
		saveDir.mkdir()
		String fileName = saveDir.getName() + File.separator + gameName + ".xml"
		String saveData = XmlExporter.makeXml()
		UnmappedMain.log.debug("this is the save data")
		UnmappedMain.log.debug(saveData)
		
		File saveFile = new File(fileName)
		saveFile.delete()
		saveFile.createNewFile()
		saveFile.write(saveData)
		
		UnmappedMain.log.debug("this is what's in the file")
		UnmappedMain.log.debug(saveFile.getText())
		
		GameState.getInstance().currentLocationAction().doAction()
	} as Action
}
