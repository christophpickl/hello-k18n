rootProject.name = "hello-k8s"

include(
    "build-tools",
    "commons",
    "commons:common-test",
    "commons:common-server",
    "commons:common-server-test",
    "users-service",
    "users-service:service-impl",
    "users-service:sdk-client",
    "users-service:sdk-model",
    "books-service",
    "books-service:service-impl",
)
