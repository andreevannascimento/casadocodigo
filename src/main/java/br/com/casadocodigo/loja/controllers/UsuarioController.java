package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private RoleDAO roleDAO;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {

        binder.addValidators(new UsuarioValidation());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {

        final List<Usuario> usuarios = this.usuarioDAO.listar();
        final ModelAndView modelAndView = new ModelAndView("usuarios/lista");
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }

    @RequestMapping("/form")
    public ModelAndView form(final Usuario usuario, final String message) {

        final ModelAndView modelAndView = new ModelAndView("usuarios/form");
        if (message != null) {
            modelAndView.addObject("falha", message);
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    @CacheEvict(value = "produtosHome", allEntries = true)
    public ModelAndView gravar(final MultipartFile sumario, @Valid final Usuario usuario, final BindingResult result,
                    final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return this.form(usuario, null);
        }

        if (!usuario.getSenha().equals(usuario.getSenhaRepetida())) {
            return this.form(usuario, "As senhas devem ser iguais!");
        }

        if (usuario.getSenha().length() < 5) {
            return this.form(usuario, "A senha deve ter no mínimo 5 caracteres!");
        }

        if (this.usuarioDAO.verificaEmailCadastrado(usuario.getEmail())) {
            return this.form(usuario, "Email já cadastrado!");
        }

        this.usuarioDAO.gravar(usuario);

        redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");

        return new ModelAndView("redirect:/usuarios");
    }

    @RequestMapping("/formAlterar")
    public ModelAndView formAlterar(final String usuarioNome, @Valid final Usuario usuario, final BindingResult result) {

        final Usuario usuarioBusca = this.usuarioDAO.findNome(usuarioNome);
        final List<String> roles = new ArrayList<>();
        String[] array = null;

        for (final Role role : usuarioBusca.getRoles()) {
            roles.add(role.getNome());
        }

        array = roles.toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
        usuarioBusca.setRolesList(array);

        final ModelAndView modelAndView = new ModelAndView("usuarios/alterar", "command", usuarioBusca);
        modelAndView.addObject("nome", usuarioBusca.getNome());
        modelAndView.addObject("usuario", usuarioBusca);
        return modelAndView;
    }

    @RequestMapping(value = "alterar", method = RequestMethod.POST)
    public ModelAndView alterar(final String nomeUsuario, final String[] rolesList, final RedirectAttributes redirectAttributes) {

        final Usuario usuarioBusca = this.usuarioDAO.findNome(nomeUsuario);
        final List<String> roles = new ArrayList<>();
        final List<Role> listRoles = new ArrayList<>();

        for (int i = 0; i <= (rolesList.length - 1); i++) {
            if (i >= 3) {
                roles.add(rolesList[i]);
            }
        }

        for (final String s : roles) {
            final Role role = new Role();
            role.setNome(s);
            listRoles.add(role);
        }
        usuarioBusca.setRoles(listRoles);
        this.usuarioDAO.alterarUsuario_Role(usuarioBusca);

        redirectAttributes.addFlashAttribute("sucesso", "Permissões alteradas com sucesso!");
        return new ModelAndView("redirect:/usuarios");
    }

    @ModelAttribute("rolesList")
    public List<String> getRolesList() {

        final List<Role> roles = this.roleDAO.listar();
        final List<String> rolesList = new ArrayList<>();

        roles.forEach(r -> {
            rolesList.add(r.getNome());
        });

        return rolesList;
    }

}
