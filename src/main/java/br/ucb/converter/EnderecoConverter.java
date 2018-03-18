package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.EnderecoDao;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.entity.Endereco;


@FacesConverter(forClass=Endereco.class)
public class EnderecoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Endereco retorno = null;
		
		if(value != null){
			EnderecoDao enderecoDao = new EnderecoDaoImpl();
			retorno = enderecoDao.findById(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null){
			return ((Endereco) value).getIdEndereco().toString();
		} return null;
	}
}
