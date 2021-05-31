package it.exolab.tesina.mybank.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
 
@Entity 
@Table(name = "transaction_unique_id")
public class TransactionUniqueId {
        @Id
		@Column(name="transaction_id") 
		private String transactionId;
        
     // OneToMany per ExternalTransaction
   	@OneToOne(mappedBy="transactionUniqueId")
   	private List<ExternalTransaction> externaltransaction;
   	
   	public List<ExternalTransaction> getExternaltransaction() {
   		return externaltransaction;
   	}
   	public void setExternaltransaction(List<ExternalTransaction> externaltransaction) {
   		this.externaltransaction = externaltransaction;
   	}        

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		@Override
		public String toString() {
			return "TransactionUniqueId [transactionId=" + transactionId + "]";
		}

		public TransactionUniqueId() {
			super();
		}

		

		
	
	}

