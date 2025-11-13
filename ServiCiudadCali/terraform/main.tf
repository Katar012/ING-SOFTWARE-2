terraform {
  required_version = ">= 1.0"

  required_providers {
    render = {
      source  = "renderinc/render"
      version = "~> 0.1.0"
    }
  }
}

provider "render" {
  api_key = var.render_api_key
}

resource "render_service" "serviciudadcali_app" {
  name        = var.app_name
  type        = "web_service"
  repo        = var.github_repo
  env         = "docker"
  plan        = "starter"
  branch      = "Vila"
  auto_deploy = true

  env_vars = {
    SPRING_DATASOURCE_URL      = var.database_url
    SPRING_DATASOURCE_USERNAME = var.db_username
    SPRING_DATASOURCE_PASSWORD = var.db_password
    SPRING_PROFILES_ACTIVE     = "prod"
    SPRING_JPA_SHOW_SQL        = "false"
    SERVER_PORT                = "8080"
  }


}
