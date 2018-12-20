package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;

@Repository
@Transactional
public class RoleDAO {

    @PersistenceContext
    private EntityManager manager;

    public void gravar(final Role role) {

        this.manager.persist(role);
    }

    public List<Role> listar() {

        return this.manager.createQuery("select distinct(r) from Role r", Role.class)
                        .getResultList();
    }

}
