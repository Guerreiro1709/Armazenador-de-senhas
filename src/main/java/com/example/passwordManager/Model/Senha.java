package com.example.passwordManager.Model;

import jakarta.persistence.*;
@Entity
@Table
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String local;

    @Column
    private String email;

    @Column
    private String senha;

    @JoinColumn
    @ManyToOne
    private Usuario usuario;

    public Senha() {
    }

    public Senha(Long id, String local, String email, String senha, Usuario usuario) {
        this.id = id;
        this.local = local;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
