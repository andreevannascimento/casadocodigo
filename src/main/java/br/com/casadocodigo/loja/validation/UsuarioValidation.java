package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {

        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "senhaRepetida", "field.required");

    }

}
