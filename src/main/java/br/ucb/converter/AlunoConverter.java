package br.ucb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.dao.AlunoDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.entity.Aluno;



@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Aluno retorno = null;

		if (value != null) {
			AlunoDao docenteDao = new AlunoDaoImpl();
			retorno = docenteDao.findById(new Integer(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Aluno) value).getIdAluno().toString();
		}
		return null;
	}
}
