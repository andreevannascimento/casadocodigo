package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Pedido implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private BigDecimal valor;

    private Calendar data;

    private List<Produto> produtos = new ArrayList<>();

    public Integer getId() {

        return this.id;
    }

    public void setId(final Integer id) {

        this.id = id;
    }

    public BigDecimal getValor() {

        return this.valor;
    }

    public void setValor(final BigDecimal valor) {

        this.valor = valor;
    }

    public Calendar getData() {

        return this.data;
    }

    public void setData(final Calendar data) {

        this.data = data;
    }

    public List<Produto> getProdutos() {

        return this.produtos;
    }

    public void setProdutos(final List<Produto> produtos) {

        this.produtos = produtos;
    }

    public String getTodosTitulos() {

        String titulos = "";

        for (final Produto produto : this.produtos) {
            if (!titulos.isEmpty()) {
                titulos += ", ";
            }
            titulos += produto.getTitulo();
        }

        return titulos;

    }

}
