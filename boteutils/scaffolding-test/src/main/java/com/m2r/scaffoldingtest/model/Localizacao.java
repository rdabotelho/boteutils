package com.m2r.scaffoldingtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.m2r.scaffolding.utils.ClassScaffold;
import com.m2r.scaffolding.utils.FieldScaffold;
import com.m2r.scaffolding.utils.ScaffoldModel;
import com.m2r.scaffoldingtest.enums.LogicoEnum;
import com.m2r.scaffoldingtest.enums.TipoComprovanteEnum;
import com.m2r.scaffoldingtest.enums.TipoEnderecoEnum;

@Entity
@Table(name = "LOCALIZACAO")
@ClassScaffold(label="Endereco", icon="fa-file-o")
public class Localizacao implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_LOCALIZACAO", sequenceName = "SEQ_LOCALIZACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOCALIZACAO")
	@Column(name = "ID_LOCALIZACAO", nullable=false)
	private Long id;

	@Column(name = "EN_CONSULTA_CEP", nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Consulta Cep", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum consultaCep;
	
	@Column(name = "EN_INDICATIVO_ZONA_URBANA", nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Indicativo Zona Urbana", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum indicativoZonaUrbana;
	
	@Column(name = "EN_TIPO_ENDERECO", nullable=false, length=16)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo Endereco", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=16)
	private TipoEnderecoEnum tipoEndereco;
	
	@Column(name = "EN_TIPO_COMPROVANTE", nullable=false, length=28)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo Comprovante", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=28)
	private TipoComprovanteEnum tipoComprovante;
	
	@Column(name = "NU_CEP", nullable=false, length=8)
	@FieldScaffold(label="Cep", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private String cep;
	
	@Column(name = "TX_LOGRADOURO", nullable=false, length=60)
	@FieldScaffold(label="Logradouro", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String logradouro;
	
	@Column(name = "NU_ENDERECO", nullable=true, length=10)
	@FieldScaffold(label="Numero", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=10)
	private String numero;
	
	@Column(name = "NO_UF", nullable=false, length=2)
	@FieldScaffold(label="Uf", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=2)
	private String uf;
	
	@Column(name = "NO_CIDADE", nullable=false, length=60)
	@FieldScaffold(label="Cidade", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String cidade;
	
	@Column(name = "TX_COMPLEMENTO", nullable=true, length=60)
	@FieldScaffold(label="Complemento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String complemento;
	
	@Column(name = "TX_PERIMETRO", nullable=true, length=60)
	@FieldScaffold(label="Perimetro", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String perimetro;
	
	@Column(name = "NO_BAIRRO", nullable=false, length=60)
	@FieldScaffold(label="Bairro", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String bairro;
	
	@Column(name = "NU_TEMPO_UTILIZACAO", nullable=false)
	@FieldScaffold(label="Tempo Utilizacao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Integer tempoUtilizacao;
	
	@Column(name = "EN_ENDERECO_CORRESPONDENCIA", nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Endereco Correspondencia", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum enderecoCorrespondencia;
	
	@Column(name = "EN_IMOVEL_PROPRIO", nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Imovel Proprio", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum imovelProprio;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LogicoEnum getConsultaCep() {
		return consultaCep;
	}

	public void setConsultaCep(LogicoEnum consultaCep) {
		this.consultaCep = consultaCep;
	}
	
	public LogicoEnum getIndicativoZonaUrbana() {
		return indicativoZonaUrbana;
	}

	public void setIndicativoZonaUrbana(LogicoEnum indicativoZonaUrbana) {
		this.indicativoZonaUrbana = indicativoZonaUrbana;
	}
	
	public TipoEnderecoEnum getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	
	public TipoComprovanteEnum getTipoComprovante() {
		return tipoComprovante;
	}

	public void setTipoComprovante(TipoComprovanteEnum tipoComprovante) {
		this.tipoComprovante = tipoComprovante;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getPerimetro() {
		return perimetro;
	}

	public void setPerimetro(String perimetro) {
		this.perimetro = perimetro;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public Integer getTempoUtilizacao() {
		return tempoUtilizacao;
	}

	public void setTempoUtilizacao(Integer tempoUtilizacao) {
		this.tempoUtilizacao = tempoUtilizacao;
	}
	
	public LogicoEnum getEnderecoCorrespondencia() {
		return enderecoCorrespondencia;
	}

	public void setEnderecoCorrespondencia(LogicoEnum enderecoCorrespondencia) {
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}
	
	public LogicoEnum getImovelProprio() {
		return imovelProprio;
	}

	public void setImovelProprio(LogicoEnum imovelProprio) {
		this.imovelProprio = imovelProprio;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).
			       append(id).
			       toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		   if (obj == null) { return false; }
		   if (obj == this) { return true; }
		   if (obj.getClass() != getClass()) {
		     return false;
		   }
		   Localizacao other = (Localizacao) obj;
		   return new EqualsBuilder()
		                 .append(id, other.id)
		                 .isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).
			       append("id", id).
			       toString();
	}

}
