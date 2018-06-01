import {Entity, PrimaryGeneratedColumn, Column} from "typeorm";
import { EntidadeBase } from "./entidade-base";

@Entity('fluxoCaixa')
export class FluxoCaixa extends EntidadeBase{
    

    @PrimaryGeneratedColumn({name:"id_fluxo_caixa"})
    id: number;

    @Column({name:"vl_receita_operacional"})
    receitaOperacional: number;

    @Column({name:"vl_receita_nao_operacional"})
    receitaNaoOperacional: number;

    @Column({name:"vl_custo_mercadoria"})
    custoMercadoria: number;

    @Column({name:"vl_pagamento_pessoal"})
    pagamentoPessoal: number;

    @Column({name:"vl_transporte"})
    transporte: number;

    @Column({name:"vl_agua_luz_telefone"})
    aguaLuzTelefone: number;

    @Column({name:"vl_taxa_aluguel"})
    taxaAluguel: number;

    @Column({name:"vl_outros_custos"})
    outrosCustos: number;

    @Column({name:"vl_outras_despesas"})
    outrasDespesas: number;

    @Column({name:"vl_outros_pagamentos"})
    outrosPagamentos: number;

}