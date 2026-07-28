package com.cafepos.backend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafepos.backend.dto.CheckoutRequest;
import com.cafepos.backend.entity.User;
import com.cafepos.backend.entity.Product;
import com.cafepos.backend.entity.Transaction;
import com.cafepos.backend.entity.TransactionItem;
import com.cafepos.backend.repository.ProductRepository;
import com.cafepos.backend.repository.TransactionRepository;
import com.cafepos.backend.repository.UserRepository;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        return transactionRepository.findById(id)
            .map(transaction -> ResponseEntity.ok().body(transaction))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Transaction transaction = new Transaction();
        transaction.setUser(user);

        BigDecimal total = BigDecimal.ZERO;

        for (CheckoutRequest.CheckoutItem itemRequest : request.getItems()){
            Product product = productRepository.findById(itemRequest.getProductId()).orElse(null);

            if (product == null) {
                return ResponseEntity.badRequest().body("Product not found: " + itemRequest.getProductId());
            }

            if (product.getStockQuantity() < itemRequest.getQuantity()) {
                return ResponseEntity.badRequest().body("Not enough stock for: " + product.getName());
            }

            //Deduct stock
            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            //Save the item at today's price, so future price changes don't affect past transactions
            TransactionItem item = new TransactionItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPriceAtSale(product.getPrice());
            item.setTransaction(transaction);

            transaction.getItems().add(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }

        transaction.setTotalAmount(total);

        Transaction saved = transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
