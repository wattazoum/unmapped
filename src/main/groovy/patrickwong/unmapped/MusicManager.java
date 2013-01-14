package patrickwong.unmapped;

import java.io.File;
import java.io.IOException;

import org.newdawn.easyogg.OggClip;


public class MusicManager {
	private static MusicManager musicManager = new MusicManager();
	
	private OggClip ogg = null;
	private String currentSong = null;
	
	private MusicManager() {
		
	}
	
	public static MusicManager getInstance() {
		return musicManager;
	}
	
	public void play(String song) {
		boolean changeSong = false;
		if (UnmappedMain.MUTE_MUSIC) {
			return;
		}
		if (ogg == null) {
			changeSong = true;
		} else if (!song.equalsIgnoreCase(currentSong)) {
			changeSong = true;
		}
		if (changeSong) {
			if (ogg != null) {
				ogg.stop();
			}
			try {
				ogg = new OggClip("music" + File.separatorChar + song + ".ogg");
			} catch (IOException e) {
				UnmappedMain.log.error(e.getMessage());
				UnmappedMain.log.error("Error trying to play " + song);
				UnmappedMain.log.error("You may need to install the music pack.");
				return;
			}
			currentSong = song;
			ogg.loop();
		}
	}
}
