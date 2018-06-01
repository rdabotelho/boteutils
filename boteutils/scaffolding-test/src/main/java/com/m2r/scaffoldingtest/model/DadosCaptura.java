package com.m2r.scaffoldingtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.m2r.scaffolding.utils.ClassScaffold;
import com.m2r.scaffolding.utils.FieldScaffold;
import com.m2r.scaffolding.utils.ScaffoldModel;
import com.m2r.scaffoldingtest.enums.TipoIdentificacaoEnum;

@Entity
@Table(name = "DADOS_CAPTURA")
@ClassScaffold(label="Dados Captura", icon="fa-file-o")
public class DadosCaptura implements BaseModel<Long>, ScaffoldModel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_DADOS_CAPTURA", sequenceName = "SEQ_DADOS_CAPTURA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DADOS_CAPTURA")
	@Column(name = "ID_DADOS_CAPTURA", nullable=false)
	private Long id;

    @OneToOne
    @JoinColumn(name = "ID_ARQUIVO_CLIENTE")
	@FieldScaffold(label="Foto Cliente", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Arquivo fotoCliente;
	
	@Column(name = "EN_TIPO_IDENTIFICACAO", nullable=true, length=9)
	@Enumerated(EnumType.STRING)
	@FieldScaffold(label="Tipo Identificacao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true, maxlength=9)
	private TipoIdentificacaoEnum tipoIdentificacao;
	
    @OneToOne
    @JoinColumn(name = "ID_ARQUIVO_IDENTIFICACAO")
	@FieldScaffold(label="Documento Identificacao", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Arquivo documentoIdentificacao;
	
    @OneToOne
    @JoinColumn(name = "ID_ARQUIVO_CPF")
	@FieldScaffold(label="Cpf", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Arquivo cpf;
	
    @OneToOne
    @JoinColumn(name = "ID_ARQUIVO_RESIDENCIA")
	@FieldScaffold(label="Comprovante Residencia", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Arquivo comprovanteResidencia;
	
    @OneToOne
    @JoinColumn(name = "ID_ARQUIVO_EMPREENDIMENTO")
	@FieldScaffold(label="Foto Empreendimento", isfilter=false, isViewed=true, isRequired=false, isViewedOnTable=true)
	private Arquivo fotoEmpreendimento;
    
    @OneToOne
	private Cliente cliente;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Arquivo getFotoCliente() {
		return fotoCliente;
	}

	public void setFotoCliente(Arquivo fotoCliente) {
		this.fotoCliente = fotoCliente;
	}
	
	public TipoIdentificacaoEnum getTipoIdentificacao() {
		return tipoIdentificacao;
	}

	public void setTipoIdentificacao(TipoIdentificacaoEnum tipoIdentificacao) {
		this.tipoIdentificacao = tipoIdentificacao;
	}
	
	public Arquivo getDocumentoIdentificacao() {
		return documentoIdentificacao;
	}

	public void setDocumentoIdentificacao(Arquivo documentoIdentificacao) {
		this.documentoIdentificacao = documentoIdentificacao;
	}
	
	public Arquivo getCpf() {
		return cpf;
	}

	public void setCpf(Arquivo cpf) {
		this.cpf = cpf;
	}
	
	public Arquivo getComprovanteResidencia() {
		return comprovanteResidencia;
	}

	public void setComprovanteResidencia(Arquivo comprovanteResidencia) {
		this.comprovanteResidencia = comprovanteResidencia;
	}
	
	public Arquivo getFotoEmpreendimento() {
		return fotoEmpreendimento;
	}

	public void setFotoEmpreendimento(Arquivo fotoEmpreendimento) {
		this.fotoEmpreendimento = fotoEmpreendimento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		   DadosCaptura other = (DadosCaptura) obj;
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
