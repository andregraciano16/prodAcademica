
package br.ucb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Historico")
public class Historico extends EntidadeBase{


	private static final long serialVersionUID = -8742364435906286825L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historico")
	private Integer idHistorico;

	@Column(name = "dataAlteracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	
	@Column(name = "alteracao")
	private String alteracao;

	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name = "id_docente")
	private Docente docente;

	@ManyToOne
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	@ManyToOne
	@JoinColumn(name = "id_projeto")
	private Projeto projeto;

	public Integer getIdHistorico() {
		return this.idHistorico;
	}

	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(String alteracao) {
		this.alteracao = alteracao;
	}

	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Historico) {
			Historico outroHistorico = (Historico) obj;
			if (outroHistorico.getDataAlteracao().equals(this.getDataAlteracao()) 
					&& outroHistorico.getAluno().equals(this.getAluno())
					&& outroHistorico.getDocente().equals(this.getDocente())
					&& outroHistorico.getProducaoAcademica().equals(this.getProducaoAcademica())
					&& outroHistorico.getProjeto().equals(this.getProjeto())){
				flag = 1;
			}
		}
		if (flag == 1) {

			return true;

		} else {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Historico))
				return false;
			Historico other = (Historico) obj;
			if (idHistorico == null) {
				if (other.idHistorico != null)
					return false;
			} else if (!idHistorico.equals(other.idHistorico))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getDataAlteracao().hashCode();
	}

}

