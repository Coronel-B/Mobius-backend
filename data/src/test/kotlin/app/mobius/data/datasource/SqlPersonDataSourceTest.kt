package app.mobius.data.datasource

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.Person
import app.mobius.domain.entity.Profile
import app.mobius.domain.entity.role.Role
import app.mobius.domain.entity.setting.Setting
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlPersonDataSourceTest {

    private lateinit var hibernate : HibernateUtil
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBM.Hibernate.openSession()
    }

    @Test
    fun `given a username and a person, when insert person, then create it`() {
        val person = Person(
                username = randomString(),
                profile = Profile(),
                setting = Setting(),
                role = Role()
        )

        assertDoesNotThrow("person exception") {
            JDBM.Hibernate.executeQuery(session) {
                if (hibernate.isUniquenessValid(person)) {
                    session.save(person)
                }
            }
        }

    }

}