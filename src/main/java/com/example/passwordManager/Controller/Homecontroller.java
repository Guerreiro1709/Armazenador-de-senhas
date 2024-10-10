package com.example.passwordManager.Controller;

import com.example.passwordManager.Model.Senha;
import com.example.passwordManager.Model.Usuario;
import com.example.passwordManager.Repository.SenhaRepository;
import com.example.passwordManager.Services.SenhaService;
import com.example.passwordManager.Services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Homecontroller {

    @Autowired
    private SenhaService senhaService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/criarsenha")
    public String criarsenha(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "local") String local,
            @RequestParam(name = "senha") String senha,
            Authentication authentication
            ){
        try {
            senhaService.criarSenha(email, local, senha, authentication);
        } catch (RuntimeException e) {
            System.out.println(senha);
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/listar")
    public ModelAndView listar(Authentication authentication){
        ModelAndView mv= new ModelAndView("listadesenhas");
        try {
            Usuario usuario = userService.findAuthenticated(authentication);
            List<Senha> senhas= senhaService.buscarSenha(authentication);

            mv.addObject("senhas", senhas);
            mv.addObject("usuario", usuario);
        }
        catch (RuntimeException ex) {
            mv.addObject("errorMessage", ex.getMessage());
        }
        return mv;
    }

    @PostMapping("/alterarsenha")
    public String atualizar(@RequestParam(name = "id") Long id, @RequestParam(name = "email") String email, @RequestParam(name = "local") String local, @RequestParam(name = "senha") String senha ){
        try{
            senhaService.atualizarsenha(id, email, local, senha);
        }
        catch(Exception e) {
            System.out.println(e.getCause());
        }
        return "redirect:/listar";
    }

    @PostMapping("/deletarsenha")
    public String deletar(@RequestParam(name = "iddelete") Long id) {
        try {
            senhaService.deletarsenha(id);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return "redirect:/listar";
    }
}
