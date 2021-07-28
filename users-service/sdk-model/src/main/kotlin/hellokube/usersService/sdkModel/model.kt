package hellokube.usersService.sdkModel

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
) {
    companion object
}
