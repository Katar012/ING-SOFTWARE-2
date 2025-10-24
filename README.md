# ServiCiudadCali – Monolito (Hexagonal)

Servicio REST que consolida la **deuda de un cliente** a partir de:
- **Acueducto**: facturas almacenadas en **MySQL** (`facturas_acueducto`).
- **Energía**: facturas simuladas desde **archivo plano** (`src/main/resources/consumos_energia.txt`).

Devuelve un JSON unificado con el nombre del cliente, la fecha de consulta, el resumen de cada factura y el total a pagar.

---

## Requisitos

- **Java 17+**
- **Maven 3.9+**
- **MySQL 8+**
- Puerto libre **8080**

---

## Clonar y compilar

```bash
git clone <URL_DE_TU_REPO>
cd ServiCiudadCali
mvn clean package
