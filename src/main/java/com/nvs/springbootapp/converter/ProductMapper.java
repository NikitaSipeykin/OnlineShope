package com.nvs.springbootapp.converter;

import com.nvs.springbootapp.dto.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.webjars.NotFoundException;
import com.nvs.springbootapp.dto.ProductDto;
import com.nvs.springbootapp.dto.ProductShortDto;
import com.nvs.springbootapp.model.Category;
import com.nvs.springbootapp.model.Product;
import com.nvs.springbootapp.repository.CategoryRepository;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

  @Autowired
  protected CategoryRepository categoryRepository;

  @Mapping(source = "category", target = "category", qualifiedByName = "titleToCategory")
  public abstract Product productShortDtoToProduct(ProductShortDto productShortDto);

  @Mappings({
      @Mapping(source = "id", target = "productId"),
      @Mapping(source = "price", target = "pricePerOne")
  })
  public abstract CartItem productToCartItem(Product product);

  @Mapping(source = "category.title", target = "category")
  public abstract ProductDto productToProductDto(Product product);

  @Named("titleToCategory")
  public Category categoryTitleToCategory(String categoryTitle) {
    return categoryRepository.findByTitle(categoryTitle).orElseThrow(() -> new NotFoundException("Category not found"));
  }
}