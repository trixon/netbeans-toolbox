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
package se.trixon.toolbox.core.about;

import org.openide.modules.OnStart;
import se.trixon.almond.nbp.about.AboutAction;
import se.trixon.almond.util.AboutModel;
import se.trixon.almond.util.SystemHelper;

/**
 *
 * @author Patrik Karlsson
 */
@OnStart
public class AboutInitializer implements Runnable {

    @Override
    public void run() {
        AboutModel aboutModel = new AboutModel(SystemHelper.getBundle(getClass(), "about"),
                SystemHelper.getResourceAsImageIcon(getClass(), "logo.png"));
        AboutAction.setAboutModel(aboutModel);
    }
}
