package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private CarrinhoCompras carrinho;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailSender sender;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public ModelAndView finalizar(@AuthenticationPrincipal final Usuario usuario, final RedirectAttributes model) {

        final String uri = "http://book-payment.herokuapp.com/payment";

        try {
            final String response = this.restTemplate.postForObject(uri, new DadosPagamento(this.carrinho.getTotal()), String.class);
            model.addFlashAttribute("message", response);
            this.carrinho.limpa();
            System.out.println(response);

            // método para tirar todos os livros do carrinho
            this.enviaEmailCompraProduto(usuario);

            return new ModelAndView("redirect:/");
        } catch (final HttpClientErrorException e) {
            e.printStackTrace();
            model.addFlashAttribute("message", "Valor maior que o permitido! Compra negada!");
            return new ModelAndView("redirect:/");
        }
    }

    private void enviaEmailCompraProduto(final Usuario usuario) {

        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Compra finalizado com sucesso");
        email.setTo(usuario.getEmail());
        email.setText("Compra aprovada com sucesso no valor de " + this.carrinho.getTotal());
        email.setFrom("compras@casadocodigo.com.br");

        System.out.println("Envio de email foi desabilitado.");
        // sender.send(email);
    }
}