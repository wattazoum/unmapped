package patrickwong.unmapped;

import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patrickwong.unmapped.mainmenu.MainMenuWindow;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.XmlExporter;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;

public class UnmappedMain {
	private static GUIScreen textGUI = null;
	public static Logger log = null;
	public static boolean suppressLevelupMessages = false;
	public static final int MAX_PARTY_SIZE = 6; // for my own sanity
	public static final boolean MUTE_MUSIC = true; // so I can listen to podcasts and debug at the same time
	
	public static final String PLATINUM_PLURAL = "sovereigns";
	public static final String PLATINUM_SINGLE = "sovereign";
	public static final String PLATINUM_SHORT = "sv";
	public static final int PLATINUM_SIZE = 5520;
	public static final String GOLD_PLURAL = "florins";
	public static final String GOLD_SINGLE = "florin";
	public static final String GOLD_SHORT = "fl";
	public static final int GOLD_SIZE = 240;
	public static final String SILVER_PLURAL = "groschen";
	public static final String SILVER_SINGLE = "groschen";
	public static final String SILVER_SHORT = "gr";
	public static final int SILVER_SIZE = 12;
	public static final String COPPER_PLURAL = "pfennigs";
	public static final String COPPER_SINGLE = "pfennig";
	public static final String COPPER_SHORT = "pf";
	
	public static void main(String[] args) {
		if (!System.getProperties().containsKey("org.slf4j.simpleLogger.defaultLogLevel")) {
			System.getProperties().setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
		}
		if (!System.getProperties().containsKey("org.slf4j.simpleLogger.levelInBrackets")) {
			System.getProperties().setProperty("org.slf4j.simpleLogger.levelInBrackets", "true");
		}
		log = LoggerFactory.getLogger("unmapped");
		
		textGUI = TerminalFacade.createGUIScreen();
		textGUI.setTheme(new UnmappedTheme());
		textGUI.getScreen().startScreen();
		
		InterfaceState.nextWindow = new MainMenuWindow();
		
		while (!(InterfaceState.endGame)) {
			showWindow(InterfaceState.nextWindow);
		}
		
		textGUI.getScreen().stopScreen();
		log.info("Dump of the game state:");
		log.info(XmlExporter.makeXml());
	}
	
	public static GUIScreen getGUI() {
		return textGUI;
	}
	
	public static void closeCurrent() {
		Window windowToClose = textGUI.getActiveWindow();
		if (windowToClose != null) {
			windowToClose.removeAllComponents();
			windowToClose.close();
		}
	}
	public static void showWindow(Window window) {
		textGUI.showWindow(window, GUIScreen.Position.CENTER);
	}
	public static void showWindow(Window window, GUIScreen.Position position) {
		textGUI.showWindow(window, position);
	}
	public static Map<String, Integer> divideMoney(int money) {
		Map<String, Integer> moneyTypes = new Hashtable<String, Integer>();
		int tempMoney = money;
		int platinum = 0;
		int gold = 0;
		int silver = 0;
		int copper = 0;
		while (tempMoney >= PLATINUM_SIZE) {
			tempMoney -= PLATINUM_SIZE;
			platinum += 1;
		}
		while (tempMoney >= GOLD_SIZE) {
			tempMoney -= GOLD_SIZE;
			gold += 1;
		}
		while (tempMoney >= SILVER_SIZE) {
			tempMoney -= SILVER_SIZE;
			silver += 1;
		}
		copper = tempMoney;
		moneyTypes.put("platinum", platinum);
		moneyTypes.put("gold", gold);
		moneyTypes.put("silver", silver);
		moneyTypes.put("copper", copper);
		return moneyTypes;
	}
	public static String moneyAsString(int money) {
		String moneyString = money + " coins";
		Map<String, Integer> dividedMoney = divideMoney(money);
		int platinum = dividedMoney.get("platinum");
		int gold = dividedMoney.get("gold");
		int silver = dividedMoney.get("silver");
		int copper = dividedMoney.get("copper");
		String platinumString = "";
		String goldString = "";
		String silverString = "";
		String copperString = "";
		if (platinum > 0) {
			platinumString = platinum + " ";
			if (platinum > 1) {
				platinumString += PLATINUM_PLURAL;
			} else {
				platinumString += PLATINUM_SINGLE;
			}
			if ((gold > 0) && (silver <= 0) && (copper <= 0)) {
				platinumString += " and ";
			} else if ((gold <= 0) && (silver > 0) && (copper <= 0)) {
				platinumString += " and ";
			} else if ((gold <= 0) && (silver <= 0) && (copper > 0)) {
				platinumString += " and ";
			} else if ((gold <= 0) && (silver <= 0) && (copper <= 0)) {
				
			} else {
				platinumString += ", ";
			}
		}
		if (gold > 0) {
			goldString += gold + " ";
			if (gold > 1) {
				goldString += GOLD_PLURAL;
			} else {
				goldString += GOLD_SINGLE;
			}
			if ((platinum > 0) && (silver <= 0) && (copper <= 0)) {
				
			} else if ((platinum <= 0) && (silver > 0) && (copper <= 0)) {
				goldString += " and ";
			} else if ((platinum <= 0) && (silver <= 0) && (copper > 0)) {
				goldString += " and ";
			} else if ((platinum <= 0) && (silver <= 0) && (copper <= 0)) {
				
			} else if ((platinum > 0) && (silver > 0) && (copper <= 0)) {
				goldString += ", and ";
			} else if ((platinum > 0) && (silver <= 0) && (copper > 0)) {
				goldString += ", and ";
			} else if ((silver > 0) && (copper > 0)) {
				goldString += ", ";
			}
		}
		if (silver > 0) {
			silverString += silver + " ";
			if (silver > 1) {
				silverString += SILVER_PLURAL;
			} else {
				silverString += SILVER_SINGLE;
			}
			if (((platinum > 0) || (gold > 0)) && (copper > 0)) {
				silverString += ", and ";
			} else if ((platinum <= 0) && (gold <= 0) && (copper > 0)) {
				silverString += " and ";
			}
		}
		if (copper > 0) {
			copperString += copper + " ";
			if (copper > 1) {
				copperString += COPPER_PLURAL;
			} else {
				copperString += COPPER_SINGLE;
			}
		}
		moneyString = platinumString + goldString + silverString + copperString;
		return moneyString;
	}
	public static String partyMoneyString() {
		return moneyAsString(GameState.getInstance().getPartyMoney());
	}
}
