package ch.roche.ss.onlinestore.api.service;

import ch.roche.ss.onlinestore.api.dto.OrderDTO;
import ch.roche.ss.onlinestore.api.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RequestMapping("/products")
public interface ProductServiceAPI
{
    @Operation(summary = "Get all products")
    @GetMapping("/")
    CollectionModel<EntityModel<ProductDTO>> getProducts();

    @Operation(summary = "Save product")
    @PostMapping("/")
    EntityModel<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO);

    @Operation(summary = "Get specific products")
    @GetMapping("/{sku}")
    EntityModel<ProductDTO> getProduct(@PathVariable Long sku);

    @Operation(summary = "Update product")
    @PutMapping("/{sku}")
    EntityModel<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable Long sku);

    @Operation(summary = "Delete product")
    @DeleteMapping("/{sku}")
    void deleteProduct(@PathVariable Long sku);
}
