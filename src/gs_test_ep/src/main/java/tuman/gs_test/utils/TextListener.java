package tuman.gs_test.utils;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;



/**
 * Слушатель изменения текстового поля.
 * @author Sergei Tumanov
 */
public abstract class TextListener {

	/**
	 * Слушатель изменения текстового поля.
	 * @author Sergei Tumanov
	 */
	private class TextFieldListener implements TraverseListener, FocusListener {

		@Override
		public void keyTraversed(TraverseEvent e) {
			if (e.detail == SWT.TRAVERSE_RETURN) {
				doApply();
			}
		}

		@Override
		public void focusGained(FocusEvent e) {}

		@Override
		public void focusLost(FocusEvent e) {
			doApply();
		}



		/**
		 * Применить изменения.
		 */
		private void doApply() {
			try {

				apply(textField.getText());
				srcText = textField.getText();

			} catch (Throwable ex) {

				MessageBox messageBox = new MessageBox(
						textField.getShell(),
						SWT.ICON_ERROR | SWT.OK | SWT.CANCEL);
				messageBox.setText("Error");
				messageBox.setMessage(ex.getMessage());
				int answer = messageBox.open();

				switch (answer) {
					case SWT.OK:
						textField.getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								textField.setFocus();
							}
						});
						break;
					case SWT.CANCEL:
					default:
						textField.setFocus();
						textField.setText(getText());
						break;
				}

			}
		}

	}



	/** Текстовое поле. */
	private Text textField;
	/** Последний приянтый текст. */
	private String srcText;

	/** Слушатель изменения текстового поля. */
	private TextFieldListener textListener;




	/**
	 * Create new instance.
	 * @param textField Текстовое поле.
	 */
	public TextListener(Text textField) {
		this.textField = textField;
		this.srcText = textField.getText();

		textListener = new TextFieldListener();
		textField.addTraverseListener(textListener);
		textField.addFocusListener(textListener);
	}



	/**
	 * Применить изменения.
	 * @param text Текст.
	 */
	public abstract void apply(String text);

	/**
	 * Получить тектовое представление управляемого объекта.
	 * @return Тектовое представление управляемого объекта.
	 */
	public String getText() {
		return srcText;
	}

}
