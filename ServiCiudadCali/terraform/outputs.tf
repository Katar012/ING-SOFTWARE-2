output "service_name" {
  description = "Nombre del servicio"
  value       = render_web_service.api.name
}

# Render suele mapear <name>.onrender.com; usa este 'guess' si el provider
# no expone directamente la URL del servicio.
output "app_url_guess" {
  description = "URL probable"
  value       = "https://${render_web_service.api.name}.onrender.com"
}
