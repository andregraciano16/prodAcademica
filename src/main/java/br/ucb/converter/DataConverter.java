package br.ucb.converter;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.util.DataUtil;

@FacesConverter("dataConverter")
public class DataConverter implements Converter{
	
	private static final String FORMATO_DATE = "dd/MM/yyyy";

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String obj) {
		if(obj != null && !obj.isEmpty()){
			return DataUtil.convertStringInDate(obj, FORMATO_DATE);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		Date obj = (Date)object;
		if(obj != null){
			return DataUtil.convertDateInString(obj, FORMATO_DATE);
		}
		return object.toString();
	}

}
