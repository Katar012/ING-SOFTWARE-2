output "app_url" {
  description = "URL de la aplicación desplegada"
  value       = "https://${render_service.serviciudadcali_app.name}.onrender.com"
}

output "service_id" {
  description = "ID del servicio en Render"
  value       = render_service.serviciudadcali_app.id
}

output "service_name" {
  description = "Nombre del servicio en Render"
  value       = render_service.serviciudadcali_app.name
}