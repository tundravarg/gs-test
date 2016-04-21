package tuman.gs_test.ep;



import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
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
import tuman.gs_test.ep.gl.GLCamera;



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
			canvas.setCurrent();
			glContext.makeCurrent();
			GL2 gl = glContext.getGL().getGL2();

			Point bounds = canvas.getSize();
			camera.setViewSize(bounds.x, bounds.y);
			camera.paint(glContext);

			gl.glMatrixMode(GL2.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glViewport(0, 0, bounds.x, bounds.y);

			gl.glClearColor(1f, 1f, 1f, 1f);
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

			gl.glLineWidth(2.0f);
			gl.glBegin(GL2.GL_LINES);
				gl.glColor3f(1f, 0f, 0f);
				gl.glVertex3f(-10.0F,  0.0F, 0.0F);
				gl.glVertex3f( 100.0F, 0.0F, 0.0F);
			gl.glEnd();
			gl.glBegin(GL2.GL_LINES);
				gl.glColor3f(0f, 1f, 0f);
				gl.glVertex3f(0.0F, -10.0F,  0.0F);
				gl.glVertex3f(0.0F,  100.0F, 0.0F);
			gl.glEnd();
			gl.glBegin(GL2.GL_LINES);
				gl.glColor3f(0f, 0f, 1f);
				gl.glVertex3f(0.0F, 0.0F, -10.0F);
				gl.glVertex3f(0.0F, 0.0F,  100.0F);
			gl.glEnd();

			canvas.swapBuffers();
			glContext.release();
		}

	}



	/**
	 * Слушатель клавиатуры.
	 * @author Sergei Tumanov
	 */
	private class KeyListener implements org.eclipse.swt.events.KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.keyCode) {
				case SWT.ARROW_UP:
					if ((e.stateMask & SWT.CONTROL) == SWT.CONTROL) {
						System.out.println("adads");
						camera.setDistance(camera.getDistance() / 1.1);
					} else {
						camera.rotate(0.0, 10.0);
					}
					break;
				case SWT.ARROW_DOWN:
					if ((e.stateMask & SWT.CONTROL) == SWT.CONTROL) {
						System.out.println("zxczxc");
						camera.setDistance(camera.getDistance() * 1.1);
					} else {
						camera.rotate(0.0, -10.0);
					}
					break;
				case SWT.ARROW_LEFT:
					camera.rotate(-10.0, 0.0);
					break;
				case SWT.ARROW_RIGHT:
					camera.rotate(10.0, 0.0);
					break;
			}
			canvas.redraw();
		}

		@Override
		public void keyReleased(KeyEvent e) {}

	}



	/** Аквариум. */
	private Acuario acuario;

	/** Корневой компонент. */
	private Composite control;
	/** Компонент отображения аквариума. */
	private GLCanvas canvas;
	/** Контекст OpenGL. */
	GLContext glContext;
	/** Камера. */
	GLCamera camera;



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
		glContext = GLDrawableFactory.getFactory(glprofile).createExternalGLContext();

		canvas.addPaintListener(new ScenePainter());
		canvas.addKeyListener(new KeyListener());

		camera = new GLCamera(100.0, 0.0, 0.0, 0, 0);
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
	}

	/**
	 * Установить фокус.
	 */
	public void setFocus() {
		System.out.println("Focus!");
		canvas.setFocus();
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
