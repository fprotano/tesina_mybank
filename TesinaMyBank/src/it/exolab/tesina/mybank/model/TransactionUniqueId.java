package it.exolab.tesina.mybank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class TransactionUniqueId {

	@Entity
	@Table(name = "internal_transaction")
	public class InternalTransaction {

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

		public InternalTransaction() {
			super();
		}
		
	
	}
}
