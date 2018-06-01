import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('ocupacao')
export class Ocupacao extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_ocupacao"})
    id: number;

    @Column({length:10, name:"cd_ocupacao"})
    codigoOcupacao: string;

    @Column({name:"en_principal"})
    principal: number;    

    @Column({name:"en_possui_comprovante"})
    possuiComprovante: number;    

    @Column({name:"dt_data_inicio_ocupacao"})
    msDataInicioOcupacao: number;

    getDataInicioOcupacao():Date{
        return this.millisecondsToDate(this.msDataInicioOcupacao);
    }

    setDataInicioOcupacao(dataInicioOcupacao:Date){
        this.msDataInicioOcupacao = this.dateToMilliseconds(dataInicioOcupacao);
    }
    
}