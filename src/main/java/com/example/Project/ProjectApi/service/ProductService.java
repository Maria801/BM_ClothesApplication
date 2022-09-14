package com.example.Project.ProjectApi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Project.ProjectApi.classes.EnterData;
import com.example.Project.ProjectApi.classes.HomeProducts;
import com.example.Project.ProjectApi.classes.ReturnProduct;
import com.example.Project.ProjectApi.classes.SizeColorAvailableImage;
import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.ProductDetails;
import com.example.Project.ProjectApi.entity.SelectedItems;
import com.example.Project.ProjectApi.entity.User;
import com.example.Project.ProjectApi.repository.ProductDetailsRepository;
import com.example.Project.ProjectApi.repository.ProductRepository;
import com.example.Project.ProjectApi.repository.SelectedItemsRepository;
import com.example.Project.ProjectApi.repository.UserRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	
	@Autowired
	SelectedItemsRepository selectedItemsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductDetailsRepository productDetailsRepository;
	
	// delete item
	public void deleteItemFromProduct(int id) {
		Product product =productRepository.findByProductId(id);
		if(product==null)
			throw new RuntimeException("There is no item with this id.");
		else { 
		List<SelectedItems> selectedItems=selectedItemsRepository.findAll();
		for (SelectedItems selectedItems2 : selectedItems) {
			if(selectedItems2.selectedProduct.pId.productId==id)
				selectedItemsRepository.delete(selectedItems2);
		}
		List<ProductDetails> productDetails= productDetailsRepository.findAll();
		for (ProductDetails productDetails2 : productDetails) {
			if(productDetails2.pId.productId==id)
				productDetailsRepository.delete(productDetails2);
		}
		productRepository.deleteById(id);
		}
	   
	}


	// add item
	public void addItem(Product product) {
		productRepository.save(product);
	}
	
	public void addColorOrSize(EnterData enterData) {
		ProductDetails productDetails= new ProductDetails();
		Product product = productRepository.findById(enterData.pId).get();
		productDetails.pId=product;
		productDetails.AvailableItems=enterData.AvailableItems;
		productDetails.color=enterData.color;
		productDetails.image=enterData.image;
		productDetails.size=enterData.size;
		productDetailsRepository.save(productDetails);
	}
	
	// get product by id
	public ReturnProduct getProductById( int productId, int userId) {
		       List<ProductDetails> productDetails;
				Product product =productRepository.findById(productId).get();
				User user =userRepository.findById(userId).get();
				List<SelectedItems>selectedItems;
				selectedItems=selectedItemsRepository.findBySelectedUserId(user);
				for (SelectedItems selectedItems2 : selectedItems) {
					if(selectedItems2.selectedProduct.pId.productId==productId)
						product.isAddedToCart=true;
				}
				
				productDetails=productDetailsRepository.findAll();
				ReturnProduct curr_product= new ReturnProduct();
				//ProductDetails productDetails2= productDetailsRepository.findProductById(product);
					for (ProductDetails productDetails2 : productDetails) {
						if(productDetails2.pId.productId==productId) {
							 if(productDetails2.AvailableItems==0)
								 continue;
							 //throw new RuntimeException("No Available Items.");
							 SizeColorAvailableImage sizeColorAvailable= new SizeColorAvailableImage();
							 sizeColorAvailable.size=productDetails2.size;
							 sizeColorAvailable.color=productDetails2.color;
							 sizeColorAvailable.AvailableItems=productDetails2.AvailableItems;
							 sizeColorAvailable.image=productDetails2.image;
							 curr_product.sizeColorAvailableImage.add(sizeColorAvailable);
							curr_product.category=productRepository.findById(productId).get().category;
							curr_product.description=productRepository.findById(productId).get().description;
							curr_product.isAddedToCart=productRepository.findById(productId).get().isAddedToCart;
							curr_product.name=productRepository.findById(productId).get().name;
							curr_product.numberOfboughtItems=productRepository.findById(productId).get().numberOfboughtItems;
							curr_product.productId=productRepository.findById(productId).get().productId;
							curr_product.price=productRepository.findById(productId).get().price;
						}
							
					}
				
			return curr_product;	
	}


	// get all products
	public  List<HomeProducts> getAllProducts() {
        List<Product> product;
        product=productRepository.findAll();
		List<ProductDetails> productDetails= productDetailsRepository.findAll();
		
		
		List<HomeProducts> homeProducts= new ArrayList<HomeProducts>();
		for (int i = 0; i <  product.size(); i++) {
			if(product.get(i).AvailableItems==0) {
				continue;
			}
			HomeProducts curr_product= new HomeProducts();
			curr_product.name=product.get(i).name;
			curr_product.price=product.get(i).price;
			curr_product.proId=product.get(i).productId;
			for (ProductDetails productDetails2 : productDetails) {
				if(productDetails2.pId.productId==product.get(i).productId) {
					curr_product.image=productDetails2.image;
					break;
			}
			
	    	}
			homeProducts.add(curr_product);
		}
		
		return homeProducts;
	}
	
	
    // get product by color
	public ReturnProduct returnBycolor(int userId,int productId, String chosenColor) {
		
		Product product =productRepository.findById(productId).get();
		User user =userRepository.findById(userId).get();
		List<SelectedItems>selectedItems;
		selectedItems=selectedItemsRepository.findBySelectedUserId(user);
		for (SelectedItems selectedItems2 : selectedItems) {
			if(selectedItems2.selectedProduct.pId.productId==productId)
				product.isAddedToCart=true;
		}
		
		
		 List<ProductDetails> productDetails=productDetailsRepository.findAll();
		ReturnProduct curr_product= new ReturnProduct();
		SizeColorAvailableImage sizeColorAvailableImage= new SizeColorAvailableImage();
		for (ProductDetails productDetails2 : productDetails) {
			if(productDetails2.pId.productId==productId && productDetails2.color.equals(chosenColor))
			{
				sizeColorAvailableImage.color=productDetails2.color;
				sizeColorAvailableImage.AvailableItems=productDetails2.AvailableItems;
				sizeColorAvailableImage.size=productDetails2.size;
				sizeColorAvailableImage.image=productDetails2.image;
				curr_product.sizeColorAvailableImage.add(sizeColorAvailableImage);
				curr_product.category=productRepository.findById(productId).get().category;
				curr_product.description=productRepository.findById(productId).get().description;
				curr_product.isAddedToCart=productRepository.findById(productId).get().isAddedToCart;
				curr_product.name=productRepository.findById(productId).get().name;
				curr_product.numberOfboughtItems=productRepository.findById(productId).get().numberOfboughtItems;
				curr_product.productId=productRepository.findById(productId).get().productId;
				curr_product.price=productRepository.findById(productId).get().price;
				
			}
		}
		
		return curr_product;
	}
	
	
}
