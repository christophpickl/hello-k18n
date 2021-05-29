dependencies {
    api(project(":commons:common-test"))
    api(Dependencies.Ktor.Test) {
        excludeKtorTest()
    }
}
