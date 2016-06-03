package com.m2r.boteutils.flatfile.model;

import static com.m2r.flatfile.enumeration.FlatFieldTypeEnum.INTEGER;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.m2r.flatfile.annotation.FlatField;
import com.m2r.flatfile.enumeration.FlatFieldTypeEnum;

public class HeaderRetorno {

	@FlatField(begin=0, end=1, converter=INTEGER, id="0")
	private Integer id;

	@FlatField(begin=1, end=2, converter=INTEGER)
	private Integer operacao;

	@FlatField(begin=9, end=11)
	private String servico;

	@FlatField(begin=26, end=31)
	private String agencia;

	@FlatField(begin=31, end=40)
	private String conta;

	@FlatField(begin=46, end=76)
	private String nomeCedente;

	@FlatField(begin=94, end=100, converter=FlatFieldTypeEnum.DATE)
	private Date dataArquivo;

	@FlatField(begin=100, end=107, converter=INTEGER)
	private Integer sequencialArquivo;

	@FlatField(begin=394, end=400, converter=INTEGER)
	private Integer sequencialRegistro;

	private List<DetalheRetorno> details = new ArrayList<DetalheRetorno>();

	public Integer getId() {

		return this.id;
	}


	public void setId(Integer id) {

		this.id = id;
	}


	public Integer getOperacao() {

		return this.operacao;
	}


	public void setOperacao(Integer operacao) {

		this.operacao = operacao;
	}


	public String getServico() {

		return this.servico;
	}


	public void setServico(String servico) {

		this.servico = servico;
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


	public String getNomeCedente() {

		return this.nomeCedente;
	}


	public void setNomeCedente(String nomeCedente) {

		this.nomeCedente = nomeCedente;
	}


	public Date getDataArquivo() {

		return this.dataArquivo;
	}


	public void setDataArquivo(Date dataArquivo) {

		this.dataArquivo = dataArquivo;
	}


	public Integer getSequencialArquivo() {

		return this.sequencialArquivo;
	}


	public void setSequencialArquivo(Integer sequencialArquivo) {

		this.sequencialArquivo = sequencialArquivo;
	}


	public Integer getSequencialRegistro() {

		return this.sequencialRegistro;
	}


	public void setSequencialRegistro(Integer sequencialRegistro) {

		this.sequencialRegistro = sequencialRegistro;
	}


	public List<DetalheRetorno> getDetails() {

		return this.details;
	}



	public void setDetails(List<DetalheRetorno> details) {

		this.details = details;
	}


	@Override
	public String toString() {

		return String.format("[id: %s, operacao: %s, servico: %s, agencia: %s, conta: %s, nomeCedente: %s, dataArquivo: %s, sequencialArquivo: %s, sequencialRegistro: %s]",
						this.id != null ? this.id : "",
										this.operacao != null ? this.operacao : "",
														this.servico != null ? this.servico : "",
																		this.agencia != null ? this.agencia : "",
																						this.conta != null ? this.conta : "",
																										this.nomeCedente != null ? this.nomeCedente : "",
																														this.dataArquivo != null ? this.dataArquivo : "",
																																		this.sequencialArquivo != null ? this.sequencialArquivo : "",
																																						this.sequencialRegistro != null ? this.sequencialRegistro : "");
	}

}
