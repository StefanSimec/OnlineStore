package ch.roche.ss.onlinestore.service.controller;

import ch.roche.ss.onlinestore.api.dto.ProductDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<ProductDTO, EntityModel<ProductDTO>> {

    @Override
    public EntityModel<ProductDTO> toModel(ProductDTO productDTO) {

        return EntityModel.of(productDTO,
                linkTo(methodOn(ProductController.class).getProduct(productDTO.getSku())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getProducts()).withRel("products"));
    }
}
