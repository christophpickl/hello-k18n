package hellokube.booksService.serviceImpl

object BooksServiceDevApp {
    @JvmStatic
    fun main(args: Array<String>) {
        BooksServiceApp(port = 8082).start()
    }
}
