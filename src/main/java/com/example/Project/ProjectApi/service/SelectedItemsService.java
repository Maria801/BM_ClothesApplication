package com.example.Project.ProjectApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Project.ProjectApi.entity.User;
import com.example.Project.ProjectApi.classes.Dataa;
import com.example.Project.ProjectApi.classes.ItemsInCart;
import com.example.Project.ProjectApi.classes.SizeColorAvailableImage;
import com.example.Project.ProjectApi.classes.TotalPrice;
import com.example.Project.ProjectApi.entity.ClassId;
import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.ProductDetails;
import com.example.Project.ProjectApi.entity.SelectedItems;
import com.example.Project.ProjectApi.repository.ProductDetailsRepository;
import com.example.Project.ProjectApi.repository.ProductRepository;
import com.example.Project.ProjectApi.repository.SelectedItemsRepository;
import com.example.Project.ProjectApi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectedItemsService {
    @Autowired
    SelectedItemsRepository selectedItemsRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;
    
    public void addSelectedItems(Dataa data) {

        User useridd=selectedItemsRepository.findUserById(data.userId);
        Product productidd=selectedItemsRepository.findProductById(data.proId);
        ProductDetails proDetail=selectedItemsRepository.findMixIdById(productidd, data.size, data.color);
        SelectedItems sItems= selectedItemsRepository.findSelectedItemById(useridd,proDetail);
        if(sItems!=null&&sItems.selectedProduct.AvailableItems>0)
        {
            sItems.boughtItemsCount++;
        }
        else
        {
            sItems=new SelectedItems();
            sItems.boughtItemsCount = 1;
            sItems.selectedUserId = useridd;
            sItems.selectedProduct = proDetail;
            sItems.selectedProduct.pId.isAddedToCart = true;
        }
        selectedItemsRepository.save(sItems);
    }

    public List<ItemsInCart> getSelectedItems(int userIdd) {
        User useridd=userRepository.findByUserId(userIdd);
        List<SelectedItems> lisItems=selectedItemsRepository.findBySelectedUserId(useridd);
        List<ItemsInCart> lisCartItem=new ArrayList<>();
        for(int i=0; i<lisItems.size(); ++i)
        {
          //  SelectedItems sItem=lisItems.get(i);
            ItemsInCart itemsCart=new ItemsInCart();
            itemsCart.AvailableItems=lisItems.get(i).selectedProduct.AvailableItems;
            itemsCart.category=lisItems.get(i).selectedProduct.pId.category;
            itemsCart.color=lisItems.get(i).selectedProduct.color;
            itemsCart.description=lisItems.get(i).selectedProduct.pId.description;
            itemsCart.image=lisItems.get(i).selectedProduct.image;
            itemsCart.isAddedToCart=true;
            itemsCart.name=lisItems.get(i).selectedProduct.pId.name;
            itemsCart.numberOfboughtItems=lisItems.get(i).selectedProduct.pId.numberOfboughtItems;
            itemsCart.price=lisItems.get(i).selectedProduct.pId.price;
            itemsCart.productId=lisItems.get(i).selectedProduct.pId.productId;
            itemsCart.size=lisItems.get(i).selectedProduct.size;
            itemsCart.quantity=lisItems.get(i).boughtItemsCount;
            lisCartItem.add(itemsCart);
        }
        return lisCartItem;
    }
    
    public void buyItemsService(int userIdd)
    {
        //List<ItemsInCart> cartItems=getSelectedItems(userIdd);
        User useridd=selectedItemsRepository.findUserById(userIdd);
        List<SelectedItems> cartItems=selectedItemsRepository.findBySelectedUserId(useridd);
        if(cartItems!=null)
        {
            for(int i=0; i<cartItems.size(); ++i)
            {
                    cartItems.get(i).selectedProduct.AvailableItems-=cartItems.get(i).boughtItemsCount;
                    cartItems.get(i).selectedProduct.pId.AvailableItems-=cartItems.get(i).boughtItemsCount;
            }
            
            List<SelectedItems> lisItems=selectedItemsRepository.findBySelectedUserId(useridd);
            for(int i=0; i<lisItems.size(); ++i)
            {
                lisItems.get(i).selectedProduct.pId.isAddedToCart=false;
                selectedItemsRepository.delete(lisItems.get(i));
            }
        }
    }

    public void updateCountPlusService(Dataa data) {
        User useridd=selectedItemsRepository.findUserById(data.userId);
        Product productidd=selectedItemsRepository.findProductById(data.proId);
        ProductDetails proDetail=selectedItemsRepository.findMixIdById(productidd, data.size, data.color);
        SelectedItems sItems= selectedItemsRepository.findSelectedItemById(useridd,proDetail);
        if(sItems.boughtItemsCount!=0&&sItems!=null&&sItems.selectedProduct.AvailableItems>0)
        {
            sItems.boughtItemsCount++;

            selectedItemsRepository.save(sItems);
        }
    }

    public void updateCountMinusService(Dataa data) {
        User useridd=selectedItemsRepository.findUserById(data.userId);
        Product productidd=selectedItemsRepository.findProductById(data.proId);
        ProductDetails proDetail=selectedItemsRepository.findMixIdById(productidd, data.size, data.color);
        SelectedItems sItems= selectedItemsRepository.findSelectedItemById(useridd,proDetail);
        if(sItems.boughtItemsCount!=0&&sItems!=null)
        {
            sItems.boughtItemsCount--;
            selectedItemsRepository.save(sItems);
            if(sItems.boughtItemsCount==0)
            {
                sItems.selectedProduct.pId.isAddedToCart=false;
                selectedItemsRepository.delete(sItems);
            }
        }
    }
    
    public TotalPrice getTotalPrice(int userIdd)
    {
        TotalPrice tp=new TotalPrice();
        User useridd=selectedItemsRepository.findUserById(userIdd);
        List<SelectedItems> cartItems=selectedItemsRepository.findBySelectedUserId(useridd);
        if(cartItems!=null)
        {
            for(int i=0; i<cartItems.size(); ++i)
            {
                tp.totalPrice+=(cartItems.get(i).boughtItemsCount)*cartItems.get(i).selectedProduct.pId.price;
                tp.shipping+= (tp.totalPrice*10)/100;
            }

        }
        return tp;
    }
}
