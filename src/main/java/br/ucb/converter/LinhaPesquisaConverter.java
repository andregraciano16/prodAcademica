package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.entity.LinhaPesquisa;

@FacesConverter(forClass=LinhaPesquisa.class)
public class LinhaPesquisaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LinhaPesquisa retorno = null;
		
		if(value != null){
			LinhaPesquisaDao linhaPesquisaDao = new LinhaPesquisaDaoImpl();
			retorno = linhaPesquisaDao.findById(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((LinhaPesquisa) value).getIdLinhaPesquisa().toString();
		} return null;
	}

}

