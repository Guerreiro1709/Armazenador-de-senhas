package com.example.passwordManager.Repository;

import com.example.passwordManager.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByNickname(String nickname);

    @Query(value = "SELECT * FROM usuario WHERE usuario.nickname= ?", nativeQuery = true)
    Usuario findByName(@Param("nome") String nome);
}
