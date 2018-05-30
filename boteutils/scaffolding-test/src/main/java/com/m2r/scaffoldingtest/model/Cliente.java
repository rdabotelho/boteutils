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

import com.m2r.scaffoldingtest.enums.CapacidadeCivelEnum;
import com.m2r.scaffoldingtest.enums.EstadoCivilEnum;
import com.m2r.scaffoldingtest.enums.GrauInstrucaoEnum;
import com.m2r.scaffoldingtest.enums.LogicoEnum;
import com.m2r.scaffoldingtest.enums.ProdutosEnum;
import com.m2r.scaffoldingtest.enums.SexoEnum;
import com.m2r.scaffoldingtest.enums.TipoContaEnum;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements BaseModel<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	@Column(name = "ID_CLIENTE")
	private Long id;

	@Column(name = "POTENCIAL", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum potencial;
	
	@Column(name = "NOME_COMPLETO", nullable=true, length=60)
	private String nomeCompleto;
	
	@Column(name = "NOME_ABREVIADO", nullable=true, length=30)
	private String nomeAbreviado;
	
	@Column(name = "SEXO", nullable=true)
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	@Column(name = "GRAU_INSTRUCAO", nullable=true)
	@Enumerated(EnumType.STRING)
	private GrauInstrucaoEnum grauInstrucao;
	
	@Column(name = "ESTADO_CIVIL", nullable=true)
	@Enumerated(EnumType.STRING)
	private EstadoCivilEnum estadoCivil;
	
	@Column(name = "NASCIMENTO", nullable=true)
	private LocalDate nascimento;
	
	@Column(name = "CAPACIDADE_CIVIL", nullable=true)
	@Enumerated(EnumType.STRING)
	private CapacidadeCivelEnum capacidadeCivil;
	
	@Column(name = "NACIONALIDADE", nullable=true, length=30)
	private String nacionalidade;
	
	@Column(name = "ESTADO", nullable=true, length=2)
	private String estado;
	
	@Column(name = "CIDADE", nullable=true, length=60)
	private String cidade;
	
	@Column(name = "AUTORIZA_CONSULTA_S_C_R", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum autorizaConsultaSCR;
	
	@Column(name = "PEP", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum pep;
	
	@Column(name = "PEP_RELACIONADO", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum pepRelacionado;
	
	@Column(name = "TIPO_CONTA", nullable=true)
	@Enumerated(EnumType.STRING)
	private TipoContaEnum tipoConta;
	
	@Column(name = "POSSUE_RENDA", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum possueRenda;
	
	@Column(name = "SERVIDOR_PUBLICO", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum servidorPublico;
	
	@Column(name = "NECESSIDADE_ESPECIAIS", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum necessidadeEspeciais;
	
	@Column(name = "SITUACAO_ESPECIAL", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum situacaoEspecial;
	
	@Column(name = "PROPOSITO_RELACAO_NEGOCIO", nullable=true, length=60)
	private String propositoRelacaoNegocio;
	
	@Column(name = "PRESTACAO_GARANTIA", nullable=true, length=60)
	private String prestacaoGarantia;
	
	@Column(name = "PRODUTOS_SELECIONADOS", nullable=true)
	@Enumerated(EnumType.STRING)
	private ProdutosEnum produtosSelecionados;
	
	@Column(name = "ATIVIDADE", nullable=true, length=10)
	private String atividade;
	
	@Column(name = "GRAU_INTERESSE", nullable=true)
	private Integer grauInteresse;
	
	@Column(name = "NOME_MAE", nullable=true, length=60)
	private String nomeMae;
	
	@Column(name = "NOME_PAI", nullable=true, length=60)
	private String nomePai;
	
	@Column(name = "POLITICAMENTE_EXPOSTA", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum politicamenteExposta;
	
	@Column(name = "TITULAR", nullable=true)
	@Enumerated(EnumType.STRING)
	private LogicoEnum titular;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LogicoEnum getPotencial() {
		return potencial;
	}

	public void setPotencial(LogicoEnum potencial) {
		this.potencial = potencial;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}
	
	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	
	public GrauInstrucaoEnum getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(GrauInstrucaoEnum grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}
	
	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	
	public CapacidadeCivelEnum getCapacidadeCivil() {
		return capacidadeCivil;
	}

	public void setCapacidadeCivil(CapacidadeCivelEnum capacidadeCivil) {
		this.capacidadeCivil = capacidadeCivil;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public LogicoEnum getAutorizaConsultaSCR() {
		return autorizaConsultaSCR;
	}

	public void setAutorizaConsultaSCR(LogicoEnum autorizaConsultaSCR) {
		this.autorizaConsultaSCR = autorizaConsultaSCR;
	}
	
	public LogicoEnum getPep() {
		return pep;
	}

	public void setPep(LogicoEnum pep) {
		this.pep = pep;
	}
	
	public LogicoEnum getPepRelacionado() {
		return pepRelacionado;
	}

	public void setPepRelacionado(LogicoEnum pepRelacionado) {
		this.pepRelacionado = pepRelacionado;
	}
	
	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public LogicoEnum getPossueRenda() {
		return possueRenda;
	}

	public void setPossueRenda(LogicoEnum possueRenda) {
		this.possueRenda = possueRenda;
	}
	
	public LogicoEnum getServidorPublico() {
		return servidorPublico;
	}

	public void setServidorPublico(LogicoEnum servidorPublico) {
		this.servidorPublico = servidorPublico;
	}
	
	public LogicoEnum getNecessidadeEspeciais() {
		return necessidadeEspeciais;
	}

	public void setNecessidadeEspeciais(LogicoEnum necessidadeEspeciais) {
		this.necessidadeEspeciais = necessidadeEspeciais;
	}
	
	public LogicoEnum getSituacaoEspecial() {
		return situacaoEspecial;
	}

	public void setSituacaoEspecial(LogicoEnum situacaoEspecial) {
		this.situacaoEspecial = situacaoEspecial;
	}
	
	public String getPropositoRelacaoNegocio() {
		return propositoRelacaoNegocio;
	}

	public void setPropositoRelacaoNegocio(String propositoRelacaoNegocio) {
		this.propositoRelacaoNegocio = propositoRelacaoNegocio;
	}
	
	public String getPrestacaoGarantia() {
		return prestacaoGarantia;
	}

	public void setPrestacaoGarantia(String prestacaoGarantia) {
		this.prestacaoGarantia = prestacaoGarantia;
	}
	
	public ProdutosEnum getProdutosSelecionados() {
		return produtosSelecionados;
	}

	public void setProdutosSelecionados(ProdutosEnum produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}
	
	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	
	public Integer getGrauInteresse() {
		return grauInteresse;
	}

	public void setGrauInteresse(Integer grauInteresse) {
		this.grauInteresse = grauInteresse;
	}
	
	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	
	public LogicoEnum getPoliticamenteExposta() {
		return politicamenteExposta;
	}

	public void setPoliticamenteExposta(LogicoEnum politicamenteExposta) {
		this.politicamenteExposta = politicamenteExposta;
	}
	
	public LogicoEnum getTitular() {
		return titular;
	}

	public void setTitular(LogicoEnum titular) {
		this.titular = titular;
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
		   Cliente other = (Cliente) obj;
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
