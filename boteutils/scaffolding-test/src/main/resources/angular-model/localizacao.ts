import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('localizacao')
export class Localizacao extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_localizacao"})
    id: number;

    @Column({name:"en_consulta_cep"})
    consultaCep: number;    

    @Column({name:"en_indicativo_zona_urbana"})
    indicativoZonaUrbana: number;    

    @Column({name:"en_tipo_endereco"})
    tipoEndereco: number;    

    @Column({name:"en_tipo_comprovante"})
    tipoComprovante: number;    

    @Column({length:8, name:"nu_cep"})
    cep: string;

    @Column({length:60, name:"tx_logradouro"})
    logradouro: string;

    @Column({length:10, name:"nu_endereco"})
    numero: string;

    @Column({length:2, name:"no_uf"})
    uf: string;

    @Column({length:60, name:"no_cidade"})
    cidade: string;

    @Column({length:60, name:"tx_complemento"})
    complemento: string;

    @Column({length:60, name:"tx_perimetro"})
    perimetro: string;

    @Column({length:60, name:"no_bairro"})
    bairro: string;

    @Column({name:"nu_tempo_utilizacao"})
    tempoUtilizacao: number;

    @Column({name:"en_endereco_correspondencia"})
    enderecoCorrespondencia: number;    

    @Column({name:"en_imovel_proprio"})
    imovelProprio: number;    

}