package chlim.creditaccount.domain.store.usecase

import chlim.creditaccount.domain.store.Store
import chlim.creditaccount.domain.store.exception.StoreAlreadyExistsException
import chlim.creditaccount.domain.store.repository.StoreRepository
import chlim.creditaccount.domain.store.usecase.command.CreateStoreCommand
import chlim.creditaccount.domain.store.usecase.result.StoreResult
import chlim.creditaccount.domain.user.exception.UserNotFoundException
import chlim.creditaccount.domain.user.repository.UserRepository
import chlim.creditaccount.domain.user.usecase.result.UserResult
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CreateStore(
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository
) {

    @Transactional
    fun execute(command: CreateStoreCommand): StoreResult {
        val user = userRepository.findById(command.userId)
            .orElseThrow { UserNotFoundException(command.userId) }

        if (storeRepository.findByUserAndName(user, command.name) != null) {
            throw StoreAlreadyExistsException(user, command.name)
        }

        val store = run {
            val store = Store(
                name = command.name,
                phoneNumber = command.phoneNumber,
                user = user
            )

            storeRepository.save(store)
        }

        return StoreResult.of(store)
    }
}
