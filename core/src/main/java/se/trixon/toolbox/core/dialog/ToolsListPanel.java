/* 
 * Copyright 2016 Patrik Karlsson.
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
package se.trixon.toolbox.core.dialog;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.DefaultRowSorter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableColumnModel;
import org.openide.util.Lookup;
import se.trixon.toolbox.core.ToolProvider;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ToolsListPanel extends javax.swing.JPanel {

    private final ToolsListTableModel mListTableModel = new ToolsListTableModel();

    /**
     * Creates new form ToolsListPanel
     */
    public ToolsListPanel() {
        initComponents();
        table.setModel(mListTableModel);
        table.setAutoCreateRowSorter(true);
        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(ToolsListTableModel.COLUMN_NAME).setPreferredWidth(100);
        columnModel.getColumn(ToolsListTableModel.COLUMN_DESCRIPTION).setPreferredWidth(100);
        columnModel.getColumn(ToolsListTableModel.COLUMN_VERSION).setPreferredWidth(10);
        columnModel.getColumn(ToolsListTableModel.COLUMN_MODULE).setPreferredWidth(100);
        columnModel.getColumn(ToolsListTableModel.COLUMN_COPYRIGHT).setPreferredWidth(100);
        columnModel.getColumn(ToolsListTableModel.COLUMN_LICENSE).setPreferredWidth(40);

        Collection<? extends ToolProvider> toolProviders = Lookup.getDefault().lookupAll(ToolProvider.class);

        toolProviders.stream().forEach((toolProvider) -> {
            mListTableModel.addRow(toolProvider);
        });

        DefaultRowSorter rowSorter = ((DefaultRowSorter) table.getRowSorter());
        ArrayList list = new ArrayList();
        list.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        rowSorter.setSortKeys(list);
        rowSorter.sort();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        scrollPane.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
