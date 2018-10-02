package promed.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.CellEditEvent;

import promed.dao.PedidoDAO;
import promed.entidade.Pedido;
import promed.entidade.Produto;
import promed.entidade.Usuario;
import promed.seguranca.Seguranca;
import promed.util.EnumFormaPagamento;
import promed.util.IEnum;

@ManagedBean(name = "carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

	private static final long serialVersionUID = -580170338628459849L;

	private Map<Usuario, Set<Produto>> produtos = new HashMap<>();
	private Produto produto;

	private Seguranca seguranca = new Seguranca();

	private PedidoDAO pedidoDAO = new PedidoDAO();

	private BigDecimal valorTotal;

	private List<SelectItem> enumFormaPagamento;
	private EnumFormaPagamento formaPagamento;

	@PostConstruct
	public void initCarrinho() {
		this.produtos.put(new Usuario(), new HashSet<>());
	}

	/**
	 * Adiciona produto ao carrinho e adiciona no map
	 */
	public void adicionarProdutoCarrinho() {
		Set<Produto> listaProdutos = produtos.get(seguranca.getUsuario());
		if (listaProdutos == null) {
			listaProdutos = new HashSet<>();
		}
		listaProdutos.add(produto);
		produtos.put(seguranca.getUsuario(), listaProdutos);
	}

	/**
	 * Calcula os itens dos carrinhos
	 * 
	 * @return
	 */
	public BigDecimal getCalcularTotalCarrinho() {
		this.valorTotal = BigDecimal.ZERO;
		for (Produto produto : getListaProdutosCarrinho()) {
			this.valorTotal = this.valorTotal.add(new BigDecimal(produto.getQnt()).multiply(produto.getPreco()));
		}
		return valorTotal;
	}

	/**
	 * Executa metodo ao alterar quantidade do campo no carrinho
	 * 
	 * @param event
	 */
	public void onCellEdit(CellEditEvent event) {
		getCalcularTotalCarrinho();
	}

	/**
	 * Finaliza compra
	 *
	 * @return
	 * @throws Exception
	 */
	public String finalizaCompra() throws Exception {
		Pedido pedido = new Pedido();
		pedido.setDataPedido(new Date());
		pedido.setNumeroPedido(gerarUnicoId().toString());
		pedido.setFormaPagamento(formaPagamento);
		Set<Produto> listaProdutos = produtos.get(seguranca.getUsuario());
		pedido.setCliente(seguranca.getUsuario().getPessoa());
		pedido.setListaProdutos(listaProdutos);
		pedidoDAO.incluir(pedido);
		produtos.put(seguranca.getUsuario(), new HashSet<>());
		adicionarMensagemSucesso(
				"Compra finalizada com sucesso, anote o nº do seu pedido: " + pedido.getNumeroPedido());
		return null;
	}

	public List<Produto> getListaProdutosCarrinho() {
		if (produtos.get(seguranca.getUsuario()) == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(produtos.get(seguranca.getUsuario()));
	}

	public Map<Usuario, Set<Produto>> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<Usuario, Set<Produto>> produtos) {
		this.produtos = produtos;
	}

	public List<SelectItem> getEnumFormaPagamento() {
		if (this.enumFormaPagamento == null || this.enumFormaPagamento.isEmpty()) {
			this.enumFormaPagamento = criaOpcoesSelect(EnumFormaPagamento.values());
		}
		return enumFormaPagamento;
	}

	public void setEnumFormaPagamento(List<SelectItem> enumFormaPagamento) {
		this.enumFormaPagamento = enumFormaPagamento;
	}

	public static List<SelectItem> criaOpcoesSelect(IEnum[] enuns) {
		List<SelectItem> resultado = new ArrayList<SelectItem>();
		for (IEnum tipo : enuns) {
			resultado.add(new SelectItem(tipo, tipo.getDescricao()));
		}
		return resultado;
	}

	public EnumFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(EnumFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public static Long gerarUnicoId() {
		long val = -1;
		do {
			final UUID uid = UUID.randomUUID();
			final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
			buffer.putLong(uid.getLeastSignificantBits());
			buffer.putLong(uid.getMostSignificantBits());
			final BigInteger bi = new BigInteger(buffer.array());
			val = bi.longValue();
		} while (val < 0);
		return val;
	}

	public static void adicionarMensagemSucesso(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}

	public static void adicionarMensagemErro(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}