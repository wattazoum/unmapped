package patrickwong.unmapped.town

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.MusicManager
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.HolyBook;
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.party.PartyMenuAction
import patrickwong.unmapped.party.WhoWillWindow
import patrickwong.unmapped.party.camp.CampAction
import patrickwong.unmapped.party.camp.CampState

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class TempleWindow extends Window {
	private static final int difficulty = 60
	public TempleWindow() {
		super("Temple")
		MusicManager.play("beginning_temple")
		addComponent(new Label("The temple is a quiet place where many dedicate their lives to mysteries."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...take sanctuary here", new CampAction(templeSanctuaryCampState())))
		addComponent(new Button("...commission the copying of a holy book", commissionCopyAction))
		addComponent(new Button("...volunteer to copy a holy book", volunteerCopyAction))
		addComponent(new Button("...(debug) get ALL the books!", getAllBooksAction))
		addComponent(new Button("...inspect your party", new PartyMenuAction(new TempleAction())))
		addComponent(new Button("...exit to the central plaza", new CentralPlazaAction()))
	}
	
	private CampState templeSanctuaryCampState() {
		CampState state = new CampState(campTitle: "Temple Sanctuary",
			campDesc: "The quarters are minimal, with simple straw beds. The food is humble porridge.\nHomeless families and diseased beggars share the space with you.",
			endCampAction: new TempleAction(),
			amenities: ["town", "temple", "quiet"]
		)
		return state
	}
	
	def commissionCopyAction = {
		GameState gs = GameState.getInstance()
		int price = DiceRoller.nextInt(2000) + 4000
		String priceString = UnmappedMain.moneyAsString(price)
		String requestedDonationString = "The requested donation is:\n" + priceString
		if (gs.partyMoney > price) {
			gs.partyMoney -= price
			List<HolyBook> holyBooks = new Vector<HolyBook>(HolyBook.holyBooks.values())
			int rand = DiceRoller.nextInt(holyBooks.size())
			HolyBook theBookToKeep = holyBooks.get(rand)
			if (gs.hasBook(theBookToKeep.key)) {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Commissioning a holy book", requestedDonationString + "\nYou pay this and get an extra copy of the\n" + theBookToKeep.fullName + "\nwhich you already have. The temple leaders figure that you want to\nstart a library or something.")
			} else {
				gs.holyBooks.add(theBookToKeep.key)
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Commissioning a holy book", requestedDonationString + "\nYou pay this and get your very own copy of the\n" + theBookToKeep.fullName)
			}
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Comissioning a holy book", requestedDonationString + "\nYou are unable to pay this, so the temple leaders recommend\nthat you try a cheap translated copy written in vernacular for\npersonal study and meditation")
		}
	} as Action
	
	def volunteerCopyAction = {
		WhoWillWindow copyBookWindow = new WhoWillWindow(" try to copy a holy book", { PlayerCharacter pc ->
			int result = pc.rollStat("SIX")	+ pc.rollStat("LOG") + pc.rollStat("MEM")
			result = (result / 3)
			result += pc.rollSkill("education") + pc.rollSkill("illumination") + pc.rollSkill("ancient_languages")
			if (result > difficulty) {
				List<HolyBook> holyBooks = new Vector<HolyBook>(HolyBook.holyBooks.values())
				int rand = DiceRoller.nextInt(holyBooks.size())
				HolyBook theBookToKeep = holyBooks.get(rand)
				if (GameState.getInstance().hasBook(theBookToKeep.key)) {
					MessageBox.showMessageBox(UnmappedMain.getGUI(), "Copying a holy book - success!", "The only book available for copying at the moment is one that you\nalready have, so you spend most of your time checking that neither\nyour copy nor the temple's has any errors")
				} else {
					GameState.getInstance().holyBooks.add(theBookToKeep.key)
					MessageBox.showMessageBox(UnmappedMain.getGUI(), "Copying a holy book - success!", "The leaders of the temple immediately put you to work on critical\ntexts of delicate composition, and soon you are rewarded with your\nown copy of the " + theBookToKeep.fullName)
				}
			} else {
				MessageBox.showMessageBox(UnmappedMain.getGUI(), "Copying a holy book - failure", "After seeing what you can do, the leaders of the temple give you\ndumbed-down translations of the books to copy for handing out to the masses,\nbelieving that this is more appropriate to your skill level")
			}
			InterfaceState.nextWindow = new TempleWindow()
			UnmappedMain.closeCurrent()
		})
		InterfaceState.nextWindow = copyBookWindow
		UnmappedMain.closeCurrent()
	} as Action
	
	def getAllBooksAction = {
		GameState gs = GameState.getInstance()
		HolyBook.holyBooks.each {
			gs.holyBooks.add(it.key)
		}
		MessageBox.showMessageBox(UnmappedMain.getGUI(), "DEBUG - get ALL the books!", "DEBUG - you can haz all the holy books!")
	} as Action
}
