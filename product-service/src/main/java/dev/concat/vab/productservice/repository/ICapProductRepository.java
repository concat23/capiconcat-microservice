package dev.concat.vab.productservice.repository;

import dev.concat.vab.productservice.model.CapProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICapProductRepository extends JpaRepository<CapProduct,String> {
}
