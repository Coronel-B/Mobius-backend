package app.mobius

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/mobiusApplicationTest")
    fun mobiusApplicationTest(): String {
        return "Hello world"
    }

}