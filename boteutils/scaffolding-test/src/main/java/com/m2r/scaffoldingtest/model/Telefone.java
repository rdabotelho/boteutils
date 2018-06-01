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
import com.m2r.scaffoldingtest.enums.TipoTelefoneEnum;

@Entity
@Table(name = "TELEFONE")
@ClassScaffold(label="Telefone", icon="fa-file-o")
public class Telefone implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_TELEFONE", sequenceName = "SEQ_TELEFONE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TELEFONE")
	@Column(name = "ID_TELEFONE", nullable=false)
	private Long id;

	@Column(name = "EN_TIPO_TELEFONE", nullable=true, length=12)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo Telefone", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=12)
	private TipoTelefoneEnum tipoTelefone;
	
	@Column(name = "NU_DDI", nullable=true, length=6)
	@FieldScaffold(label="Ddi", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=6)
	private String ddi;
	
	@Column(name = "NU_DDD", nullable=true, length=3)
	@FieldScaffold(label="Ddd", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=3)
	private String ddd;
	
	@Column(name = "NU_TELEFONE", nullable=true, length=16)
	@FieldScaffold(label="Telefone", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=16)
	private String telefone;
	
	@Column(name = "NU_RAMAL", nullable=true, length=10)
	@FieldScaffold(label="Ramal", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=10)
	private String ramal;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTelefoneEnum getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefoneEnum tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	
	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}
	
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
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
		   Telefone other = (Telefone) obj;
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
