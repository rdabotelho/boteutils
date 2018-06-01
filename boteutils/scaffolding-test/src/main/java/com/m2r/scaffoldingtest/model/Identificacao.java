package com.m2r.scaffoldingtest.model;

import java.time.LocalDate;

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
import com.m2r.scaffoldingtest.enums.TipoIdentificacaoEnum;

@Entity
@Table(name = "IDENTIFICACAO")
@ClassScaffold(label="Identificacao", icon="fa-file-o")
public class Identificacao implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_IDENTIFICACAO", sequenceName = "SEQ_IDENTIFICACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDENTIFICACAO")
	@Column(name = "ID_IDENTIFICACAO", nullable=false)
	private Long id;

	@Column(name = "NU_IDENTIFICACAO", nullable=true, length=20)
	@FieldScaffold(label="Numero", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private String numero;
	
	@Column(name = "DT_DATA_EXPEDICAO", nullable=true)
	@FieldScaffold(label="Data Expedicao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate dataExpedicao;
	
	@Column(name = "NU_VIA", nullable=true, length=20)
	@FieldScaffold(label="Numero Via", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private String numeroVia;
	
	@Column(name = "NO_ORIGEM_DOCUMENTO", nullable=true, length=20)
	@FieldScaffold(label="Origem Documento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private String origemDocumento;
	
	@Column(name = "NO_ORGAO_EMISSOR", nullable=true, length=20)
	@FieldScaffold(label="Orgao Emissor", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private String orgaoEmissor;
	
	@Column(name = "NO_ESTADO_EMISSOR", nullable=true, length=2)
	@FieldScaffold(label="Estado Emissor", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=2)
	private String estadoEmissor;
	
	@Column(name = "NO_CIDADE_EMISSOR", nullable=true, length=60)
	@FieldScaffold(label="Cidade Emissor", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String cidadeEmissor;
	
	@Column(name = "EN_TIPO_IDENTIFICACAO", nullable=true, length=9)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo Identificacao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=9)
	private TipoIdentificacaoEnum tipoIdentificacao;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public LocalDate getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(LocalDate dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
	
	public String getNumeroVia() {
		return numeroVia;
	}

	public void setNumeroVia(String numeroVia) {
		this.numeroVia = numeroVia;
	}
	
	public String getOrigemDocumento() {
		return origemDocumento;
	}

	public void setOrigemDocumento(String origemDocumento) {
		this.origemDocumento = origemDocumento;
	}
	
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}
	
	public String getEstadoEmissor() {
		return estadoEmissor;
	}

	public void setEstadoEmissor(String estadoEmissor) {
		this.estadoEmissor = estadoEmissor;
	}
	
	public String getCidadeEmissor() {
		return cidadeEmissor;
	}

	public void setCidadeEmissor(String cidadeEmissor) {
		this.cidadeEmissor = cidadeEmissor;
	}
	
	public TipoIdentificacaoEnum getTipoIdentificacao() {
		return tipoIdentificacao;
	}

	public void setTipoIdentificacao(TipoIdentificacaoEnum tipoIdentificacao) {
		this.tipoIdentificacao = tipoIdentificacao;
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
		   Identificacao other = (Identificacao) obj;
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
