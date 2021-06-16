package it.exolab.tesina.mybank.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class createAndSendPdf {

	static OutputStream file;

	public  static void write() throws IOException {	
		
		 try {
		       	//Create Document instance.
			Document document = new Document();
		 
			//Create OutputStream instance.
			OutputStream outputStream = 
				new FileOutputStream(new File("C:\\sviluppo\\PestFile.pdf"));
		 
			//Create PDFWriter instance.
		        PdfWriter.getInstance(document, outputStream);
		 
		        //Open the document.
		        document.open();
		 
		        //Add content to the document.
		        document.add(new Paragraph("Hello world, " +
		       	"this is a test pdf file."));
		 
		        //Close document and outputStream.
		        document.close();
		        outputStream.close();
		 
		        System.out.println("Pdf created successfully.");
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  }
	


	}
