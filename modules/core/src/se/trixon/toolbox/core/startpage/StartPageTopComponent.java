/*
 * Copyright 2015 Patrik Karlsson.
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
package se.trixon.toolbox.core.startpage;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.prefs.Preferences;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import se.trixon.almond.about.AboutAction;
import se.trixon.almond.dictionary.Dict;
import se.trixon.toolbox.core.ToolProvider;
import se.trixon.toolbox.core.news.NewsBuilder;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ConvertAsProperties(
        dtd = "-//se.trixon.toolbox.core//StartPage//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "StartPageTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "se.trixon.toolbox.core.StartPageTopComponent")
@ActionReference(path = "Menu/Help", position = 1400)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_StartPageAction",
        preferredID = "StartPageTopComponent"
)
public final class StartPageTopComponent extends TopComponent {

    public static final String KEY_SHOW_START_PAGE_ON_STARTUP = "showStartPageOnStartup";
    private final Preferences mPreferences;

    public StartPageTopComponent() {
        mPreferences = NbPreferences.forModule(StartPageTopComponent.class);
        initComponents();

        setName(NbBundle.getMessage(StartPageTopComponent.class, "CTL_StartPageTopComponent"));
        init();
        initInstalledTools();
        initNews();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color upperColor = new Color(212, 0, 0);
        Color lowerColor = Color.WHITE;
        GradientPaint gp = new GradientPaint(0, 0, upperColor, 0, h, lowerColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    private void init() {
        headerLabel.setFont(headerLabel.getFont().deriveFont(headerLabel.getFont().getStyle() | java.awt.Font.BOLD, 48));
        copyrightLabel.setText(AboutAction.getAboutBundle().getString("application.copyright"));
    }
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private void initInstalledTools() {
        Collection<? extends ToolProvider> toolProviders = Lookup.getDefault().lookupAll(ToolProvider.class);
        StringBuilder builder = new StringBuilder("<html><head><style>li { font-size: 12px }</style></head>");

        if (toolProviders.isEmpty()) {
            String header = NbBundle.getMessage(StartPageTopComponent.class, "noInstalledTools");
            builder.append("<h2>").append(header).append("</h2>");
        } else {
            String header = NbBundle.getMessage(StartPageTopComponent.class, "installedTools");
            builder.append("<h2>").append(header).append("</h2>");

            ArrayList<String> tools = new ArrayList<>();

            for (ToolProvider provider : toolProviders) {
                tools.add(String.format("%s (%s)", provider.getName(), provider.getDescription()));
            }

            Collections.sort(tools);
            builder.append("<ul>");

            for (String tool : tools) {
                builder.append("<li>").append(tool).append("</li>");
            }

            builder.append("</ul>");
        }

        toolsLabel.setText(builder.toString());
    }

    private void initNews() {
        StringBuilder builder = new StringBuilder("<html><head><style>body {margin-left: 16px; } li { font-size: 12px } h1 { font-size: 16px; color: red; margin-bottom: 0px; } h2 { font-size: 11px; margin-bottom: 0px; } p {margin-bottom: 4px;margin-top: 4px;}</style></head>");
        builder.append("<h1>").append(Dict.NEWS.getString()).append("</h1>");
        builder.append(new NewsBuilder().getNews());

        newsTextPane.setText(builder.toString());
        newsTextPane.setCaretPosition(0);
        newsScrollPane.getVerticalScrollBar().setValue(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLabel = new javax.swing.JLabel();
        startCheckBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        welcomeLabel = new javax.swing.JLabel();
        splitPanel = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(32, 0), new java.awt.Dimension(32, 0), new java.awt.Dimension(32, 32767));
        toolsLabel = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(64, 0), new java.awt.Dimension(64, 0), new java.awt.Dimension(64, 32767));
        newsScrollPane = new javax.swing.JScrollPane();
        newsTextPane = new javax.swing.JTextPane();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(32, 0), new java.awt.Dimension(32, 0), new java.awt.Dimension(32, 32767));
        creditLabel = new javax.swing.JLabel();
        copyrightLabel = new javax.swing.JLabel();

        headerLabel.setForeground(new java.awt.Color(62, 62, 62));
        org.openide.awt.Mnemonics.setLocalizedText(headerLabel, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.headerLabel.text")); // NOI18N
        headerLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        headerLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        headerLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        headerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                headerLabelMouseClicked(evt);
            }
        });

        startCheckBox.setBackground(new java.awt.Color(212, 0, 0));
        startCheckBox.setFont(startCheckBox.getFont());
        startCheckBox.setForeground(new java.awt.Color(62, 62, 62));
        org.openide.awt.Mnemonics.setLocalizedText(startCheckBox, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.startCheckBox.text")); // NOI18N
        startCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startCheckBoxActionPerformed(evt);
            }
        });

        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(welcomeLabel.getFont().getStyle() | java.awt.Font.BOLD, welcomeLabel.getFont().getSize()+6));
        welcomeLabel.setForeground(new java.awt.Color(62, 62, 62));
        org.openide.awt.Mnemonics.setLocalizedText(welcomeLabel, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.welcomeLabel.text")); // NOI18N

        splitPanel.setOpaque(false);
        splitPanel.setLayout(new javax.swing.BoxLayout(splitPanel, javax.swing.BoxLayout.LINE_AXIS));
        splitPanel.add(filler1);

        org.openide.awt.Mnemonics.setLocalizedText(toolsLabel, "Installed tools");
        toolsLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        toolsLabel.setMaximumSize(new java.awt.Dimension(32767, 32767));
        toolsLabel.setMinimumSize(new java.awt.Dimension(25, 25));
        toolsLabel.setPreferredSize(new java.awt.Dimension(32767, 32767));
        splitPanel.add(toolsLabel);
        splitPanel.add(filler2);

        newsScrollPane.setPreferredSize(new java.awt.Dimension(32767, 32767));

        newsTextPane.setEditable(false);
        newsTextPane.setContentType("text/html"); // NOI18N
        newsTextPane.setMaximumSize(new java.awt.Dimension(32767, 32767));
        newsScrollPane.setViewportView(newsTextPane);

        splitPanel.add(newsScrollPane);
        splitPanel.add(filler3);

        org.openide.awt.Mnemonics.setLocalizedText(creditLabel, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.creditLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(copyrightLabel, "copyright"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(headerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                        .addComponent(startCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(copyrightLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(creditLabel)
                        .addGap(32, 32, 32))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(splitPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startCheckBox)
                    .addComponent(headerLabel))
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(welcomeLabel)
                .addGap(18, 18, 18)
                .addComponent(splitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(copyrightLabel)
                    .addComponent(creditLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startCheckBoxActionPerformed
        mPreferences.putBoolean(KEY_SHOW_START_PAGE_ON_STARTUP, startCheckBox.isSelected());
    }//GEN-LAST:event_startCheckBoxActionPerformed

    private void headerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerLabelMouseClicked
        try {
            URI mUri = new URI(AboutAction.getAboutBundle().getString("application.uri"));
            Desktop.getDesktop().browse(mUri);
        } catch (IOException | URISyntaxException ex) {
            System.err.println(ex);
        }

    }//GEN-LAST:event_headerLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel copyrightLabel;
    private javax.swing.JLabel creditLabel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane newsScrollPane;
    private javax.swing.JTextPane newsTextPane;
    private javax.swing.JPanel splitPanel;
    private javax.swing.JCheckBox startCheckBox;
    private javax.swing.JLabel toolsLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        startCheckBox.setSelected(mPreferences.getBoolean(KEY_SHOW_START_PAGE_ON_STARTUP, true));
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0"); //NOI18N
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version"); //NOI18N
    }
}
