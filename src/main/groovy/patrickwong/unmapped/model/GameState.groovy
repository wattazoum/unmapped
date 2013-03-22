package patrickwong.unmapped.model

import java.util.concurrent.ConcurrentSkipListSet

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.equipment.GameItem
import patrickwong.unmapped.outdoors.OutsideAction
import patrickwong.unmapped.town.TavernAction

import com.googlecode.lanterna.gui.Action

public class GameState {
	String gameName = "New Game"
	Boolean gameInProgress = false
	PlayerCharacter tempCharacter = new PlayerCharacter()
	List<PlayerCharacter> party = new Vector<PlayerCharacter>()
	List<Quest> quests = new Vector<Quest>()
	Set<String> holyBooks = new ConcurrentSkipListSet<String>()
	List<GameItem> stash = new Vector<GameItem>()
	int partyMoney = 1000000
	String currentLocation = "default location"
	String currentRegion = "default region"
	Long currentTime = 0
	
	private static GameState gameState = new GameState()
	
	private GameState() { }
	
	public static GameState getInstance() {
		return gameState
	}
	
	public static void wipeGameState() {
		gameState = new GameState()
	}
	
	public String getMoneyString() {
		return UnmappedMain.moneyAsString(partyMoney)
	}
	
	// NOTE - PARTY MANAGEMENT
	
	public PlayerCharacter getCharacter(String charName) {
		for (PlayerCharacter pc : party) {
			if (charName.equalsIgnoreCase(pc.getName())) {
				return pc
			}
		}
		UnmappedMain.log.debug("could not find character $charName");
		return null
	}
	public PlayerCharacter getCharacter(Integer charNumber) {
		return party.get(charNumber)
	}
	
	public List<PlayerCharacter> getLivingCharacters() {
		List<PlayerCharacter> living = new Vector<PlayerCharacter>()
		for (PlayerCharacter pc : party) {
			if (!pc.isDead()) {
				living.add(pc)
			}
		}
		return living
	}
	
	public Boolean isPartyAlive() {
		List<PlayerCharacter> living = getLivingCharacters()
		return (living.size() > 0)
	}
	
	public PlayerCharacter getRandomLivingCharacter() {
		List<PlayerCharacter> living = getLivingCharacters()
		if (living.size() > 0) {
			int random = DiceRoller.nextInt(living.size())
			return living.get(random)
		} else {
			return null
		}
	}
	
	public boolean checkNameCollision(String charName) {
		PlayerCharacter pc = getCharacter(charName)
		return (pc != null)
	}
	
	public void addToParty(PlayerCharacter newCharacter) {
		party = party.sort()
		newCharacter.setOrder(party.size())
		party.add(newCharacter);
	}
	
	public void removeFromParty(String charName) {
		PlayerCharacter charToRemove = getCharacter(charName)
		party.remove(charToRemove)
		
		party = party.sort();
		int i = 0;
		for (PlayerCharacter pc : party) {
			pc.setOrder(i);
			i++;
		}
	}
	
	// NOTE - INVENTORY AND STASH MANAGEMENT
	public boolean partyHasItem(String key) {
		for (PlayerCharacter pc : party) {
			GameItem gi = pc.getItem(key)
			if (gi != null) {
				return true
			}
		}
		return false
	}
	
	// NOTE - QUEST MANAGEMENT
	public boolean hasQuest(String key) {
		quests.each {
			if (it.key.equalsIgnoreCase(key)) {
				return true
			}
		}
		return false
	}
	
	public Quest getQuest(String key) {
		quests.each {
			if (it.key.equalsIgnoreCase(key)) {
				return it
			}
		}
		return null
	}
	
	public boolean hasQuestPhase(String quest, String key) {
		Quest questToSearch = getQuest(quest)
		if (questToSearch == null) {
			return false
		}
		questToSearch.phases.each {
			if (key.equalsIgnoreCase(it.key)) {
				return true
			}
		}
		return false
	}
	
	public void addQuestPhase(String quest, QuestPhase phase) {
		Quest questToAddTo = getQuest(quest)
		if (questToAddTo == null) {
			questToAddTo = new Quest(key: quest)
			quests.add(questToAddTo)
			quests = quests.sort()
		}
		if (questToAddTo.hasPhase(phase.key)) {
			return
		}
		questToAddTo.phases.add(phase)
		questToAddTo.phases = questToAddTo.phases.sort()
	}
	
	// NOTE - HOLY BOOK MANAGEMENT
	public boolean hasBook(String key) {
		return holyBooks.contains(key)
	}
	
	public void addBook(String key) {
		HolyBook hb = HolyBook.holyBooks.get(key)
		if (hb != null) {
			holyBooks.add(key)
		}
	}
	
	public List<HolyBook> getBooksForCombat() {
		List<HolyBook> books = new Vector<HolyBook>()
		holyBooks.each {
			HolyBook hb = HolyBook.holyBooks.get(it)
			if (hb.usableInCombat()) {
				books.add(hb)
			}
		}
		return books
	}
	
	public boolean hasBooksForCombat() {
		List<HolyBook> books = getBooksForCombat()
		return (books.size() > 0)
	}
	
	public List<HolyBook> getBooksForField() {
		List<HolyBook> books = new Vector<HolyBook>()
		holyBooks.each {
			HolyBook hb = HolyBook.holyBooks.get(it)
			if (hb.usableInField()) {
				books.add(hb)
			}
		}
		return books
	}
	
	public boolean hasBooksForField() {
		List<HolyBook> books = getBooksForField()
		return (books.size() > 0)
	}
	
	// NOTE - LOCATION MANAGEMENT
	public Action currentLocationAction() {
		Action actionToReturn = null
		
		if (currentLocation.equalsIgnoreCase("tavern")) {
			actionToReturn = new TavernAction()
		} else if (currentLocation.equalsIgnoreCase("outside")) {
			actionToReturn = new OutsideAction()
		} else {
			actionToReturn = new TavernAction()
		}
		
		return actionToReturn
	}
	
	public void timeAdvanceMinute() {
		currentTime++
	}
	public void timeAdvanceMinutes(Integer minutes) {
		currentTime += minutes
	}
	public void timeAdvanceHour() {
		currentTime += 60
	}
	public void timeAdvanceHours(Integer hours) {
		currentTime += (60 * hours)
	}
	public Boolean isNightTime() {
		return false
	}
}
