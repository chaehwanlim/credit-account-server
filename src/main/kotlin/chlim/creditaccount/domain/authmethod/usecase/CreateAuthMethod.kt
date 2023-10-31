package chlim.creditaccount.domain.authmethod.usecase

import chlim.creditaccount.domain.authmethod.AuthMethod
import chlim.creditaccount.domain.authmethod.exception.AuthMethodAlreadyExistsException
import chlim.creditaccount.domain.authmethod.repository.AuthMethodRepository
import chlim.creditaccount.domain.authmethod.usecase.command.CreateAuthMethodCommand
import chlim.creditaccount.domain.user.exception.UserNotFoundException
import chlim.creditaccount.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateAuthMethod(
    private val userRepository: UserRepository,
    private val authMethodRepository: AuthMethodRepository
) {

    @Transactional
    fun execute(command: CreateAuthMethodCommand) {
        val user = userRepository.findById(command.userId)
            .orElseThrow { UserNotFoundException(command.userId) }

        authMethodRepository.findByResourceIdAndType(command.resourceId, command.type)?.let {
            throw AuthMethodAlreadyExistsException(command.toString())
        }

        val authMethod = AuthMethod(
            user = user,
            resourceId = command.resourceId,
            type = command.type
        )

        authMethodRepository.save(authMethod)
    }
}
