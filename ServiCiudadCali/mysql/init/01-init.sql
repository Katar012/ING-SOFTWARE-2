CREATE DATABASE IF NOT EXISTS acueducto

USE acueducto;

DROP TABLE IF EXISTS facturas_acueducto;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
  id        VARCHAR(20)  NOT NULL,
  nombre    VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE facturas_acueducto (
  id           BIGINT NOT NULL AUTO_INCREMENT,
  id_cliente   VARCHAR(20) NOT NULL,
  periodo      VARCHAR(20) NOT NULL,   
  consumo_m3   INT NOT NULL,
  valor_pagar  DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id),
  KEY idx_facturas_id_cliente (id_cliente),
  CONSTRAINT fk_factura_cliente
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
);

INSERT INTO cliente (id, nombre) VALUES
  ('0001234567', 'Juan Perez'),
  ('0009876543', 'Maria Gomez'),
  ('1106514392', 'Luis Herrera');

INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES
  ('0001234567', '202510', 11,  73000.00),
  ('0009876543', '202510', 18, 115000.00),
  ('1106514392', '202510',  9,  60000.00);
