package it.exolab.tesina.mybank.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import it.exolab.tesina.mybank.model.Payment;

public class CreateAndSendPdf extends EmailFactoryData {

	public String writeCapo(Payment payment) throws IOException {
		File file = null;
		Document document = new Document(PageSize.A4, 80, 80, 80, 80);
		try {
			file = File.createTempFile("fattura  ", ".pdf");
			PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		document.open();

		try {

			Font font = FontFactory.getFont(FontFactory.COURIER, 26, BaseColor.BLACK);
			Chunk chunk = new Chunk("Salve", font);
			document.add(chunk);
			document.add(new Paragraph("Pagamento avvenuto con successo"));
			document.add(new Paragraph("Email :" + payment.getEmail()));
			document.add(new Paragraph("Prezzo :" + payment.getAmount() + "€"));
			document.add(new Paragraph("Codice Ordine :" + payment.getCustomCode()));
			document.add(new Paragraph("Codice Transazione:" + payment.getTransactionId()));
			document.add(new Paragraph("Grazie buona giornata"));

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
		return file.getAbsolutePath();
	}

	// ##############################################################################################################################
	// ##############################################################################################################################
	// ##############################################################################################################################
	// ##############################################################################################################################
	// ##############################################################################################################################

	// da finire - per pdf con tabella
	public String doMakePdf(Payment payment) throws IOException {
		File file = null;
		Document document = new Document(PageSize.A4, 40, 40, 40, 40);
		try {

			// file = File.createTempFile("fattura_"+Timestamp.valueOf(LocalDateTime.now()),".pdf");
			// PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath()));

			PdfWriter.getInstance(document, new FileOutputStream(filePath));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		document.open();

		try {
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);

			PdfPTable table = new PdfPTable(2);

			LineSeparator line = new LineSeparator();

			// setta la larghezza della table in percentuale rispetto lo schermo
			table.setWidthPercentage(100);

			// la larghezza di ogni colonna. Il totale deve combaciare con il totalwidth
			table.setWidths(new int[] { 1, 4 });

			PdfPHeaderCell header = new PdfPHeaderCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// header.setBorderWidth(1);
			header.setColspan(2);
			header.setFixedHeight(14f);
			table.addCell(header);

			addRows(table, "Email", payment.getEmail());
			addRows(table, "Quantità", "€ " + payment.getAmount().toString());
			addRows(table, "ID Ordine", payment.getCustomCode());
			addRows(table, "ID Transazione", payment.getTransactionId());

			document.add(table);
			document.add(line);

			// Font font = FontFactory.getFont(FontFactory.COURIER, 26, BaseColor.BLACK);
			// Chunk chunk = new Chunk("Salve", font);
			// document.add( chunk );
			// document.add( new Paragraph( "Pagamento avvenuto con successo" ) );
			// document.add( new Paragraph( "Email :" +payment.getEmail() ) );
			// document.add( new Paragraph( "Prezzo :" +payment.getAmount() +"€") );
			// document.add( new Paragraph( "Codice Ordine :" +payment.getCustomCode() ) );
			// document.add( new Paragraph( "Codice Transazione:"
			// +payment.getTransactionId() ) );
			// document.add( new Paragraph( "Grazie buona giornata" ) );

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
		return filePath;
		// return file.getAbsolutePath();
	}

	// aggiungo riga alla tabella
	private void addRows(PdfPTable table, String intestazione, String valore) {
		table.addCell(intestazione);
		table.addCell(valore);
	}

	// da deprecare
	public int delete() {
		int ret = 0;
		File f = new File(filePath);
		if (f.delete()) {
			ret = 1;
		}
		return ret;
	}

	// deprecato
	// public static String write() {
	// String ret="";
	// Document document = new Document();
	// try {
	// PdfWriter.getInstance(document, new FileOutputStream(filePath));
	// ret="File temporaneo creato con successo.";
	// } catch (FileNotFoundException e) {
	// ret="Errore di I/O";
	// e.printStackTrace();
	//
	// } catch (DocumentException e) {
	// ret="Errore documentoException";
	// e.printStackTrace();
	// }
	//
	//
	// document.open();
	//
	// Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
	//
	// Chunk chunk = new Chunk("CIAObELLI", font);
	//
	//
	//
	// try {
	// document.add(chunk);
	//
	//
	//
	// } catch (DocumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// document.close();
	// String a = "" + document;
	// return ret;
	//
	// }

}
