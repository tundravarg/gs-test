package tuman.gs_test.ep;



import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;



/**
 * Компонент отображения аквариума.
 * @author Sergei Tumanov
 */
public class GLAcuarioEditor extends EditorPart {

	/** Идентификатор редактора. */
	public static final String ID = "gs_test_ep.GLAcuarioEditor";

	/** Компонент отображения аквариума. */
	private GLAcuarioViewer acuarioViewer;



	/**
	 * Create new instance.
	 */
	public GLAcuarioEditor() {}



	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public void createPartControl(Composite parent) {
		acuarioViewer = new GLAcuarioViewer(parent);
		acuarioViewer.setInput(getEditorInput().acuario);
	}

	@Override
	public void setFocus() {
		acuarioViewer.setFocus();
	}



	@Override
	public AcuarioEditor.Input getEditorInput() {
		return (AcuarioEditor.Input)super.getEditorInput();
	}



	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void doSaveAs() {
		throw new UnsupportedOperationException();
	}

}
