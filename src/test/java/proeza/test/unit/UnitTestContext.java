package proeza.test.unit;

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
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartManager;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.service.IArticuloService;
import com.proeza.sgs.business.service.IClaseService;
import com.proeza.sgs.business.service.IMarcaService;
import com.proeza.sgs.business.service.IRubroService;
import com.proeza.sgs.business.service.ITipoService;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.mail.IMailManager;

@Configuration
public class UnitTestContext {

    @Bean
    public IUserService userService () {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public IUsuarioDao usuarioDao () {
        return Mockito.mock(IUsuarioDao.class);
    }

    @Bean
    public HistorialPrecioChartManager historialPrecioLineChartManager () {
        return new HistorialPrecioChartManager();
    }

    @Bean
    public IPageDao pageDao () {
        return Mockito.mock(IPageDao.class);
    }

    @Bean
    public IArticuloDao articuloDao () {
        return Mockito.mock(IArticuloDao.class);
    }

    @Bean
    public IClaseService claseService () {
        return Mockito.mock(IClaseService.class);
    }

    @Bean
    public IRubroService rubroService () {
        return Mockito.mock(IRubroService.class);
    }

    @Bean
    public ITipoService tipoService () {
        return Mockito.mock(ITipoService.class);
    }

    @Bean
    public IMarcaService marcaService () {
        return Mockito.mock(IMarcaService.class);
    }

    @Bean
    public IMailService mailService () {
        return Mockito.mock(IMailService.class);
    }

    @Bean
    public IArticuloService productService () {
        return Mockito.mock(IArticuloService.class);
    }

    @Bean
    public IMailManager mailManager () {
        return Mockito.mock(IMailManager.class);
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