package chlim.creditaccount

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class CreditAccountApplication

fun main(args: Array<String>) {
	runApplication<CreditAccountApplication>(*args)
}
