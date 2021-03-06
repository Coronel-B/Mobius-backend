package app.mobius.crendentialManagment

import app.mobius.MobiusFeatureIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest(classes = [MobiusFeatureIntegrationTest::class])
@CredentialManagmentFeatureTestConfiguration
class AppAuthorizationUseCaseIntegrationTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when run a test using spring boot test then success`() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8090/mobiusApplicationTest")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

}