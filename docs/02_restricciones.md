# 2. Restricciones

## Restricciones Técnicas

- **Silos de datos intocables:**  
  Cada sistema legado funciona como una caja negra. No existen APIs expuestas y la única integración posible es mediante procesos batch nocturnos que intercambian archivos planos (TXT, CSV, EBCDIC en el caso del mainframe).  

- **Indisponibilidad programada:**  
  El mainframe de Energía requiere una ventana de mantenimiento de 4 horas cada domingo en la madrugada, lo cual entra en conflicto con la necesidad de ofrecer servicios 24/7.  

- **Tecnología obsoleta:**  
  La lógica de negocio crítica está implementada en programas COBOL y PL/SQL de hace décadas. El conocimiento de estas tecnologías se encuentra en riesgo debido a la jubilación de los ingenieros más antiguos.  

## Restricciones de Tiempo

- **Plazo de entrega:**  
  La Alcaldía estableció un plazo de **4 meses** para el lanzamiento del nuevo portal unificado y la aplicación móvil.  

- **Agilidad obligatoria:**  
  Proyectos anteriores como la “factura unificada” fracasaron tras 18 meses de implementación. El tiempo disponible exige una arquitectura flexible, desacoplada y que permita entregas incrementales.

  Estas restricciones definiran las decisiones de arquitectura para el desarrollo del proyecto ServiCiudad Conectada, asegurando una alineacion a los estandares y necesidades de la empresa.
