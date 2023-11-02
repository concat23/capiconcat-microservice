package dev.concat.vab.productservice.service;

import dev.concat.vab.productservice.dto.CapProductRequest;
import dev.concat.vab.productservice.dto.CapProductResponse;
import dev.concat.vab.productservice.model.CapProduct;
import dev.concat.vab.productservice.repository.ICapProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class CapProductService {

    private final ICapProductRepository iCapProductRepository;

    public void createProduct(CapProductRequest productRequest){
        CapProduct product = CapProduct.builder()
                            .id(UUID.randomUUID().toString())
                            .name(productRequest.getName())
                            .description(productRequest.getDescription())
                            .price(productRequest.getPrice())
                            .build();
        iCapProductRepository.save(product);
        log.info("Product "+ product.getId()+" is saved");
        log.info("Product {} is saved",product.getName());
    }

    public List<CapProductResponse> getAllProducts(){
      List<CapProduct> list = iCapProductRepository.findAll();
      List<CapProductResponse> listResult = list.stream().map(this::mapToCapProductResponse).toList();
      log.info("List: {} ( product )", listResult.size());
      return  listResult;
    }

    private CapProductResponse mapToCapProductResponse(CapProduct product) {
        return CapProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
