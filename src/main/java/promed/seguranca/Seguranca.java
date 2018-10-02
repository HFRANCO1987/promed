package promed.seguranca;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import promed.entidade.Usuario;
import promed.util.EnumGrupo;

@ManagedBean(name = "seguranca")
@SessionScoped
public class Seguranca implements Serializable {

	private static final long serialVersionUID = 3254591370815586997L;

	private UsuarioSistema usuarioLogado = null;

	public Seguranca() {
	}

	public String getNomeUsuario() {
		String nome = null;
		if (this.usuarioLogado != null) {
			nome = this.usuarioLogado.getUsuario().getLogin();
		}
		return nome;
	}

	/**
	 * Recupera usuário logado do spring security
	 * 
	 * @return
	 */
	public UsuarioSistema getUsuarioLogado() {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (auth != null && auth.getPrincipal() != null) {
			this.usuarioLogado = (UsuarioSistema) auth.getPrincipal();
		}
		return this.usuarioLogado;
	}

	public Usuario getUsuario() {
		if (getUsuarioLogado() != null) {
			return getUsuarioLogado().getUsuario();
		}
		return new Usuario();
	}

	/**
	 * Verifica se tem permissão de Admin, caso verdadeiro retorno true
	 * 
	 * @return
	 */
	public boolean getPermissaoAdmin() {
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(EnumGrupo.ADMIN.getDescricao());
	}

	/**
	 * Verifica se tem permissão de Cliente, caso verdadeiro retorno true
	 * 
	 * @return
	 */
	public boolean getPermissaoCliente() {
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(EnumGrupo.CLIENTE.getDescricao());
	}

	public Boolean getUsuarioEstaLogado() {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (auth != null && auth.getPrincipal() != null && auth.isAuthenticated()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Verifica se usuário está logado e o perfil dele é Cliente
	 * 
	 * @return
	 */
	public Boolean getAutenticadoEhCliente() {
		if (getUsuarioEstaLogado() && getPermissaoCliente()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}