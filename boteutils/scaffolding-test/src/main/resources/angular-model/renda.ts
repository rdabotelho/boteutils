import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('renda')
export class Renda extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_renda"})
    id: number;

    @Column({name:"en_renda_principal"})
    rendaPrincipal: number;    

    @Column({name:"en_natureza"})
    natureza: number;    

    @Column({name:"en_tipo_comprovante"})
    tipoComprovante: number;    

    @Column({name:"dt_data_emissao"})
    msDataEmissao: number;

    @Column({name:"dt_inicio_renda"})
    msInicioRenda: number;

    @Column({name:"vl_valor_bruto"})
    valorBruto: number;

    @Column({name:"vl_valor_liquido"})
    valorLiquido: number;

    getDataEmissao():Date{
        return this.millisecondsToDate(this.msDataEmissao);
    }

    setDataEmissao(dataEmissao:Date){
        this.msDataEmissao = this.dateToMilliseconds(dataEmissao);
    }
    
    getInicioRenda():Date{
        return this.millisecondsToDate(this.msInicioRenda);
    }

    setInicioRenda(inicioRenda:Date){
        this.msInicioRenda = this.dateToMilliseconds(inicioRenda);
    }
    
}