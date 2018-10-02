package promed.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("ValorMonetarioConverter")
public class ValorMonetarioConverter implements Converter {

	/**
	 * Recebe o valor da tela e retorna um objeto. O valor � passado por uma String
	 * e deve ser convertido para BigDecimal.
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		BigDecimal valor = null;
		try {
			if (value != null) {
				valor = formatarValorMonetario(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valor;
	}

	/**
	 * Recebe um objeto BigDecimal do neg�cio e retorna uma String com a m�scara
	 * "1.111.111.111,1111" para ser apresentada na tela.
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String valor = null;
		try {
			if (value != null) {
				valor = formatarValorMonetario((BigDecimal) value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valor;
	}

	public BigDecimal formatarValorMonetario(final String valor) {
		BigDecimal retorno = BigDecimal.ZERO;

		if (valor == null || valor.trim().equals("")) {
			return null;
		}
		// Valor vindo da tela --> 1.111.111.111,1111 para 1111111111.1111
		final String str = valor.replace('.', ' ');// retirando os pontos e
													// colocando espaï¿½os em
													// branco
		final String numero = str.replace(" ", "");// retirando os espaï¿½os em
													// branco
		final BigDecimal valorBanco = new BigDecimal(numero.replace(',', '.'));
		retorno = valorBanco;
		return retorno;
	}

	public String formatarValorMonetario(final BigDecimal valor) {
		if (valor != null) {
			DecimalFormat nf = new DecimalFormat("#,##0.00");
			return nf.format(valor.doubleValue());
		}
		return "";
	}

}
