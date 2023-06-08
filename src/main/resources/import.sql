insert into item_de_estoque (id, nome, unidade_de_medida, quantidade_total, quantidade_maxima, quantidade_minima) values (1L,'coca cola', 'packs', 6,20,3);
insert into item_de_estoque (id, nome, unidade_de_medida, quantidade_total, quantidade_maxima, quantidade_minima) values (2L,'café', 'packs', 3,20,3);

insert into movimentacao_de_estoque(id, tipo_de_movimentacao, data, quantidade,item_estoque_id) values (1L, 0, '2023-05-21', 2, 1L);
insert into movimentacao_de_estoque(id, tipo_de_movimentacao, data, quantidade,item_estoque_id) values (2L, 1, '2023-05-21', 2, 2L);