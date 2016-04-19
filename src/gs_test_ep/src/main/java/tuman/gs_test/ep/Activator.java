package tuman.gs_test.ep;



import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import tuman.gs_test.Acuario;



/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID = "tuman.gs_test_ep"; //$NON-NLS-1$

	/** The shared instance. */
	private static Activator plugin;



	/**
	 * Create new instance.
	 */
	public Activator() {}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		System.out.println("START tuman.gs_test_ep");
		Acuario acuario = Acuario.randomAquario();
		System.out.println(acuario);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		System.out.println("STOP tuman.gs_test_ep:");
	}

	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
