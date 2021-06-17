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

public class createAndSendPdf {



	public  static String write()  {	
	
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("ciaoBello.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
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
	     return a;		
		
	}
      
	}
