package com.acorngaru.konggaru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.model.Product;
import com.acorngaru.konggaru.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/list")
	public String showProductList() {
		return "list";
	}

	@GetMapping
	@ResponseBody
    public Page<Product> productList(@RequestParam(name = "currentPageNo", required = false, defaultValue = "1") int currentPageNo,
    								@RequestParam(name = "rows", required = false, defaultValue = "4") int rows,
    								@RequestParam(name = "searchType", required = false, defaultValue = "name") String searchType,
    								@RequestParam(name = "searchTerm", required = false, defaultValue = "") String searchTerm,
    								@RequestParam(name = "sortBy", required = false, defaultValue = "low") String sortBy) {
		return service.productList(currentPageNo, rows, searchType, searchTerm, sortBy);
    }
	
	@DeleteMapping
	@ResponseBody
	public boolean deleteProduct(@RequestBody List<Product> deleteList) {
		return service.deleteProduct(deleteList);
	}

}