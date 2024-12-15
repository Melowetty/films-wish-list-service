package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.dto.UserDto
import ru.melowetty.filmswishlistservice.entity.UserEntity

@Component
class UserMapper {
    fun toDto(entity: UserEntity): UserDto {
        return UserDto(
            username = entity.username,
            provider = entity.provider,
            roles = entity.roles,
            language = entity.language,
            created = entity.created,
            edited = entity.modified,
        )
    }
}