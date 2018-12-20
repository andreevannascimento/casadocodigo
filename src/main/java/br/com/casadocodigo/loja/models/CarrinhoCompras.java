package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

    public Collection<CarrinhoItem> getItens() {

        return this.itens.keySet();
    }

    public void add(final CarrinhoItem item) {

        this.itens.put(item, this.getQuantidade(item) + 1);
    }

    public Integer getQuantidade(final CarrinhoItem item) {

        if (!this.itens.containsKey(item)) {
            this.itens.put(item, 0);
        }
        return this.itens.get(item);
    }

    public int getQuantidade() {

        return this.itens.values().stream().reduce(0,
                        (proximo, acumulador) -> proximo + acumulador);
    }

    public BigDecimal getTotal(final CarrinhoItem item) {

        return item.getTotal(this.getQuantidade(item));
    }

    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (final CarrinhoItem item : this.itens.keySet()) {
            total = total.add(this.getTotal(item));
        }
        return total;
    }

    public void remover(final Integer produtoId, final TipoPreco tipoPreco) {

        final Produto produto = new Produto();
        produto.setId(produtoId);
        this.itens.remove(new CarrinhoItem(produto, tipoPreco));
    }

    public void limpa() {

        this.itens.clear();
    }

}
