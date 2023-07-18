package chlim.creditaccount.domain.contact.usecase

import chlim.creditaccount.domain.contact.exception.ContactAlreadyExistsException
import chlim.creditaccount.domain.contact.repository.ContactRepository
import chlim.creditaccount.domain.contact.usecase.command.CreateContactCommand
import chlim.creditaccount.domain.store.exception.StoreNotFoundException
import chlim.creditaccount.domain.store.repository.StoreRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk
import java.util.*

class CreateContactTest: FreeSpec({

    val storeRepository: StoreRepository = mockk()
    val contactRepository: ContactRepository = mockk()
    val createContact = CreateContact(storeRepository, contactRepository)

    val command = CreateContactCommand(
        name = "홍길동",
        phoneNumber = "01012345678",
        memo = "단골 손님",
        storeId = 1L
    )

    "가게가 없으면" - {
        every { storeRepository.findById(any()) } returns Optional.empty()

        "연락처를 생성할 수 없다." {
            shouldThrow<StoreNotFoundException> {
                createContact.execute(command)
            }
        }
    }

    "가게가 존재하고" - {
        every { storeRepository.findById(any()) } returns Optional.of(mockk(
            relaxed = true
        ))

        "동일한 번호의 연락처가 존재하면" - {
            every { contactRepository.findByStoreAndPhoneNumber(any(), any()) } returns mockk(
                relaxed = true
            )

            "연락처를 생성할 수 없다." {
                shouldThrow<ContactAlreadyExistsException> {
                    createContact.execute(command)
                }
            }
        }

        "동일한 번호의 연락처가 존재하지 않으면" - {
            every { contactRepository.findByStoreAndPhoneNumber(any(), any()) } returns null
            every { contactRepository.save(any()) } returns mockk(
                relaxed = true
            )

            "연락처를 생성할 수 있다." {
                createContact.execute(command)
            }
        }
    }
})
