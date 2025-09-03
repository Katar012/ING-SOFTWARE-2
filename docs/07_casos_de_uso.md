## 7. Casos de Uso

## Caso de Uso 1
**CU-001** – Consultar saldos de servicios

Actor principal: Ciudadano
Actores secundarios: Capa de integración, sistemas legados (mainframe, Oracle Solaris, telecomunicaciones)

Descripción:
Permite al ciudadano consultar el saldo pendiente de pago en servicios públicos (energía, acueducto y telecomunicaciones) a través del portal web o app móvil, con una vista unificada y amigable.

**Precondiciones**:

- El ciudadano debe estar autenticado.

- El sistema debe tener conectividad con los sistemas legados.

**Flujo principal**:

1. El ciudadano accede a la plataforma y se autentica.

2. Selecciona la opción “Consultar saldos”.

3. El sistema solicita a la capa de integración los saldos pendientes de cada servicio.

4. La capa de integración consulta los saldos en los respectivos sistemas legados.

5. Los datos se transforman y normalizan en formato unificado.

6. El sistema presenta al ciudadano:

- Monto total a pagar.

- Monto por cada servicio.

7. El ciudadano visualiza la información y puede optar por realizar un pago (CU-002).

**Flujos alternativos**:

3A. Si uno o más sistemas legados están fuera de servicio, se muestra un mensaje: “Información no disponible temporalmente para X servicio”.

6A. Si no existen saldos pendientes, se muestra: “Actualmente no tiene saldos pendientes.”

**Reglas de negocio**:

Todos los saldos deben estar actualizados con una antigüedad máxima de 15 minutos.

Se debe garantizar la integridad de los datos usando la capa anticorrupción.

**Postcondiciones**:

El ciudadano obtiene la información actualizada de sus saldos.

## Caso de Uso 2
**CU-002** – Realizar pago en línea

Actor principal: Ciudadano
Actores secundarios: Plataforma de pagos, capa de integración, sistemas de facturación legados

Descripción:
El ciudadano puede pagar uno o más saldos desde la plataforma mediante medios de pago electrónicos habilitados.

**Precondiciones**:

- Debe existir saldo pendiente.

- El ciudadano debe estar autenticado.

- La plataforma de pagos debe estar operativa.

**Flujo principal**:

1. El ciudadano, desde la vista de saldos (CU-001), selecciona el botón “Pagar ahora”.

2. Elige uno o más servicios a pagar.

3. Selecciona el método de pago (PSE, tarjeta, billetera, etc.).

4. El sistema redirige a la pasarela de pagos.

5. Se realiza la transacción.

6. El sistema valida la respuesta de la pasarela.

7. Si es exitosa:

-  Se genera el comprobante.

-  Se registra el pago en el sistema correspondiente.

8. El ciudadano visualiza el comprobante y puede descargarlo.

**Flujos alternativos**:

5A. Si la transacción es rechazada, se notifica al usuario con el código de error.

7A. Si el sistema de facturación no recibe el pago correctamente, se guarda en cola para reintento y se notifica al usuario.

**Reglas de negocio**:

No se permite el pago parcial de un mismo servicio.

Debe garantizarse la trazabilidad del pago.

Se deben registrar los pagos en los sistemas legados mediante la capa anticorrupción.

**Postcondiciones**:

El pago queda registrado en el sistema y se genera un comprobante único.

## Caso de Uso 3
**CU-003** – Autenticación de usuarios ciudadanos

Actor principal: Ciudadano
Actores secundarios: Plataforma de identidad, capa de seguridad

Descripción:
Permite que un ciudadano acceda a la plataforma mediante un sistema de autenticación único.

**Precondiciones**:

- El usuario debe estar registrado en el sistema.

- La plataforma debe estar conectada al servicio de identidad.

**Flujo principal**:

1. El usuario ingresa a la plataforma.

2. Digita su número de documento y clave o usa autenticación electrónica (Ej. ID Digital).

3. El sistema valida las credenciales.

4. Si son correctas, redirige a la vista principal de servicios.

