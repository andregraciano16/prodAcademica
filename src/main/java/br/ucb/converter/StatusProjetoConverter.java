package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import br.ucb.dao.StatusProjetoDao;
import br.ucb.dao.impl.StatusProjetoDaoImpl;
import br.ucb.entity.StatusProjeto;

@FacesConverter(forClass=StatusProjeto.class)
public class StatusProjetoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		StatusProjeto retorno = null;
		
		if(value != null){
			StatusProjetoDao statusProjetoDao = new StatusProjetoDaoImpl();
			retorno = statusProjetoDao.findById(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((StatusProjeto) value).getIdStatusProjeto().toString();
		} return null;
	}
}
