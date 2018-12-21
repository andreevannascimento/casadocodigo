package br.com.casadocodigo.loja.conf;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(final String viewName, final Locale locale) throws Exception {

        final MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);

        return view;
    }

}
