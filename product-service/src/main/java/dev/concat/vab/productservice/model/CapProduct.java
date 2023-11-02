package dev.concat.vab.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cap_products")
public class CapProduct {
    @Id
    private String id ;
    private String name;
    private String description;
    private BigDecimal price;

    public CapProduct(){
        this.id = UUID.randomUUID().toString();
    }

}
