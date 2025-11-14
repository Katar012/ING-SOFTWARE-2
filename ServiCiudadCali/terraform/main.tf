terraform {
  required_version = ">= 1.5.0"
  required_providers {
    render = {
      source  = "render-oss/render"
      version = ">= 1.7.0"
    }
  }
}

provider "render" {
  api_key  = var.render_api_key
  owner_id = var.render_owner_id
}

resource "render_web_service" "api" {
  name   = var.app_name
  plan   = var.render_plan
  region = var.render_region

  runtime_source = {
    type = "git"
    docker = {
      dockerfile_path = "ServiCiudadCali/Dockerfile"
      repo_url        = var.github_repo
      branch          = var.github_branch
      root_dir        = "ServiCiudadCali"
    }
  }

  env_vars = {
    SPRING_PROFILES_ACTIVE     = { value = "prod" }
    SPRING_DATASOURCE_URL      = { value = var.jdbc_url }
    SPRING_DATASOURCE_USERNAME = { value = var.db_username }
    SPRING_DATASOURCE_PASSWORD = { value = var.db_password }
    SERVER_PORT                = { value = "8080" }
  }
}
