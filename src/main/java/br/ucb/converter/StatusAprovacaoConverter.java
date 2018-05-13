package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.StatusAprovacaoDao;
import br.ucb.dao.impl.StatusAprovacaoDaoImpl;
import br.ucb.entity.StatusAprovacao;

@FacesConverter(forClass = StatusAprovacao.class)
public class StatusAprovacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		StatusAprovacao retorno = null;

		if (value != null) {
			StatusAprovacaoDao statusAprovacaoDao = new StatusAprovacaoDaoImpl();
			retorno = statusAprovacaoDao.findById(new Integer(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((StatusAprovacao) value).getIdStatusAprovacao().toString();
		}
		return null;
	}

}
