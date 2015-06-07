package proeza.test.integration.form;

import javax.validation.ValidatorFactory;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import proeza.test.integration.IntegrationTest;

import com.proeza.security.form.UsuarioForm;

@Ignore
/* Crear un test que verifique realmente que la validacion del formulario sea correcta */
public class FormValidationTest extends IntegrationTest {

    // @Autowired
    private ValidatorFactory   factory;

    @Autowired
    private ApplicationContext context;

    @Test
    public void validateUsuarioForm () {
        UsuarioForm usuarioForm = this.context.getBean(UsuarioForm.class);
        this.factory.getValidator().validate(usuarioForm);
    }
}