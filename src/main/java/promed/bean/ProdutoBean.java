package promed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import promed.dao.ProdutoDAO;
import promed.entidade.Produto;

@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 606992555572919784L;

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private List<Produto> listaProdutos = new ArrayList<>();
	private List<Produto> listaProdutosAdmin = new ArrayList<>();
	private Produto produto = new Produto();

	public String cadastrarProduto() throws Exception {
		if (produto != null && produto.getId() != null) {
			produtoDAO.alterar(produto);
			adicionarMensagemSucesso("Produto alterado com sucesso!");
			produto = new Produto();
			initProdutosAdmin();
		} else{
			produtoDAO.incluir(produto);
			adicionarMensagemSucesso("Produto cadastrado com sucesso!");
			produto = new Produto();
			initProdutosAdmin();
		}
		return null;
	}

	public String excluir() throws Exception {
		if (produto != null && produto.getId() != null) {
			produtoDAO.excluir(produto);
			adicionarMensagemSucesso("Produto excluído com sucesso!");
			produto = new Produto();
			initProdutosAdmin();
		} else {
			adicionarMensagemErro("Ocorreu uma falha ao excluir produto!");
		}
		return null;
	}

	public void initProdutos() {
		this.listaProdutos = produtoDAO.obterTodos();
	}

	public void initProdutosAdmin() throws Exception {
		this.listaProdutosAdmin = produtoDAO.obterTodos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public static void adicionarMensagemSucesso(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}

	public static void adicionarMensagemErro(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosAdmin() {
		return listaProdutosAdmin;
	}

	public void setListaProdutosAdmin(List<Produto> listaProdutosAdmin) {
		this.listaProdutosAdmin = listaProdutosAdmin;
	}
}
