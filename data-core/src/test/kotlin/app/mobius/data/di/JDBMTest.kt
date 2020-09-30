package app.mobius.data.di

import app.mobius.domain.entity.role.Resource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.io.InputStream

class JDBMTest {

//      ----- With Hibernate CFG  -----

    @Test
    fun `get input stream for configuration of session factory`() {
        val currentWorkingDir = System.getProperty("user.dir")
        val absoulutePath = "$currentWorkingDir/src/main/resources/secret-hibernate.cfg.xml"
        val targetStream: InputStream = FileInputStream(absoulutePath)
        Assertions.assertNotEquals(targetStream, null)
    }

    @Test
    fun `open session with hibernate cfg and only one entity`() {
        JDBM.Hibernate.openSessionForOnly(Resource::class.java)
    }

    @Test
    fun `close session with hibernate cfg and only one entity`() {
        val session = JDBM.Hibernate.openSessionForOnly(Resource::class.java)

//        Execute an empty operation for close session
        JDBM.Hibernate.executeQuery(session, "") {}
    }

    @Test
    fun `open session with hibernate cfg and reflextion for all mapped entities`() {
        JDBM.Hibernate.openSession()
    }

}