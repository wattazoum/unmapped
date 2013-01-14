package patrickwong.unmapped.model

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.equipment.GameItem
import patrickwong.unmapped.town.TavernAction

import com.googlecode.lanterna.gui.Action

public class GameState {
	Boolean gameInProgress = false
	PlayerCharacter tempCharacter = new PlayerCharacter()
	List<PlayerCharacter> party = new Vector<PlayerCharacter>()
	List<GameItem> stash = new Vector<GameItem>()
	Long partyMoney = 0
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
	
	public boolean partyHasItem(String key) {
		for (PlayerCharacter pc : party) {
			GameItem gi = pc.getItem(key)
			if (gi != null) {
				return true
			}
		}
		return false
	}
	
	public Action currentLocationAction() {
		Action actionToReturn = null
		
		if (currentLocation.equalsIgnoreCase("tavern")) {
			actionToReturn = new TavernAction()
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
