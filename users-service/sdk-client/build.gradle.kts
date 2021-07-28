dependencies {
    api(project(":users-service:sdk-model"))

    api(Dependencies.Arrow.Core)
    implementation(Dependencies.Ktor.Client)
    implementation(Dependencies.Ktor.ClientLogging)
    implementation(Dependencies.Ktor.ClientApache)
    implementation(Dependencies.Klogging)
}
