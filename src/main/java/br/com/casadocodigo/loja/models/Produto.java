package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    private String descricao;

    private int paginas;

    private String sumarioPath;

    @ElementCollection
    private List<Preco> precos = new ArrayList<>();

    @DateTimeFormat
    private Calendar dataLancamento;

    public String getTitulo() {

        return this.titulo;
    }

    public void setTitulo(final String titulo) {

        this.titulo = titulo;
    }

    public String getDescricao() {

        return this.descricao;
    }

    public void setDescricao(final String descricao) {

        this.descricao = descricao;
    }

    public int getPaginas() {

        return this.paginas;
    }

    public void setPaginas(final int paginas) {

        this.paginas = paginas;
    }

    public int getId() {

        return this.id;
    }

    public void setId(final int id) {

        this.id = id;
    }

    public List<Preco> getPrecos() {

        return this.precos;
    }

    public void setPrecos(final List<Preco> precos) {

        this.precos = precos;
    }

    public Calendar getDataLancamento() {

        return this.dataLancamento;
    }

    public void setDataLancamento(final Calendar dataLancamento) {

        this.dataLancamento = dataLancamento;
    }

    public String getSumarioPath() {

        return this.sumarioPath;
    }

    public void setSumarioPath(final String sumarioPath) {

        this.sumarioPath = sumarioPath;
    }

    @Override
    public String toString() {

        return "Produto [titulo=" + this.titulo + ", descricao=" + this.descricao + ", paginas=" + this.paginas + "]";
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = (prime * result) + this.id;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public BigDecimal precoPara(final TipoPreco tipoPreco) {

        return this.precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco))
                        .findFirst().get().getValor();
    }

}
