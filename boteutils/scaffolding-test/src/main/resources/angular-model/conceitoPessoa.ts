import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('conceitoPessoa')
export class ConceitoPessoa extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_conceito_pessoa"})
    id: number;

    @Column({name:"dt_data_referencia"})
    msDataReferencia: number;

    @Column({name:"en_conceito_praca"})
    conceitoPraca: number;    

    @Column({name:"en_habito_pagamento"})
    habitoPagamento: number;    

    getDataReferencia():Date{
        return this.millisecondsToDate(this.msDataReferencia);
    }

    setDataReferencia(dataReferencia:Date){
        this.msDataReferencia = this.dateToMilliseconds(dataReferencia);
    }
    
}