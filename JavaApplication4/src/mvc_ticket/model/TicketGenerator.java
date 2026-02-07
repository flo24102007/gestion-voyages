package mvc_ticket.model;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author flo
 */
public class TicketGenerator {
  public void genererPdf(Ticket ticket, String cheminFichier) {
        Document document = new Document(PageSize.A6.rotate()); 
        try {
            PdfWriter.getInstance(document, new FileOutputStream(cheminFichier));
            document.open();

            Font policeTitre = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
            Font policeLabel = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.GRAY);
            Font policeValeur = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);

            // Titre
            Paragraph titre = new Paragraph("CARNET DE VOYAGE - CROISIÈRE", policeTitre);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);
            
            document.add(new Paragraph("Navire : " + ticket.getNomBateau(), policeValeur));
            document.add(new Chunk(new LineSeparator()));

            // Infos Passager
            document.add(new Paragraph("PASSAGER :", policeLabel));
            document.add(new Paragraph(ticket.getNomPassager().toUpperCase(), policeValeur));

            // Trajet
            document.add(new Paragraph("ITINÉRAIRE :", policeLabel));
            document.add(new Paragraph("De : " + ticket.getLieuDepart() + " À : " + ticket.getLieuArrivee(), policeValeur));

            // Horaires
            document.add(new Paragraph("DATE DU VOYAGE : " + ticket.getDateVoyage(), policeValeur));
            document.add(new Paragraph("DÉPART : " + ticket.getHeureDepart() + "  |  ARRIVÉE PRÉVUE : " + ticket.getHeureArrivee(), policeValeur));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
