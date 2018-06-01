package com.m2r.scaffoldingtest.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.m2r.scaffolding.utils.ClassScaffold;
import com.m2r.scaffolding.utils.FieldScaffold;
import com.m2r.scaffolding.utils.ScaffoldModel;
import com.m2r.scaffoldingtest.enums.CapacidadeCivelEnum;
import com.m2r.scaffoldingtest.enums.EstadoCivilEnum;
import com.m2r.scaffoldingtest.enums.GrauInstrucaoEnum;
import com.m2r.scaffoldingtest.enums.LogicoEnum;
import com.m2r.scaffoldingtest.enums.ProdutosEnum;
import com.m2r.scaffoldingtest.enums.SexoEnum;
import com.m2r.scaffoldingtest.enums.TipoContaEnum;

@Entity
@Table(name = "CLIENTE")
@ClassScaffold(label="Cliente", icon="fa-file-o")
public class Cliente implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	@Column(name = "ID_CLIENTE", nullable=false)
	private Long id;

	@Version
	@Column(name = "NU_VERSAO", nullable=true)
	@FieldScaffold(label="Versao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Long versao;
	
	@Column(name = "TX_UUID", nullable=true, length=40)
	@FieldScaffold(label="Uuid", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=40)
	private String uuid;
	
	@Column(name = "EN_POTENCIAL", nullable=false, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Potencial", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum potencial;
	
	@Column(name = "NO_COMPLETO", nullable=false, length=60)
	@FieldScaffold(label="Nome Completo", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String nomeCompleto;
	
	@Column(name = "NO_ABREVIADO", nullable=true, length=30)
	@FieldScaffold(label="Nome Abreviado", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=30)
	private String nomeAbreviado;
	
	@Column(name = "EN_SEXO", nullable=true, length=14)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Sexo", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=14)
	private SexoEnum sexo;
	
	@Column(name = "EN_GRAU_INSTRUCAO", nullable=true, length=34)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Grau Instrucao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=34)
	private GrauInstrucaoEnum grauInstrucao;
	
	@Column(name = "EN_ESTADO_CIVIL", nullable=true, length=18)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Estado Civil", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=18)
	private EstadoCivilEnum estadoCivil;
	
	@Column(name = "DT_NASCIMENTO", nullable=true)
	@FieldScaffold(label="Nascimento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private LocalDate nascimento;
	
	@Column(name = "EN_CAPACIDADE_CIVIL", nullable=true, length=26)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Capacidade Civil", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=26)
	private CapacidadeCivelEnum capacidadeCivil;
	
	@Column(name = "TX_NACIONALIDADE", nullable=true, length=30)
	@FieldScaffold(label="Nacionalidade", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=30)
	private String nacionalidade;
	
	@Column(name = "TX_ESTADO", nullable=true, length=2)
	@FieldScaffold(label="Estado", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=2)
	private String estado;
	
	@Column(name = "TX_CIDADE", nullable=true, length=60)
	@FieldScaffold(label="Cidade", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String cidade;
	
	@Column(name = "EN_AUTORIZA_CONSULTA_SCR", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Autoriza Consulta Scr", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum autorizaConsultaScr;
	
	@Column(name = "EN_PEP", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Pep", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum pep;
	
	@Column(name = "EN_PEP_RELACIONADO", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Pep Relacionado", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum pepRelacionado;
	
	@Column(name = "EN_TIPO_CONTA", nullable=true, length=17)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo Conta", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=17)
	private TipoContaEnum tipoConta;
	
	@Column(name = "EN_POSSUE_RENDA", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Possue Renda", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum possueRenda;
	
	@Column(name = "EN_SERVIDOR_PUBLICO", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Servidor Publico", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum servidorPublico;
	
	@Column(name = "EN_NECESSIDADE_ESPECIAIS", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Necessidade Especiais", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum necessidadeEspeciais;
	
	@Column(name = "EN_SITUACAO_ESPECIAL", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Situacao Especial", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum situacaoEspecial;
	
	@Column(name = "TX_PROPOSITO_RELACAO_NEGOCIO", nullable=true, length=60)
	@FieldScaffold(label="Proposito Relacao Negocio", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String propositoRelacaoNegocio;
	
	@Column(name = "TX_PRESTACAO_GARANTIA", nullable=true, length=60)
	@FieldScaffold(label="Prestacao Garantia", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String prestacaoGarantia;
	
	@Column(name = "EN_PRODUTOS_SELECIONADOS", nullable=true, length=23)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Produtos Selecionados", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=23)
	private ProdutosEnum produtosSelecionados;
	
	@Column(name = "TX_ATIVIDADE", nullable=true, length=10)
	@FieldScaffold(label="Atividade", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=10)
	private String atividade;
	
	@Column(name = "NU_GRAU_INTERESSE", nullable=true)
	@FieldScaffold(label="Grau Interesse", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Integer grauInteresse;
	
	@Column(name = "NO_MAE", nullable=true, length=60)
	@FieldScaffold(label="Nome Mae", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String nomeMae;
	
	@Column(name = "NO_PAI", nullable=true, length=60)
	@FieldScaffold(label="Nome Pai", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=60)
	private String nomePai;
	
	@Column(name = "EN_POLITICAMENTE_EXPOSTA", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Politicamente Exposta", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum politicamenteExposta;
	
	@Column(name = "EN_TITULAR", nullable=true, length=8)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Titular", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=8)
	private LogicoEnum titular;
	
    @ManyToOne
	@FieldScaffold(label="Carteira", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Carteira carteira;
    
    @OneToOne(mappedBy="cliente")
    private DadosCaptura dadosCaptura;
    
	@OneToMany
	@FieldScaffold(label="Identificacoes", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private List<Identificacao> identificacoes;
	
    @OneToOne
	@FieldScaffold(label="Referencia", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Referencia referencia;
	
	@OneToMany
	@FieldScaffold(label="Telefones", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private List<Telefone> telefones;
	
	@OneToMany
	@FieldScaffold(label="Enderecos", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private List<Localizacao> enderecos;
	
    @OneToOne
	@FieldScaffold(label="Enquadramento Contabil", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private EnquadramentoContabil enquadramentoContabil;
	
    @OneToOne
	@FieldScaffold(label="Renda", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Renda renda;
	
    @OneToOne
	@FieldScaffold(label="Ocupacao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Ocupacao ocupacao;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	
	public LogicoEnum getAutorizaConsultaScr() {
		return autorizaConsultaScr;
	}

	public void setAutorizaConsultaScr(LogicoEnum autorizaConsultaScr) {
		this.autorizaConsultaScr = autorizaConsultaScr;
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
	
	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}
	
	public DadosCaptura getDadosCaptura() {
		return dadosCaptura;
	}

	public void setDadosCaptura(DadosCaptura dadosCaptura) {
		this.dadosCaptura = dadosCaptura;
	}
	
	public List<Identificacao> getIdentificacoes() {
		return identificacoes;
	}

	public void setIdentificacoes(List<Identificacao> identificacoes) {
		this.identificacoes = identificacoes;
	}
	
	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<Localizacao> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Localizacao> enderecos) {
		this.enderecos = enderecos;
	}
	
	public EnquadramentoContabil getEnquadramentoContabil() {
		return enquadramentoContabil;
	}

	public void setEnquadramentoContabil(EnquadramentoContabil enquadramentoContabil) {
		this.enquadramentoContabil = enquadramentoContabil;
	}
	
	public Renda getRenda() {
		return renda;
	}

	public void setRenda(Renda renda) {
		this.renda = renda;
	}
	
	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
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
