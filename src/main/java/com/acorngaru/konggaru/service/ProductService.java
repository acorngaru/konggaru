package com.acorngaru.konggaru.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acorngaru.konggaru.mapper.ProductMapper;
import com.acorngaru.konggaru.mapper.UsedIngredientMapper;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	UsedIngredientMapper usedIngredientMapper; 
	
	public Page<Product> productList(int currentPageNo, int rows, String searchType, String searchTerm, String sortBy) {
		try {
			Page<Product> productPage = new Page<Product>();
			productPage.process(rows, currentPageNo, productMapper.countProducts(searchType, searchTerm), productMapper.findProducts(currentPageNo, rows, searchType, searchTerm, sortBy));
			return productPage;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean deleteProduct(List<Product> deleteList) {
        try {
        	deleteList.forEach(productInfo ->
        					{
        						usedIngredientMapper.deleteByProductId(productInfo.getId());
        						productMapper.delete(productInfo);
        					});
        	return true;
        } catch (Exception e) {
            return false;
        }
	}

}
