package com.example.passwordManager.Services;

import com.example.passwordManager.Model.Usuario;
import com.example.passwordManager.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario findAuthenticated(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()){
            Optional<Usuario> usuario = usuarioRepository.findByNickname(authentication.getName());
            if(usuario.isPresent()){
                return usuario.get();
            } else {
                throw new RuntimeException("Usuário não encontrado");
            }
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public Usuario cadastro(String nickname, String password){
        Usuario usuario = new Usuario();
        usuario.setUsuario(nickname);
        String encodedsenha= encoder.encode(password);
        usuario.setSenha(encodedsenha);
        usuarioRepository.save(usuario);

        return usuario;
    }
}
