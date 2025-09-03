# 4. Escenarios de Calidad

## Descripción

Esta sección detalla los escenarios de calidad definidos para cada atributo seleccionado.

## Atributos escogidos
### Primarios: 
  - Seguridad 
  - Disponilidad
  - Interoperabilidad

### Secundarios:
  - Escalabilidad
  - Performance/Rendimiento

## Seguridad
### Escenario 1

- Código del Escenario: SEG_BD01
- Descripción: Prevención de accesos no autorizados a la base de datos
- Interesado: Administrador de seguridad
- Atributo: Seguridad
- Origen del estímulo: Usuario intentando acceder sin credenciales válidas
- Estímulo: Intento de conexión a la base de datos con credenciales incorrectas
- Entorno: Sistema de base de datos en producción con cifrado activo
- Artefacto: Base de datos (BD)
- Respuesta: La conexión es rechazada y el intento registrado en logs de auditoría
- Medida de respuesta: 100% de intentos no autorizados bloqueados; registro de logs ≤ 1 segundo después del intento

### Escenario 2

- Código del Escenario: SEG_SRV01
- Descripción: Protección contra inyección de SQL en microservicios
- Interesado: Desarrollador de backend
- Atributo: Seguridad
- Origen del estímulo: Usuario malintencionado enviando parámetros maliciosos
- Estímulo: Petición HTTP con intentos de inyección SQL
- Entorno: Microservicio en producción con firewall de aplicaciones web activo
- Artefacto: Servicio (SRV)
- Respuesta: Solicitud rechazada y notificación de alerta generada
- Medida de respuesta: 100% de inyecciones bloqueadas; tiempo de respuesta ≤ 500 ms

### Escenario 3

- Código del Escenario: SEG_INT01
- Descripción: Protección de la interfaz web frente a ataques de fuerza bruta
- Interesado: Ciudadanos/usuarios finales
- Atributo: Seguridad
- Origen del estímulo: Usuario malintencionado intentando múltiples inicios de sesión
- Estímulo: Más de 10 intentos de login fallidos consecutivos
- Entorno: Portal web y app móvil en producción con autenticación MFA
- Artefacto: Interfaz (INT)
- Respuesta: Bloqueo temporal de la cuenta y notificación al usuario legítimo
- Medida de respuesta: Bloqueo automático ≤ 2 segundos después del 10º intento fallido

### Escenario 4

- Código del Escenario: SEG_COD01
- Descripción: Cifrado de datos sensibles en código fuente y transporte
- Interesado: Administrador de TI / auditor
- Atributo: Seguridad
- Origen del estímulo: Lectura o transferencia de datos sensibles
- Estímulo: Solicitud de información personal o financiera del usuario
- Entorno: Aplicación en producción con TLS y cifrado AES-256
- Artefacto: Código fuente (COD)
- Respuesta: Los datos son cifrados y transmitidos de forma segura
- Medida de respuesta: 100% de datos cifrados; tiempo de cifrado ≤ 5 ms por registro

## Disponibilidad
### Escenario 1

- Código del Escenario: DISP_BD01
- Descripción: Alta disponibilidad de la base de datos principal durante horas pico
- Interesado: Administrador de base de datos
- Atributo: Disponibilidad
- Origen del estímulo: Usuarios concurrentes realizando transacciones críticas
- Estímulo: 10,000 transacciones concurrentes por minuto sobre la base de datos principal
- Entorno: Sistema operativo y DBMS en producción, con replicación activa en cluster
- Artefacto: Base de datos (BD)
- Respuesta: Todas las transacciones son aceptadas y procesadas sin fallos de conexión
- Medida de respuesta: ≥ 99.95% de transacciones exitosas durante el periodo de 1 hora

### Escenario 2

- Código del Escenario: DISP_SRV01
- Descripción: Disponibilidad de servicio de facturación unificada ante caída de un nodo
- Interesado: Operador del sistema
- Atributo: Disponibilidad
- Origen del estímulo: Falla de un contenedor de microservicio en producción
- Estímulo: Caída del nodo que ejecuta el servicio de facturación
- Entorno: Cluster de microservicios desplegado en Kubernetes con balanceo de carga activo
- Artefacto: Servicio (SRV)
- Respuesta: El sistema redirige automáticamente las solicitudes a nodos activos y mantiene el servicio disponible
- Medida de respuesta: 100% de disponibilidad para usuarios finales, con tiempo de failover ≤ 2 segundos

### Escenario 3

