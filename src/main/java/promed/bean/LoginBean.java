package promed.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 7522752702532817259L;

	private String email;

	public void preRender() {
		if ("true".equals(getServletRequest().getParameter("invalid"))) {
			adicionarMensagemErro("Usuário ou Senha incorreto!");
		}
	}

	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletRequest().getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(getServletRequest(), getServletResponse());
		getResponseComplete();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static void getResponseComplete() {
		FacesContext.getCurrentInstance().getResponseComplete();
	}

	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	public static void adicionarMensagemErro(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}
}