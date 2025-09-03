## 5. Tácticas para Garantizar Atributos de Calidad

## Descripción

Esta sección detalla las tácticas de calidad escogidas para garantizar los atributos de calidad de forma correcta.

## Seguridad 
- **Autenticación robusta**: Uso de OAuth2, MFA o certificados digitales.

- **Autorización basada en roles (RBAC)**: Controlar qué operaciones puede hacer cada usuario.

- **Encriptación de datos**: Cifrado en tránsito (TLS) y en reposo (AES, RSA).

- **Validación de entrada**: Sanitización de datos para prevenir inyecciones.

- **Monitoreo y logging seguro**: Trazas de auditoría inmutables.

## Disponibilidad
- **Replicación activa/pasiva**: Tener servidores en standby listos para activarse en caso de fallo.

- **Heartbeat y failover**: Monitorización constante para detectar fallos y redirigir tráfico automáticamente.

- **Despliegue redundante**: Múltiples instancias en distintas zonas de disponibilidad.

- **Checkpoint & rollback**: Guardar estado intermedio y restaurar en caso de caída.

## Interoperabilidad
- **Uso de estándares de integración**: REST, gRPC, JSON, XML, OpenAPI/Swagger.

- **Adaptadores**: Capas que traducen protocolos o formatos de datos entre sistemas.

- **Mensajería desacoplada**: Middleware como Kafka, RabbitMQ para integrar sistemas heterogéneos.

- **Versionado de APIs**: Permite coexistencia de diferentes clientes y servicios.

## Escalabilidad
- **Escalamiento horizontal**: Añadir más instancias de servicios automáticamente.

- **Particionamiento y sharding**: Dividir datos entre múltiples nodos.

- **Elasticidad en la nube**: Auto-scaling groups en AWS, GCP o Azure.

- **Colas y sistemas asíncronos**: Manejo de picos sin sobrecargar servicios.

- **CDN**: Distribuir contenido estático globalmente para reducir latencia.

## Performance
- **Caching**: Cache en cliente, servidor o distribuida (Redis, CDN).

- **Load balancing**: Balanceo de carga para distribuir peticiones.

- **Optimización de consultas**: Índices, particionamiento y optimización de queries.

- **Compresión y streaming**: Reducir tamaño de mensajes y usar streaming para grandes volúmenes.

- **Escalamiento vertical temporal**: Aumentar recursos de servidores críticos en picos de carga.
