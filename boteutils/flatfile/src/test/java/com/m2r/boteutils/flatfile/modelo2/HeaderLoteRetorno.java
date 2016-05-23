package com.m2r.boteutils.flatfile.modelo2;

import static com.m2r.flatfile.enumeration.FlatFieldTypeEnum.INTEGER;

import com.m2r.flatfile.annotation.FlatField;
import com.m2r.flatfile.utils.ToStringBuilder;

public class HeaderLoteRetorno {

	@FlatField(begin = 0, end = 3)
	private String banco;

	@FlatField(begin = 3, end = 7)
	private String lote;

	@FlatField(begin = 7, end = 8, converter = INTEGER, id = "1")
	private Integer id;

	@FlatField(begin = 8, end = 9)
	private String tipoOperacao;

	@FlatField(begin = 40, end = 44)
	private String tipoServico;

	@FlatField(begin = 13, end = 16)
	private String layoutLote;

	@FlatField(begin = 17, end = 18, converter = INTEGER)
	private Integer tipoInscricaoEmpresa;

	@FlatField(begin = 18, end = 32)
	private String numeroInscricaoEmpresa;

	@FlatField(begin = 32, end = 52)
	private String convenio;

	@FlatField(begin = 52, end = 58)
	private String agencia;

	@FlatField(begin = 58, end = 71)
	private String conta;

	@FlatField(begin = 72, end = 102)
	private String empresa;

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

	public String getTipoOperacao() {

		return this.tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {

		this.tipoOperacao = tipoOperacao;
	}

	public String getTipoServico() {

		return this.tipoServico;
	}

	public void setTipoServico(String tipoServico) {

		this.tipoServico = tipoServico;
	}

	public String getLayoutLote() {

		return this.layoutLote;
	}

	public void setLayoutLote(String layoutLote) {

		this.layoutLote = layoutLote;
	}

	public Integer getTipoInscricaoEmpresa() {

		return this.tipoInscricaoEmpresa;
	}

	public void setTipoInscricaoEmpresa(Integer tipoInscricaoEmpresa) {

		this.tipoInscricaoEmpresa = tipoInscricaoEmpresa;
	}

	public String getNumeroInscricaoEmpresa() {

		return this.numeroInscricaoEmpresa;
	}

	public void setNumeroInscricaoEmpresa(String numeroInscricaoEmpresa) {

		this.numeroInscricaoEmpresa = numeroInscricaoEmpresa;
	}

	public String getConvenio() {

		return this.convenio;
	}

	public void setConvenio(String convenio) {

		this.convenio = convenio;
	}

	public String getAgencia() {

		return this.agencia;
	}

	public void setAgencia(String agencia) {

		this.agencia = agencia;
	}

	public String getConta() {

		return this.conta;
	}

	public void setConta(String conta) {

		this.conta = conta;
	}

	public String getEmpresa() {

		return this.empresa;
	}

	public void setEmpresa(String empresa) {

		this.empresa = empresa;
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
