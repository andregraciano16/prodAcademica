package br.ucb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Projeto implements Serializable{

	private static final long serialVersionUID = -5861079694379582651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_projeto")
	private Integer idProjeto;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "orgaoFinanciador")
	private String orgaoFinanciador;
	
	@Column(name = "dataInicio")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name = "horasDedicadasSemana")
	private String horasDedicadasSemana;
	
	@ManyToOne
	@JoinColumn(name = "id_tipoProjeto")
	private TipoProjeto tipoProjeto;
	
	@ManyToOne
	@JoinColumn(name = "id_statusProjeto")
	private StatusProjeto statusProjeto;

	@ManyToOne
	@JoinColumn(name = "id_linhaPesquisa")
	private LinhaPesquisa linhaPesquisa;
	
	@ManyToOne
	@JoinColumn(name = "id_docente")
	private Docente docenteResponsavel;
	
	@ManyToOne
	@JoinColumn(name = "id_externo")
	private Externo externoResponsavel;
	
	@ManyToMany
	private List<Aluno> alunosParticipantes;
	
	@ManyToMany
	private List<Docente> docentesParticipantes;
	
	@ManyToMany
	private List<Externo> externoParticipantes;

	public Integer getIdProjeto() {
		return this.idProjeto;
	}

	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOrgaoFinanciador() {
		return orgaoFinanciador;
	}

	public void setOrgaoFinanciador(String orgaoFinanciador) {
		this.orgaoFinanciador = orgaoFinanciador;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public TipoProjeto getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(TipoProjeto tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

	public StatusProjeto getStatusProjeto() {
		return statusProjeto;
	}

	public void setStatusProjeto(StatusProjeto statusProjeto) {
		this.statusProjeto = statusProjeto;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}
	
	public Docente getDocenteResponsavel() {
		return docenteResponsavel;
	}

	public void setDocenteResponsavel(Docente docenteResponsavel) {
		this.docenteResponsavel = docenteResponsavel;
	}
	
	public List<Aluno> getAlunosParticipantes() {
		return alunosParticipantes;
	}

	public void setAlunosParticipantes(List<Aluno> alunosParticipantes) {
		this.alunosParticipantes = alunosParticipantes;
	}

	public List<Docente> getDocentesParticipantes() {
		return docentesParticipantes;
	}

	public void setDocentesParticipantes(List<Docente> docentesParticipantes) {
		this.docentesParticipantes = docentesParticipantes;
	}

	public String getHorasDedicadasSemana() {
		return horasDedicadasSemana;
	}

	public void setHorasDedicadasSemana(String horasDedicadasSemana) {
		this.horasDedicadasSemana = horasDedicadasSemana;
	}

	public Externo getExternoResponsavel() {
		return externoResponsavel;
	}

	public void setExternoResponsavel(Externo externoResponsavel) {
		this.externoResponsavel = externoResponsavel;
	}

	public List<Externo> getExternoParticipantes() {
		return externoParticipantes;
	}

	public void setExternoParticipantes(List<Externo> externoParticipantes) {
		this.externoParticipantes = externoParticipantes;
	}

	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Projeto) {
			Projeto outroProjeto = (Projeto) obj;
			if (outroProjeto.getDescricao().trim().equals(this.getDescricao().trim()) 
					&& outroProjeto.getNome().trim().equals(this.getNome().trim())
					&& outroProjeto.getOrgaoFinanciador().trim().equals(this.getOrgaoFinanciador().trim())
					&& outroProjeto.getLinhaPesquisa().equals(this.getLinhaPesquisa()) 
					&& outroProjeto.getTipoProjeto().equals(this.getTipoProjeto())) {
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
			if (!(obj instanceof Projeto))
				return false;
			Projeto other = (Projeto) obj;
			if (idProjeto == null) {
				if (other.idProjeto != null)
					return false;
			} else if (!idProjeto.equals(other.idProjeto))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getDescricao().hashCode();
	}

}
