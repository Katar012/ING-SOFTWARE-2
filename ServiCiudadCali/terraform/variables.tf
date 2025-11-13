variable "render_api_key" {
  description = "API Key de Render"
  type        = string
  sensitive   = true
}

variable "database_url" {
  description = "JDBC URL de MySQL desde Railway"
  type        = string
  sensitive   = true
}

variable "db_username" {
  description = "Usuario de MySQL"
  type        = string
  sensitive   = true
  default     = "root"
}

variable "db_password" {
  description = "Contraseña de MySQL"
  type        = string
  sensitive   = true
}

variable "github_repo" {
  description = "URL del repositorio de GitHub"
  type        = string
  default     = "https://github.com/Katar012/ING-SOFTWARE-2.git"
}

variable "app_name" {
  description = "Nombre de la aplicación"
  type        = string
  default     = "serviciudadcali"
}