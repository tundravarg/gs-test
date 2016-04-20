package tuman.gs_test.ep;



import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;



/**
 * Команда создания случайного аквариума.
 * @author Sergei Tumanov
 */
public class CreateGLAcuarioCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			page.openEditor(new AcuarioEditor.Input(), GLAcuarioEditor.ID);
		} catch (PartInitException ex) {
			throw new RuntimeException(ex);
		}
		return null;
	}

}
