package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Product;
import com.capgemini.exception.BillNotFoundException;
import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.exception.StockUnavailableException;

public interface ProductService {
	

	public Product addProduct(Long billId, Integer productId, Integer productQuantity) throws StockUnavailableException, ProductNotFoundException, BillNotFoundException;

	public Product getProductById(Integer productId) throws ProductNotFoundException;

	public Product updateProduct(Integer productId, Product product) throws ProductNotFoundException;

	public void deleteProduct(Integer productId) throws ProductNotFoundException;

	public void deleteProductFromBill(Long billId, Integer productId) throws BillNotFoundException;

	public List<Product> getAllProducts();

	public Product addProduct(Product product);

//	public List<Product> getAllProductsByBillId(Integer billId) throws BillNotFoundException;

//	public List<Product> getAllProductsBybillId(Integer billId);

}
