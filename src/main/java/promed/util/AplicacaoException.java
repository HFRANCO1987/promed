package promed.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AplicacaoException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<String> listaMensagens = new ArrayList<String>();

	public AplicacaoException(String message) {
		super(message);
	}

	public AplicacaoException() {

	}

	public AplicacaoException(List<String> listaMensagens, Exception e) {
		super(e);
		this.listaMensagens = listaMensagens;
	}

	public AplicacaoException(List<String> listaMensagens) {
		this.listaMensagens = listaMensagens;
	}

	public AplicacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public AplicacaoException(Throwable cause) {
		super(cause);
	}

	public List<String> getListaMensagens() {
		return Collections.unmodifiableList(listaMensagens);
	}

	@Override
	public String getMessage() {
		String demaisMensagens = "";
		if (listaMensagens != null && !listaMensagens.isEmpty()) {
			demaisMensagens = listaMensagens.toString();
		}
		return String.format("%s %s", super.getMessage(), demaisMensagens);
	}

}
