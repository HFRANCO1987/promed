package promed.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import promed.entidade.Pedido;

public class PedidoDAO implements Serializable {

	private static final long serialVersionUID = 3335900217122611144L;

	public void incluir(Pedido pedido) {
		Transaction transacao = null;
		try {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(pedido);
			transacao.commit();
			sessao.close();
		} catch (Exception e) {
			transacao.rollback();
		}
	}

}
