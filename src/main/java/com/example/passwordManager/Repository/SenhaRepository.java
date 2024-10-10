package com.example.passwordManager.Repository;

import com.example.passwordManager.Model.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {

    @Query(value = "SELECT * FROM senha WHERE senha.usuario_id=?", nativeQuery = true)
    List<Senha> findByUsuario(@Param("usuario") Long id);
}
