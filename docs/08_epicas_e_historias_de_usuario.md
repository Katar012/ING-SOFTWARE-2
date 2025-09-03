## 7. Tácticas para Garantizar Atributos de Calidad

### ÉPICA 1: Consulta de Saldos y Pagos en Línea

## HU1.1 – Consulta de saldos como ciudadano

Como ciudadano registrado,
quiero consultar mi saldo pendiente en servicios municipales (ej: agua, predial, etc.),
para saber cuánto debo pagar y en qué fecha.
Criterios de aceptación:

El sistema debe mostrar el detalle del saldo por servicio.

El sistema debe permitir consultar el historial de pagos.

El sistema debe validar la identidad del usuario.

## HU1.2 – Realización de pagos como ciudadano

Como ciudadano registrado,
quiero poder pagar mis servicios municipales en línea,
para cumplir mis obligaciones sin tener que desplazarme.
Criterios de aceptación:

Deben habilitarse medios de pago PSE, tarjeta crédito/débito y billeteras digitales.

Debe generarse confirmación del pago vía correo o notificación.

El pago debe reflejarse en tiempo real en el mainframe.

## HU1.3 – Consulta de pagos como operador interno

Como operador interno,
quiero ver los pagos realizados por los ciudadanos,
para poder hacer seguimiento y conciliación.
Criterios de aceptación:

El sistema debe permitir filtros por fecha, servicio, estado del pago.

Los datos deben obtenerse en tiempo real del mainframe.

Solo usuarios autorizados pueden acceder a esta información.

### ÉPICA 2: Integridad de Datos y Acceso Seguro

## HU2.1 – Validación de autenticidad de datos

Como administrador del sistema,
quiero asegurarme de que los datos que consultan los usuarios provienen del sistema oficial,
para garantizar la transparencia y evitar fraudes.
Criterios de aceptación:

El sistema debe mostrar un sello o verificación de integridad.

Todos los datos deben ser obtenidos a través de una capa anticorrupción validada.

Deben registrarse logs de acceso a datos.

## HU2.2 – Acceso seguro a través de autenticación multifactor (MFA)

Como ciudadano,
quiero acceder al sistema con doble verificación,
para proteger mi información personal y financiera.
Criterios de aceptación:

El sistema debe permitir autenticación por correo, SMS o app.

El sistema debe revocar accesos después de múltiples intentos fallidos.

El MFA debe estar disponible en web y app móvil.

### ÉPICA 3: Soporte Técnico y Gestión de Fallas

## HU3.1 – Reporte de fallas como técnico de campo

Como técnico de campo,
quiero reportar una falla directamente desde la app móvil,
para que el sistema la registre y el área correspondiente tome acción rápidamente.
Criterios de aceptación:

El reporte debe incluir ubicación GPS, foto y tipo de falla.

El reporte debe estar asociado a un ticket con seguimiento.

El sistema debe estar disponible sin conexión, sincronizando al reconectarse.

## HU3.2 – Seguimiento de fallas como operador

Como operador interno,
quiero ver todos los reportes de fallas y su estado,
para asignarlos y hacer trazabilidad en tiempo real.
Criterios de aceptación:

El sistema debe permitir actualizar el estado del ticket.

El sistema debe mostrar un dashboard con métricas por zona y tipo.

Solo operadores autorizados pueden modificar tickets.

### ÉPICA 4: Administración del Sistema

## HU4.1 – Creación y gestión de roles de usuarios

Como administrador,
quiero crear y asignar roles con permisos específicos,
para garantizar que cada usuario solo acceda a lo que le corresponde.
Criterios de aceptación:

El sistema debe permitir CRUD de roles.

Cada funcionalidad debe estar protegida por permisos.

Los logs deben registrar la creación/modificación de roles.

## HU4.2 – Auditoría de acciones del sistema

Como auditor,
quiero ver el historial de acciones del sistema (consultas, pagos, cambios, accesos),
para detectar anomalías o usos indebidos.
Criterios de aceptación:

Toda acción crítica debe estar auditada.

<<<<<<< HEAD:docs/07_epicas_e_historias_de_usuario.md
Los logs deben incluir fecha, usuario, IP y tipo de acción.

Debe existir un buscador para filtrar logs por usuario y fecha.

## HU4.3 – Visualización de reportes gerenciales

Como administrador de alto nivel,
quiero ver reportes y estadísticas del uso del sistema,
para tomar decisiones informadas sobre servicios digitales.
Criterios de aceptación:

El sistema debe mostrar KPIs como pagos realizados, fallas reportadas, consultas activas.

Los datos deben poder exportarse a Excel o PDF.

El sistema debe actualizar datos diariamente.


### ÉPICA 5: Monitoreo y Resiliencia de Sistemas

## HU5.1 – Monitoreo de disponibilidad de sistemas

Como administrador de TI,
quiero visualizar en tiempo real el estado de todos los sistemas legados y microservicios,
para anticipar fallas y garantizar la continuidad del servicio.
Criterios de aceptación:

Dashboard con estado de cada sistema (online, offline, degradado).

Alertas automáticas ante fallos o lentitud crítica.

Registro histórico de incidentes y tiempos de respuesta.

## HU5.2 – Manejo de fallos críticos mediante Circuit Breaker

