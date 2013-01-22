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
import patrickwong.unmapped.shop.ShopItem
import patrickwong.unmapped.shop.ShopState
import patrickwong.unmapped.shop.ShopWindow

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
		
		if ((gameState.getParty().size() * costPerPerson) > gameState.partyMoney) {
			addComponent(new Button("...see that you cannot afford to stay here today"))
		} else {
			addComponent(new Button("...stay here for a day", new CampAction(stayAtTavernState())))
		}
		
		addComponent(new Button("...gamble", gambleAction));
		addComponent(new Button("...get a drink", getDrinkAction));
		
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
		
		addComponent(new Button("...recruit someone to your party", new NewCharacterAction()));
		addComponent(new Button("...inspect your party", new PartyMenuAction(new TavernAction())));
		addComponent(new Button("...return to the main menu to save or load the game", new MainMenuAction()));
		addComponent(new Button("...go to town", new MainStreetAction()));
	}
	
	// NOTE - Getting a drink
	private static ShopState tavernDrinkShop = new ShopState(
		title: "Tavern Drinks",
		description: "This tavern is focused on serving large amounts of people,\nand does not have a particularly wide selection.",
		leaveShopAction: {
			InterfaceState.nextWindow = new TavernWindow()
			UnmappedMain.closeCurrent()
		} as Action,
		items: [
			new ShopItem(name:"Boiled Water", actionName:"get some boiled water", listedPrice: 1,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 1; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a cup of boiled water"); getDrinkShop() }
			),
			new ShopItem(name:"Milk", actionName:"get some milk", listedPrice: 3,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 3; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a cup of milk"); getDrinkShop() }
			),
			new ShopItem(name:"Beer", actionName:"get some beer", listedPrice: 5,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 5; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a mug of beer"); getDrinkShop() }
			),
			new ShopItem(name:"Ale", actionName:"get some ale", listedPrice: 7,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 7; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a mug of ale"); getDrinkShop() }
			),
			new ShopItem(name:"Lager", actionName:"get some lager", listedPrice: 7,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 7; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a mug of lager"); getDrinkShop() }
			),
			new ShopItem(name:"Mead", actionName:"get some mead", listedPrice: 10,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 10; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a mug of mead"); getDrinkShop() }
			),
			new ShopItem(name:"White Wine", actionName:"get some white wine", listedPrice: 20,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 20; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a cup of white wine"); getDrinkShop() }
			),
			new ShopItem(name:"Red Wine", actionName:"get some red wine", listedPrice: 20,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 20; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a cup of red wine"); getDrinkShop() }
			),
			new ShopItem(name:"House Microbrew", actionName:"get some of the house microbrew", listedPrice: 50,
				check: { PlayerCharacter pc -> GameState.getInstance().partyMoney -= 50; MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " drinks", pc.name + " drinks a mug of the house microbrew"); getDrinkShop() }
			)
		]
	)
	private static void getDrinkShop() {
		InterfaceState.nextWindow = new ShopWindow(tavernDrinkShop)
		UnmappedMain.closeCurrent()
	}
	def getDrinkAction = {
		getDrinkShop()
	} as Action
	
	// NOTE - Gambling
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
			String resultString = UnmappedMain.moneyAsString(result)
			if (gambleForReal) {
				gameState.partyMoney += result
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You win " + resultString + " from gambling")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You would have won " + resultString + " from gambling")
			}
		} else if (result == 0) {
			if (gambleForReal) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You break even while gambling")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You would have broken even while gambling")
			}
		} else {
			result = result * (-1)
			String resultString = UnmappedMain.moneyAsString(result)
			if (gambleForReal) {
				if (result > gameState.partyMoney) {
					result = gameState.partyMoney
				}
				gameState.partyMoney -= result
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You lose " + resultString + " from gambling")
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Gambling results", "You would have lost " + resultString + " from gambling")
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
