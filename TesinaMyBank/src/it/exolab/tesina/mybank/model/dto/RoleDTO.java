package it.exolab.tesina.mybank.model.dto;



public class RoleDTO {
	
	private Integer id;
	private String title;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "RoleDTO [id=" + id + ", title=" + title + "]";
	}
	
}
