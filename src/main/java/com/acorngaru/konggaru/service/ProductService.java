package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> findProducts(int pageNo, int rows, String searchType, String searchTerm, String sortBy) throws Exception;
    Product findProductById(int id) throws Exception;
    void insert(Product product, Optional<MultipartFile> image) throws Exception;
    void update(Product product, Optional<MultipartFile> image) throws Exception;
    void deleteProducts(List<Product> product) throws Exception;
}
