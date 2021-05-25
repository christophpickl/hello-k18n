rootProject.name = "hello-k8s"

include(
    "commons",
    "commons:common-server",
    "users-service",
    "users-service:service-impl",
    "books-service",
    "books-service:service-impl",
)
