package ssia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssia.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p " +
            "WHERE p.name LIKE %:text% " +
            "AND p.owner=?#{authentication.name}")
    List<Product> findProductByNameContains(String text);
}
