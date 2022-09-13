package com.example.Project.ProjectApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project.ProjectApi.entity.ClassId;
import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.ProductDetails;
import com.example.Project.ProjectApi.entity.SelectedItems;
import com.example.Project.ProjectApi.entity.User;

public interface SelectedItemsRepository extends JpaRepository<SelectedItems, ClassId> {

    @Query("SELECT i FROM User i WHERE i.userId=:idd")
    public User findUserById(int idd);

    @Query("SELECT i FROM Product i WHERE i.productId=:idd")
    public Product findProductById(int idd);

    @Query("SELECT i FROM ProductDetails i WHERE i.pId=:idd AND i.size=:size AND i.color=:color")
    public ProductDetails findMixIdById(Product idd,String size,String color);

    @Query("SELECT i FROM SelectedItems i WHERE i.selectedProduct=:proD AND i.selectedUserId=:uid")
    public SelectedItems findSelectedItemById(User uid,ProductDetails proD);

    public List<SelectedItems> findBySelectedUserId(User uid);

    public void deleteBySelectedUserId(User uid);

}