- Código del Escenario: DISP_INS01
- Descripción: Disponibilidad de instancias de servidor ante mantenimiento programado
- Interesado: Administrador de infraestructura
- Atributo: Disponibilidad
- Origen del estímulo: Mantenimiento programado de servidores físicos
- Estímulo: Servidores de aplicación apagados para actualizaciones durante 30 minutos
- Entorno: Balanceador de carga distribuido con instancias redundantes activas
- Artefacto: Instancia (INS)
- Respuesta: Los usuarios continúan accediendo al sistema sin interrupción
- Medida de respuesta: ≤ 0.1% de solicitudes fallidas durante el periodo de mantenimiento

### Escenario 4

- Código del Escenario: DISP_RED01
- Descripción: Disponibilidad de la red interna durante picos de tráfico
- Interesado: Administrador de red
- Atributo: Disponibilidad
- Origen del estímulo: Incremento súbito de tráfico en horas de pago masivo
- Estímulo: 50,000 solicitudes simultáneas desde app móvil y portal web
- Entorno: Red corporativa con balanceadores y redundancia de enlaces
- Artefacto: Red (RED)
- Respuesta: Todos los paquetes se enrutan correctamente sin pérdida significativa
- Medida de respuesta: Pérdida de paquetes ≤ 0.5% y latencia promedio ≤ 50 ms durante el pico

## Interoperabilidad
### Escenario 1

- Código del Escenario: INT_SRV01
- Descripción: Integración del servicio de facturación unificada con sistemas legados
- Interesado: Operador de TI
- Atributo: Interoperabilidad
- Origen del estímulo: Petición de facturación desde portal web
- Estímulo: Solicitud de saldo para ciudadano
- Entorno: Microservicio consumiendo API adaptada del mainframe
- Artefacto: Servicio (SRV)
- Respuesta: Datos del mainframe se entregan en formato JSON estandarizado
- Medida de respuesta: 100% de respuestas conformes al estándar; tiempo ≤ 2 segundos

### Escenario 2

- Código del Escenario: INT_BD01
- Descripción: Consulta de datos entre microservicios heterogéneos
- Interesado: Desarrollador de backend
- Atributo: Interoperabilidad
- Origen del estímulo: Microservicio de dashboard solicitando datos de clientes y consumos
- Estímulo: Llamada API a múltiples bases de datos con distintos formatos
- Entorno: Sistema en operación normal, con transformadores de datos activos
- Artefacto: Base de datos (BD)
- Respuesta: Datos transformados a formato unificado y entregados al microservicio
- Medida de respuesta: ≥ 99% de datos correctamente transformados; tiempo de integración ≤ 3 segundos

### Escenario 3

- Código del Escenario: INT_RED01
- Descripción: Interoperabilidad con redes externas de pagos
- Interesado: Usuario final / operador de pagos
- Atributo: Interoperabilidad
- Origen del estímulo: Ciudadano realizando pago en línea
- Estímulo: Solicitud de pago a gateway externo
- Entorno: Conexión a red pública mediante protocolo seguro (HTTPS)
- Artefacto: Red (RED)
- Respuesta: Pago procesado y confirmado correctamente
- Medida de respuesta: 100% de pagos reconocidos; tiempo de confirmación ≤ 5 segundos

### Escenario 4

- Código del Escenario: INT_INS01
- Descripción: Orquestación de microservicios heterogéneos
- Interesado: Administrador de plataforma
- Atributo: Interoperabilidad
- Origen del estímulo: Evento de actualización de saldo de un ciudadano
- Estímulo: Microservicio de pagos publica evento a sistema de dashboard y notificaciones
- Entorno: Entorno productivo con colas de mensajes y middleware de eventos
- Artefacto: Instancia (INS)
- Respuesta: Todos los microservicios suscritos reciben el evento y actualizan información
- Medida de respuesta: ≥ 99.9% de eventos entregados sin pérdida; latencia ≤ 1 segundo

## Escalabilidad
### Escenario 1

- Código del Escenario: ESC_SRV01
- Descripción: Escalabilidad horizontal del servicio de facturación
- Interesado: Administrador de plataforma
- Atributo: Escalabilidad
- Origen del estímulo: Incremento repentino de solicitudes de facturación
- Estímulo: 20,000 peticiones concurrentes por minuto
- Entorno: Microservicio desplegado en cluster con orquestación automática
- Artefacto: Servicio (SRV)
- Respuesta: Nuevas instancias se despliegan automáticamente para absorber la carga
- Medida de respuesta: ≥ 99.9% de solicitudes procesadas; tiempo de provisioning ≤ 30 segundos por instancia

