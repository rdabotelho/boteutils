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
import com.m2r.scaffoldingtest.enums.NaturezaReferenciaEnum;

@Entity
@Table(name = "REFERENCIA")
@ClassScaffold(label="Referencia", icon="fa-file-o")
public class Referencia implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_REFERENCIA", sequenceName = "SEQ_REFERENCIA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REFERENCIA")
	@Column(name = "ID_REFERENCIA", nullable=false)
	private Long id;

	@Column(name = "EN_NATUREZA", nullable=true, length=28)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Natureza", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=28)
	private NaturezaReferenciaEnum natureza;
	
	@Column(name = "NO_REFERENCIA", nullable=true, length=60)
	@FieldScaffold(label="Nome", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String nome;
	
	@Column(name = "NU_DDD", nullable=true, length=3)
	@FieldScaffold(label="Ddd", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=3)
	private String ddd;
	
	@Column(name = "NU_TELEFONE", nullable=true, length=15)
	@FieldScaffold(label="Telefone", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=15)
	private String telefone;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NaturezaReferenciaEnum getNatureza() {
		return natureza;
	}

	public void setNatureza(NaturezaReferenciaEnum natureza) {
		this.natureza = natureza;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		   Referencia other = (Referencia) obj;
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
