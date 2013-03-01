package patrickwong.unmapped.outdoors.raubritter;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class RaubritterSiegeAction implements Action {
	private int wavesRemaining;
	public RaubritterSiegeAction(int wavesRemaining) {
		this.wavesRemaining = wavesRemaining;
	}
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new RaubritterSiegeWindow(wavesRemaining);
		UnmappedMain.closeCurrent();
	}

}
