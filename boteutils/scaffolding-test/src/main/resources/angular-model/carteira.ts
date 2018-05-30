import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('carteira')
export class Carteira extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_carteira"})
    id: number;

    @OneToMany(type => Cliente)
    clientes: Cliente[];

}