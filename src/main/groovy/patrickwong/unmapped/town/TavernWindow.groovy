package patrickwong.unmapped.town;

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.MusicManager
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.mainmenu.MainMenuAction
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.newcharacter.NewCharacterAction
import patrickwong.unmapped.party.PartyMenuAction
import patrickwong.unmapped.party.WhoWillWindow
import patrickwong.unmapped.party.camp.CampAction
import patrickwong.unmapped.party.camp.CampState

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class TavernWindow extends Window {
	private static int costPerPerson = 10
	private int partySize
	private int moneyToSpend
	public TavernWindow() {
		super("Tavern");
		MusicManager.play("beginning_tavern");
		UnmappedMain.suppressLevelupMessages = false
		GameState gameState = GameState.getInstance();
		gameState.setGameInProgress(true);
		gameState.setCurrentLocation("tavern");
		
		partySize = GameState.getInstance().getParty().size()
		moneyToSpend = partySize * costPerPerson
		
		addComponent(new Label("You are in the tavern, which is the heart of the town at night.\nIt smells strongly of cheap alcohol and sweaty patrons.\nMead, ale, and beer spill freely from the bar."));
		addComponent(new Label("You decide to..."));
		
		// Getting a room
		/*
		addComponent(new Button("...get a room", new Action() {
			@Override
			public void doAction() {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Getting a Room", "You get a room (not implemented)");
			}
		}));
		*/
		
		// Drinking
		/*
		addComponent(new Button("...drink", new Action() {
			@Override
			public void doAction() {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Drinking", "You get a drink (not implemented)");
			}
		}));
		*/
		
		// Talking
		/*
		addComponent(new Button("...talk to the others here", new Action() {
			@Override
			public void doAction() {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Talking", "You talk to others in the tavern (not implemented)");
			}
		}));
		*/
		
		// Singing
		/*
		addComponent(new Button("...sing", new Action() {
			@Override
			public void doAction() {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Singing", "You sing a tale (not implemented)");
			}
		}));
		*/
		
		if ((gameState.getParty().size() * costPerPerson) > gameState.partyMoney) {
			addComponent(new Button("...see that you cannot afford to stay here today"))
		} else {
			addComponent(new Button("...stay here for a day", new CampAction(stayAtTavernState())))
		}
		addComponent(new Button("...gamble", gambleAction));
		
		addComponent(new Button("...recruit someone to your party", new NewCharacterAction()));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new TavernAction())));
		addComponent(new Button("...return to the main menu to save or load the game", new MainMenuAction()));
		addComponent(new Button("...go to town", new MainStreetAction()));
	}
	
	// Gambling
	def gambleCheck = { PlayerCharacter pc ->
		GameState gameState = GameState.getInstance()
		boolean gambleForReal = true
		if (gameState.partyMoney <= 0) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "No money for gambling", "You have no money to gamble with,\nso you play for fun for a bit")
			gambleForReal = false
		}
		int result = 0
		result += pc.rollStat("MEM")
		result += pc.rollSkill("socializing")
		result += pc.rollSkill("gambling")
		result -= 100
		if (result > 0) {
			if (gambleForReal) {
				gameState.partyMoney += result
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You win " + result + " from gambling")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You would have won " + result + " from gambling")
			}
		} else if (result == 0) {
			if (gambleForReal) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You break even while gambling")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You would have broken even while gambling")
			}
		} else {
			result = result * (-1)
			if (gambleForReal) {
				if (result > gameState.partyMoney) {
					result = gameState.partyMoney
				}
				gameState.partyMoney -= result
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You lose " + result + " from gambling")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You would have lost " + result + " from gambling")
			}
		}
		InterfaceState.nextWindow = new TavernWindow()
		UnmappedMain.closeCurrent()
	}
	def gambleAction = {
		InterfaceState.nextWindow = new WhoWillWindow("gamble", gambleCheck)
		UnmappedMain.closeCurrent()
	} as Action
	
	// Stay at the tavern
	private CampState stayAtTavernState() {
		CampState campState = new CampState(campTitle: "Resting at the Tavern",
			campDesc: "Beds are stacked end-on-end here.",
			endCampAction: new TavernAction(),
			specialNote: {
				UnmappedMain.log.debug("stayAtTavernState specialNote()")
				return "With a party of $partySize the cost is $moneyToSpend per day."
			},
			onEndDay: {
				UnmappedMain.log.debug("stayAtTavernState onEndDay()")
				GameState.getInstance().partyMoney -= moneyToSpend
				return "You spend $moneyToSpend to stay here."
			},
			canContinueStaying: {
				UnmappedMain.log.debug("stayAtTavernState canContinueStaying")
				return (moneyToSpend < GameState.getInstance().partyMoney)
			},
			onKickedOut: {
				UnmappedMain.log.debug("stayAtTavernState onKickedOut")
				return "You can no longer afford to stay here."
			},
			amenities: ["tavern", "town"]
		)
		return campState
	}
}
