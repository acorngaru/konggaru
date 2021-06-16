package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.exception.ProductNotFoundException;
import com.acorngaru.konggaru.mapper.ProductMapper;
import com.acorngaru.konggaru.mapper.UsedIngredientMapper;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.model.Product;
import com.acorngaru.konggaru.repository.S3Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final S3Repository s3Repository;
    private final ProductMapper productMapper;
    private final UsedIngredientMapper usedIngredientMapper;

    /**
     * 모든 상품 정보 반환
     */
    @Override
    public List<Product> findProductsByCategoryId(int categoryId) {
        return productMapper.findProductsByCategoryId(categoryId);
    }

    /**
     * 검색 또는 정렬한 상품 목록을 페이징하여 반환
     */
    @Override
    public Page<Product> findProducts(int pageNo, int rows, String searchType, String searchTerm, String sortBy) throws Exception {
        Page<Product> productPage = new Page<>();

        productPage.process(
                rows,
                pageNo,
                productMapper.countProducts(searchType, searchTerm),
                productMapper.findProducts(pageNo, rows, searchType, searchTerm, sortBy)
        );

        return productPage;
    }

    /**
     * ID를 통한 상품 정보 반환
     */
    @Override
    public Product findProductById(int id) throws Exception {
        return productMapper.findProductById(id).orElseThrow(() -> new ProductNotFoundException("해당 상품을 찾을 수 없습니다."));
    }

    /**
     * 상품 추가
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Product product, Optional<MultipartFile> image) throws Exception {
        product.setImageUrl(image.isPresent() ?
                s3Repository.upload(image.get()) :
                ""
        );

        productMapper.insert(product);

        product.getRecipe().forEach(usedIngredient -> {
            usedIngredient.setProductId(product.getId());
            usedIngredientMapper.insert(usedIngredient);
        });
    }

    /**
     * 상품 수정
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Product product, Optional<MultipartFile> image) throws Exception {
        product.setImageUrl(image.isPresent() ?
                s3Repository.upload(image.get()) :
                product.getImageUrl()
        );

        productMapper.update(product);

        usedIngredientMapper.deleteByProductId(product.getId());
        product.getRecipe().forEach(usedIngredientMapper::insert);
    }

    /**
     * 상품 삭제
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProducts(List<Product> products) throws Exception {
        for (Product product : products) {
            usedIngredientMapper.deleteByProductId(product.getId());
            productMapper.delete(product);
        }
    }
}
