package proeza.test.unit.web.context;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.proeza.core.resources.IMessageResolver;
import com.proeza.core.resources.MessageResolver;
import com.proeza.core.service.IMailService;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.service.IUserService;
import com.proeza.security.service.UserService;
import com.proeza.sgs.system.dao.IPageDao;

@Configuration
public class TestContext {

	@Bean
	public IUserService userService () {
		return Mockito.mock(UserService.class);
	}

	@Bean
	public IUsuarioDao usuarioDao () {
		return Mockito.mock(IUsuarioDao.class);
	}

	@Bean
	public IPageDao pageDao () {
		return Mockito.mock(IPageDao.class);
	}

	@Bean
	public IMailService mailService () {
		return Mockito.mock(IMailService.class);
	}

	@Bean
	public IMessageResolver proezaMessageResolver () {
		return Mockito.mock(MessageResolver.class);
	}

	@Bean
	public LocaleResolver localeResolver () {
		return new FixedLocaleResolver();
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager () {
		return Mockito.mock(PlatformTransactionManager.class);
	}
}