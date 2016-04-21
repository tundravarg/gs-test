package tuman.gs_test.ep.gl;



import java.util.List;

import com.jogamp.opengl.GLContext;



/**
 * Группа объектов OpenGL.
 * @author Sergei Tumanov
 */
public interface IGLGroup extends IGLObject {

	/**
	 * Получить список дочерних объектов.
	 * @return список дочерних объектов.
	 */
	List<IGLObject> getChildren();

	@Override
	default void paint(GLContext glContext) {
		for (IGLObject child: getChildren())
			child.paint(glContext);
	}

}
