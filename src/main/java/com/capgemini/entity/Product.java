package com.capgemini.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private String productDescription;
	private String productCompany;
	private float productPrice;
	private Integer productQuantity;
	private Integer productBatchNo;
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
//	@JsonFormat(pattern = "yyyy/MM/dd")
	private LocalDate ProductExpiryDate;
	private String productUrl;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "products")
	@JsonIgnore
	private Set<Billing> bills = new HashSet<>();

	public Product() {

	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Integer getProductBatchNo() {
		return productBatchNo;
	}

	public void setProductBatchNo(Integer productBatchNo) {
		this.productBatchNo = productBatchNo;
	}

	public LocalDate getProductExpiryDate() {
		return ProductExpiryDate;
	}

	public void setProductExpiryDate(LocalDate productExpiryDate) {
		ProductExpiryDate = productExpiryDate;
	}
	

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public Set<Billing> getBills() {
		return bills;
	}

	public void setBills(Set<Billing> bills) {
		this.bills = bills;
	}

}
