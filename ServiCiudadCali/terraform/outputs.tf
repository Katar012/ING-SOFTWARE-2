output "service_name" {
  description = "Nombre del servicio"
  value       = render_web_service.api.name
}

output "app_url_guess" {
  description = "URL probable"
  value       = "https://${render_web_service.api.name}.onrender.com"
}
