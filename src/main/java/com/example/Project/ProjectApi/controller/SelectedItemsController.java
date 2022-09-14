package com.example.Project.ProjectApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Project.ProjectApi.classes.Dataa;
import com.example.Project.ProjectApi.classes.ItemsInCart;
import com.example.Project.ProjectApi.classes.SizeColorAvailableImage;
import com.example.Project.ProjectApi.classes.TotalPrice;
import com.example.Project.ProjectApi.entity.ClassId;
import com.example.Project.ProjectApi.entity.SelectedItems;
import com.example.Project.ProjectApi.service.SelectedItemsService;

import lombok.Data;

import java.util.List;

@RestController
@RequestMapping("/select")
public class SelectedItemsController {

    @Autowired
    SelectedItemsService selectedItemsService;

    @PostMapping(value="/addItem")
    public void addItem(@RequestBody Dataa data) {

        selectedItemsService.addSelectedItems(data);
    }

    @PostMapping(value= "/plusCount")
    public void updateCountPlus(@RequestBody Dataa data) {

        selectedItemsService.updateCountPlusService(data);
    }

    @PostMapping(value="/minusCount")
    public void updateCountMinus(@RequestBody Dataa data) {

        selectedItemsService.updateCountMinusService(data);
    }

    @GetMapping(value= "/getItems/userId/{userId}")
    public List<ItemsInCart> getCartItems(@PathVariable int userId){
        return selectedItemsService.getSelectedItems(userId);
    }

    @GetMapping(value= "/buyItems/userId/{userId}")
    public void buyItems(@PathVariable int userId){
        selectedItemsService.buyItemsService(userId);
    }


    @GetMapping(value= "/totalPrice/userId/{userId}")
    public TotalPrice totalPrice(@PathVariable int userId){
        return selectedItemsService.getTotalPrice(userId);
    }
}
