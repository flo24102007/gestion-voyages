/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.recherche;

import mvc_voyage.recherche.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author kali
 */
public class Search {
    public static void recherche(JTable jt, JTextField jtf, JLabel jl){
        DefaultTableModel dtm = (DefaultTableModel)jt.getModel();
        String mot = jtf.getText().trim();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dtm);
        trs.setRowFilter(RowFilter.regexFilter(mot));
        int nbr = jt.getRowCount();
        if (nbr==0) {
            jl.setForeground(Color.RED);
            jl.setText("aucun resultat");
        }
        
         if (nbr==1) {
            jl.setForeground(new Color(0,102,0));
             jl.setText("un resultat trouvé");
        }
         
          if (nbr==0) {
            jl.setForeground(new Color(0,102,0));
             jl.setText("Retrouvé: "+nbr);
        }
        
    }
    
}
