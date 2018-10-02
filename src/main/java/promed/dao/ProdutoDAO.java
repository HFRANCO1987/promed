package promed.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import promed.entidade.Produto;

public class ProdutoDAO {

	public void incluir(Produto produto) {
		Transaction transacao = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(produto);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			transacao.rollback();
		}
	}

	public void alterar(Produto produto) {
		Transaction transacao = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(produto);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			transacao.rollback();
		}
	}

	public void excluir(Produto produto) {
		Transaction transacao = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(produto);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			transacao.rollback();
		}
	}

	public List<Produto> obterTodos() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Produto p order by p.nome");
		return consulta.list();
	}

}
