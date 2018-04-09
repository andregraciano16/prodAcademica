package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.TipoDocenteDao;
import br.ucb.dao.impl.TipoDocenteDaoImpl;
import br.ucb.entity.TipoDocente;

@FacesConverter("tipoDocenteConverter")
public class TipoDocenteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoDocente retorno = null;
		
		if(value != null){
			TipoDocenteDao tipoDocenteDao = new TipoDocenteDaoImpl();
			retorno = tipoDocenteDao.findByKey(TipoDocente.class, new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((TipoDocente) value).getIdTipoDocente().toString();
		} 
		return null;
	}
	
}
