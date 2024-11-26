package org.senac.aula01.repository;

import org.senac.aula01.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

    
}
