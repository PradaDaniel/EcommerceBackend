package com.example.project_back_api_aula_web.Controler;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_back_api_aula_web.model.Produto;
import com.example.project_back_api_aula_web.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> get(
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "order", defaultValue = "asc") String order
    ) {
        List<Produto> produtos = produtoRepository.findAll();

        Comparator<Produto> comparator;
        switch (sortBy.toLowerCase()) {
            case "value":
                comparator = Comparator.comparing(Produto::getPreco);
                break;
            case "name":
            default:
                comparator = Comparator.comparing(Produto::getNome);
                break;
        }
//
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        return produtos.stream().sorted(comparator).collect(Collectors.toList());
    }
}
