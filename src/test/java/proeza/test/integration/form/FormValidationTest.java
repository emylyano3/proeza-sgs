package proeza.test.integration.form;

import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.proeza.security.form.UsuarioForm;
import com.proeza.sgs.config.root.JpaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(profiles = "test")
public class FormValidationTest {

	@Autowired
	private ValidatorFactory	factory;

	@Autowired
	private ApplicationContext	context;

	@Test
	public void validateUsuarioForm () {
		UsuarioForm usuarioForm = this.context.getBean(UsuarioForm.class);
		this.factory.getValidator().validate(usuarioForm);
	}
}