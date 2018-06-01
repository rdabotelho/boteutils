package com.m2r.scaffoldingtest.model;

import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.m2r.scaffolding.utils.ClassScaffold;
import com.m2r.scaffolding.utils.FieldScaffold;
import com.m2r.scaffolding.utils.ScaffoldModel;
import com.m2r.scaffoldingtest.enums.TipoArquivoEnum;

@Entity
@Table(name = "ARQUIVO")
@ClassScaffold(label="Arquivo", icon="fa-file-o")
public class Arquivo implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ARQUIVO", sequenceName = "SEQ_ARQUIVO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ARQUIVO")
	@Column(name = "ID_ARQUIVO", nullable=false)
	private Long id;

	@Column(name = "NO_ARQUIVO", nullable=false, length=100)
	@FieldScaffold(label="Nome", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=100)
	private String nome;
	
	@Column(name = "EN_TIPO", nullable=false, length=20)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=20)
	private TipoArquivoEnum tipo;

	@Lob
	@Column(name = "BI_CONTEUDO", nullable=false)
	@FieldScaffold(label="Conteudo", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Blob conteudo;
	
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
	
	public TipoArquivoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoArquivoEnum tipo) {
		this.tipo = tipo;
	}
	
	public void setConteudo(byte[] arquivo) {
		try {
			this.conteudo = new SerialBlob(arquivo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] getConteudo() {
		try {
			return this.conteudo.getBytes(1, (int) this.conteudo.length());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
		   Arquivo other = (Arquivo) obj;
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
