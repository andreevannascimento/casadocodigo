package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.RelatorioProdutosDTO;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {

    @Autowired
    private ProdutoDAO produtoDAO;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RelatorioProdutosDTO relatorioProdutoPorLancamento(@RequestParam("data") final String data) throws ParseException {

        final RelatorioProdutosDTO rpDTO = new RelatorioProdutosDTO();

        List<Produto> produtos = new ArrayList<>();

        final Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(data));

        produtos = this.produtoDAO.listarProdutosRelatorio(cal);

        rpDTO.setDataGeracao(Calendar.getInstance());
        rpDTO.setProdutos(produtos);
        rpDTO.setQuantidade(produtos.size());

        return rpDTO;
    }

}
