package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.RelatorioProdutosDTO;

@CrossOrigin
@RestController
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {

    @Autowired
    private ProdutoDAO produtoDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView relatorioProdutoPorLancamento(
                    @RequestParam(value = "data", required = false) @DateTimeFormat(iso = ISO.DATE) final String data)
                    throws ParseException {

        final RelatorioProdutosDTO rpDTO = new RelatorioProdutosDTO();
        List<Produto> produtos = new ArrayList<>();
        final ModelAndView andView = new ModelAndView();

        produtos = this.produtoDAO.listarProdutosRelatorio(data);

        rpDTO.setDataGeracao(Calendar.getInstance());
        rpDTO.setProdutos(produtos);
        rpDTO.setQuantidade(produtos.size());
        andView.addObject("relatorioDTO", rpDTO);

        return andView;
    }

}
