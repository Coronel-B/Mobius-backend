package app.mobius.data.datasource

import app.mobius.data.di.JDBCManager
import app.mobius.domain.entity.Identity
import app.mobius.domain.entity.role.Role
import io.mockk.mockk
import org.junit.jupiter.api.Test

class SqlIdentityDataSourceTest {

    @Test
    fun `create profile`() {

    }

    @Test
    fun `create setting`() {

    }

    @Test
    fun `create role`() {
        val role = mockk<Role>()
        val session = JDBCManager.openSession_2(annotatedClass = Role::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(role)
        }
    }

    @Test
    fun `create identity`() {
        val identity = mockk<Identity>()
        val session = JDBCManager.openSession_2(annotatedClass = Identity::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(identity)
        }
    }

}