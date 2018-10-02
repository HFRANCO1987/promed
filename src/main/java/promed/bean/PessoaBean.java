package promed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import promed.dao.PessoaDAO;
import promed.dao.UsuarioDAO;
import promed.entidade.Grupo;
import promed.entidade.Pessoa;
import promed.entidade.Usuario;
import promed.util.EnumGrupo;
import promed.util.EnumUf;
import promed.util.IEnum;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 606992555572919784L;

	private PessoaDAO pessoaDAO = new PessoaDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private List<SelectItem> enumUf;
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> listaPessoas = new ArrayList<>();

	/**
	 * Cria a pessoa e logo em seguida o usuário
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public String gravarUsuario() throws Exception {
		if (pessoa != null) {
			pessoa.setGrupo(EnumGrupo.CLIENTE);
			pessoaDAO.incluir(pessoa);

			Usuario usuario = new Usuario();
			usuario.setLogin(pessoa.getEmail());
			usuario.setSenha(pessoa.getSenha());
			usuario.getGrupos().add(new Grupo(2L));
			usuario.setPessoa(pessoa);
			usuarioDAO.incluir(usuario);

			adicionarMensagemSucesso("Cadastro realizado com sucesso!");
			pessoa = new Pessoa();
		} else {
			adicionarMensagemErro("Ocorreu uma falha ao realizar o cadastro!");
		}
		return null;
	}

	/**
	 * Lista todas as pessoas que pertence ao grupo = CLIENTE
	 * 
	 * @throws Exception
	 */
	public void initClientes() throws Exception {
		this.listaPessoas = pessoaDAO.obterClientes();
	}

	public List<SelectItem> getEnumUf() {
		if (this.enumUf == null || this.enumUf.isEmpty()) {
			this.enumUf = criaOpcoesSelect(EnumUf.values());
		}
		return enumUf;
	}

	public void setEnumUf(List<SelectItem> enumUf) {
		this.enumUf = enumUf;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public static void adicionarMensagemSucesso(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}

	public static void adicionarMensagemErro(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}

	public static List<SelectItem> criaOpcoesSelect(IEnum[] enuns) {
		List<SelectItem> resultado = new ArrayList<SelectItem>();
		for (IEnum tipo : enuns) {
			resultado.add(new SelectItem(tipo, tipo.getStatus()));
		}
		return resultado;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

}
