/* 
 * Copyright 2019 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.toolbox.core;

import java.awt.Color;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import org.openide.modules.OnStart;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.ActionHelper;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.nbp.InitIcons;
import se.trixon.almond.nbp.NbLog;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.toolbox.core.startpage.StartPageTopComponent;

/**
 *
 * @author Patrik Karlsson
 */
@OnStart
public class Installer implements Runnable {

    private final Preferences mPreferences;

    public Installer() {
        mPreferences = NbPreferences.forModule(StartPageTopComponent.class);
    }

    @Override
    public void run() {
        WindowManager.getDefault().invokeWhenUIReady(() -> {
            ResourceBundle bundle = ResourceBundle.getBundle("se/trixon/toolbox/core/about/about");
            JFrame mainFrame = (JFrame) WindowManager.getDefault().getMainWindow();
            mainFrame.setTitle(bundle.getString("app.name"));
//                openWindow("output");
            if (mPreferences.getBoolean(StartPageTopComponent.KEY_SHOW_START_PAGE_ON_STARTUP, true)) {
                openWindow("StartPageTopComponent");
            }

            InitIcons.run();

//            IconColor iconColor = AlmondOptions.getInstance().getIconColor();
            String category = "Actions/File";
            String id = "se.trixon.toolbox.core.actions.ToolInfoAction";
            Color iconColor = Color.BLUE;
            ActionHelper.setIconSmall(category, id, MaterialIcon._Action.INFO_OUTLINE.getImageIcon(Almond.ICON_SMALL, iconColor));
            ActionHelper.setIconLarge(category, id, MaterialIcon._Action.INFO_OUTLINE.getImageIcon(Almond.ICON_LARGE, iconColor));

            id = "se.trixon.toolbox.core.actions.ToolListAction";
            ActionHelper.setIconSmall(category, id, MaterialIcon._Action.LIST.getImageIcon(Almond.ICON_SMALL, iconColor));
            ActionHelper.setIconLarge(category, id, MaterialIcon._Action.LIST.getImageIcon(Almond.ICON_LARGE, iconColor));

            id = "se.trixon.toolbox.core.actions.ToolOptionsAction";
            //TODO Will not set icon, Action does not exist at this time
            ActionHelper.setIconSmall(category, id, MaterialIcon._Action.SETTINGS.getImageIcon(Almond.ICON_SMALL, iconColor));
            ActionHelper.setIconLarge(category, id, MaterialIcon._Action.SETTINGS.getImageIcon(Almond.ICON_LARGE, iconColor));

            NbLog.select();
            NbLog.v(Toolbox.LOG_TAG, "Loaded and ready...");
        });
    }

    private void openWindow(String id) {
        TopComponent topComponent = WindowManager.getDefault().findTopComponent(id);
        if (topComponent != null) {
            topComponent.open();
        }
    }
}
