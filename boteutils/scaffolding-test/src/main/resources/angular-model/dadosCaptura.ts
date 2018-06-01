import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('dadosCaptura')
export class DadosCaptura extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_dados_captura"})
    id: number;

    @OneToOne(type => Arquivo, {
        cascade: true
    })
    @JoinColumn()
    fotoCliente: Arquivo;

    @Column({name:"en_tipo_identificacao"})
    tipoIdentificacao: number;    

    @OneToOne(type => Arquivo, {
        cascade: true
    })
    @JoinColumn()
    documentoIdentificacao: Arquivo;

    @OneToOne(type => Arquivo, {
        cascade: true
    })
    @JoinColumn()
    cpf: Arquivo;

    @OneToOne(type => Arquivo, {
        cascade: true
    })
    @JoinColumn()
    comprovanteResidencia: Arquivo;

    @OneToOne(type => Arquivo, {
        cascade: true
    })
    @JoinColumn()
    fotoEmpreendimento: Arquivo;

    @OneToOne(type => Cliente, {
        cascade: true
    })
    @JoinColumn()
    cliente: Cliente;

}