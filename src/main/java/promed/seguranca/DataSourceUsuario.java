package promed.seguranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import promed.dao.UsuarioDAO;
import promed.entidade.Grupo;
import promed.entidade.Usuario;

/**
 * Implementa UserDetailsService do spring security e faz o override do metodo
 * loadUserByUsername
 *
 */
public class DataSourceUsuario implements UserDetailsService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	/**
	 * Executa consulta do usuario por e-mail
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Usuario usuario = null;
			usuario = usuarioDAO.porEmail(username);
			UsuarioSistema user = null;
			if (usuario != null) {
				user = new UsuarioSistema(usuario, getGrupos(usuario));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Preenche os grupos do usuario logado, informando perfis
	 * 
	 * @param usuario
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		return authorities;
	}

}