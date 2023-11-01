package chlim.creditaccount

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/simple")
class SimpleRestController {
    
    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }
}
