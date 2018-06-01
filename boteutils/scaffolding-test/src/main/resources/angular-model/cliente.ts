import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('cliente')
export class Cliente extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_cliente"})
    id: number;

    @Column({name:"nu_versao"})
    versao: number;

    @Column({length:40, name:"tx_uuid"})
    uuid: string;

    @Column({name:"en_potencial"})
    potencial: number;    

    @Column({length:60, name:"no_completo"})
    nomeCompleto: string;

    @Column({length:30, name:"no_abreviado"})
    nomeAbreviado: string;

    @Column({name:"en_sexo"})
    sexo: number;    

    @Column({name:"en_grau_instrucao"})
    grauInstrucao: number;    

    @Column({name:"en_estado_civil"})
    estadoCivil: number;    

    @Column({name:"dt_nascimento"})
    msNascimento: number;

    @Column({name:"en_capacidade_civil"})
    capacidadeCivil: number;    

    @Column({length:30, name:"tx_nacionalidade"})
    nacionalidade: string;

    @Column({length:2, name:"tx_estado"})
    estado: string;

    @Column({length:60, name:"tx_cidade"})
    cidade: string;

    @Column({name:"en_autoriza_consulta_scr"})
    autorizaConsultaScr: number;    

    @Column({name:"en_pep"})
    pep: number;    

    @Column({name:"en_pep_relacionado"})
    pepRelacionado: number;    

    @Column({name:"en_tipo_conta"})
    tipoConta: number;    

    @Column({name:"en_possue_renda"})
    possueRenda: number;    

    @Column({name:"en_servidor_publico"})
    servidorPublico: number;    

    @Column({name:"en_necessidade_especiais"})
    necessidadeEspeciais: number;    

    @Column({name:"en_situacao_especial"})
    situacaoEspecial: number;    

    @Column({length:60, name:"tx_proposito_relacao_negocio"})
    propositoRelacaoNegocio: string;

    @Column({length:60, name:"tx_prestacao_garantia"})
    prestacaoGarantia: string;

    @Column({name:"en_produtos_selecionados"})
    produtosSelecionados: number;    

    @Column({length:10, name:"tx_atividade"})
    atividade: string;

    @Column({name:"nu_grau_interesse"})
    grauInteresse: number;

    @Column({length:60, name:"no_mae"})
    nomeMae: string;

    @Column({length:60, name:"no_pai"})
    nomePai: string;

    @Column({name:"en_politicamente_exposta"})
    politicamenteExposta: number;    

    @Column({name:"en_titular"})
    titular: number;    

    @OneToOne(type => Carteira, {
        cascade: true
    })
    @JoinColumn()
    carteira: Carteira;

    @OneToOne(type => DadosCaptura, {
        cascade: true
    })
    @JoinColumn()
    dadosCaptura: DadosCaptura;

    @OneToMany(type => Identificacao)
    identificacoes: Identificacao[];

    @OneToOne(type => Referencia, {
        cascade: true
    })
    @JoinColumn()
    referencia: Referencia;

    @OneToMany(type => Telefone)
    telefones: Telefone[];

    getNascimento():Date{
        return this.millisecondsToDate(this.msNascimento);
    }

    setNascimento(nascimento:Date){
        this.msNascimento = this.dateToMilliseconds(nascimento);
    }
    
}