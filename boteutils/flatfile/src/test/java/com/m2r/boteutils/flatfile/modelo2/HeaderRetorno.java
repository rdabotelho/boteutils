package com.m2r.boteutils.flatfile.modelo2;

import static com.m2r.flatfile.enumeration.FlatFieldTypeEnum.INTEGER;

import java.util.Date;

import com.m2r.flatfile.annotation.FlatField;
import com.m2r.flatfile.enumeration.FlatFieldTypeEnum;
import com.m2r.flatfile.utils.ToStringBuilder;

public class HeaderRetorno {

	@FlatField(begin = 0, end = 3)
	private String banco;

	@FlatField(begin = 3, end = 7)
	private String lote;

	@FlatField(begin = 7, end = 8, converter = INTEGER, id = "0")
	private Integer id;

	@FlatField(begin = 8, end = 17)
	private String exclusivoFebraban;

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

	@FlatField(begin = 142, end = 143, converter = INTEGER)
	private Integer codigoRemessa;

	@FlatField(begin = 143, end = 151, converter = FlatFieldTypeEnum.DATE)
	private Date dataGeracao;

	@FlatField(begin = 151, end = 157)
	private String horaGeracao;

	@FlatField(begin = 157, end = 163, converter = INTEGER)
	private Integer sequencialArquivo;

	@FlatField(begin = 163, end = 166)
	private String versaoLayout;

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

	public String getExclusivoFebraban() {

		return this.exclusivoFebraban;
	}

	public void setExclusivoFebraban(String exclusivoFebraban) {

		this.exclusivoFebraban = exclusivoFebraban;
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

	public Integer getCodigoRemessa() {

		return this.codigoRemessa;
	}

	public void setCodigoRemessa(Integer codigoRemessa) {

		this.codigoRemessa = codigoRemessa;
	}

	public Date getDataGeracao() {

		return this.dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {

		this.dataGeracao = dataGeracao;
	}

	public String getHoraGeracao() {

		return this.horaGeracao;
	}

	public void setHoraGeracao(String horaGeracao) {

		this.horaGeracao = horaGeracao;
	}

	public Integer getSequencialArquivo() {

		return this.sequencialArquivo;
	}

	public void setSequencialArquivo(Integer sequencialArquivo) {

		this.sequencialArquivo = sequencialArquivo;
	}

	public String getVersaoLayout() {

		return this.versaoLayout;
	}

	public void setVersaoLayout(String versaoLayout) {

		this.versaoLayout = versaoLayout;
	}

	@Override
	public String toString() {
		return ToStringBuilder.toString(this);
	}

}
