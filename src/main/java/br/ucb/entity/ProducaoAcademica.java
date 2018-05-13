package br.ucb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProducaoAcademica extends EntidadeBase implements Comparable<ProducaoAcademica>{

	private static final long serialVersionUID = 5005969022033064082L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producaoAcademica")
	private Integer idProducaoAcademica;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "conceitoQualis")
	private String conceitoQualis;

	@Column(name = "arquivo")
	private String arquivo;

	@Column(name = "dataCadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "divulgacao")
	private Integer divulgacao; // DivulgacaoEnum

	@Column(name = "URL")
	private String URL;

	@Column(name = "observacao")
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "id_tipoProducao")
	private TipoProducao tipoProducao;

	@ManyToOne
	@JoinColumn(name = "id_linhaPesquisa")
	private LinhaPesquisa linhaPesquisa;

	@ManyToOne
	@JoinColumn(name = "id_statusProducao")
	private StatusProducao statusProducao;
	
	public Integer getDivulgacao() {
		return this.divulgacao;
	}

	public void setDivulgacao(Integer divulgacao) {
		this.divulgacao = divulgacao;
	}

	public String getURL() {
		return this.URL;
	}

	public void setURL(String uRL) {
		this.URL = uRL;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdProducaoAcademica() {
		return this.idProducaoAcademica;
	}

	public void setIdProducaoAcademica(Integer idProducaoAcademica) {
		this.idProducaoAcademica = idProducaoAcademica;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConceitoQualis() {
		return this.conceitoQualis;
	}

	public void setConceitoQualis(String conceitoQualis) {
		this.conceitoQualis = conceitoQualis;
	}

	public String getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoProducao getTipoProducao() {
		return this.tipoProducao;
	}

	public void setTipoProducao(TipoProducao tipoProducao) {
		this.tipoProducao = tipoProducao;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return this.linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

	public StatusProducao getStatusProducao() {
		return this.statusProducao;
	}

	public void setStatusProducao(StatusProducao statusProducao) {
		this.statusProducao = statusProducao;
	}
	
	@Override
	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof ProducaoAcademica) {
			ProducaoAcademica outroProducaoAcademica = (ProducaoAcademica) obj;
			if (outroProducaoAcademica.getDescricao().trim().equals(this.getDescricao().trim())) {
		//		flag = 1;
			}
		}
		if (flag == 1) {

			return true;

		} else {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof ProducaoAcademica))
				return false;
			ProducaoAcademica other = (ProducaoAcademica) obj;
			if (idProducaoAcademica == null) {
				if (other.idProducaoAcademica != null)
					return false;
			} else if (!idProducaoAcademica.equals(other.idProducaoAcademica))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getDescricao().hashCode();
	}

	@Override
	public int compareTo(ProducaoAcademica outro) {
		return getDataCadastro().compareTo(outro.getDataCadastro());
	}

}
