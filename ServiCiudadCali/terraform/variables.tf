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
  default = "https://github.com/Katar012/ING-SOFTWARE-2"
  }
variable "github_branch" {
  type = string
  default = "main"
  }
variable "render_plan"   {
  type = string
  default = "starter"
  }
variable "render_region" {
  type = string
  default = "oregon"
  }

variable "jdbc_url" {
  type = string
  sensitive = true 
  }  
variable "db_username" {
  type = string
  sensitive = true
  }
variable "db_password" {
  type = string
  sensitive = true
  }