**Flujos alternativos**:

3A. Si las credenciales son incorrectas, se muestra mensaje de error.

3B. Si hay múltiples intentos fallidos, se bloquea la cuenta temporalmente.

**Reglas de negocio**:

El sistema debe registrar todos los intentos de acceso.

El acceso debe cumplir con los lineamientos de seguridad nacionales (Ej: MinTIC).

**Postcondiciones**:

El ciudadano queda autenticado y puede usar la plataforma.

## Caso de Uso 4
**CU-004** – Consulta técnica y monitoreo de disponibilidad

Actor principal: Técnico de campo / Administrador
Actores secundarios: Sistema de monitoreo, mainframe, microservicios

Descripción:
Permite a los operadores técnicos y administradores visualizar en tiempo real la disponibilidad de los sistemas legados y microservicios.

**Precondiciones**:

- Usuario debe ser técnico o administrador autenticado.

- El sistema debe estar monitoreando los servicios vía pings o APIs.

**Flujo principal**:

1. El técnico accede al panel de monitoreo.

2. El sistema muestra estado actual de cada componente:

- Mainframe

- Servicios Oracle

- Servicios de telecomunicaciones

- Microservicios

3. El técnico puede consultar métricas (tiempo de respuesta, disponibilidad, errores).

4. Se pueden generar alertas automáticas si un sistema está caído o con respuesta degradada.

**Flujos alternativos**:

2A. Si el sistema de monitoreo falla, se muestra un mensaje de error y se reintenta la conexión.

**Reglas de negocio**:

Se deben guardar logs de disponibilidad y eventos críticos.

El tiempo de inactividad debe ser medido por componente.

**Postcondiciones**:

El técnico puede diagnosticar fallas y generar alertas preventivas.

## Caso de Uso 5
**CU-005** – Validación y registro de pagos en sistemas legados

Actor principal: Capa de integración
Actores secundarios: Administrador, técnicos de campo, sistemas legados

Descripción:
Automatiza la validación de los pagos recibidos por el portal y su posterior inserción en los sistemas legados.

**Precondiciones**:

- Debe existir un pago exitoso registrado.

- El sistema legado correspondiente debe estar disponible.

**Flujo principal**:

1. La capa de integración recibe el pago desde la pasarela.

2. Valida la estructura de los datos y el hash de integridad.

3. Convierte los datos al formato requerido por el sistema legado.

4.Envía el registro de pago al sistema.

5. El sistema legado responde con estado OK.

6. El sistema guarda el estado como "confirmado".

**Flujos alternativos**:

4A. Si el sistema legado no responde, se almacena el pago en cola de reintento.

5A. Si el sistema legado devuelve error, se genera una alerta para intervención manual.

**Reglas de negocio**:

Los datos deben validarse antes de la inserción.

Solo se realizan hasta 3 reintentos automáticos. Luego se debe revisar manualmente.

**Postcondiciones**:

El pago queda registrado correctamente o se gestiona como incidente.

## Caso de Uso 6
**CU-006** – Administración de servicios y componentes

**Actor principal**: Administrador
Actores secundarios: Técnicos de campo, microservicios

**Descripción**:
Permite gestionar el estado y configuración de los servicios disponibles en la plataforma (alta, baja, mantenimiento, actualización).

**Precondiciones**:

- Usuario debe tener rol de administrador.

- El microservicio debe estar expuesto para configuración remota.

**Flujo principal**:

1. El administrador accede al panel de servicios.

2. Selecciona un componente o microservicio.

3. Visualiza su estado actual (activo, inactivo, error, mantenimiento).

4. Puede activar, desactivar o actualizar configuración.

5. El sistema guarda cambios y propaga la nueva configuración.

**Flujos alternativos**:

4A. Si no hay conectividad con el microservicio, se bloquea la operación.

**Reglas de negocio**:

Toda configuración debe quedar registrada con timestamp y usuario.

Las operaciones críticas deben requerir autenticación de segundo factor.

**Postcondiciones**:

El estado del componente queda actualizado en la plataforma.
