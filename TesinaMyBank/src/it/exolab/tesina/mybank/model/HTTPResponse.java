package it.exolab.tesina.mybank.model;

public class HTTPResponse {

	
	private boolean success;
	private String err;
	private String err_code;
	private Object data;
	private String tipo;
	public HTTPResponse(boolean success, String err, String err_code, Object data) {
		super();
		this.success = success;
		this.err = err;
		this.err_code = err_code;
		this.data = data;
	}
	
	public HTTPResponse(Object data) {
		this(true,null,null,data);
	}
	public HTTPResponse(String err,String err_code) {
		this(true,err,err_code,null);
	}
	
	
	public HTTPResponse() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
