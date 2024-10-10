package com.example.passwordManager.Services;

import com.example.passwordManager.Model.Senha;
import com.example.passwordManager.Model.Usuario;
import com.example.passwordManager.Repository.SenhaRepository;
import com.example.passwordManager.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Senha criarSenha( String email, String local, String senha, Authentication authentication) {
        Senha senha1 = new Senha();
        Usuario user = usuarioRepository.findByName(authentication.getName());
        senha1.setEmail(email);
        senha1.setLocal(local);
        senha1.setSenha(senha);
        senha1.setUsuario(user);
        return senhaRepository.save(senha1);
    }

    public List<Senha> buscarSenha(Authentication authentication){
            Usuario usuario= usuarioRepository.findByName(authentication.getName());
            return senhaRepository.findByUsuario(usuario.getId());
    }

    public Senha atualizarsenha(Long id,  String email, String local, String senha){
        Senha senha1= senhaRepository.getById(id);
        senha1.setEmail(email);
        senha1.setLocal(local);
        senha1.setSenha(senha);

        return senhaRepository.save(senha1);
    }

    public void deletarsenha(Long id){
        Senha senha1= senhaRepository.getById(id);
        senhaRepository.delete(senha1);
    }
}
