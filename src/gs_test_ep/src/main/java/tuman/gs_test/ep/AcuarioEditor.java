package tuman.gs_test.ep;



import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import tuman.gs_test.Acuario;



/**
 * Компонент отображения аквариума.
 * @author Sergei Tumanov
 */
public class AcuarioEditor extends EditorPart {

	/**
	 * Данные.
	 * @author Sergei Tumanov
	 */
	public static class Input implements IEditorInput {

		/** Аквариум. */
		public Acuario acuario;



		/**
		 * Create new instance.
		 */
		public Input() {
			this(Acuario.randomAquario());
		}

		/**
		 * Create new instance.
		 * @param acuario Аквариум.
		 */
		public Input(Acuario acuario) {
			this.acuario = acuario;
		}



		@Override
		public String getName() {
			return "Acuario";
		}

		@Override
		public String getToolTipText() {
			return "Acuario";
		}

		@Override
		public ImageDescriptor getImageDescriptor() {
			return null;
		}

		@Override
		public boolean exists() {
			return true;
		}

		@Override
		public <T> T getAdapter(Class<T> adapter) {
			return null;
		}

		@Override
		public IPersistableElement getPersistable() {
			return null;
		}

	}



	/** Идентификатор редактора. */
	public static final String ID = "gs_test_ep.AcuarioEditor";

	/** Компонент отображения аквариума. */
	private AcuarioViewer acuarioViewer;



	/**
	 * Create new instance.
	 */
	public AcuarioEditor() {}



	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public void createPartControl(Composite parent) {
		acuarioViewer = new AcuarioViewer(parent);
		acuarioViewer.setInput(getEditorInput().acuario);
	}

	@Override
	public void setFocus() {
		acuarioViewer.setFocus();
	}



	@Override
	public Input getEditorInput() {
		return (Input)super.getEditorInput();
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
