package chlim.creditaccount.domain.user.usecase

import chlim.creditaccount.domain.user.User
import chlim.creditaccount.domain.user.repository.UserRepository
import chlim.creditaccount.domain.user.usecase.command.CreateUserCommand
import chlim.creditaccount.domain.user.usecase.result.UserResult
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CreateUser(
    private val userRepository: UserRepository
) {

    @Transactional
    fun execute(command: CreateUserCommand): UserResult {
        userRepository.findByPhoneNumber(command.phoneNumber)?.let {
            throw IllegalArgumentException("이미 존재하는 유저입니다.")
        }

        val user = run {
            val user = User(
                name = command.name,
                email = command.email,
                phoneNumber = command.phoneNumber
            )

            userRepository.save(user)
        }

        return UserResult(
            name = user.name,
            email = user.email,
            phoneNumber = user.phoneNumber.value
        )
    }
}
