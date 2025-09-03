### Políticas y Normatividad del Proyecto ServiCiudad Conectada
## 1. Políticas Organizacionales

1.1 Política de Transformación Digital
El sistema debe alinearse con la estrategia municipal “Cali Conectada”, garantizando servicios digitales integrados, accesibles y centrados en el ciudadano.

1.2 Política de Gobernanza de Datos
Todos los datos de clientes, consumos y transacciones deben gestionarse bajo un modelo unificado de gobierno de datos, evitando duplicidad e inconsistencias.

1.3 Política de Continuidad del Servicio
La disponibilidad mínima exigida para los servicios críticos (pagos, facturación, reportes de fallas) será de 99.95% mensual, respaldada con planes de contingencia y recuperación ante desastres.

1.4 Política de Integración con Legados
Se adoptará el principio de capa anticorrupción: ningún microservicio expondrá directamente la complejidad de los sistemas heredados.

## 2. Normatividad Legal y Regulatoria

2.1 Protección de Datos Personales
Cumplimiento estricto de la Ley 1581 de 2012 (Habeas Data en Colombia) y normativas de la Superintendencia de Industria y Comercio (SIC) para garantizar la privacidad de la información.

2.2 Normas Financieras y de Pagos
La pasarela de pagos deberá cumplir con PCI-DSS para seguridad de datos de tarjetas y normatividad de la Superintendencia Financiera de Colombia.

2.3 Normatividad de Servicios Públicos
Adhesión a las directrices de la Comisión de Regulación de Energía y Gas (CREG) y de la Comisión de Regulación de Comunicaciones (CRC) en lo referente a facturación, tarifas y transparencia al usuario.

2.4 Accesibilidad Digital
El portal y la aplicación móvil cumplirán con el estándar WCAG 2.1 AA para garantizar acceso inclusivo a personas con discapacidad.

## 3. Políticas Técnicas

3.1 Política de Seguridad de la Información
El sistema se regirá por ISO/IEC 27001 e incluirá autenticación multifactor (MFA), cifrado AES-256 en datos en reposo y TLS 1.3 en datos en tránsito.

3.2 Política de Arquitectura de Software
El sistema se construirá bajo microservicios desacoplados, orquestados con Kubernetes, gestionados vía API Gateway, y aplicando patrones de resiliencia (Circuit Breaker, Retry, Bulkhead).

3.3 Política de Calidad y Desarrollo
El software seguirá lineamientos de ISO/IEC 25010, con prácticas ágiles (Scrum) y pruebas automatizadas de regresión y seguridad.

3.4 Política de Escalabilidad
Todos los servicios críticos deben escalar horizontalmente en la nube pública/privada, garantizando soporte para picos de hasta 200,000 solicitudes concurrentes.

## 4. Políticas Operativas

4.1 Monitoreo y Observabilidad
Todos los microservicios estarán instrumentados con logs centralizados, métricas y trazas distribuidas bajo un sistema como ELK Stack o Prometheus + Grafana.

4.2 Política de Mantenimiento
Las ventanas de mantenimiento planificadas no podrán exceder 4 horas mensuales, con comunicación anticipada a usuarios y planes de contingencia activos.

4.3 Política de Capacitación Interna
Los operadores, técnicos de campo y administradores recibirán formación continua en nuevas tecnologías, normatividad vigente y uso del sistema.
