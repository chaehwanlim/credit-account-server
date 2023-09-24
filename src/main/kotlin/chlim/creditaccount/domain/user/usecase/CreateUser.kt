package chlim.creditaccount.domain.user.usecase

import chlim.creditaccount.domain.user.User
import chlim.creditaccount.domain.user.exception.UserAlreadyExistsException
import chlim.creditaccount.domain.user.repository.UserRepository
import chlim.creditaccount.domain.user.usecase.command.CreateUserCommand
import chlim.creditaccount.domain.user.usecase.result.UserResult
import org.springframework.stereotype.Service
import jakarta.transaction.Transactional

@Service
class CreateUser(
    private val userRepository: UserRepository
) {

    @Transactional
    fun execute(command: CreateUserCommand): UserResult {
        if (userRepository.findByPhoneNumber(command.phoneNumber) != null) {
            throw UserAlreadyExistsException(command.phoneNumber)
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