### Escenario 2

- Código del Escenario: ESC_BD01
- Descripción: Escalabilidad de la base de datos ante aumento de transacciones
- Interesado: Administrador de base de datos
- Atributo: Escalabilidad
- Origen del estímulo: Mayor número de ciudadanos realizando pagos simultáneamente
- Estímulo: 50,000 transacciones simultáneas durante hora pico
- Entorno: Base de datos con replicación y particionamiento activo
- Artefacto: Base de datos (BD)
- Respuesta: La base de datos escala mediante shards o réplicas sin degradar rendimiento
- Medida de respuesta: Tiempo de transacción ≤ 3 segundos; disponibilidad ≥ 99.95%
  
### Escenario 3

- Código del Escenario: ESC_INS02
- Descripción: Escalabilidad de instancias de servidor bajo alta carga
- Interesado: Administrador de infraestructura
- Atributo: Escalabilidad
- Origen del estímulo: Incremento súbito de usuarios durante promoción o campaña
- Estímulo: 100,000 usuarios simultáneos accediendo a la app y portal
- Entorno: Entorno cloud con auto-scaling habilitado
- Artefacto: Instancia (INS)
- Respuesta: Se agregan automáticamente instancias adicionales para soportar la carga
- Medida de respuesta: ≤ 1% de solicitudes rechazadas; tiempo de escalado ≤ 60 segundos

### Escenario 4

- Código del Escenario: ESC_BAL01
- Descripción: Escalabilidad del balanceador de carga ante picos de tráfico
- Interesado: Administrador de red
- Atributo: Escalabilidad
- Origen del estímulo: Aumento repentino de peticiones a múltiples microservicios
- Estímulo: 200,000 solicitudes simultáneas por minuto
- Entorno: Cluster con balanceadores redundantes y autoescalado habilitado
- Artefacto: Balanceador (BAL)
- Respuesta: Se distribuye la carga entre nuevas instancias de balanceador y servicios
- Medida de respuesta: ≤ 0.5% de solicitudes fallidas; latencia de enrutamiento ≤ 50 ms

## Performance/Rendimiento
### Escenario 1

- Código del Escenario: PER_BD01
- Descripción: Tiempo de respuesta de consultas complejas en base de datos
- Interesado: Usuario final
- Atributo: Performance
- Origen del estímulo: Usuario ejecutando consulta compleja
- Estímulo: Consulta SQL con múltiples JOINs sobre 1M+ registros
- Entorno: Sistema en operación normal con 70% de carga
- Artefacto: Base de datos (BD)
- Respuesta: La consulta se ejecuta y retorna resultados
- Medida de respuesta: Tiempo de respuesta ≤ 3 segundos en el 95% de los casos

### Escenario 2

- Código del Escenario: PER_SRV01
- Descripción: Rendimiento de microservicio de facturación unificada
- Interesado: Ciudadano
- Atributo: Performance
- Origen del estímulo: Solicitud de pago simultánea desde app móvil y portal web
- Estímulo: 5,000 transacciones concurrentes
- Entorno: Cluster de microservicios en Kubernetes con balanceo de carga
- Artefacto: Servicio (SRV)
- Respuesta: Todas las solicitudes procesadas correctamente
- Medida de respuesta: Tiempo promedio ≤ 2 segundos por transacción; ≥ 99.5% de solicitudes exitosas

### Escenario 3

- Código del Escenario: PER_INT01
- Descripción: Tiempo de respuesta de la interfaz web
- Interesado: Ciudadano
- Atributo: Performance
- Origen del estímulo: Usuario navegando portal web con dashboard
- Estímulo: Petición de carga del dashboard con datos consolidados
- Entorno: Sistema en operación normal, 50% de concurrencia
- Artefacto: Interfaz (INT)
- Respuesta: Dashboard cargado completamente
- Medida de respuesta: Tiempo de carga ≤ 3 segundos en 95% de los casos

### Escenario 4

- Código del Escenario: PER_RED01
- Descripción: Latencia en la red durante picos de tráfico
- Interesado: Administrador de red
- Atributo: Performance
- Origen del estímulo: Solicitudes concurrentes de usuarios
- Estímulo: 50,000 solicitudes simultáneas desde app y portal
- Entorno: Red corporativa con balanceadores activos
- Artefacto: Red (RED)
- Respuesta: Paquetes entregados sin pérdida significativa
- Medida de respuesta: Latencia promedio ≤ 50 ms; pérdida de paquetes ≤ 0.5% durante el pico
