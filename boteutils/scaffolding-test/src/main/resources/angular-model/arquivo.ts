import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('arquivo')
export class Arquivo extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_arquivo"})
    id: number;

    @Column({length:100, name:"no_arquivo"})
    nome: string;

    @Column({name:"en_tipo"})
    tipo: number;    

    @Column({name:"bi_conteudo"})
    conteudo: blob;    

}