package app.moebius.controller

import app.moebius.domain.model.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController {

    @GetMapping("/profile")
    fun getProfile(): Profile = TODO()

}