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
import com.m2r.scaffoldingtest.enums.LogicoEnum;

@Entity
@Table(name = "OCUPACAO")
@ClassScaffold(label="Ocupacao", icon="fa-file-o")
public class Ocupacao implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_OCUPACAO", sequenceName = "SEQ_OCUPACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OCUPACAO")
	@Column(name = "ID_OCUPACAO", nullable=false)
	private Long id;

	@Column(name = "CD_OCUPACAO", nullable=false, length=10)
	@FieldScaffold(label="Codigo Ocupacao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=10)
	private String codigoOcupacao;
	
	@Column(name = "EN_PRINCIPAL", nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Principal", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum principal;
	
	@Column(name = "EN_POSSUI_COMPROVANTE", nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Possui Comprovante", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum possuiComprovante;
	
	@Column(name = "DT_DATA_INICIO_OCUPACAO", nullable=false)
	@FieldScaffold(label="Data Inicio Ocupacao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate dataInicioOcupacao;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoOcupacao() {
		return codigoOcupacao;
	}

	public void setCodigoOcupacao(String codigoOcupacao) {
		this.codigoOcupacao = codigoOcupacao;
	}
	
	public LogicoEnum getPrincipal() {
		return principal;
	}

	public void setPrincipal(LogicoEnum principal) {
		this.principal = principal;
	}
	
	public LogicoEnum getPossuiComprovante() {
		return possuiComprovante;
	}

	public void setPossuiComprovante(LogicoEnum possuiComprovante) {
		this.possuiComprovante = possuiComprovante;
	}
	
	public LocalDate getDataInicioOcupacao() {
		return dataInicioOcupacao;
	}

	public void setDataInicioOcupacao(LocalDate dataInicioOcupacao) {
		this.dataInicioOcupacao = dataInicioOcupacao;
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
		   Ocupacao other = (Ocupacao) obj;
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
