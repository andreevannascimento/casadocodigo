package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {

    @PersistenceContext
    private EntityManager manager;

    public void gravar(final Produto produto) {

        this.manager.persist(produto);
    }

    public List<Produto> listar() {

        return this.manager.createQuery("select distinct(p) from Produto p join fetch p.precos", Produto.class)
                        .getResultList();
    }

    public Produto find(final Integer id) {

        return this.manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id",
                        Produto.class).setParameter("id", id)
                        .getSingleResult();
    }

    public BigDecimal somaPrecosPorTipo(final TipoPreco tipoPreco) {

        final TypedQuery<BigDecimal> query = this.manager.createQuery("select sum(preco.valor) from Produto p join p.precos preco "
                        + "where preco.tipo = :tipoPreco", BigDecimal.class);
        query.setParameter("tipoPreco", tipoPreco);
        return query.getSingleResult();
    }

    public List<Produto> listarProdutosRelatorio(final Calendar data) {

        return this.manager.createQuery("select distinct(p) from Produto p join fetch p.precos where p.dataLancamento > :data", Produto.class)
                        .setParameter("data", data)
                        .getResultList();
    }
}