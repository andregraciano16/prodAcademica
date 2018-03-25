package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent UIComponent, String obj) {
		if(obj != null && !obj.isEmpty()){
			obj = obj.replaceAll("[^0-9]", "");
		}
		return obj;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent UIComponent, Object obj) {
		return obj.toString();
	}
	

}
