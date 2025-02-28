-- Create Endereco
INSERT INTO endereco (id, cep, logradouro, numero, complemento, bairro, cidade, estado, latitude, longitude) VALUES
(1, '01001-000', 'Rua das Flores', '123', 'Apt 101', 'Centro', 'São Paulo', 'SP', -23.550520, -46.633308),
(2, '02002-000', 'Avenida Paulista', '456', 'Suite 202', 'Bela Vista', 'São Paulo', 'SP', -23.561414, -46.655881);

-- Create UBS
INSERT INTO ubs (id, nome, telefone, inicio_atendimento, fim_atendimento, endereco_id) VALUES
(1, 'UBS Central', '123-456-7890', '08:00', '17:00', 1),
(2, 'UBS Paulista', '456-789-0123', '09:00', '18:00', 2);

-- Create Medico
INSERT INTO medico (id, nome, email, telefone, crm) VALUES
(1, 'Dr. João Silva', 'joao.silva@email.com', '(11) 98765-4321', 'CRM12345'),
(2, 'Dra. Maria Souza', 'maria.souza@email.com', '(21) 99876-5432', 'CRM67890'),
(3, 'Dr. Carlos Pereira', 'carlos.pereira@email.com', '(31) 91234-5678', 'CRM11122'),
(4, 'Dra. Fernanda Oliveira', 'fernanda.oliveira@email.com', '(41) 95678-9012', 'CRM33344');

-- Create Especialidades
INSERT INTO medico_especialidades (medico_id, especialidades) VALUES
(1, 'CARDIOLOGIA'),
(1, 'CLINICA_MEDICA'),
(2, 'DERMATOLOGIA'),
(3, 'CIRUGIA_GERAL'),
(3, 'CLINICA_MEDICA'),
(4, 'GERIATRIA'),
(4, 'GINECOLOGIA');

-- Create Paciente
INSERT INTO paciente (id, nome, email, cpf, telefone, cns, endereco_id) VALUES
(1, 'Joao Souza', 'joao.souza@email.com', '123.456.789-00', '987-654-3210', '123456789012345', 2),
(2, 'Maria Braga', 'maria.braga@email.com', '987.654.321-00', '654-321-0987', '987654321098765', 1);

-- Create Receita
INSERT INTO receita (id, medico_id, paciente_id, data_hora_criacao) VALUES
(1, 1, 1, NOW()),
(2, 2, 2, NOW());

-- Create Medicamento
INSERT INTO medicamento (id, nome, tarja, sku) VALUES
(1, 'Paracetamol', 'VERMELHA', 'PAR-2023-10-01'),
(2, 'Amoxicillina', 'AMARELA', 'AMO-2023-10-01'),
(3, 'Ibuprofeno', 'PRETA', 'IBU-2023-10-01'),
(4, 'Omeprazol', 'VERMELHA', 'OME-2023-10-01');

-- Create Posologia
INSERT INTO posologia (id, medicamento_id, receita_id, descricao, quantidade) VALUES
(1, 1, 1, 'Take one tablet every 6 hours', 10),
(2, 2, 1, 'Take one capsule every 8 hours', 15);

-- Create Estoque
INSERT INTO estoque (id, medicamento_id, ubs_id, quantidade) VALUES
(1, 1, 1, 100),
(2, 2, 1, 100),
(3, 3, 1, 100),
(4, 4, 2, 100);