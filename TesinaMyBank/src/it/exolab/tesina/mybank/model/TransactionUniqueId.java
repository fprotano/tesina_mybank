package it.exolab.tesina.mybank.model;



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
   	private ExternalTransaction externaltransaction;
   	
   	public ExternalTransaction getExternaltransaction() {
   		return externaltransaction;
   	}
   	public void setExternaltransaction(ExternalTransaction externaltransaction) {
   		this.externaltransaction = externaltransaction;
   	}        

    // OneToOne per InternalTransaction
   	@OneToOne(mappedBy="transactionUniqueId")
   	private InternalTransaction internaltransaction;
   	
	public InternalTransaction getInternaltransaction() {
		return internaltransaction;
	}
	public void setInternaltransaction(InternalTransaction internaltransaction) {
		this.internaltransaction = internaltransaction;
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

