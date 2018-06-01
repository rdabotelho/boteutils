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
import com.m2r.scaffoldingtest.enums.ConceitoPracaEnum;
import com.m2r.scaffoldingtest.enums.HabitoPagamentoEnum;

@Entity
@Table(name = "CONCEITO_PESSOA")
@ClassScaffold(label="Conceito Pessoa", icon="fa-file-o")
public class ConceitoPessoa implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_CONCEITO_PESSOA", sequenceName = "SEQ_CONCEITO_PESSOA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONCEITO_PESSOA")
	@Column(name = "ID_CONCEITO_PESSOA", nullable=false)
	private Long id;

	@Column(name = "DT_DATA_REFERENCIA", nullable=true)
	@FieldScaffold(label="Data Referencia", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate dataReferencia;
	
	@Column(name = "EN_CONCEITO_PRACA", nullable=true, length=13)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Conceito Praca", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=13)
	private ConceitoPracaEnum conceitoPraca;
	
	@Column(name = "EN_HABITO_PAGAMENTO", nullable=true, length=20)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Habito Pagamento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private HabitoPagamentoEnum habitoPagamento;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(LocalDate dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
	
	public ConceitoPracaEnum getConceitoPraca() {
		return conceitoPraca;
	}

	public void setConceitoPraca(ConceitoPracaEnum conceitoPraca) {
		this.conceitoPraca = conceitoPraca;
	}
	
	public HabitoPagamentoEnum getHabitoPagamento() {
		return habitoPagamento;
	}

	public void setHabitoPagamento(HabitoPagamentoEnum habitoPagamento) {
		this.habitoPagamento = habitoPagamento;
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
		   ConceitoPessoa other = (ConceitoPessoa) obj;
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
