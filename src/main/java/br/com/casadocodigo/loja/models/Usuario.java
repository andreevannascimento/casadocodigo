package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String email;

    private String nome;

    private String senha;

    @Transient
    private String senhaRepetida;

    @Transient
    private String[] rolesList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Usuario_Role", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "role_nome"))
    private List<Role> roles = new ArrayList<>();

    public String getEmail() {

        return this.email;
    }

    public void setEmail(final String email) {

        this.email = email;
    }

    public String getSenha() {

        return this.senha;
    }

    public void setSenha(final String senha) {

        this.senha = senha;
    }

    public String getNome() {

        return this.nome;
    }

    public void setNome(final String nome) {

        this.nome = nome;
    }

    public List<Role> getRoles() {

        return this.roles;
    }

    public void setRoles(final List<Role> roles) {

        this.roles = roles;
    }

    public String getSenhaRepetida() {

        return this.senhaRepetida;
    }

    public void setSenhaRepetida(final String senhaRepetida) {

        this.senhaRepetida = senhaRepetida;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.roles;
    }

    @Override
    public String getPassword() {

        return this.senha;
    }

    @Override
    public String getUsername() {

        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public List<String> getTodosRoles() {

        final List<String> roles = new ArrayList<>();

        for (final Role role : this.roles) {
            roles.add(role.getNome());
        }
        return roles;
    }

    public String[] getRolesList() {

        return this.rolesList;
    }

    public void setRolesList(final String[] rolesList) {

        this.rolesList = rolesList;
    }

}
