package com.nvs.springbootapp.controller.rest;

import com.nvs.springbootapp.dto.Cart;
import com.nvs.springbootapp.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartRestController {

  private CartService cartService;

  public CartRestController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public Cart getCart() {
    return cartService.getCartForCurrentUser();
  }

  @PostMapping("/product/{id}")
  public Cart addProduct(@PathVariable Long id) {
    return cartService.addProductById(id);
  }

  @DeleteMapping("/product/{id}")
  public Cart deleteProduct(@PathVariable Long id) {
    return cartService.removeProductById(id);
  }
}
