package br.edu.ifsp.hto;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.edu.ifsp.hto.Util.LumenInterval;

/**
 * Tag that supports the AmbientIntelligence feature. This tag will return a
 * HTML defining line that will chance the layout color according to the lumens
 * in the session.
 * 
 * @author davir
 *
 */
public class AmbientInteligence extends SimpleTagSupport {

	private HashMap<String, String> layoutDifferences;

	public static final String ATTR_NAME = "currLumens";

	private static final boolean ISDEBUGING = true; // FIXME SEMPRE ALTERAR PARA
													// FALSE QUANDO EM PRODUÇÃO

	// Construtor para contruir o map contendo as URLS dos css
	public AmbientInteligence() {
		fillHashMap();
	}

	/**
	 * Preencher o hashmap com as possíveis stylesheets
	 */
	private void fillHashMap() {
		layoutDifferences = new HashMap<>();
		layoutDifferences.put(LumenInterval.LOW_LIGHT.name(), "css/stylesheet_lowLight.css");
		layoutDifferences.put(LumenInterval.INSIDE_AMBIENT.name(), "css/stylesheet_insideAmbient.css");
		layoutDifferences.put(LumenInterval.EXTERN_AMBIENT.name(), "css/stylesheet_externAmbient.css");
		layoutDifferences.put(LumenInterval.EXTREME_LIGHT.name(), "css/stylesheet_extremeLight.css");
	}

	StringWriter sw = new StringWriter();
	private ServletContext servletContext;

	private static final String STYLESHEET_STRING = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";

	private static final String STYLESHEET_ERROR = "<!-- Error when trying to find %s file. Check if the file exists in it's folder.  -->";

	@Override
	public void doTag() throws JspException, IOException {

		if (ISDEBUGING) {
			boolean isLogado = Util.checkIfUserIsLogado((PageContext) getJspContext());

			if (!isLogado) {
				getJspContext().getOut().write(Util.ERROR_UNAUTH);
				return;
			}
		}
		LumenInterval interval = getCurrentLumens();
		String returningStylesheet = layoutDifferences.get(interval.name());

		servletContext = ((PageContext) getJspContext()).getServletContext();

		boolean cssExists = checkIfCssExists(returningStylesheet);
		// Caso o arquivo de CSS não exista, colocaremos um comentário na pagina
		// para alertar o desenvolvedor que é necessário
		// declarar o estilo que ele deseja pra cada tipo de ambiente.
		if (cssExists) {
			getJspContext().getOut().write(String.format(STYLESHEET_STRING, returningStylesheet));

		} else {
			getJspContext().getOut().write(String.format(STYLESHEET_ERROR, returningStylesheet));
		}

	}

	private boolean checkIfCssExists(String path) {
		if (servletContext == null) {
			return false;
		}
		InputStream is = servletContext.getResourceAsStream(path);
		if (is == null) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Retona o intervalo de lumens do parâmetro da sessão atual.
	 * 
	 * @return {@link LumenInterval}
	 */
	public LumenInterval getCurrentLumens() {
		JspContext jspContext = getJspContext();
		Object sessionAttribute = jspContext.getAttribute(ATTR_NAME, PageContext.SESSION_SCOPE);

		LumenInterval interval = LumenInterval.LOW_LIGHT;
		if (sessionAttribute != null && sessionAttribute instanceof String) {
			int currLumens = Integer.parseInt(sessionAttribute.toString());
			interval = LumenInterval.getLumenInterval(currLumens);
		}

		return interval;

	}

}
