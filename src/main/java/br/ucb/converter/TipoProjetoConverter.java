
package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.TipoProjetoDao;
import br.ucb.dao.impl.TipoProjetoDaoImpl;
import br.ucb.entity.TipoProjeto;


@FacesConverter(forClass=TipoProjeto.class)
public class TipoProjetoConverter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoProjeto retorno = null;
		
		if(value != null){
			TipoProjetoDao tipoProjetoDao = new TipoProjetoDaoImpl();
			retorno = tipoProjetoDao.findById(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((TipoProjeto) value).getIdTipoProjeto().toString();
		} return null;
	}


}