package com.example.passwordManager.Services.Exceptions;

public class UsuarioNaoEncontrado extends RuntimeException{
    public UsuarioNaoEncontrado(){
        super("Usuário ou Senha incorretos, Tente novamente!!");
    }

    public UsuarioNaoEncontrado(String m){
        super(m);
    }
}