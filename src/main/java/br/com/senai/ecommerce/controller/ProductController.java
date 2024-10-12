package br.com.senai.ecommerce.controller;

import br.com.senai.ecommerce.entities.Product;
import br.com.senai.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //suporta apenas dados
@RequestMapping("/product") // localhost:8080/product
public class ProductController {
    //injeção de dependência, criar um objeto
    @Autowired
    private ProductRepository productRepository;

    //metodo para criar um product
    @PostMapping
    public Product createUsuario(@RequestBody Product product){
        return productRepository.save(product);
    }
    // listar todos os products do banco de dados
    @GetMapping
    public List<Product> getAllProducts(){
        // SELECT * FROM product
        return productRepository.findAll();
    }
    //pegar um product pelo seu id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }
    // deleta um product pelo seu id
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
    }
    //atualiza informações do product pelo seu id
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                           @RequestBody Product product){
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            product.setName(product.getName());
            product.setEmail(product.getEmail());
            product.setPassword(product.getPassword());
            return productRepository.save(product);
        }
        return null;
    }
}

