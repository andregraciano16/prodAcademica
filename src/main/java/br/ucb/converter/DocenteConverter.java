package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.DocenteDao;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.entity.Docente;

@FacesConverter(forClass = Docente.class)
public class DocenteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Docente retorno = null;

		if (value != null) {
			DocenteDao docenteDao = new DocenteDaoImpl();
			retorno = docenteDao.findById(new Integer(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Docente) value).getIdDocente().toString();
		}
		return null;
	}

}
