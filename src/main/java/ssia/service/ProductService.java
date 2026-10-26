package ssia.service;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import ssia.model.Product;

import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
// продаем продукты и возвращаем список проданных продуктов
        return products;
    }
}
