package it.exolab.tesina.mybank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="role")
public class Role {
	
	@Id
	private Integer id;
	private String title;
	
	@OneToMany(mappedBy="role")
//	@Transient
	private List<Staff> staff;
	
	public List<Staff> getStaff() {
		return staff;
	}
	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}
	
	
	
	
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
		return "Role [id=" + id + ", title=" + title + "]";
	}
	public Role() {
		super();
	}
	
}
