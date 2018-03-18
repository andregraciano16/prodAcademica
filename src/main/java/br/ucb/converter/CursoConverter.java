package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.CursoDao;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.entity.Curso;


@FacesConverter(forClass=Curso.class)
public class CursoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Curso retorno = null;
		
		if(value != null){
			CursoDao cursoDao = new CursoDaoImpl();
			retorno = cursoDao.findById(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((Curso) value).getIdCurso().toString();
		} return null;
	}
	
}
