package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.enums.NaturezaEnum;

@FacesConverter("naturezaConverter")
public class NaturezaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor != null && !valor.isEmpty()){
		    return NaturezaEnum.valueOf(valor);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(obj != null){
			return ((NaturezaEnum) obj).getDescricao();
		}
		return null;
	}

}
