package app.mobius.data.datasource

import app.mobius.domain.model.Person
import javax.persistence.*

@Entity
@Table(name = "test")
data class SomeTest(@Id val test: String?)

class SqlIdentityDataSource: IdentityStore {

    override fun createIdentity(person: Person) {


    }

    fun test() {
        /*val session = JDBCManager.openSession(annotatedClass = SomeTest::class.java)

        JDBCManager.executeQuery(session, "Work") {
            session.save(SomeTest("3"))
        }*/
    }

}

fun main() {
//    SqlIdentityDataSource().createIdentity()
    SqlIdentityDataSource().test()
}

