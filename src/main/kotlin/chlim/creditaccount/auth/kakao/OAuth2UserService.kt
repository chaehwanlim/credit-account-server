package chlim.creditaccount.auth.kakao

import chlim.creditaccount.domain.authmethod.AuthMethodType
import chlim.creditaccount.domain.authmethod.repository.AuthMethodRepository
import chlim.creditaccount.domain.authmethod.usecase.CreateAuthMethod
import chlim.creditaccount.domain.authmethod.usecase.command.CreateAuthMethodCommand
import chlim.creditaccount.domain.user.repository.UserRepository
import chlim.creditaccount.domain.user.usecase.CreateUser
import chlim.creditaccount.domain.user.usecase.command.CreateUserCommand
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OAuth2UserService(
    private val userRepository: UserRepository,
    private val authMethodRepository: AuthMethodRepository,
    private val createUser: CreateUser,
    private val createAuthMethod: CreateAuthMethod,
) : DefaultOAuth2UserService() {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        // DefaultOAuth2UserService의 loadUser가 kakaoAccount까지 조회하는 역할을 해줌.
        val userInfo = super.loadUser(userRequest)
        val attributes = userInfo.attributes
        val oAuthServiceName = userRequest?.clientRegistration?.clientName
        val userNameAttributeName =
            userRequest?.clientRegistration?.providerDetails?.userInfoEndpoint?.userNameAttributeName

        val userId = when {
            oAuthServiceName == "kakao" -> resolveUserFromKakao(attributes)
            else -> throw RuntimeException("$oAuthServiceName is not supported.")
        }

        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority("ROLE_USER")),
            attributes + mapOf("userId" to userId),
            userNameAttributeName
        )
    }

    private fun resolveUserFromKakao(attributes: Map<String, Any>): Long {
        val kakaoId = attributes["id"].toString()
        val kakaoAccount = attributes["kakao_account"] as Map<*, *>
        val kakaoProfile = kakaoAccount["profile"] as Map<*, *>

        return authMethodRepository.findByResourceIdAndType(kakaoId, AuthMethodType.KAKAO)?.user?.id
            ?: run {
                val userId = userRepository.findByEmail(kakaoAccount["email"] as String)?.id
                    ?: createUser.execute(
                        CreateUserCommand(
                            name = kakaoProfile["nickname"] as String,
                            email = kakaoAccount["email"] as String,
                            phoneNumber = "01064230550"
                        )
                    ).id

                createAuthMethod.execute(
                    CreateAuthMethodCommand(
                        userId = userId,
                        resourceId = kakaoId,
                        type = AuthMethodType.KAKAO
                    )
                )

                userId
            }
    }
}