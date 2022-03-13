package com.coffeelovers.pcu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * this class  represents the factories that use the system
 */
@Entity
@Table(name = "actors")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long actorId;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "activity")
	private String activity;

	@Column(name = "phone")
	private long phone;

	@Column(name = "webside")
	private String webside;
	
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name = "address")
	private String address;

	public Actor() {}
	
	/***
	 * 
	 * @param brand
	 * @param activity for example, women garment , man garment or lingerie .. 
	 * @param phone
	 * @param webside
	 * @param email
	 * @param password
	 * @param address
	 */
	public Actor(String brand, String activity, long phone, String webside, String email, 
			String password, String address) {
		this.brand = brand;
		this.activity = activity;
		this.phone = phone;
		this.webside = webside;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public long getActorId() {
		return actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getWebside() {
		return webside;
	}

	public void setWebside(String webside) {
		this.webside = webside;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/* Methods ------------------------------------------------------------------------------ */
	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", brand=" + brand + ", activity=" + activity + ", phone=" + phone
				+ ", webside=" + webside + ", email=" + email + ", password=" + password + ", address=" + address + "]";
	}
}