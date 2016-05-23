package com.m2r.boteutils.flatfile.modelo2;

import static com.m2r.flatfile.enumeration.FlatFieldTypeEnum.DECIMAL;
import static com.m2r.flatfile.enumeration.FlatFieldTypeEnum.INTEGER;

import java.math.BigDecimal;

import com.m2r.flatfile.annotation.FlatField;

public class TraillerRetorno {

	@FlatField(begin=0, end=1, converter=INTEGER, id="9")
	private Integer id;

	@FlatField(begin=17, end=25, converter=INTEGER)
	private Integer quantidade;

	@FlatField(begin=25, end=39, converter=DECIMAL)
	private BigDecimal valorTotal;

	@FlatField(begin=394, end=400, converter=INTEGER)
	private Integer sequencialRegistro;

	
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	
	public Integer getQuantidade() {
	
		return quantidade;
	}

	
	public void setQuantidade(Integer quantidade) {
	
		this.quantidade = quantidade;
	}

	
	public BigDecimal getValorTotal() {
	
		return valorTotal;
	}

	
	public void setValorTotal(BigDecimal valorTotal) {
	
		this.valorTotal = valorTotal;
	}

	
	public Integer getSequencialRegistro() {
	
		return sequencialRegistro;
	}

	
	public void setSequencialRegistro(Integer sequencialRegistro) {
	
		this.sequencialRegistro = sequencialRegistro;
	}

	@Override
	public String toString() {
	
		return String.format("[id: %s, quantidade: %s, valorTotal: %s, sequencialRegistro: %s]",
						this.id != null ? this.id : "",
						this.quantidade != null ? this.quantidade : "",
						this.valorTotal != null ? this.valorTotal : "",
						this.sequencialRegistro != null ? this.sequencialRegistro : "");
	}

}
