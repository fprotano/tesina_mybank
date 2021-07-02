package it.exolab.tesina.mybank.bat.auto.refuse.external.transaction;



public class AutoRefuseExternalTransaction {
public static void main(String[]args) {
	java.sql.Connection conn = null;
	java.sql.Statement stmt = null;
	 try {
	    try {
	       Class.forName("com.mysql.jdbc.Driver");
	} catch (Exception e) {
	   System.out.println(e);
	}
		conn = (java.sql.Connection) java.sql.DriverManager.getConnection(args[0], args[1], args[2]); // modificare con vostro indirizzo
		System.out.println("Connessione effettuata:");
		System.out.println(args[0] + " " + args[1] + " " + args[2]);
		stmt = (java.sql.Statement) conn.createStatement();
		String query1 = "UPDATE external_transaction AS et \r\n" + 
				"SET et.transaction_status_id=2 , et.transaction_error_reason='transazione annullata per scadenza termini'  \r\n" + 
				"WHERE et.transaction_status_id=1 AND et.created_at < NOW() - INTERVAL 3 DAY;";
		stmt.executeUpdate(query1);
		System.out.println("Record aggiornato .........-------->");
	 } catch (java.sql.SQLException e) {
	  
	 } catch (Exception x) {
	    
	 } finally {
	    try {
	       if (stmt != null)
	       conn.close();
	    } catch (java.sql.SQLException se) {}
	    try {
	       if (conn != null)
	       conn.close();
	    } catch (java.sql.SQLException se) {
	       se.printStackTrace();
	    }
	 }
    
  }

}

