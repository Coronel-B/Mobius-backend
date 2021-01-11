package app.mobius.data.service

import app.mobius.data.repository.AppAuthorizationJpaRepository
import app.mobius.domain.entity.security.Platform
import app.mobius.service.AppAuthorizationService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(value = [MockitoExtension::class, SpringExtension::class])
open class AppAuthorizationServiceTest {

    @MockBean
    private lateinit var appAuthorizationJpaRepository: AppAuthorizationJpaRepository

    @InjectMocks
    private lateinit var appAuthorizationService: AppAuthorizationService

    @Test
    fun `should return true when app authorization is valid app`() {
        val appAuthorizationDeveloper = "f60b447c-90c7-4edd-9399-cb7ebd9051a8"
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")
        val developerName = "Braian Coronel"
        val privateKey = "randomKey"

        Mockito
                .`when`(appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(androidMobile, developerName))
                .thenReturn(appAuthorizationDeveloper)
        Mockito
                .`when`(appAuthorizationJpaRepository.isValidAppAuthorization(
                            UUID.fromString(appAuthorizationDeveloper),
                            privateKey))
                .thenReturn(true)

        assert(
                appAuthorizationService.isValidAppAuthorization(androidMobile, developerName, privateKey)
        )
    }

    @Test
    fun `should return false when app authorization is valid app`() {
        val appAuthorizationDeveloper = "f60b447c-90c7-4edd-9399-cb7ebd9051a8"
        val androidMobile = Platform(name = "Android", ecosystem = "Mobile")
        val developerName = "Braian Coronel"
        val privateKey = "randomKey"

        Mockito
                .`when`(appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(androidMobile, developerName))
                .thenReturn(appAuthorizationDeveloper)
        Mockito
                .`when`(appAuthorizationJpaRepository.isValidAppAuthorization(
                        UUID.fromString(appAuthorizationDeveloper),
                        privateKey))
                .thenReturn(false)

        Assertions.assertFalse(
                appAuthorizationService.isValidAppAuthorization(androidMobile, developerName, privateKey)
        )
    }

}