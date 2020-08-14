package app.moebius.controller

import app.moebius.entity.Rol
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RolController {

    @GetMapping("roles")
    fun getRoles() = Rol(1, true, true)

}