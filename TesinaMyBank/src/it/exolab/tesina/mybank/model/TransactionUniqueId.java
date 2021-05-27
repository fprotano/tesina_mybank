package it.exolab.tesina.mybank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name = "transaction_unique_id")
public class TransactionUniqueId {
	
        @Id
		@Column(name="transaction_id") 
		private String transactionId;

		public String getTransactionId() { 
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId; 
		}

		@Override 
		public String toString() {
			return "InternalTransaction [transactionId=" + transactionId + "]";
		}

		public TransactionUniqueId(){
			
		}
	
	}

