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
