import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('assessor')
export class Assessor extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_assessor"})
    id: number;

    @Column({length:60, name:"no_assessor"})
    nome: string;

    @Column({length:11, name:"tx_cpf"})
    cpf: string;

    @Column({length:10, name:"tx_rg"})
    rg: string;

    @Column({length:20, name:"tx_titulo_eleitor"})
    tituloEleitor: string;

    @Column({length:20, name:"tx_ctps"})
    ctps: string;

    @Column({name:"dt_nascimento"})
    msNascimento: number;

    @Column({length:${field.maxLength}, name:"tx_naturalidade"})
    naturalidade: string;

    @Column({length:${field.maxLength}, name:"tx_nacionalidade"})
    nacionalidade: string;

    @Column({name:"en_estado_civil"})
    estadoCivil: number;    

    @Column({length:15, name:"tx_telefone_fixo"})
    telefoneFixo: string;

    @Column({length:15, name:"tx_telefone_movel"})
    telefoneMovel: string;

    @Column({length:30, name:"tx_referencia"})
    referencia: string;

    @Column({length:8, name:"tx_cep"})
    cep: string;

    @Column({length:60, name:"tx_endereco"})
    endereco: string;

    @Column({length:6, name:"tx_numero"})
    numero: string;

    @Column({length:60, name:"tx_complemento"})
    complemento: string;

    @Column({length:60, name:"tx_cidade"})
    cidade: string;

    @Column({length:2, name:"tx_estado"})
    estado: string;

    @Column({name:"dt_inicio_contrato"})
    msInicioContrato: number;

    @Column({name:"dt_fim_contrato"})
    msFimContrato: number;

    @Column({name:"vl_rendimento_bruto"})
    rendimentoBruto: number;

    @OneToOne(type => Carteira, {
        cascade: true
    })
    @JoinColumn()
    carteira: Carteira;

    getNascimento():Date{
        return this.millisecondsToDate(this.msNascimento);
    }

    setNascimento(nascimento:Date){
        this.msNascimento = this.dateToMilliseconds(nascimento);
    }
    
    getInicioContrato():Date{
        return this.millisecondsToDate(this.msInicioContrato);
    }

    setInicioContrato(inicioContrato:Date){
        this.msInicioContrato = this.dateToMilliseconds(inicioContrato);
    }
    
    getFimContrato():Date{
        return this.millisecondsToDate(this.msFimContrato);
    }

    setFimContrato(fimContrato:Date){
        this.msFimContrato = this.dateToMilliseconds(fimContrato);
    }
    
}