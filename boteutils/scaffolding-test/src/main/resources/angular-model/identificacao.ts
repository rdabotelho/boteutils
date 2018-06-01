import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('identificacao')
export class Identificacao extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_identificacao"})
    id: number;

    @Column({length:20, name:"nu_identificacao"})
    numero: string;

    @Column({name:"dt_data_expedicao"})
    msDataExpedicao: number;

    @Column({length:20, name:"nu_via"})
    numeroVia: string;

    @Column({length:20, name:"no_origem_documento"})
    origemDocumento: string;

    @Column({length:20, name:"no_orgao_emissor"})
    orgaoEmissor: string;

    @Column({length:2, name:"no_estado_emissor"})
    estadoEmissor: string;

    @Column({length:60, name:"no_cidade_emissor"})
    cidadeEmissor: string;

    @Column({name:"en_tipo_identificacao"})
    tipoIdentificacao: number;    

    getDataExpedicao():Date{
        return this.millisecondsToDate(this.msDataExpedicao);
    }

    setDataExpedicao(dataExpedicao:Date){
        this.msDataExpedicao = this.dateToMilliseconds(dataExpedicao);
    }
    
}