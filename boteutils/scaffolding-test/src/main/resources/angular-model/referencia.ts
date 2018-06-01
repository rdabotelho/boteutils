import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('referencia')
export class Referencia extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_referencia"})
    id: number;

    @Column({name:"en_natureza"})
    natureza: number;    

    @Column({length:60, name:"no_referencia"})
    nome: string;

    @Column({length:3, name:"nu_ddd"})
    ddd: string;

    @Column({length:15, name:"nu_telefone"})
    telefone: string;

}