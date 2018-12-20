package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Usuario loadUserByUsername(final String email) {

        final List<Usuario> usuarios = this.manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
                        .setParameter("email", email)
                        .getResultList();

        if (usuarios.isEmpty()) {
            throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
        }

        return usuarios.get(0);
    }

    public void gravar(final Usuario usuario) {

        this.manager.persist(usuario);
    }

    public List<Usuario> listar() {

        return this.manager.createQuery("select distinct(u) from Usuario u left join fetch u.roles order by u", Usuario.class)
                        .getResultList();
    }

    public boolean verificaEmailCadastrado(final String email) {

        return this.manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
                        .setParameter("email", email)
                        .getResultList().size() > 0;
    }

    public Usuario findNome(final String usuarioNome) {

        return this.manager.createQuery("Select u from Usuario u where u.nome = :nome", Usuario.class).setParameter("nome", usuarioNome)
                        .getSingleResult();
    }

    public void alterarUsuario_Role(final Usuario usuarioBusca) {

        this.manager.persist(usuarioBusca);
    }
}