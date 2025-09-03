## 7. Tácticas para Garantizar Atributos de Calidad

ÉPICA 1: Experiencia Unificada de Cliente

Como ciudadano,
quiero una vista consolidada de mis saldos, pagos y reporte de fallas desde un único portal o app,
para gestionar mis servicios públicos sin fragmentación.

Valor de negocio: Mejora la satisfacción del cliente y reduce consultas al call center.

Criterios de aceptación de épica:

Acceso consolidado desde un solo punto (portal/app).

Visualización de saldos, pagos e incidencias en una interfaz uniforme.

Respuesta actualizada y confiable.

Estimación: S (Pequeña)

Prioridad: 1 (Alta)

Historias de Usuario asociadas:

## HISTORIA DE USUARIO: HU‑1 – Consulta Unificada de Saldos
Como ciudadano autenticado,
quiero consultar mis saldos pendientes de energía, agua y telecomunicaciones,
para ver mis obligaciones financieras desde una única vista.

Criterios de aceptación:

DADO QUE estoy autenticado
CUANDO accedo a la sección de saldos
ENTONCES veo el total y el desglose por servicio

Y DADO QUE algún sistema legado está fuera de servicio
CUANDO intento consultar ese saldo
ENTONCES veo un mensaje de “Saldo no disponible temporalmente”

Definición de terminado:

[x] Código revisado

[x] Pruebas unitarias pasadas

[x] Pruebas de aceptación superadas

[x] Documentación actualizada

Estimación: 5 Story Points
Prioridad: Alta
Dependencias: HU‑2 (Pago en Línea), integración con sistemas legados

## HISTORIA DE USUARIO: HU‑2 – Pago en Línea Unificado
Como ciudadano,
quiero pagar uno o más servicios desde el portal/app,
para saldar mis deudas de una sola vez.

Criterios de aceptación:

DADO QUE tengo saldos pendientes
CUANDO selecciono servicios y completo el pago
ENTONCES recibo comprobante y saldo se actualiza

Y DADO QUE el pago falla
CUANDO finaliza el intento
ENTONCES recibo notificación de error y puedo reintentar

Definición de terminado: igual HU‑1
Estimación: 8 SP
Prioridad: Alta
Dependencias: HU‑1, pasarela de pagos

## HISTORIA DE USUARIO: HU‑3 – Reporte de Fallas
Como ciudadano,
quiero reportar fallas con geolocalización y descripción,
para que el sistema genere un ticket y pueda hacer seguimiento.

Criterios de aceptación:

DADO QUE informo una falla
CUANDO la envío
ENTONCES se crea ticket asociado al servicio y confirmación llega al usuario

Estimación: 5 SP
Prioridad: Alta
Dependencias: HU‑1, sistema de tickets interno

## HISTORIA DE USUARIO: HU‑4 – Notificaciones Automáticas
Como ciudadano,
quiero recibir notificaciones (SMS, email, push) sobre eventos relevantes,
para estar informado sin necesidad de revisar el portal/app.

Criterios de aceptación:

DADO QUE ocurre un evento relevante (pago, corte, falla)
CUANDO sucede
ENTONCES recibo notificación según mis preferencias

Estimación: 5 SP
Prioridad: Media
Dependencias: HU‑2, HU‑3

## HISTORIA DE USUARIO: HU‑5 – Panel Administrativo
Como operador interno o administrador,
quiero monitorear eventos, tickets y estado de servicios,
para operatividad y seguimiento eficiente.

Criterios de aceptación:

DADO QUE estoy autenticado como administrador
CUANDO accedo al panel
ENTONCES veo estadísticas, tickets abiertos y estado del sistema

Estimación: 8 SP
Prioridad: Alta
Dependencias: HU‑3, HU‑4, monitoreo de sistemas legados