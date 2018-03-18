package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.entity.ProducaoAcademica;

@FacesConverter(forClass=ProducaoAcademica.class)
public class ProducaoAcademicaConverter implements Converter{

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ProducaoAcademica retorno = null;

		if (value != null) {
			ProducaoAcademicaDao producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
			retorno = producaoAcademicaDao.findById(new Integer(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((ProducaoAcademica) value).getIdProducaoAcademica().toString();
		}
		return null;
	}

}

