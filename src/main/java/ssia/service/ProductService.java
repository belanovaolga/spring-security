package ssia.service;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import ssia.model.Product;
import ssia.utils.Names;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> findProducts() {

        List<Product> products = new ArrayList<>();
        products.add(new Product("beer", Names.NIKOLAI));
        products.add(new Product("candy", Names.NIKOLAI));
        products.add(new Product("chocolate", Names.JULIEN));

        return products;
    }
}
