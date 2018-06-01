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
import com.m2r.scaffoldingtest.enums.CategoriaEnqContabilEnum;
import com.m2r.scaffoldingtest.enums.EnquadramentoEnum;
import com.m2r.scaffoldingtest.enums.NaturezaEnqContabilEnum;

@Entity
@Table(name = "ENQUADRAMENTO_CONTABIL")
@ClassScaffold(label="Enquadramento Contabil", icon="fa-file-o")
public class EnquadramentoContabil implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ENQUADRAMENTO_CONTABIL", sequenceName = "SEQ_ENQUADRAMENTO_CONTABIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENQUADRAMENTO_CONTABIL")
	@Column(name = "ID_ENQUADRAMENTO_CONTABIL", nullable=false)
	private Long id;

	@Column(name = "EN_CATEGORIA", nullable=false, length=20)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Categoria", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private CategoriaEnqContabilEnum categoria;
	
	@Column(name = "EN_NATUREZA", nullable=false, length=28)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Natureza", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=28)
	private NaturezaEnqContabilEnum natureza;
	
	@Column(name = "EN_ENQUADRAMENTO", nullable=false, length=25)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Enquadramento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=25)
	private EnquadramentoEnum Enquadramento;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoriaEnqContabilEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnqContabilEnum categoria) {
		this.categoria = categoria;
	}
	
	public NaturezaEnqContabilEnum getNatureza() {
		return natureza;
	}

	public void setNatureza(NaturezaEnqContabilEnum natureza) {
		this.natureza = natureza;
	}
	
	public EnquadramentoEnum getEnquadramento() {
		return Enquadramento;
	}

	public void setEnquadramento(EnquadramentoEnum Enquadramento) {
		this.Enquadramento = Enquadramento;
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
		   EnquadramentoContabil other = (EnquadramentoContabil) obj;
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
