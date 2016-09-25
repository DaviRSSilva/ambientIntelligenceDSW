package br.edu.ifsp.hto;

import javax.servlet.jsp.PageContext;

public class Util {

	public static String ERROR_UNAUTH = "Not authorized!";

	/**
	 * Método checa se o usuário está logado.
	 * @param context - para procurar se o atributo de logado está true.
	 * @return isLogado
	 */
	public static boolean checkIfUserIsLogado(PageContext context) {
		Object sessionAttribute = context.getAttribute("isLogado", PageContext.SESSION_SCOPE);
		if (sessionAttribute != null && Boolean.getBoolean(sessionAttribute.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Enum de lumens, seguindo a lógica do négocio. 
	 * @author davir
	 *
	 */
	public enum LumenInterval {
		LOW_LIGHT, INSIDE_AMBIENT, EXTERN_AMBIENT, EXTREME_LIGHT;

		/**
		 * Recebe o valor de lumens atual e retorna o Enum referente aquele valor.
		 * @param valor de lumens
		 * @return {@link LumenInterval} Enum referente ao valor
		 */
		public static LumenInterval getLumenInterval(int value) {
			if (value <= 20) {
				return LumenInterval.LOW_LIGHT;
			} else if (value >= 21 && value <= 50) {
				return LumenInterval.INSIDE_AMBIENT;
			} else if (value >= 51 && value <= 80) {
				return LumenInterval.EXTERN_AMBIENT;
			} else if (value >= 81) {
				return LumenInterval.EXTREME_LIGHT;
			}
			return LumenInterval.EXTREME_LIGHT;
		}
	}

}
