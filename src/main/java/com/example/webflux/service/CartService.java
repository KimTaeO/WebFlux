package com.example.webflux.service;

import com.example.webflux.entity.Cart;
import com.example.webflux.entity.CartItem;
import com.example.webflux.repository.CartRepository;
import com.example.webflux.repository.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<Cart> addToCart(String cartId, String id) {
        return cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart"))
                .flatMap(cart -> cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getItem()
                                .getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() -> itemRepository.findById(id)
                                .map(CartItem::new)
                                .map(cartItem -> {
                                    cart.getCartItems().add(cartItem);
                                    return cart;
                                })))
                .flatMap(cartRepository::save);
    }
}
