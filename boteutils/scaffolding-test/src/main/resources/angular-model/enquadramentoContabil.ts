import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('enquadramentoContabil')
export class EnquadramentoContabil extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_enquadramento_contabil"})
    id: number;

    @Column({name:"en_categoria"})
    categoria: number;    

    @Column({name:"en_natureza"})
    natureza: number;    

    @Column({name:"en_enquadramento"})
    Enquadramento: number;    

}