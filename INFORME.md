## Informe técnico: Arquitectura y patrones

Este documento describe de forma clara y pedagógica la arquitectura de nuestro monolito y la justificación de los cinco patrones de diseño utilizados, con referencias concretas al código del repositorio.

---

## Arquitectura general del monolito

El sistema sigue una arquitectura hexagonal. Esto significa que:

- El dominio (reglas de negocio) se mantiene independiente de frameworks, bases de datos y detalles técnicos.
- La infraestructura (adaptadores) se conecta al dominio a través de puertos (interfaces), de forma que podemos cambiar tecnologías sin tocar la lógica de negocio.
- La capa de aplicación define modelos de intercambio (DTOs) y sirve de contrato de entrada/salida para el caso de uso expuesto por la API.

Estructura por capas (mapeada al código):

- Dominio
	- Modelos: `domain/model/Cliente.java`, `FacturaAcueducto.java`, `FacturaEnergia.java`
	- Puertos: `domain/ports/in/ConsultarFacturasClienteUseCase.java` (entrada), `domain/ports/outs/*Port.java` (salida)
	- Caso de uso: `domain/useCase/ConsultarFacturasClienteUseCaseImpl.java`
	- Excepciones de negocio: `domain/exception/ResourceNotFoundException.java`

- Aplicación
	- DTOs de respuesta: `application/dto/DeudaConsolidadaDTO.java`

- Infraestructura
	- Controlador REST: `infrastructure/controllers/DeudaConsolidadaController.java`
	- Manejo global de errores HTTP: `infrastructure/exception/GlobalException.java`
	- Persistencia y fuentes de datos:
		- Entidades JPA: `infrastructure/entity/ClienteEntity.java`, `FacturaAcueductoEntity.java`
		- Repositorios JPA: `infrastructure/repository/JpaClienteRepository.java`, `JpaFacturaAcueductoRepository.java`
		- Adaptadores hacia los puertos del dominio: `JpaClienteRepositoryAdapter.java`, `JpaFacturaAcueductoRepositoryAdapter.java`, `FacturaEnergiaRepositoryAdapter.java` (lee archivo `src/main/resources/consumos_energia.txt`)

Flujo end-to-end (resumen):

1) Petición HTTP GET a `/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}`
2) El controlador invoca el caso de uso `ConsultarFacturasClienteUseCaseImpl`
3) El caso de uso consulta los puertos `ClientePort`, `AcueductoPort`, `EnergiaPort`
	 - Cliente y Acueducto llegan por JPA (MySQL en este entorno)
	 - Energía se obtiene de un archivo plano (`consumos_energia.txt`)
4) Se construye `DeudaConsolidadaDTO` y se retorna
5) Si faltan datos, el dominio lanza `ResourceNotFoundException`, que la infraestructura traduce a HTTP 404

## Patrones de diseño aplicados (con justificación)

1) Patrón Adapter

- Problema: el archivo plano de energía (mainframe/legado) usa un formato de texto de ancho fijo, incompatible con los objetos del dominio.
- Solución: crear un adaptador que lea el archivo, parsee cada línea y construya objetos de dominio `FacturaEnergia`.
- Implementación en el repo: `infrastructure/repository/FacturaEnergiaRepositoryAdapter.java` implementa el puerto `domain/ports/outs/EnergiaPort.java`. Lee `consumos_energia.txt` y devuelve `Optional<FacturaEnergia>` para un `clienteId`.
- Beneficio: el dominio no depende de cómo se obtienen los datos (archivo, API externa, etc.). Si mañana cambiamos el origen, solo sustituimos el adaptador.

2) Patrón Builder

- Problema: el objeto de respuesta JSON es complejo, compuesto por datos de varias fuentes (energía, acueducto, cliente), y debe construirse de manera clara y segura.
- Solución: usar el patrón Builder para crear `DeudaConsolidadaDTO` y sus sub-objetos sin constructores gigantes ni setters encadenados.
- Implementación: `application/dto/DeudaConsolidadaDTO.java` y sus clases internas usan `@Builder` de Lombok para construir:
	- `DeudaConsolidadaDTO`
	- `DeudaConsolidadaDTO.ResumenDeudaDTO`
	- `DeudaConsolidadaDTO.FacturaEnergiaDTO`
	- `DeudaConsolidadaDTO.FacturaAcueductoDTO`
