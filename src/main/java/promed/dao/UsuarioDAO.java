package promed.dao;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import promed.entidade.Usuario;

public class UsuarioDAO {

	public Usuario porEmail(String email) {
		Usuario usuario = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Query consulta = sessao.createQuery("from Usuario u left join fetch u.grupos where u.login = :email");
			consulta.setParameter("email", email);
			usuario = (Usuario) consulta.uniqueResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return usuario;
	}

	public void incluir(Usuario usuario) {
		Transaction transacao = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(usuario);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			transacao.rollback();
		}
	}
}
