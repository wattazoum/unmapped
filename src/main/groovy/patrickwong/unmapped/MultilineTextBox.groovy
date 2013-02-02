package patrickwong.unmapped

import java.util.regex.Pattern.End

import com.googlecode.lanterna.gui.Interactable.Result
import com.googlecode.lanterna.gui.component.TextBox
import com.googlecode.lanterna.input.Key

public class MultilineTextBox extends TextBox {
	@Override
	public Result keyboardInteraction(Key key)
	{
		try {
			switch(key.getKind()) {
				case Tab:
				case Enter:
					return Result.NEXT_INTERACTABLE_RIGHT;
					
				case ArrowDown:
					return Result.NEXT_INTERACTABLE_DOWN;

				case ReverseTab:
					return Result.PREVIOUS_INTERACTABLE_LEFT;
					
				case ArrowUp:
					return Result.PREVIOUS_INTERACTABLE_UP;

				case ArrowRight:
					if(editPosition == backend.length())
						break;
					editPosition++;
					if(editPosition - visibleLeftPosition >= lastKnownWidth)
						visibleLeftPosition++;
					break;

				case ArrowLeft:
					if(editPosition == 0)
						break;
					editPosition--;
					if(editPosition - visibleLeftPosition < 0)
						visibleLeftPosition--;
					break;

				case End:
					editPosition = backend.length();
					if(editPosition - visibleLeftPosition >= lastKnownWidth)
						visibleLeftPosition = editPosition - lastKnownWidth + 1;
					break;

				case Home:
					editPosition = 0;
					visibleLeftPosition = 0;
					break;

				case Delete:
					if(editPosition == backend.length())
						break;
					backend = backend.substring(0, editPosition) + backend.substring(editPosition + 1);
					break;

				case Backspace:
					if(editPosition == 0)
						break;
					editPosition--;
					if(editPosition - visibleLeftPosition < 0)
						visibleLeftPosition--;
					backend = backend.substring(0, editPosition) + backend.substring(editPosition + 1);
					break;

				case NormalKey:
					//Add character
					if(Character.isISOControl(key.getCharacter()) || key.getCharacter() > 127)
						break;

					backend = backend.substring(0, editPosition) + (char)key.getCharacter() + backend.substring(editPosition);
					editPosition++;
					if(editPosition - visibleLeftPosition >= lastKnownWidth)
						visibleLeftPosition++;
					break;
					
				default:
					return Result.EVENT_NOT_HANDLED;
			}
			return Result.EVENT_HANDLED;
		}
		finally {
			invalidate();
		}
	}
}
