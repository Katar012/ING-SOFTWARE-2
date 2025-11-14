variable "render_api_key"  {
  type = string 
  sensitive = true 
  }
variable "render_owner_id" {
   type = string
   sensitive = true 
   }

variable "app_name"      {
  type = string
  default = "serviciudadcali"
  }
variable "github_repo"   {
  type = string
  default = "https://github.com/Katar012/ING-SOFTWARE-2.git"
  }
variable "github_branch" {
  type = string
  default = "Vila"
  }
variable "render_plan"   {
  type = string
  default = "starter"
  }
variable "render_region" {
  type = string
  default = "oregon"
  }

# IMPORTANTE: usa formato JDBC correcto
variable "jdbc_url" {
  type = string
  sensitive = true 
  }   # ej: jdbc:mysql://host:port/db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
variable "db_username" {
  type = string
  sensitive = true
  }
variable "db_password" {
  type = string
  sensitive = true
  }