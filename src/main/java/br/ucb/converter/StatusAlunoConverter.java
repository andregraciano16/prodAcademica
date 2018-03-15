package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.StatusAlunoDao;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.entity.StatusAluno;

@FacesConverter(forClass=StatusAluno.class)
public class StatusAlunoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		StatusAluno retorno = null;
		
		if(value != null){
			StatusAlunoDao statusAlunoDao = new StatusAlunoDaoImpl();
			retorno = statusAlunoDao.findById(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((StatusAluno) value).getIdStatusAluno().toString();
		} return null;
	}

}
