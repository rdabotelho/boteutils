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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.m2r.scaffolding.utils.ClassScaffold;
import com.m2r.scaffolding.utils.FieldScaffold;
import com.m2r.scaffolding.utils.ScaffoldModel;
import com.m2r.scaffoldingtest.enums.EstadoCivilEnum;

@Entity
@Table(name = "ASSESSOR")
@ClassScaffold(label="Assessor", icon="fa-file-o")
public class Assessor implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ASSESSOR", sequenceName = "SEQ_ASSESSOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ASSESSOR")
	@Column(name = "ID_ASSESSOR", nullable=false)
	private Long id;

	@Column(name = "NO_ASSESSOR", nullable=false, length=60)
	@FieldScaffold(label="Nome", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String nome;
	
	@Column(name = "TX_CPF", nullable=false, length=11)
	@FieldScaffold(label="Cpf", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=11)
	private String cpf;
	
	@Column(name = "TX_RG", nullable=false, length=10)
	@FieldScaffold(label="Rg", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=10)
	private String rg;
	
	@Column(name = "TX_TITULO_ELEITOR", nullable=true, length=20)
	@FieldScaffold(label="Titulo Eleitor", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private String tituloEleitor;
	
	@Column(name = "TX_CTPS", nullable=true, length=20)
	@FieldScaffold(label="Ctps", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private String ctps;
	
	@Column(name = "DT_NASCIMENTO", nullable=false)
	@FieldScaffold(label="Nascimento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate nascimento;
	
	@Column(name = "TX_NATURALIDADE", nullable=false, length=20)
	@FieldScaffold(label="Naturalidade", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private String naturalidade;
	
	@Column(name = "TX_NACIONALIDADE", nullable=false, length=20)
	@FieldScaffold(label="Nacionalidade", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private String nacionalidade;
	
	@Column(name = "EN_ESTADO_CIVIL", nullable=false, length=5)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Estado Civil", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=5)
	private EstadoCivilEnum estadoCivil;
	
	@Column(name = "TX_TELEFONE_FIXO", nullable=true, length=15)
	@FieldScaffold(label="Telefone Fixo", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=15)
	private String telefoneFixo;
	
	@Column(name = "TX_TELEFONE_MOVEL", nullable=true, length=15)
	@FieldScaffold(label="Telefone Movel", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=15)
	private String telefoneMovel;
	
	@Column(name = "TX_REFERENCIA", nullable=true, length=30)
	@FieldScaffold(label="Referencia", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=30)
	private String referencia;
	
	@Column(name = "TX_CEP", nullable=true, length=8)
	@FieldScaffold(label="Cep", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private String cep;
	
	@Column(name = "TX_ENDERECO", nullable=true, length=60)
	@FieldScaffold(label="Endereco", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String endereco;
	
	@Column(name = "TX_NUMERO", nullable=true, length=6)
	@FieldScaffold(label="Numero", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=6)
	private String numero;
	
	@Column(name = "TX_COMPLEMENTO", nullable=true, length=60)
	@FieldScaffold(label="Complemento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String complemento;
	
	@Column(name = "TX_CIDADE", nullable=true, length=60)
	@FieldScaffold(label="Cidade", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String cidade;
	
	@Column(name = "TX_ESTADO", nullable=true, length=2)
	@FieldScaffold(label="Estado", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=2)
	private String estado;
	
	@Column(name = "DT_INICIO_CONTRATO", nullable=true)
	@FieldScaffold(label="Inicio Contrato", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate inicioContrato;
	
	@Column(name = "DT_FIM_CONTRATO", nullable=true)
	@FieldScaffold(label="Fim Contrato", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate fimContrato;
	
	@Column(name = "VL_RENDIMENTO_BRUTO", nullable=true, precision=8, scale=2)
	@FieldScaffold(label="Rendimento Bruto", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private BigDecimal rendimentoBruto;
	
    @OneToOne(mappedBy="assessor")
	@FieldScaffold(label="Carteira", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Carteira carteira;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}
	
	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}
	
	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	
	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	
	public String getTelefoneMovel() {
		return telefoneMovel;
	}

	public void setTelefoneMovel(String telefoneMovel) {
		this.telefoneMovel = telefoneMovel;
	}
	
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public LocalDate getInicioContrato() {
		return inicioContrato;
	}

	public void setInicioContrato(LocalDate inicioContrato) {
		this.inicioContrato = inicioContrato;
	}
	
	public LocalDate getFimContrato() {
		return fimContrato;
	}

	public void setFimContrato(LocalDate fimContrato) {
		this.fimContrato = fimContrato;
	}
	
	public BigDecimal getRendimentoBruto() {
		return rendimentoBruto;
	}

	public void setRendimentoBruto(BigDecimal rendimentoBruto) {
		this.rendimentoBruto = rendimentoBruto;
	}
	
	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
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
		   Assessor other = (Assessor) obj;
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
