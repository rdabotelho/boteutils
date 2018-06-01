package com.m2r.scaffoldingtest.model;

import java.math.BigDecimal;
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
import com.m2r.scaffoldingtest.enums.NaturezaRendaEnum;
import com.m2r.scaffoldingtest.enums.TipoComprovanteEnum;

@Entity
@Table(name = "RENDA")
@ClassScaffold(label="Renda", icon="fa-file-o")
public class Renda implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_RENDA", sequenceName = "SEQ_RENDA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RENDA")
	@Column(name = "ID_RENDA", nullable=false)
	private Long id;

	@Column(name = "EN_RENDA_PRINCIPAL", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Renda Principal", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum rendaPrincipal;
	
	@Column(name = "EN_NATUREZA", nullable=true, length=24)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Natureza", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=24)
	private NaturezaRendaEnum natureza;
	
	@Column(name = "EN_TIPO_COMPROVANTE", nullable=true, length=16)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo Comprovante", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=16)
	private TipoComprovanteEnum tipoComprovante;
	
	@Column(name = "DT_DATA_EMISSAO", nullable=true)
	@FieldScaffold(label="Data Emissao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate dataEmissao;
	
	@Column(name = "DT_INICIO_RENDA", nullable=true)
	@FieldScaffold(label="Inicio Renda", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate inicioRenda;
	
	@Column(name = "VL_VALOR_BRUTO", nullable=true, precision=8, scale=2)
	@FieldScaffold(label="Valor Bruto", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal valorBruto;
	
	@Column(name = "VL_VALOR_LIQUIDO", nullable=true, precision=8, scale=2)
	@FieldScaffold(label="Valor Liquido", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal valorLiquido;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LogicoEnum getRendaPrincipal() {
		return rendaPrincipal;
	}

	public void setRendaPrincipal(LogicoEnum rendaPrincipal) {
		this.rendaPrincipal = rendaPrincipal;
	}
	
	public NaturezaRendaEnum getNatureza() {
		return natureza;
	}

	public void setNatureza(NaturezaRendaEnum natureza) {
		this.natureza = natureza;
	}
	
	public TipoComprovanteEnum getTipoComprovante() {
		return tipoComprovante;
	}

	public void setTipoComprovante(TipoComprovanteEnum tipoComprovante) {
		this.tipoComprovante = tipoComprovante;
	}
	
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public LocalDate getInicioRenda() {
		return inicioRenda;
	}

	public void setInicioRenda(LocalDate inicioRenda) {
		this.inicioRenda = inicioRenda;
	}
	
	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}
	
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
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
		   Renda other = (Renda) obj;
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
