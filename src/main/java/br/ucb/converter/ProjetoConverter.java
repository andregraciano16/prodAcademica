package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.ProjetoDao;
import br.ucb.dao.impl.ProjetoDaoImpl;
import br.ucb.entity.Projeto;



@FacesConverter(forClass=Projeto.class)
public class ProjetoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Projeto retorno = null;
		
		if(value != null){
			ProjetoDao projetoDao = new ProjetoDaoImpl();
			retorno = projetoDao.findById(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((Projeto) value).getIdProjeto().toString();
		} return null;
	}


}
