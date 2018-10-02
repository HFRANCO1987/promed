package promed.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import promed.entidade.Pessoa;

public class PessoaDAO {

	public void incluir(Pessoa pessoa) {
		Transaction transacao = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(pessoa);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			transacao.rollback();
		}
	}
	
	public void alterar(Pessoa pessoa) {
		Transaction transacao = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(pessoa);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			transacao.rollback();
		}
	}


	public List<Pessoa> obterClientes() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Pessoa p where order by p.nome ");
		return consulta.list();
	}

}
