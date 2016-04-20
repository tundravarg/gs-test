package tuman.gs_test.ep;



import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import tuman.gs_test.Acuario;



/**
 * Компонент отображения аквариума.
 * @author Sergei Tumanov
 */
public class AcuarioViewer extends Viewer {

	/** Аквариум. */
	private Acuario acuario;

	/** Корневой компонент. */
	private Composite control;
	/** Компонент отображения аквариума. */
	private Text log;



	/**
	 * Create new instance.
	 * @param parent Родительский компонент.
	 */
	public AcuarioViewer(Composite parent) {
		createControls(parent);
	}



	/**
	 * Создание визуальных компонентов.
	 * @param parent Родительский компонент.
	 */
	private void createControls(Composite parent) {
		control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(1, false));

		log = new Text(control , SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY);
		log.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		log.setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));
	}



	@Override
	public Control getControl() {
		return control;
	}



	@Override
	public Acuario getInput() {
		return acuario;
	}

	@Override
	public void setInput(Object input) {
		this.acuario = (Acuario)input;
		refresh();
	}

	@Override
	public void refresh() {
		if (acuario != null) {
			log.setText(acuario.toString());
		} else {
			log.setText("");
		}
	}

	/**
	 * Получить доступность компонента.
	 * @return Доступность компонента.
	 */
	public boolean getEnabled() {
		return control.isEnabled();
	}

	/**
	 * Задать доступность компонента.
	 * @param enabled Доступность компонента.
	 */
	public void setEnabled(boolean enabled) {
		control.setEnabled(enabled);
		enabled &= acuario != null;
		log.setEnabled(enabled);
	}

	/**
	 * Установить фокус.
	 */
	public void setFocus() {
		log.setFocus();
	}



	@Override
	public ISelection getSelection() {
		return new StructuredSelection(acuario);
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
		StructuredSelection structuredSelection = (StructuredSelection)selection;
		setInput(structuredSelection.getFirstElement());
	}

}
