## 6. Requerimientos Funcionales del Sistema

## Requerimiento Funcional 1
**RF-001**: Consulta Unificada de Saldos

Descripción:
El sistema deberá permitir a los ciudadanos autenticados consultar, desde un único portal web o app móvil, el saldo actual pendiente por cada uno de los servicios públicos contratados (energía, acueducto y telecomunicaciones), mostrando un resumen claro y consolidado de los valores.

Criterios de validación:

El usuario autenticado puede ver los saldos pendientes para cada servicio.

Se muestra un total acumulado y los montos por servicio.

El dato debe actualizarse en tiempo real o con un retraso máximo de 15 minutos.

Manejo adecuado de servicios no disponibles.

Prioridad: Alta
Dependencias: RF-002, RF-003, sistemas legados

## Requerimiento Funcional 2
**RF-002**: Realización de Pagos en Línea

Descripción:
El sistema deberá permitir a los ciudadanos realizar pagos unificados de sus servicios públicos desde el portal o app móvil, con soporte para múltiples métodos de pago (tarjeta débito/crédito, PSE).

Criterios de validación:

El usuario puede seleccionar uno o más servicios para pagar.

El sistema integra pasarela de pagos segura y funcional.

Confirmación inmediata o diferida (máximo 15 min) de la transacción.

Generación automática de comprobante digital.

Actualización de saldos posterior al pago exitoso.

Prioridad: Alta
Dependencias: RF-001, integración con pasarela de pagos, sistemas legados

## Requerimiento Funcional 3
**RF-003**: Autenticación Segura de Ciudadanos

Descripción:
El sistema deberá contar con un mecanismo de autenticación para ciudadanos basado en correo electrónico y/o número de identificación, con validación de identidad mediante contraseña y segundo factor opcional (OTP).

Criterios de validación:

El sistema valida credenciales del usuario.

Permite recuperación de contraseña mediante correo.

Maneja sesión segura (token con expiración).

Soporta doble autenticación como configuración opcional.

Prioridad: Alta
Dependencias: Sistema de identidad, RF-001, RF-002

## Requerimiento Funcional 4
**RF-004**: Reporte de Fallas en Tiempo Real

Descripción:
El sistema deberá permitir a los ciudadanos reportar fallas en el servicio (apagones, fugas de agua, caídas de internet) a través de la app o portal, incluyendo ubicación, descripción y tipo de incidente.

Criterios de validación:

El usuario puede seleccionar el tipo de fallo.

Puede adjuntar ubicación y comentario opcional.

Se genera automáticamente un ticket interno para la unidad correspondiente.

Confirmación inmediata y trazabilidad del reporte.

Prioridad: Alta
Dependencias: Sistema de enrutamiento de tickets, RF-003

## Requerimiento Funcional 5
**RF-005**: Notificaciones Automáticas Multicanal

Descripción:
El sistema deberá enviar notificaciones automáticas por SMS, correo electrónico o notificaciones push ante eventos relevantes (confirmación de pago, cortes programados, incidentes masivos, etc.).

Criterios de validación:

El usuario puede configurar canales de notificación.

Se envía una notificación al generar un evento relevante.

Se registra la entrega y recepción del mensaje.

Prioridad: Media
Dependencias: Motor de eventos, RF-002, RF-004

## Requerimiento Funcional 6
**RF-006**: Visualización de Historial de Facturación

Descripción:
Los usuarios podrán consultar su historial de facturación de los últimos 12 meses por cada servicio, incluyendo consumo detallado, fecha de corte, pagos realizados y estado.

Criterios de validación:

Listado de facturas ordenadas por fecha.

Visualización y descarga en PDF.

Filtrado por servicio o fecha.

Acceso restringido a usuarios autenticados.

Prioridad: Media
Dependencias: RF-003, sistemas legados, RF-002

## Requerimiento Funcional 7
**RF-007**: Panel Administrativo de Gestión y Monitoreo

Descripción:
El sistema deberá contar con un panel administrativo exclusivo para operadores y administradores, donde podrán monitorear eventos, tickets, estadísticas de uso y estado de sistemas integrados.

Criterios de validación:

Visualización de reportes por servicio y fecha.

Gestión de incidentes y fallas reportadas.

Acceso limitado según perfil de usuario.

Permite consulta de trazabilidad de interacciones.

Prioridad: Alta
Dependencias: RF-004, RF-005, control de accesos

## Requerimiento Funcional 8
**RF-008**: Sincronización Asíncrona con Sistemas Legados

Descripción:
El sistema deberá permitir la integración de datos de los sistemas legados mediante sincronización asíncrona y desacoplada, garantizando la disponibilidad y consistencia eventual de la información expuesta al usuario.

Criterios de validación:

Se construye una vista local agregada del cliente en un servicio intermedio.

Los datos son sincronizados por eventos o procesos periódicos.

La vista se invalida y actualiza automáticamente ante eventos críticos (pagos, fallas, etc.).

Prioridad: Alta
Dependencias: RF-001, RF-002, RF-006, sistemas legados

## Requerimiento Funcional 9
**RF-009**: Gestión de Perfiles y Preferencias del Usuario

Descripción:
El sistema permitirá a los ciudadanos gestionar sus datos personales, métodos de contacto, servicios activos y preferencias de notificación desde un panel de usuario.

Criterios de validación:

Edición de datos básicos y contactos.

Configuración de servicios contratados.

Activación/desactivación de notificaciones.

Cambios auditados y trazables.

Prioridad: Media
Dependencias: RF-003, RF-005

## Requerimiento Funcional 10
**RF-010**: Disponibilidad Continua con Manejo de Mantenimiento

Descripción:
El sistema deberá garantizar una disponibilidad 24/7, implementando mecanismos de manejo de errores y mensajes de mantenimiento cuando los sistemas legados no estén disponibles.

Criterios de validación:

Visualización de mensajes amigables durante mantenimientos.

Registro de intentos fallidos para posterior reintento.

Servicios críticos se mantienen funcionales aunque otros estén caídos.

Prioridad: Alta
Dependencias: RF-001, RF-002, sistemas legados
