package chlim.creditaccount.security

import chlim.creditaccount.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
    private val user: User
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        user.getRoles().map(::SimpleGrantedAuthority).toMutableList()

    override fun getPassword(): String = null!!

    override fun getUsername(): String = user.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
