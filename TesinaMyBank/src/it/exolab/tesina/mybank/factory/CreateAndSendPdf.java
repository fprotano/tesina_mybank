package it.exolab.tesina.mybank.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateAndSendPdf extends EmailFactoryData{



	public  static String write()  {	
		String ret="";
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			ret="File temporaneo creato con successo.";
		} catch (FileNotFoundException e) {
			ret="Errore di I/O";
			e.printStackTrace();
			 
		} catch (DocumentException e) {
			ret="Errore documentoException";
			e.printStackTrace();
		}

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("CIAObELLI", font);
    
		try {
			document.add(chunk);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
	     String a = "" + document;
	     return ret;		
		
	}

	public int delete() {
		int ret=0;
		File f= new File(filePath);  
		if (f.delete()) {
			ret=1;
		}
		return ret;
	}
	
}
