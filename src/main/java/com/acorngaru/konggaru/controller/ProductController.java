package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.model.Product;
import com.acorngaru.konggaru.model.Response;
import com.acorngaru.konggaru.service.ProductService;
import com.acorngaru.konggaru.util.StringToObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/add")
    public String showProductAdd() {
        return "product/add";
    }

    @GetMapping("/list")
    public String showProductList() { return "product/list"; }

    @GetMapping
    @ResponseBody
    public Response<Page<Product>> getProducts(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                               @RequestParam(required = false, defaultValue = "4") int rows,
                                               @RequestParam(required = false, defaultValue = "name") String searchType,
                                               @RequestParam(required = false, defaultValue = "") String searchTerm,
                                               @RequestParam(required = false, defaultValue = "low") String sortBy) throws Exception {
        return Response.OK(
                productService.findProducts(pageNo, rows, searchType, searchTerm, sortBy)
        );
    }

    @PostMapping
    @ResponseBody
    public Response<?> insertProduct(@StringToObject(name = "product") Product product,
                                     @RequestPart(required = false) Optional<MultipartFile> image) throws Exception {
        log.info("insertProduct() - {}", product);

        productService.insert(product, image);

        return Response.OK();
    }

    @DeleteMapping
    @ResponseBody
    public Response<?> deleteProducts(@RequestBody List<Product> products) throws Exception {
        log.info("deleteProduct() - {}", products);

        productService.deleteProducts(products);

        return Response.OK();
    }

    @PutMapping
    @ResponseBody
    public Response<?> updateProduct(@StringToObject(name = "product") Product product,
                                     @RequestPart(required = false) Optional<MultipartFile> image) throws Exception {
        log.info("updateProduct() - {}", product);

        productService.update(product, image);

        return Response.OK();
    }
}