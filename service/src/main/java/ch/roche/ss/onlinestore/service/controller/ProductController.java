package ch.roche.ss.onlinestore.service.controller;

import ch.roche.ss.onlinestore.api.dto.ProductDTO;
import ch.roche.ss.onlinestore.api.service.ProductServiceAPI;
import ch.roche.ss.onlinestore.data.model.Product;
import ch.roche.ss.onlinestore.service.exception.NotFoundException;
import ch.roche.ss.onlinestore.service.mapper.ProductMapper;
import ch.roche.ss.onlinestore.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * REST Controller for handling Product API calls
 *
 * @see ProductServiceAPI
 */
@RestController
public class ProductController implements ProductServiceAPI
{
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductModelAssembler assembler;

    @Override
    public CollectionModel<EntityModel<ProductDTO>> getProducts()
    {
        List<EntityModel<ProductDTO>> products = repository.findAll().stream()
                .map(p -> mapper.toProductDTO(p))
                .map(p -> assembler.toModel(p))
                .collect(Collectors.toList());

        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getProducts()).withSelfRel());
    }

    @Override
    public EntityModel<ProductDTO> saveProduct(ProductDTO productDTO)
    {
        Product product = mapper.toProduct(productDTO);
        product.setDeleted(false);
        product = repository.save(product);
        return assembler.toModel(mapper.toProductDTO(product));
    }

    @Override
    public EntityModel<ProductDTO> getProduct(Long sku)
    {
        return repository.findById(sku)
                .map(p->mapper.toProductDTO(p))
                .map(p->assembler.toModel(p))
                .orElseThrow(()->new NotFoundException("Product", sku));
    }

    @Override
    public EntityModel<ProductDTO> updateProduct(ProductDTO productDTO, Long sku)
    {
        return repository.findById(sku)
                .map(p->
                {
                    productDTO.setSku(sku);
                    return saveProduct(productDTO);
                })
                .orElseThrow(()->new NotFoundException("Product", sku));
    }

    @Override
    public void deleteProduct(Long sku)
    {
        repository.deleteById(sku);
    }
}
