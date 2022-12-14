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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
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

//	@OneToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "CUSTOMERID")
//	// @NotBlank(message="id should not be blank")
//	private Customer customer;

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

}

