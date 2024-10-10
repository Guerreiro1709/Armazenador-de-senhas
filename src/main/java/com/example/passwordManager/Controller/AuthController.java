package com.example.passwordManager.Controller;

import com.example.passwordManager.Services.Exceptions.ErroCadastro;
import com.example.passwordManager.Services.Exceptions.UsuarioNaoEncontrado;
import com.example.passwordManager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(name = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");
        if(error != null) {
            mv.addObject("errorMessage", "Usuário ou Senha incorretos, Tente novamente!!");
        }
        return mv;
    }

    @GetMapping(value = "/registro")
    public ModelAndView registro() {
        ModelAndView mv = new ModelAndView("registro");
        return mv;
    }

    @PostMapping(value = "/registro")
    public ModelAndView register (@RequestParam(name = "novousuario") String nickname, @RequestParam(name = "novasenha") String password){
       ModelAndView mv = new ModelAndView("login");

        try{
            userService.cadastro(nickname, password);
            mv.addObject("successMessage", "Usuário cadastrado");
            return mv;
        }
        catch (RuntimeException erroCadastro){
            mv.addObject("errorMessage", "Usuário já existe");
            mv.setViewName("registro");
            return mv;
        }

    }


}
