package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedido;

@Controller
@RequestMapping("/pedidos")
public class PedidosServicoController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {

        final String uri = "http://book-payment.herokuapp.com/orders";

        final ResponseEntity<List<Pedido>> response = this.restTemplate.exchange(
                        "http://book-payment.herokuapp.com/orders",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Pedido>>() {
                        });
        final List<Pedido> pedidos = response.getBody();

        final ModelAndView modelAndView = new ModelAndView("pedidos/pedidos");
        modelAndView.addObject("pedidos", pedidos);
        return modelAndView;
    }

}
