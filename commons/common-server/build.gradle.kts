
dependencies {
    api(Dependencies.Ktor.Core)
    api(Dependencies.Ktor.Netty) {
        excludeStdlibJdk7()
    }
    implementation(Dependencies.Klogging)
}