- Beneficio: construcción legible, inmutabilidad en la respuesta, y bajo riesgo de errores por parámetros en orden incorrecto.

3) Patrón Data Transfer Object (DTO)

- Problema: no debemos exponer entidades JPA o modelos internos directamente por la API.
- Solución: definir DTOs específicos para la respuesta esperada por el cliente.
- Implementación: `application/dto/DeudaConsolidadaDTO.java` modela exactamente la respuesta JSON (cliente, fecha, resumen y total a pagar). El caso de uso arma estos DTOs y el controlador los retorna.
- Beneficio: contrato estable de API, desacople de entidades de persistencia y posibilidad de evolucionar el modelo interno sin romper clientes.

4) Patrón Repository (Spring Data JPA)

- Problema: escribir código repetitivo (boilerplate) para CRUD y consultas a BD incrementa el acoplamiento y la complejidad.
- Solución: usar interfaces Repository de Spring Data JPA para declarar consultas y delegar a la infraestructura el código de acceso a datos.
- Implementación: `infrastructure/repository/JpaClienteRepository.java` y `JpaFacturaAcueductoRepository.java` extienden `JpaRepository` y definen métodos como `findById` y `findFirstByIdClienteOrderByPeriodoDesc`.
- Beneficio: menos código repetitivo, mayor mantenibilidad, y claridad al separar “qué” se consulta del “cómo”. Además, estos repos se consumen desde adaptadores que implementan puertos del dominio.

5) Inversión de Control / Inyección de Dependencias (Spring)

- Problema: crear manualmente dependencias y gestionar su ciclo de vida acopla las capas y complica las pruebas.
- Solución: el framework Spring administra componentes anotados (`@Service`, `@Repository`, `@RestController`, `@Component`) e inyecta dependencias donde se necesitan.
- Implementación: 
	- El caso de uso `ConsultarFacturasClienteUseCaseImpl` está anotado con `@Service` y recibe por constructor los puertos `AcueductoPort`, `EnergiaPort`, `ClientePort`.
	- El controlador `DeudaConsolidadaController` inyecta el caso de uso para exponerlo por HTTP.
	- Los adaptadores (`Jpa*RepositoryAdapter`, `FacturaEnergiaRepositoryAdapter`) son beans de Spring (`@Repository`/`@Component`) que el contenedor inyecta donde corresponda.
- Beneficio: bajo acoplamiento (dependemos de interfaces), alta cohesión (cada clase hace una cosa), y facilidad para intercambiar implementaciones (por ejemplo, otro adaptador de energía) o hacer tests con dobles (mocks).

---

## Principios SOLID en la práctica

- S — Responsabilidad Única: el controlador solo expone HTTP; el caso de uso orquesta reglas; los adaptadores transforman/obtienen datos; las entidades JPA mapean tablas; los DTOs transportan datos hacia afuera.
- O — Abierto/Cerrado: podemos agregar nuevas fuentes (otro adaptador de `EnergiaPort`) sin modificar el caso de uso; extender sin tocar el núcleo.
- L — Sustitución de Liskov: cualquier implementación de `ClientePort`/`AcueductoPort`/`EnergiaPort` puede reemplazarse sin romper consumidores, mientras respete el contrato (`Optional<T>` y semántica de dominio).
- I — Segregación de Interfaces: puertos pequeños y específicos por necesidad (`obtenerPorCliente`, `obtenerPorId`), evitando interfaces “gordas”.
- D — Inversión de Dependencias: el dominio depende de abstracciones (puertos), no de detalles (JPA/archivos); Spring inyecta implementaciones en tiempo de ejecución.

Probando CI POR cuarta VEZ

