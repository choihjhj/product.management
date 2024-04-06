package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.application.SimpleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    @Autowired
    ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return simpleProductService.add(productDto);
    }

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    @GetMapping("/products")
    public List<ProductDto> findProducts(
            @RequestParam(required = false) String name
    ) {
        if (null == name)
            return simpleProductService.findAll();

        return simpleProductService.findByNameContaining(name);
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }

}