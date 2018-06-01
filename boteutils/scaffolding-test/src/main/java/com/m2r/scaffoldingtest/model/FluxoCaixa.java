package com.m2r.scaffoldingtest.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "FLUXO_CAIXA")
@ClassScaffold(label="Fluxo Caixa", icon="fa-file-o")
public class FluxoCaixa implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_FLUXO_CAIXA", sequenceName = "SEQ_FLUXO_CAIXA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLUXO_CAIXA")
	@Column(name = "ID_FLUXO_CAIXA", nullable=false)
	private Long id;

	@Column(name = "VL_RECEITA_OPERACIONAL", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Receita Operacional", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal receitaOperacional = BigDecimal.ZERO;
	
	@Column(name = "VL_RECEITA_NAO_OPERACIONAL", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Receita Nao Operacional", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal receitaNaoOperacional = BigDecimal.ZERO;
	
	@Column(name = "VL_CUSTO_MERCADORIA", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Custo Mercadoria", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal custoMercadoria = BigDecimal.ZERO;
	
	@Column(name = "VL_PAGAMENTO_PESSOAL", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Pagamento Pessoal", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal pagamentoPessoal = BigDecimal.ZERO;
	
	@Column(name = "VL_TRANSPORTE", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Transporte", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal transporte = BigDecimal.ZERO;
	
	@Column(name = "VL_AGUA_LUZ_TELEFONE", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Agua Luz Telefone", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal aguaLuzTelefone = BigDecimal.ZERO;
	
	@Column(name = "VL_TAXA_ALUGUEL", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Taxa Aluguel", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal taxaAluguel = BigDecimal.ZERO;
	
	@Column(name = "VL_OUTROS_CUSTOS", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Outros Custos", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal outrosCustos = BigDecimal.ZERO;
	
	@Column(name = "VL_OUTRAS_DESPESAS", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Outras Despesas", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal outrasDespesas = BigDecimal.ZERO;
	
	@Column(name = "VL_OUTROS_PAGAMENTOS", nullable=false, precision=8, scale=2)
	@FieldScaffold(label="Outros Pagamentos", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal outrosPagamentos = BigDecimal.ZERO;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getReceitaOperacional() {
		return receitaOperacional;
	}

	public void setReceitaOperacional(BigDecimal receitaOperacional) {
		this.receitaOperacional = receitaOperacional;
	}
	
	public BigDecimal getReceitaNaoOperacional() {
		return receitaNaoOperacional;
	}

	public void setReceitaNaoOperacional(BigDecimal receitaNaoOperacional) {
		this.receitaNaoOperacional = receitaNaoOperacional;
	}
	
	public BigDecimal getCustoMercadoria() {
		return custoMercadoria;
	}

	public void setCustoMercadoria(BigDecimal custoMercadoria) {
		this.custoMercadoria = custoMercadoria;
	}
	
	public BigDecimal getPagamentoPessoal() {
		return pagamentoPessoal;
	}

	public void setPagamentoPessoal(BigDecimal pagamentoPessoal) {
		this.pagamentoPessoal = pagamentoPessoal;
	}
	
	public BigDecimal getTransporte() {
		return transporte;
	}

	public void setTransporte(BigDecimal transporte) {
		this.transporte = transporte;
	}
	
	public BigDecimal getAguaLuzTelefone() {
		return aguaLuzTelefone;
	}

	public void setAguaLuzTelefone(BigDecimal aguaLuzTelefone) {
		this.aguaLuzTelefone = aguaLuzTelefone;
	}
	
	public BigDecimal getTaxaAluguel() {
		return taxaAluguel;
	}

	public void setTaxaAluguel(BigDecimal taxaAluguel) {
		this.taxaAluguel = taxaAluguel;
	}
	
	public BigDecimal getOutrosCustos() {
		return outrosCustos;
	}

	public void setOutrosCustos(BigDecimal outrosCustos) {
		this.outrosCustos = outrosCustos;
	}
	
	public BigDecimal getOutrasDespesas() {
		return outrasDespesas;
	}

	public void setOutrasDespesas(BigDecimal outrasDespesas) {
		this.outrasDespesas = outrasDespesas;
	}
	
	public BigDecimal getOutrosPagamentos() {
		return outrosPagamentos;
	}

	public void setOutrosPagamentos(BigDecimal outrosPagamentos) {
		this.outrosPagamentos = outrosPagamentos;
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
		   FluxoCaixa other = (FluxoCaixa) obj;
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
