package com.m2r.boteutils.flatfile.model;

import static com.m2r.flatfile.enumeration.FlatFieldTypeEnum.INTEGER;

import java.math.BigDecimal;
import java.util.Date;

import com.m2r.flatfile.annotation.FlatField;
import com.m2r.flatfile.enumeration.FlatFieldTypeEnum;

public class DetailRetorno {

	@FlatField(begin = 0, end = 1, converter = INTEGER, id = "7")
	private Integer id;

	@FlatField(begin = 63, end = 80)
	private String nossoNumero;

	@FlatField(begin = 80, end = 81, converter = INTEGER)
	private Integer tipoCobranca;

	@FlatField(begin = 86, end = 88)
	private String naturezaRecebimento;

	@FlatField(begin = 88, end = 91)
	private String prefixoTitulo;

	@FlatField(begin = 91, end = 94)
	private String variacaoCarteira;

	@FlatField(begin = 106, end = 108)
	private String carteira;

	@FlatField(begin = 110, end = 116, converter = FlatFieldTypeEnum.DATE)
	private Date dataLiquidacao;

	@FlatField(begin = 116, end = 126)
	private String numeroTitulo;

	@FlatField(begin = 146, end = 152, converter = FlatFieldTypeEnum.DATE)
	private Date dataVencimento;

	@FlatField(begin = 152, end = 165, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorTitulo;

	@FlatField(begin = 165, end = 168)
	private String bancoRecebedor;

	@FlatField(begin = 168, end = 173)
	private String agenciaRecebedora;

	@FlatField(begin = 173, end = 175, converter = INTEGER)
	private Integer especie;

	@FlatField(begin = 175, end = 181, converter = FlatFieldTypeEnum.DATE)
	private Date dataCredito;

	@FlatField(begin = 253, end = 266, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorRecebidoParcial;

	@FlatField(begin = 305, end = 318, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorLancamento;

	@FlatField(begin = 320, end = 332, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorAjuste;

	@FlatField(begin = 394, end = 400, converter = INTEGER)
	private Integer sequencialRegistro;

	public Integer getId() {

		return this.id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getNossoNumero() {

		return this.nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {

		this.nossoNumero = nossoNumero;
	}

	public Integer getTipoCobranca() {

		return this.tipoCobranca;
	}

	public void setTipoCobranca(Integer tipoCobranca) {

		this.tipoCobranca = tipoCobranca;
	}

	public String getNaturezaRecebimento() {

		return this.naturezaRecebimento;
	}

	public void setNaturezaRecebimento(String naturezaRecebimento) {

		this.naturezaRecebimento = naturezaRecebimento;
	}

	public String getPrefixoTitulo() {

		return this.prefixoTitulo;
	}

	public void setPrefixoTitulo(String prefixoTitulo) {

		this.prefixoTitulo = prefixoTitulo;
	}

	public String getVariacaoCarteira() {

		return this.variacaoCarteira;
	}

	public void setVariacaoCarteira(String variacaoCarteira) {

		this.variacaoCarteira = variacaoCarteira;
	}

	public String getCarteira() {

		return this.carteira;
	}

	public void setCarteira(String carteira) {

		this.carteira = carteira;
	}

	public Date getDataLiquidacao() {

		return this.dataLiquidacao;
	}

	public void setDataLiquidacao(Date dataLiquidacao) {

		this.dataLiquidacao = dataLiquidacao;
	}

	public String getNumeroTitulo() {

		return this.numeroTitulo;
	}

	public void setNumeroTitulo(String numeroTitulo) {

		this.numeroTitulo = numeroTitulo;
	}

	public Date getDataVencimento() {

		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {

		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorTitulo() {

		return this.valorTitulo;
	}

	public void setValorTitulo(BigDecimal valorTitulo) {

		this.valorTitulo = valorTitulo;
	}

	public String getBancoRecebedor() {

		return this.bancoRecebedor;
	}

	public void setBancoRecebedor(String bancoRecebedor) {

		this.bancoRecebedor = bancoRecebedor;
	}

	public String getAgenciaRecebedora() {

		return this.agenciaRecebedora;
	}

	public void setAgenciaRecebedora(String agenciaRecebedora) {

		this.agenciaRecebedora = agenciaRecebedora;
	}

	public Integer getEspecie() {

		return this.especie;
	}

	public void setEspecie(Integer especie) {

		this.especie = especie;
	}

	public Date getDataCredito() {

		return this.dataCredito;
	}

	public void setDataCredito(Date dataCredito) {

		this.dataCredito = dataCredito;
	}

	public BigDecimal getValorRecebidoParcial() {

		return this.valorRecebidoParcial;
	}

	public void setValorRecebidoParcial(BigDecimal valorRecebidoParcial) {

		this.valorRecebidoParcial = valorRecebidoParcial;
	}

	public BigDecimal getValorLancamento() {

		return this.valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {

		this.valorLancamento = valorLancamento;
	}

	public BigDecimal getValorAjuste() {

		return this.valorAjuste;
	}

	public void setValorAjuste(BigDecimal valorAjuste) {

		this.valorAjuste = valorAjuste;
	}

	public Integer getSequencialRegistro() {

		return this.sequencialRegistro;
	}

	public void setSequencialRegistro(Integer sequencialRegistro) {

		this.sequencialRegistro = sequencialRegistro;
	}

	@Override
	public String toString() {
		return String.format("[id: %s, nossoNumero: %s, carteira: %s, dataLiquidacao: %s, numeroTitulo: %s, dataVencimento: %s, valorTitulo: %s, valorLancamento: %s, sequencialRegistro: %s]",
			this.id != null ? this.id : "",
			this.nossoNumero != null ? this.nossoNumero : "",
			this.carteira != null ? this.carteira : "",
			this.dataLiquidacao != null ? this.dataLiquidacao : "",
			this.numeroTitulo != null ? this.numeroTitulo : "",
			this.dataVencimento != null ? this.dataVencimento : "",
			this.valorTitulo != null ? this.valorTitulo : "",
			this.valorLancamento != null ? this.valorLancamento : "",
			this.sequencialRegistro != null ? this.sequencialRegistro : "");
	}

}
