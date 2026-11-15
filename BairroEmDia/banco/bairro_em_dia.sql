PRAGMA foreign_keys = ON;
BEGIN TRANSACTION;
CREATE TABLE morador (id INTEGER PRIMARY KEY, nome TEXT NOT NULL CHECK(length(trim(nome))>=3), email TEXT NOT NULL UNIQUE, bairro TEXT NOT NULL);
CREATE TABLE categoria (id INTEGER PRIMARY KEY, nome TEXT NOT NULL UNIQUE);
CREATE TABLE ocorrencia (id INTEGER PRIMARY KEY, titulo TEXT NOT NULL, descricao TEXT NOT NULL, localizacao TEXT NOT NULL, status TEXT NOT NULL CHECK(status IN ('Aberta','Em análise','Resolvida')), morador_id INTEGER NOT NULL REFERENCES morador(id), categoria_id INTEGER NOT NULL REFERENCES categoria(id));
CREATE TABLE atualizacao (id INTEGER PRIMARY KEY, ocorrencia_id INTEGER NOT NULL REFERENCES ocorrencia(id), data TEXT NOT NULL, mensagem TEXT NOT NULL);
INSERT INTO morador VALUES (1,'Ana Exemplo','ana@example.com','Centro'),(2,'Bruno Exemplo','bruno@example.com','Vila Verde');
INSERT INTO categoria VALUES (1,'Iluminação'),(2,'Vias públicas'),(3,'Limpeza');
INSERT INTO ocorrencia VALUES (1,'Lâmpada apagada','Poste sem iluminação há três dias.','Rua das Flores, 120','Aberta',1,1),(2,'Buraco na via','Buraco dificulta a passagem de bicicletas.','Av. Central, 45','Em análise',2,2),(3,'Descarte irregular','Resíduos removidos no exemplo simulado.','Praça do Sol','Resolvida',1,3);
INSERT INTO atualizacao VALUES (1,1,'2026-11-06','Registro criado pelo morador.'),(2,2,'2026-11-06','Ocorrência em análise na simulação.'),(3,3,'2026-11-06','Resolução registrada na simulação.');
COMMIT;
-- Ocorrências com cidadão e categoria
SELECT o.id,o.titulo,o.status,m.nome AS morador,c.nome AS categoria FROM ocorrencia o JOIN morador m ON m.id=o.morador_id JOIN categoria c ON c.id=o.categoria_id;
-- Quantidade por situação
SELECT status,COUNT(*) AS quantidade FROM ocorrencia GROUP BY status;
-- Histórico de cada ocorrência
SELECT o.titulo,a.data,a.mensagem FROM atualizacao a JOIN ocorrencia o ON o.id=a.ocorrencia_id ORDER BY a.data,a.id;
