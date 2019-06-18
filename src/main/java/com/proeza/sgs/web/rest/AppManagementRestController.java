package com.proeza.sgs.web.rest;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.service.ILoginService;
import com.proeza.sgs.config.root.ContextLocale;
import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.system.service.dto.MenuDTO;

@CrossOrigin
@RestController
@RequestMapping("api")
public class AppManagementRestController {

	private static Logger	log	= Logger.getLogger(AppManagementRestController.class);

	@Autowired
	private IMenuService	menuService;

	@Autowired
	private ILoginService	loginService;

	@Autowired
	private ContextLocale	contextLocale;

	@RequestMapping(value = "/menu/{code}/{user}", method = RequestMethod.GET)
	public MenuDTO getMenu (@PathVariable String code, @PathVariable String user, Locale locale) {
		this.contextLocale.setLocale(locale.toString());
		return this.menuService.getMenu(code, user, locale.toString());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login (@RequestParam String user, @RequestParam String pass) {
		try {
			UsuarioDTO userDTO = this.loginService.login(user, pass);
			Algorithm algorithm = Algorithm.RSA256(null, getPrivateKey());
			String token = JWT
				.create()
				.withIssuer(userDTO.getAlias())
				.sign(algorithm);
			StringBuilder sb = new StringBuilder()
				.append("{")
				.append("\"idToken\": ")
				.append("\"")
				.append(token)
				.append("\"")
				.append(",")
				.append("\"expiresIn\": \"1200\"")
				.append("}");
			return sb.toString();
		} catch (JWTCreationException e) {
			throw new RuntimeException(e);
		} catch (AuthenticationException e) {
			throw new RuntimeException(e);
		}
	}
	public RSAPrivateKey getPrivateKey () {
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(
				new BigInteger(getModulus(), 16),
				new BigInteger(getPrivateExponent(), 16));
			return (RSAPrivateKey) kf.generatePrivate(privateKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error("No se puede obtener la key privada", e);
			return null;
		}
	}

	private String getPrivateExponent () {
		return "57791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb1";
	}

	private String getModulus () {
		return "57791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb157791d5430d593164082036ad8b29fb1";
	}
}