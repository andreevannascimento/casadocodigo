package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class RelatorioProdutosDTO {

    @DateTimeFormat
    private Calendar dataGeracao;

    private Integer quantidade;

    private List<Produto> produtos = new ArrayList<>();

    public Calendar getDataGeracao() {

        return this.dataGeracao;
    }

    public void setDataGeracao(final Calendar dataLancamento) {

        this.dataGeracao = dataLancamento;
    }

    public Integer getQuantidade() {

        return this.quantidade;
    }

    public void setQuantidade(final Integer quantidade) {

        this.quantidade = quantidade;
    }

    public List<Produto> getProdutos() {

        return this.produtos;
    }

    public void setProdutos(final List<Produto> produtos) {

        this.produtos = produtos;
    }

}