Como administrador de TI,
quiero evitar que la lentitud o caída de un sistema legado impacte el portal/app,
para mantener la experiencia del usuario sin interrupciones.
Criterios de aceptación:

Configuración de límites de tiempo para llamadas a sistemas legados.

Fallos detectados disparan notificaciones y fallback automáticos.

Historial de eventos críticos registrado para auditoría.

### ÉPICA 6: Orquestación y Comunicación Eficiente

## HU6.1 – Notificaciones y difusión de eventos

Como sistema de notificaciones,
quiero difundir información crítica (apagones, cortes de agua, incidentes de telecom) a todos los usuarios afectados,
para mantener a la ciudadanía informada y mejorar la respuesta operativa.
Criterios de aceptación:

Notificaciones vía SMS, push y correo.

Actualización de mapa de incidencias en tiempo real.

Sistema desacoplado mediante patrón Publish-Subscribe.

## HU6.2 – Construcción de Dashboards unificados

Como ciudadano,
quiero ver en un solo panel mi saldo de energía, consumo de agua y datos de telecom,
para tomar decisiones rápidas y claras sobre mis servicios.
Criterios de aceptación:

Resumen consolidado de todos los servicios.

Datos obtenidos de forma consistente y con formato estandarizado (JSON).

Dashboard actualizado cada vez que hay cambios de saldo o consumo.

### ÉPICA 7: Gestión y Auditoría de Seguridad

## HU7.1 – Registro y auditoría de acciones del usuario

Como auditor,
quiero tener un registro completo de todas las acciones de usuarios y operadores,
para garantizar trazabilidad y cumplimiento normativo.
Criterios de aceptación:

Logs detallados: usuario, acción, fecha, hora, IP.

Accesibles solo a personal autorizado.

Exportación a formatos de análisis (CSV/PDF).

## HU7.2 – Control de roles y permisos

Como administrador,
quiero definir roles y permisos específicos para cada tipo de usuario,
para proteger la información y limitar accesos no autorizados.
Criterios de aceptación:

Roles configurables (ciudadano, operador, técnico, administrador).

Permisos aplicados a funcionalidades específicas.

Cambios registrados en logs de auditoría.

Con estas épicas e historias de usuario, se cubre la totalidad del alcance del proyecto “ServiCiudad Conectada”, incluyendo:

Ciudadano → Consulta, pagos, dashboard, notificaciones

Operadores internos → Seguimiento, tickets, monitoreo

Administradores → Seguridad, roles, auditoría, resiliencia

Sistema → Integración con legados, microservicios, orquestación

### ÉPICA 8: Integración con Sistemas Legados

## HU8.1 – Adaptador para sistema de energía (Mainframe)

Como microservicio de facturación,
quiero consultar el consumo de energía usando un adaptador que simule terminal 3270,
para obtener información del mainframe como si fuera una API REST moderna.
Criterios de aceptación:

Adaptador encapsula toda la complejidad del mainframe.

Respuestas estandarizadas en JSON.

Errores o tiempos de espera son manejados sin afectar al usuario.

## HU8.2 – Transformación de datos del sistema de acueducto

Como microservicio de facturación unificada,
quiero convertir reportes de texto de ancho fijo a JSON,
para que el sistema pueda procesar la información de manera estandarizada.
Criterios de aceptación:

Todos los reportes convertidos correctamente.

Validación de consistencia de datos.

Logs de errores y excepciones disponibles para auditoría.

### ÉPICA 9: Enrutamiento y Coordinación de Servicios

## HU9.1 – Enrutamiento de solicitudes de soporte

Como portal/app,
quiero dirigir automáticamente las solicitudes de soporte al sistema de tickets correspondiente según el servicio,
para que cada incidencia llegue al área correcta sin intervención manual.
Criterios de aceptación:

Reglas de enrutamiento definidas por tipo de servicio.

Confirmación de recepción del ticket al usuario.

Fallback en caso de error en el sistema de tickets.

## HU9.2 – Publicación de eventos críticos (apagones, cortes, incidencias)

Como microservicio de eventos,
quiero notificar a todos los usuarios afectados y actualizar sistemas internos de manera desacoplada,
para garantizar respuesta rápida ante incidentes críticos.
Criterios de aceptación:

Patrón Publish-Subscribe implementado.

Notificaciones vía SMS, push y correo.

Mapas de incidencia actualizados en tiempo real.

### ÉPICA 10: Persistencia y Sincronización de Datos

## HU10.1 – Base de datos por servicio

Como microservicio de clientes,
quiero tener mi propia base de datos sincronizada con los sistemas legados,
para proporcionar información rápida y confiable sin afectar los sistemas principales.
Criterios de aceptación:

Datos sincronizados mediante eventos o Sagas.

Consultas rápidas sin degradar rendimiento del mainframe.

Integridad de datos garantizada mediante validaciones automáticas.

## HU10.2 – Construcción de objetos de respuesta compuestos (DashboardDTO)

Como microservicio de dashboard,
quiero construir un objeto que combine saldos de energía, agua y telecom,
para mostrar la información de manera legible y mantenible en la app/web.
Criterios de aceptación:

Objeto consistente y actualizado con cada cambio de saldo o consumo.

Implementación mediante patrón Builder para facilidad de mantenimiento.

Rendimiento optimizado para no bloquear la interfaz.
