package chlim.creditaccount.domain.user.usecase

import chlim.creditaccount.domain.user.User
import chlim.creditaccount.domain.user.exception.UserAlreadyExistsException
import chlim.creditaccount.domain.user.repository.UserRepository
import chlim.creditaccount.domain.user.usecase.command.CreateUserCommand
import chlim.creditaccount.domain.user.usecase.result.UserResult
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateUser(
    private val userRepository: UserRepository
) {

    @Transactional
    fun execute(command: CreateUserCommand): UserResult {
        if (userRepository.findByEmail(command.email) != null) {
            throw UserAlreadyExistsException(command.toString())
        }

        val user = run {
            val user = User(
                name = command.name,
                email = command.email,
                phoneNumber = command.phoneNumber
            )

            userRepository.save(user)
        }

        return UserResult.from(user)
    }
}
