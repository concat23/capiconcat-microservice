package dev.concat.vab.productservice.controller;

import dev.concat.vab.productservice.dto.CapProductRequest;
import dev.concat.vab.productservice.dto.CapProductResponse;
import dev.concat.vab.productservice.service.CapProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/api/product")
public class CapProductAPIController {

    private final CapProductService capProductService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody CapProductRequest productRequest){
        capProductService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CapProductResponse> getAllProducts(){
        return capProductService.getAllProducts();
    }
}
