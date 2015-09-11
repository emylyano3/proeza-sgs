package proeza.test.unit;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.proeza.core.resources.message.IMessageResolver;
import com.proeza.core.resources.message.MessageResolver;
import com.proeza.core.service.IErrorService;
import com.proeza.core.service.IImageService;
import com.proeza.core.service.IMailService;
import com.proeza.security.dao.IRolDao;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.service.IRoleService;
import com.proeza.security.service.IUserService;
import com.proeza.security.service.impl.UserService;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartManager;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.IVentaDao;
import com.proeza.sgs.business.service.IArticuloChartService;
import com.proeza.sgs.business.service.IArticuloService;
import com.proeza.sgs.business.service.IClaseService;
import com.proeza.sgs.business.service.IMarcaService;
import com.proeza.sgs.business.service.IRubroService;
import com.proeza.sgs.business.service.ITipoService;
import com.proeza.sgs.business.service.IVentaChartService;
import com.proeza.sgs.business.service.IVentaService;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.mail.IMailManager;
import com.proeza.sgs.system.service.IPageService;

@Configuration
public class UnitTestContext {

    @Bean
    public IVentaService ventaService () {
        return Mockito.mock(IVentaService.class);
    }

    @Bean
    public IVentaChartService ventaChartService () {
        return Mockito.mock(IVentaChartService.class);
    }

    @Bean
    public IVentaDao ventaDao () {
        return Mockito.mock(IVentaDao.class);
    }

    @Bean
    public IUserService userService () {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public PasswordEncoder sasswordEncoder () {
        return Mockito.mock(PasswordEncoder.class);
    }

    @Bean
    public IImageService imageService () {
        return Mockito.mock(IImageService.class);
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
    public IRolDao rolDao () {
        return Mockito.mock(IRolDao.class);
    }

    @Bean
    public IClaseService claseService () {
        return Mockito.mock(IClaseService.class);
    }

    @Bean
    public IPageService pageService () {
        return Mockito.mock(IPageService.class);
    }

    @Bean
    public IRoleService roleService () {
        return Mockito.mock(IRoleService.class);
    }

    @Bean
    public IErrorService errorService () {
        return Mockito.mock(IErrorService.class);
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
    public IArticuloChartService productChartService () {
        return Mockito.mock(IArticuloChartService.class);
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