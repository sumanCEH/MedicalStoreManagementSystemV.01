package com.capgemini.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long billId;

	@NotBlank(message = "Address should not be blank")
	private String address;
	
	@NotBlank(message = "Name should not be blank")
	private String customerName;
	
	@NotBlank(message = "Phone should not be blank")
	private String phoneNo;
	
	@NotBlank(message = "Age should not be blank")
	private String age;
	
	@NotBlank(message = "Sex should not be blank")
	private String sex;
	
	@NotNull(message = "Amount should not be blank")
	private Long totalAmount;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "bill_product", joinColumns = { @JoinColumn(name = "bill_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	private Set<Product> products = new HashSet<>();

	public void addProduct(Product product) {
		this.products.add(product);
		product.getBills().add(this);
	}

	public void removeProduct(Integer productId) {
		Product product = this.products.stream().filter(t -> t.getProductId() == productId).findFirst().orElse(null);
		if (product != null) {
			this.products.remove(product);
			product.getBills().remove(this);
		}
	}
	
	

	public Billing() {
	
	}

	public Billing(Long billId, @NotBlank(message = "Address should not be blank") String address,
			@NotBlank(message = "Name should not be blank") String customerName,
			@NotBlank(message = "Phone should not be blank") String phoneNo,
			@NotBlank(message = "Age should not be blank") String age,
			@NotBlank(message = "Sex should not be blank") String sex,
			@NotNull(message = "Amount should not be blank") Long totalAmount, Set<Product> products) {
		super();
		this.billId = billId;
		this.address = address;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.age = age;
		this.sex = sex;
		this.totalAmount = totalAmount;
		this.products = products;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Billing [billId=" + billId + ", address=" + address + ", customerName=" + customerName + ", phoneNo="
				+ phoneNo + ", age=" + age + ", sex=" + sex + ", totalAmount=" + totalAmount + ", products=" + products
				+ "]";
	}
	
	
	

}

