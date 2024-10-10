package com.example.passwordManager.Services.Exceptions;

public class ErroCadastro extends RuntimeException{
    public ErroCadastro(){
        super("Usuário em uso");
    }

    public ErroCadastro(String m){
        super(m);
    }
}
