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

import com.m2r.scaffoldingtest.enums.EstadoCivilEnum;

@Entity
@Table(name = "ASSESSOR")
public class Assessor implements BaseModel<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ASSESSOR", sequenceName = "SEQ_ASSESSOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ASSESSOR")
	@Column(name = "ID_ASSESSOR")
	private Long id;

	@Column(name = "NOME", nullable=true, length=60)
	private String nome;
	
	@Column(name = "CPF", nullable=true, length=11)
	private String cpf;
	
	@Column(name = "RG", nullable=true, length=10)
	private String rg;
	
	@Column(name = "TITULO_ELEITOR", nullable=true, length=20)
	private String tituloEleitor;
	
	@Column(name = "CTPS", nullable=true, length=20)
	private String ctps;
	
	@Column(name = "NASCIMENTO", nullable=true)
	private LocalDate nascimento;
	
	@Column(name = "NATURALIDADE", nullable=true)
	private String naturalidade;
	
	@Column(name = "NACIONALIDADE", nullable=true)
	private String nacionalidade;
	
	@Column(name = "ESTADO_CIVIL", nullable=true)
	@Enumerated(EnumType.STRING)
	private EstadoCivilEnum estadoCivil;
	
	@Column(name = "TELEFONE_FIXO", nullable=true, length=15)
	private String telefoneFixo;
	
	@Column(name = "TELEFONE_MOVEL", nullable=true, length=15)
	private String telefoneMovel;
	
	@Column(name = "REFERENCIA", nullable=true, length=30)
	private String referencia;
	
	@Column(name = "CEP", nullable=true, length=8)
	private String cep;
	
	@Column(name = "ENDERECO", nullable=true, length=60)
	private String endereco;
	
	@Column(name = "NUMERO", nullable=true, length=6)
	private String numero;
	
	@Column(name = "COMPLEMENTO", nullable=true, length=60)
	private String complemento;
	
	@Column(name = "CIDADE", nullable=true, length=60)
	private String cidade;
	
	@Column(name = "ESTADO", nullable=true, length=2)
	private String estado;
	
	@Column(name = "INICIO_CONTRATO", nullable=true)
	private LocalDate inicioContrato;
	
	@Column(name = "FIM_CONTRATO", nullable=true)
	private LocalDate fimContrato;
	
	@Column(name = "RENDIMENTO_BRUTO", nullable=true, precision=8, scale=2)
	private BigDecimal rendimentoBruto;
	
    @OneToOne
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
