package hellokube.usersService.serviceImpl

object UsersServiceDevApp {
    @JvmStatic
    fun main(args: Array<String>) {
        UsersServiceApp(port = 8081).start()
    }
}