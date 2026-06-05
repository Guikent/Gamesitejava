package com.example.gamestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }

    @PostMapping("/cadastrar-usuario")
    public String cadastrarUsuario(Usuario usuario, HttpSession session) {
        usuarioRepository.save(usuario);
        session.setAttribute("usuarioLogado", usuario.getLogin());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/logar")
    public String logar(String login, String senha, HttpSession session, Model model) {
        Usuario usuario = usuarioRepository.findByLogin(login).orElse(null);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            session.setAttribute("usuarioLogado", usuario.getLogin());
            return "redirect:/home";
        }
        model.addAttribute("erro", "Usuário ou senha incorretos!");
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        model.addAttribute("jogos", jogoRepository.findAll());
        model.addAttribute("login", session.getAttribute("usuarioLogado"));
        return "home";
    }

    @GetMapping("/games")
    public String games(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        model.addAttribute("jogos", jogoRepository.findAll());
        return "games";
    }

    @GetMapping("/suporte")
    public String suporte(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        model.addAttribute("chavePix", "https://livepix.gg/kiota");
        return "suporte";
    }

    @PostMapping("/enviar-relatorio")
    public String enviarRelatorio() {
        return "agradecimento";
    }

    @GetMapping("/cadastro")
    public String abrirCadastro(Model model) {
        model.addAttribute("jogo", new Jogo());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarJogo(Jogo jogo) {
        jogoRepository.save(jogo);
        return "redirect:/games";
    }

    @GetMapping("/pagamento/{id}")
    public String paginaPagamento(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        Jogo jogo = jogoRepository.findById(id).orElse(null);
        model.addAttribute("jogo", jogo);
        model.addAttribute("chavePix", "https://livepix.gg/kiota");
        return "pagamento";
    }
}