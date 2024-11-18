package com.exemplo.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import java.util.List;

@SpringBootApplication
public class ClienteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
    }
}

@Entity
@Table(name = "clientes")
class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


interface ClienteRepository extends JpaRepository<Cliente, Long> {
}


@RestController
@RequestMapping("/clientes")
class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(novoCliente);
    }
}
