/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.view;

import java.awt.Font;
import java.awt.event.ActionListener; // AJOUTEZ CET IMPORT
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import mvc_voyage.infra.view.ButtonEditor;
import mvc_voyage.infra.view.ButtonRenderer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 *
 * @author macbook
 */
public class VoyageView extends javax.swing.JFrame {

    /**
     * Creates new form VoyageView
     */
    public VoyageView() {
        initComponents();
        customComponents();
        configurerBoutonsActions();
    }

    private void customComponents() {
        tbListeVoyages.setRowHeight(40);
        tbListeVoyages.getColumn("ID").setPreferredWidth(0);
        tbListeVoyages.getColumn("ID").setMinWidth(0);
        tbListeVoyages.getColumn("ID").setMaxWidth(0);

        // Ajuster la largeur des colonnes
        tbListeVoyages.getColumn("Lieu Départ").setPreferredWidth(100);
        tbListeVoyages.getColumn("Lieu Arrivée").setPreferredWidth(100);
        tbListeVoyages.getColumn("Date Départ").setPreferredWidth(150);
        tbListeVoyages.getColumn("Date Arrivée").setPreferredWidth(150);
        tbListeVoyages.getColumn("Prix").setPreferredWidth(80);
        tbListeVoyages.getColumn("Sièges").setPreferredWidth(80);
        tbListeVoyages.getColumn("Bateau ID").setPreferredWidth(80);
        tbListeVoyages.getColumn("Durée").setPreferredWidth(80);
        tbListeVoyages.getColumn("Actions").setPreferredWidth(150);

        // Configurer les boutons d'actions
        Font font = new Font("Comic Sans MS", 1, 18);
        tbListeVoyages.setFont(font);
        JTableHeader header = tbListeVoyages.getTableHeader();
        header.setFont(font);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
    }
     

