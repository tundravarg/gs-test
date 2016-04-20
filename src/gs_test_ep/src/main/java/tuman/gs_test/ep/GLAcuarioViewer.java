package tuman.gs_test.ep;



import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLDrawableFactory;
import com.jogamp.opengl.GLProfile;

import tuman.gs_test.Acuario;



/**
 * Компонент отображения аквариума.
 * @author Sergei Tumanov
 */
public class GLAcuarioViewer extends Viewer {

	/**
	 * Художник сцены.
	 * @author Sergei Tumanov
	 */
	private class ScenePainter implements PaintListener {

		@Override
		public void paintControl(PaintEvent e) {
			Point bounds = canvas.getSize();
			canvas.setCurrent();
			glcontext.makeCurrent();
			GL2 gl = glcontext.getGL().getGL2();

			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrtho(-bounds.x / 2, bounds.x / 2, bounds.y / 2, -bounds.y / 2, -100, 100);

			gl.glMatrixMode(GL2.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glViewport(0, 0, bounds.x, bounds.y);

			gl.glClearColor(1f, 1f, 1f, 1f);
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

			gl.glLineWidth(5F);
			gl.glBegin(GL2.GL_LINES);
				gl.glColor3f(1f, 0f, 0f);
				gl.glVertex3f(-10.0F,  0.0F, 0.0F);
				gl.glVertex3f( 100.0F, 0.0F, 0.0F);
			gl.glEnd();

			gl.glLineWidth(5F);
			gl.glBegin(GL2.GL_LINES);
				gl.glColor3f(0f, 1f, 0f);
				gl.glVertex3f(0.0F, -10.0F,  0.0F);
				gl.glVertex3f(0.0F,  100.0F, 0.0F);
			gl.glEnd();

			gl.glLineWidth(5F);
			gl.glBegin(GL2.GL_LINES);
				gl.glColor3f(0f, 0f, 1f);
				gl.glVertex3f(0.0F, 0.0F, -10.0F);
				gl.glVertex3f(0.0F, 0.0F,  100.0F);
			gl.glEnd();

			canvas.swapBuffers();
			glcontext.release();
		}

	}



	/** Аквариум. */
	private Acuario acuario;

	/** Корневой компонент. */
	private Composite control;
	/** Компонент отображения аквариума. */
	private GLCanvas canvas;
	/** Контекст OpenGL. */
	GLContext glcontext;



	/**
	 * Create new instance.
	 * @param parent Родительский компонент.
	 */
	public GLAcuarioViewer(Composite parent) {
		createControls(parent);
	}



	/**
	 * Создание визуальных компонентов.
	 * @param parent Родительский компонент.
	 */
	private void createControls(Composite parent) {
		control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(1, false));

		GLData gldata = new GLData();
		gldata.doubleBuffer = true;
		canvas = new GLCanvas(control, SWT.NO_BACKGROUND, gldata);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		canvas.setCurrent();
		GLProfile glprofile = GLProfile.getDefault();
		glcontext = GLDrawableFactory.getFactory(glprofile).createExternalGLContext();

		canvas.addPaintListener(new ScenePainter());
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
		} else {
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
	}

	/**
	 * Установить фокус.
	 */
	public void setFocus() {
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
