package ssia.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ssia.model.Product;
import ssia.service.ProductService;
import ssia.utils.Names;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/sell")
    public List<Product> sellProduct() {

        List<Product> products = new ArrayList<>();
        products.add(new Product("beer", Names.NIKOLAI));
        products.add(new Product("candy", Names.NIKOLAI));
        products.add(new Product("chocolate", Names.JULIEN));

        return productService.sellProducts(products);
    }

//    @GetMapping("/sell")
//    public List<Product> sellProduct() {
//        List<Product> products = List.of(
//        new Product("beer", "nikolai"),
//                new Product("candy", "nikolai"),
//                new Product("chocolate", "julien"));
//        return productService.sellProducts(products);
//    }
}
