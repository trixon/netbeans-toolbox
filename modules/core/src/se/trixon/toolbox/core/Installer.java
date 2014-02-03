package se.trixon.toolbox.core;

import org.openide.modules.ModuleInstall;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.Xlog;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
//        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
//            @Override
//            public void run() {
//                openWindow("output");
//            }
//        });
        Xlog.v(Toolbox.LOG_TAG, "Loaded and ready...");
    }

    private void openWindow(String id) {
        TopComponent topComponent = WindowManager.getDefault().findTopComponent(id);
        if (topComponent != null) {
            topComponent.open();
        }
    }
}
