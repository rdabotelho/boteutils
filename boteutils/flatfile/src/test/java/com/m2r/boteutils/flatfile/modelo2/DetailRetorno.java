package com.m2r.boteutils.flatfile.modelo2;

import static com.m2r.flatfile.enumeration.FlatFieldTypeEnum.INTEGER;

import java.math.BigDecimal;
import java.util.Date;

import com.m2r.flatfile.annotation.FlatField;
import com.m2r.flatfile.enumeration.FlatFieldTypeEnum;
import com.m2r.flatfile.utils.ToStringBuilder;

public class DetailRetorno {

	@FlatField(begin = 0, end = 3)
	private String banco;

	@FlatField(begin = 3, end = 7)
	private String lote;

	@FlatField(begin = 7, end = 8, converter = INTEGER, id = "3")
	private Integer id;

	@FlatField(begin = 8, end = 13, converter = INTEGER)
	private Integer sequencialRegistro;

	@FlatField(begin = 13, end = 14, id="U")
	private String codigoSegmento;

	@FlatField(begin = 14, end = 15)
	private String tipoMovimento;

	@FlatField(begin = 15, end = 17)
	private String codigoInstrucao;

	@FlatField(begin = 17, end = 20)
	private String bancoDestino;

	@FlatField(begin = 21, end = 22)
	private String dvCodigoBarra;

	@FlatField(begin = 22, end = 36, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorCodigoBarra;

	@FlatField(begin = 61, end = 91)
	private String nomeCedente;

	@FlatField(begin = 91, end = 99, converter = FlatFieldTypeEnum.DATE)
	private Date dataVencimento;

	@FlatField(begin = 99, end = 114, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorTitulo;

	@FlatField(begin = 114, end = 129, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorDescontoAbatimento;

	@FlatField(begin = 129, end = 144, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorMoraMulta;

	@FlatField(begin = 144, end = 152, converter = FlatFieldTypeEnum.DATE)
	private Date dataPagamento;

	@FlatField(begin = 152, end = 167, converter = FlatFieldTypeEnum.DECIMAL)
	private BigDecimal valorPagamento;

	@FlatField(begin = 202, end = 222)
	private String nossoNumero;

	@FlatField(begin = 222, end = 224)
	private String codigoMoeda;

	@FlatField(begin = 230, end = 240)
	private String codigoOcorrencia;

	public String getBanco() {

		return this.banco;
	}

	public void setBanco(String banco) {

		this.banco = banco;
	}

	public String getLote() {

		return this.lote;
	}

	public void setLote(String lote) {

		this.lote = lote;
	}

	public Integer getId() {

		return this.id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getSequencialRegistro() {

		return this.sequencialRegistro;
	}

	public void setSequencialRegistro(Integer sequencialRegistro) {

		this.sequencialRegistro = sequencialRegistro;
	}

	public String getCodigoSegmento() {

		return this.codigoSegmento;
	}

	public void setCodigoSegmento(String codigoSegmento) {

		this.codigoSegmento = codigoSegmento;
	}

	public String getTipoMovimento() {

		return this.tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {

		this.tipoMovimento = tipoMovimento;
	}

	public String getCodigoInstrucao() {

		return this.codigoInstrucao;
	}

	public void setCodigoInstrucao(String codigoInstrucao) {

		this.codigoInstrucao = codigoInstrucao;
	}

	public String getBancoDestino() {

		return this.bancoDestino;
	}

	public void setBancoDestino(String bancoDestino) {

		this.bancoDestino = bancoDestino;
	}

	public String getDvCodigoBarra() {

		return this.dvCodigoBarra;
	}

	public void setDvCodigoBarra(String dvCodigoBarra) {

		this.dvCodigoBarra = dvCodigoBarra;
	}

	public BigDecimal getValorCodigoBarra() {

		return this.valorCodigoBarra;
	}

	public void setValorCodigoBarra(BigDecimal valorCodigoBarra) {

		this.valorCodigoBarra = valorCodigoBarra;
	}

	public String getNomeCedente() {

		return this.nomeCedente;
	}

	public void setNomeCedente(String nomeCedente) {

		this.nomeCedente = nomeCedente;
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

	public BigDecimal getValorDescontoAbatimento() {

		return this.valorDescontoAbatimento;
	}

	public void setValorDescontoAbatimento(BigDecimal valorDescontoAbatimento) {

		this.valorDescontoAbatimento = valorDescontoAbatimento;
	}

	public BigDecimal getValorMoraMulta() {

		return this.valorMoraMulta;
	}

	public void setValorMoraMulta(BigDecimal valorMoraMulta) {

		this.valorMoraMulta = valorMoraMulta;
	}

	public Date getDataPagamento() {

		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {

		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPagamento() {

		return this.valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {

		this.valorPagamento = valorPagamento;
	}

	public String getNossoNumero() {

		return this.nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {

		this.nossoNumero = nossoNumero;
	}

	public String getCodigoMoeda() {

		return this.codigoMoeda;
	}

	public void setCodigoMoeda(String codigoMoeda) {

		this.codigoMoeda = codigoMoeda;
	}

	public String getCodigoOcorrencia() {

		return this.codigoOcorrencia;
	}

	public void setCodigoOcorrencia(String codigoOcorrencia) {

		this.codigoOcorrencia = codigoOcorrencia;
	}

	@Override
	public String toString() {

		return ToStringBuilder.toString(this);
	}

}
