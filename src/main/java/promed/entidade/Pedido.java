package promed.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import promed.util.EnumFormaPagamento;
import promed.util.EnumStatusPedido;

@Entity
@Table(name = "tab_pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 716035492295843177L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "numeropedido")
	private String numeroPedido;

	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa cliente;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tab_pedidos_cliente", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
	private Set<Produto> listaProdutos = new HashSet<Produto>();

	@Column(name = "datapedido")
	private Date dataPedido;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EnumStatusPedido status;

	@Enumerated(EnumType.STRING)
	@Column(name = "formapagamento")
	private EnumFormaPagamento formaPagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public EnumStatusPedido getStatus() {
		return status;
	}

	public void setStatus(EnumStatusPedido status) {
		this.status = status;
	}

	public EnumFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(EnumFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Set<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(Set<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroPedido == null) ? 0 : numeroPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroPedido == null) {
			if (other.numeroPedido != null)
				return false;
		} else if (!numeroPedido.equals(other.numeroPedido))
			return false;
		return true;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

}
