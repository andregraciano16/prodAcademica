package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.ExternoDao;
import br.ucb.dao.impl.ExternoDaoImpl;
import br.ucb.entity.Externo;

@FacesConverter(forClass = Externo.class)
public class ExternoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Externo retorno = null;

		if (value != null) {
			ExternoDao externoDao = new ExternoDaoImpl();  
			retorno = externoDao.findById(new Integer(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Externo) value).getIdExterno().toString();
		}
		return null;
	}
	
}
