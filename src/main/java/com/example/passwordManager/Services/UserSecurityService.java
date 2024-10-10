package com.example.passwordManager.Services;

import com.example.passwordManager.Model.Usuario;
import com.example.passwordManager.Repository.UsuarioRepository;
import com.example.passwordManager.Services.Exceptions.UsuarioNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsuarioNaoEncontrado {
        Optional<Usuario> result= usuarioRepository.findByNickname(nickname);
        if (result.isPresent()){
            return result.get();
        }
        throw new UsuarioNaoEncontrado();
    }
}
