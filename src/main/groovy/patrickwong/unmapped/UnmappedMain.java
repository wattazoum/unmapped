package patrickwong.unmapped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patrickwong.unmapped.mainmenu.MainMenuWindow;
import patrickwong.unmapped.model.XmlExporter;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;

public class UnmappedMain {
	private static GUIScreen textGUI = null;
	public static Logger log = null;
	public static final int MAX_PARTY_SIZE = 6; // for my own sanity
	public static final int MAX_INVENTORY_SIZE = 60; // mostly for UI purposes
	public static final boolean MUTE_MUSIC = true; // so I can listen to podcasts and debug at the same time
	
	public static void main(String[] args) {
		if (!System.getProperties().containsKey("org.slf4j.simpleLogger.defaultLogLevel")) {
			System.getProperties().setProperty("org.slf4j.simpleLogger.defaultLogLevel", "info");
		}
		if (!System.getProperties().containsKey("org.slf4j.simpleLogger.levelInBrackets")) {
			System.getProperties().setProperty("org.slf4j.simpleLogger.levelInBrackets", "true");
		}
		log = LoggerFactory.getLogger("unmapped");
		
		textGUI = TerminalFacade.createGUIScreen();
		textGUI.setTheme(new UnmappedTheme());
		textGUI.getScreen().startScreen();
		
		Window mainMenuWindow = new MainMenuWindow();
		textGUI.showWindow(mainMenuWindow, GUIScreen.Position.CENTER);
		
		log.info("Dump of the game state:");
		log.info(XmlExporter.makeXml());
		
		textGUI.getScreen().stopScreen();
	}
	
	public static GUIScreen getGUI() {
		return textGUI;
	}
	
	public static void closeCurrent() {
		textGUI.getActiveWindow().close();
	}
	public static void showWindow(Window window) {
		textGUI.showWindow(window, GUIScreen.Position.CENTER);
	}
	public static void showWindow(Window window, GUIScreen.Position position) {
		textGUI.showWindow(window, position);
	}
}
