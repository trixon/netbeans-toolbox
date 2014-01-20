package se.trixon.toolbox.branding;

import java.util.ResourceBundle;
import org.openide.util.ImageUtilities;
import se.trixon.almond.about.AboutAction;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AboutInitializer {

    public static void init() {
        AboutAction.setAboutBundle(ResourceBundle.getBundle("se/trixon/toolbox/branding/about"));
        AboutAction.setLicenseBundle(ResourceBundle.getBundle("se/trixon/toolbox/branding/license"));
        AboutAction.setImageIcon(ImageUtilities.loadImageIcon("se/trixon/toolbox/branding/logo.png", false));
    }
}
