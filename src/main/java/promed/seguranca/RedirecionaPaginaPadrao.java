package promed.seguranca;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import promed.util.EnumGrupo;

/**
 * 
 * https://github.com/eugenp/tutorials/blob/master/spring-security-mvc-persisted-remember-me/src/main/java/org/baeldung/security/MySimpleUrlAuthenticationSuccessHandler.java
 *
 */

@Component
public class RedirecionaPaginaPadrao implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			System.out.println("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Define para qual página irá ser redirecionada
	 * 
	 * @param authentication
	 * @return
	 */
	protected String determineTargetUrl(Authentication authentication) {
		boolean isUser = false;
		boolean isAdmin = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals(EnumGrupo.CLIENTE.getDescricao())) {
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals(EnumGrupo.ADMIN.getDescricao())) {
				isAdmin = true;
				break;
			}
		}

		if (isUser) {
			return "/index.xhtml";
		} else if (isAdmin) {
			return "/admin/index.xhtml";
		} else {
			throw new IllegalStateException(
					"Favor entrar em contato com Administração para verificação do perfil cadastrado!");
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
