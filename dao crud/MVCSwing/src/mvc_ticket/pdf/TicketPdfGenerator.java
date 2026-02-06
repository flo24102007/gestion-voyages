/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_ticket.pdf;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import mvc_bateau.model.Ticket;
import mvc_bateau.model.Voyage;
import mvc_bateau.model.Passager;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;



/**
 * Générateur simple de ticket PDF
 * @author flo
 */
import java.io.File;
import java.io.FileOutputStream;


public class TicketPdfGenerator {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    /**
     * Génère un ticket PDF avec un nom de fichier automatique
     */
    public static String genererTicketAuto(Ticket ticket, Voyage voyage, Passager passager) {
        try {
            String nomFichier = "Ticket_" + ticket.getId() + "_" + passager.getNom().replace(" ", "_") + ".pdf";
            String outputPath = "tickets/" + nomFichier;
            
            File dossier = new File("tickets");
            if (!dossier.exists()) {
                dossier.mkdirs();
            }
            
            genererTicket(ticket, voyage, passager, outputPath);
            
            System.out.println("✅ Ticket généré avec succès : " + outputPath);
            return outputPath;
            
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la génération du ticket : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Génère un ticket PDF
     */
    public static void genererTicket(Ticket ticket, Voyage voyage, Passager passager, String outputPath) 
            throws Exception {
        // ... votre code existant ...
    }
    
}
