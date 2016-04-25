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
import org.eclipse.swt.widgets.Text;

import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLDrawableFactory;
import com.jogamp.opengl.GLProfile;

import tuman.gs_test.Acuario;
import tuman.gs_test.ep.gl.GLAcuario;
import tuman.gs_test.ep.gl.GLAxes;
import tuman.gs_test.ep.gl.GLCamera;
import tuman.gs_test.ep.gl.GLInit;
import tuman.gs_test.ep.gl.GLLight;
import tuman.gs_test.ep.gl.GLScene;
import tuman.gs_test.utils.TextListener;



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

			Point bounds = canvas.getSize();
			scene.getCamera().setViewSize(bounds.x, bounds.y);
			scene.paint(glContext);

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
			GLCamera camera = scene.getCamera();
			switch (e.keyCode) {
				case SWT.ARROW_UP:
					if ((e.stateMask & SWT.CONTROL) == SWT.CONTROL) {
						camera.setDistance(camera.getDistance() / 1.1);
					} else {
						camera.rotate(0.0, 10.0);
					}
					break;
				case SWT.ARROW_DOWN:
					if ((e.stateMask & SWT.CONTROL) == SWT.CONTROL) {
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



	/**
	 * Слушатель изменений поля высот.
	 * @author Sergei Tumanov
	 */
	private class GroundFieldListener extends TextListener {

		/**
		 * Create new instance.
		 * @param textField Тектовове поле.
		 */
		public GroundFieldListener(Text textField) {
			super(textField);
		}

		@Override
		public void apply(String text) {
			acuario.setBottom(text);
			acuario.fill();
			acuario.pourOut();
			glAcuario.refresh();
			canvas.redraw();
		}

		@Override
		public String getText() {
			return acuario.getBottomString();
		}

	}



	/** Аквариум. */
	private Acuario acuario;

	/** Корневой компонент. */
	private Composite control;
	/** Поле управления высотами основания. */
	private Text groundField;
	/** Компонент отображения аквариума. */
	private GLCanvas canvas;
	/** Контекст OpenGL. */
	GLContext glContext;
	/** Сцена. */
	GLScene scene;
	/** GL-объект Аквариума. */
	GLAcuario glAcuario;



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

		GridLayout layout = new GridLayout(1, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		control.setLayout(layout);

		groundField = new Text(control, SWT.BORDER);
		groundField.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		new GroundFieldListener(groundField);

		GLData gldata = new GLData();
		gldata.doubleBuffer = true;
		canvas = new GLCanvas(control, SWT.NO_BACKGROUND, gldata);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		canvas.setCurrent();
		GLProfile glprofile = GLProfile.getDefault();
		glContext = GLDrawableFactory.getFactory(glprofile).createExternalGLContext();

		canvas.addPaintListener(new ScenePainter());
		canvas.addKeyListener(new KeyListener());

		createScene();
	}

	/**
	 * Создать сцену.
	 */
	private void createScene() {
		scene = new GLScene();
		scene.setCamera(new GLCamera(200.0, 45.0, -45.0, 0, 0));
		scene.getChildren().add(new GLInit());
		scene.getChildren().add(new GLLight());
		scene.getChildren().add(new GLAxes());
		scene.getChildren().add(glAcuario = new GLAcuario(acuario));
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
		groundField.setText(acuario != null ? acuario.getBottomString() : "");
		glAcuario.setAcuario(acuario);
		glAcuario.refresh();
		setEnabled(isEnabled());
	}

	/**
	 * Получить доступность компонента.
	 * @return Доступность компонента.
	 */
	public boolean isEnabled() {
		return control.isEnabled();
	}

	/**
	 * Задать доступность компонента.
	 * @param enabled Доступность компонента.
	 */
	public void setEnabled(boolean enabled) {
		control.setEnabled(enabled);
		enabled &= acuario != null;
		groundField.setEnabled(enabled);
	}

	/**
	 * Установить фокус.
	 */
	public void setFocus() {
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
