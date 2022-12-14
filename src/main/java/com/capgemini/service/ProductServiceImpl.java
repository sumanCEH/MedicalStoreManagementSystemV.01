package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.Billing;
import com.capgemini.entity.Product;
import com.capgemini.exception.BillNotFoundException;
import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.exception.StockUnavailableException;
import com.capgemini.repository.BillingRepository;
import com.capgemini.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BillingRepository billRepository;

	@Override
	public Product addProduct(Long billId, Integer productId, Integer productQuantity) throws StockUnavailableException, ProductNotFoundException, BillNotFoundException{
		
		Optional<Billing> b = billRepository.findById(billId);
		if(b.isPresent()) {
			Billing bill = b.get();
			if(productId!=null) {
				
				Optional<Product> _product = productRepository.findById(productId);
				if(_product.isPresent()) {
					if(productQuantity<=_product.get().getProductQuantity()) {
						long totalAmt = bill.getTotalAmount();
						totalAmt+= _product.get().getProductPrice()*productQuantity;
						bill.setTotalAmount(totalAmt);
						_product.get().setProductQuantity(_product.get().getProductQuantity() - productQuantity);
						bill.addProduct(_product.get());
						billRepository.save(bill);
						return _product.get();
						
					}
					else {
						throw new StockUnavailableException("Sorry! Product is not available in stock");
					}
				}
				else {
					throw new ProductNotFoundException("Product not found with productId " + productId + "in our stock");
				}
				
			}
			else {
				throw new ProductNotFoundException("Product not found, please enter a valid product id");
			}
		}
		else {
			throw new BillNotFoundException("Bill not found with billId " + billId);
		}
		
		

			
		
		
	}

	@Override
	public Product getProductById(Integer productId) throws ProductNotFoundException {
		Optional<Product> product =  productRepository.findById(productId);
		if(product.isPresent()) {
			return product.get();
		}
		else {
			throw new ProductNotFoundException("Product not found with productId " + productId);
		}
	}

	@Override
	public Product updateProduct(Integer productId, Product product) throws ProductNotFoundException {
		Optional<Product> _product = productRepository.findById(productId);
		if(_product.isPresent()) {
			_product.get().setProductName(product.getProductName());
			_product.get().setProductDescription(product.getProductDescription());
			_product.get().setProductCompany(product.getProductCompany());
			_product.get().setProductPrice(product.getProductPrice());
			_product.get().setProductQuantity(product.getProductQuantity());
			_product.get().setProductBatchNo(product.getProductBatchNo());
			_product.get().setProductExpiryDate(product.getProductExpiryDate());
			return productRepository.save(_product.get());
		}
		else {
			throw new ProductNotFoundException("Product not found with productId " + productId);
		}
	}

	@Override
	public void deleteProduct(Integer productId) throws ProductNotFoundException {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			productRepository.deleteById(productId);
		}
		else {
			throw new ProductNotFoundException("Product not found with productId " + productId);
		}
		
		
	}

	@Override
	public void deleteProductFromBill(Long billId, Integer productId) throws BillNotFoundException {
		Optional<Billing> bill = billRepository.findById(billId);
		
//		Optional<Product> product = productRepository.findById(productId);
		
		
		if(bill.isPresent()) {
			bill.get().removeProduct(productId);
			billRepository.save(bill.get());
		}
		else {
			throw new BillNotFoundException("Bill not found with billId " + billId);
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	/*@override
	public List<Product> getAllProductsByBillId(Integer billId) throws BillNotFoundException {
		if(!billRepository.existsById(billId)) {
			throw new BillNotFoundException("Bill not found with billId " + billId);
		}
		
		List<Product> products = productRepository.findProductsByBillId(billId);
		return products;
	}
	*/

	
	

}
