import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('telefone')
export class Telefone extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_telefone"})
    id: number;

    @Column({name:"en_tipo_telefone"})
    tipoTelefone: number;    

    @Column({length:6, name:"nu_ddi"})
    ddi: string;

    @Column({length:3, name:"nu_ddd"})
    ddd: string;

    @Column({length:16, name:"nu_telefone"})
    telefone: string;

    @Column({length:10, name:"nu_ramal"})
    ramal: string;

}