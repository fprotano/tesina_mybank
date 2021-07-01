package it.exolab.tesina.mybank.model;

public class ExternalPayment {

	private String customerName;
	private String customerSurname;
	private String customerCreditCardNo;
	private String customerCreditCardCin;
	private String customerCreditCardExpiresAt;

	private Payment payment;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public String getCustomerCreditCardNo() {
		return customerCreditCardNo;
	}

	public void setCustomerCreditCardNo(String customerCreditCardNo) {
		this.customerCreditCardNo = customerCreditCardNo;
	}

	public String getCustomerCreditCardCin() {
		return customerCreditCardCin;
	}

	public void setCustomerCreditCardCin(String customerCreditCardCin) {
		this.customerCreditCardCin = customerCreditCardCin;
	}

	public String getCustomerCreditCardExpiresAt() {
		return customerCreditCardExpiresAt;
	}

	public void setCustomerCreditCardExpiresAt(String customerCreditCardExpiresAt) {
		this.customerCreditCardExpiresAt = customerCreditCardExpiresAt;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public ExternalPayment(String customerName, String customerSurname, String customerCreditCardNo,
			String customerCreditCardCin, String customerCreditCardExpiresAt, Payment payment) {
		super();
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.customerCreditCardNo = customerCreditCardNo;
		this.customerCreditCardCin = customerCreditCardCin;
		this.customerCreditCardExpiresAt = customerCreditCardExpiresAt;
		this.payment = payment;
	}

	public ExternalPayment() {
		super();
	}

	@Override
	public String toString() {
		return "ExternalPayment [customerName=" + customerName + ", customerSurname=" + customerSurname
				+ ", customerCreditCardNo=" + customerCreditCardNo + ", customerCreditCardCin=" + customerCreditCardCin
				+ ", customerCreditCardExpiresAt=" + customerCreditCardExpiresAt + ", payment=" + payment + "]";
	}

}