    private void configurerBoutonsActions() {
        // --- 1. CONFIGURATION COLONNE ACTIONS (Index 9) ---
        ButtonRenderer actionsRenderer = new ButtonRenderer();
        ButtonEditor actionsEditor = new ButtonEditor(tbListeVoyages);

        ActionListener editListener = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int row = tbListeVoyages.getSelectedRow();
                if (row >= 0) {
                    onEditAction(row);
                }
            }
        };

        ActionListener deleteListener = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int row = tbListeVoyages.getSelectedRow();
                if (row >= 0) {
                    onDeleteAction(row);
                }
            }
        };

        actionsRenderer.setEditActionListener(editListener);
        actionsRenderer.setDeleteActionListener(deleteListener);
        actionsEditor.setEditActionListener(editListener);
        actionsEditor.setDeleteActionListener(deleteListener);

        TableColumn actionsColumn = tbListeVoyages.getColumn("Actions");
        actionsColumn.setCellRenderer(actionsRenderer);
        actionsColumn.setCellEditor(actionsEditor);

        // --- 2. CONFIGURATION COLONNE DURÉE (Index 8) ---
        ButtonRenderer dureeRenderer = new ButtonRenderer();
        ButtonEditor dureeEditor = new ButtonEditor(tbListeVoyages);

        ActionListener calculListener = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Utilisation de VoyageView.this pour éviter l'erreur sur le contexte
                int row = tbListeVoyages.getSelectedRow();
                if (row >= 0) {
                    calculerDureeVoyage(row);
                }
            }
        };

        // Appliquer à la colonne Durée
        TableColumn dureeColumn = tbListeVoyages.getColumn("Durée");
        dureeColumn.setCellRenderer(dureeRenderer);
        dureeColumn.setCellEditor(dureeEditor);

        // IMPORTANT: On réutilise le setter de ton ButtonEditor
        dureeEditor.setEditActionListener(calculListener);
    } // FIN de la méthode configurerBoutonsActions

    private void onEditAction(int row) {
        if (row >= 0 && row < tbListeVoyages.getRowCount()) {
            // Récupérer les données de la ligne
            Long id = (Long) tbListeVoyages.getValueAt(row, 0);
            String depart = (String) tbListeVoyages.getValueAt(row, 1);
            String arrivee = (String) tbListeVoyages.getValueAt(row, 2);
            String dateDepart = (String) tbListeVoyages.getValueAt(row, 3);
            Integer prix = (Integer) tbListeVoyages.getValueAt(row, 5);
            Integer sieges = (Integer) tbListeVoyages.getValueAt(row, 6);

            // Afficher les détails
            String message = String.format(
                    "Détails du voyage à modifier:\n\n"
                    + "ID: %d\n"
                    + "Départ: %s\n"
                    + "Arrivée: %s\n"
                    + "Date départ: %s\n"
                    + "Prix: %d €\n"
                    + "Sièges disponibles: %d\n\n"
                    + "Cette fonctionnalité ouvrirait normalement\n"
                    + "un formulaire de modification.",
                    id, depart, arrivee, dateDepart, prix, sieges
            );

            JOptionPane.showMessageDialog(this,
                    message,
                    "Modifier voyage",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void calculerDureeVoyage(int row) {
        try {
            // 1. Récupération des valeurs
            Object depObj = tbListeVoyages.getValueAt(row, 3);
            Object arrObj = tbListeVoyages.getValueAt(row, 4);

            if (depObj == null || arrObj == null) {
                JOptionPane.showMessageDialog(this, "Dates manquantes.");
                return;
            }

            // 2. Préparation du Formatter pour le format : "EEE MMM dd HH:mm:ss zzz yyyy"
            // Exemple : Thu Feb 05 00:00:00 GMT 2026
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

            // 3. Conversion des Strings en LocalDateTime
            LocalDateTime debut = LocalDateTime.parse(depObj.toString(), formatter);
            LocalDateTime fin = LocalDateTime.parse(arrObj.toString(), formatter);

            // 4. Calcul de la différence
            long jours = ChronoUnit.DAYS.between(debut, fin);
            long heures = ChronoUnit.HOURS.between(debut, fin) % 24;
            long minutes = ChronoUnit.MINUTES.between(debut, fin) % 60;

            // 5. Construction du texte de résultat
            StringBuilder sb = new StringBuilder();
            if (jours > 0) {
                sb.append(jours).append("j ");
            }
            if (heures > 0) {
                sb.append(heures).append("h ");
            }
            if (minutes > 0 || sb.length() == 0) {
                sb.append(minutes).append("min");
            }

            String resultat = sb.toString().trim();

            JOptionPane.showMessageDialog(this, "le voyage va durée : " + resultat);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de calcul : " + e.getMessage());
        }
    }

    private void onDeleteAction(int row) {
        if (row >= 0 && row < tbListeVoyages.getRowCount()) {
            Long id = (Long) tbListeVoyages.getValueAt(row, 0);
            String depart = (String) tbListeVoyages.getValueAt(row, 1);
            String arrivee = (String) tbListeVoyages.getValueAt(row, 2);

            int confirmation = JOptionPane.showConfirmDialog(this,
                    String.format(
                            "Êtes-vous sûr de vouloir supprimer ce voyage ?\n\n"
                            + "ID: %d\n"
                            + "Trajet: %s → %s",
                            id, depart, arrivee
                    ),
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                // Supprimer la ligne de la table
                ((DefaultTableModel) tbListeVoyages.getModel()).removeRow(row);

                JOptionPane.showMessageDialog(this,
                        "Voyage supprimé avec succès.",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Méthode pour charger des données de test
    public void chargerDonneesTest() {
        DefaultTableModel model = (DefaultTableModel) tbListeVoyages.getModel();
        model.setRowCount(0); // Vider la table

        // Ajouter des données de test
        Object[] voyage1 = {1L, "Iome", "kara", "Tue Feb 03 00:00", "Sat Feb 07 00:00", 2500, 12, 1, "4 jours", null};
        Object[] voyage2 = {2L, "kio", "oki", "Fri Feb 06 00:00", "Sat Feb 07 00:00", 1500, 0, 1, "1 jour", null};
        Object[] voyage3 = {3L, "Marseille", "Corse", "Sun Jun 01 00:00", "Sun Jun 01 18:00", 45, 3, 1, "18h", null};

        model.addRow(voyage1);
        model.addRow(voyage2);
        model.addRow(voyage3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListeVoyages = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btNouveau = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setBackground(new java.awt.Color(0, 204, 255));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Liste des voyages");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbListeVoyages.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{ // Laisser vide - les données seront ajoutées dynamiquement
                },
                new String[]{
                    "ID", "Lieu Départ", "Lieu Arrivée", "Date Départ", "Date Arrivée", "Prix", "Sièges", "Bateau ID", "Durée", "Actions"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbListeVoyages);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel1.setBackground(new java.awt.Color(0, 204, 255));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Copyright (c) KOUASSI 2025");

        btNouveau.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        btNouveau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mvc_voyage/icons/plus-icon.png"))); // NOI18N
        btNouveau.setText("Nouveau");
        btNouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNouveauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btNouveau)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btNouveau)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>                        

    private void btNouveauActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public JButton getBtNouveau() {
        return btNouveau;
    }

    public JTable getTbListeVoyages() {
        return tbListeVoyages;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btNouveau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbListeVoyages;
    // End of variables declaration                   
}
