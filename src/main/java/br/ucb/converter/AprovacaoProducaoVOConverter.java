package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.VO.AprovacaoProducaoVO;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;

@FacesConverter(forClass=AprovacaoProducaoVO.class)
public class AprovacaoProducaoVOConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		AprovacaoProducaoVO retorno = null;

		if (value != null) {
			ProducaoAcademicaDao producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
			retorno = producaoAcademicaDao.findByIdVO(new Integer(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((AprovacaoProducaoVO) value).getId().toString();
		}
		return null;
	}
}
