package app.mobius.domain.model

import java.util.*
import javax.persistence.*

/**
 * Map with:
 *  . AppConsumerUsers
 *  . AppConsumerPartner
 *  . AppConsumerOrganization
 */
@Entity
@Table(name = "application")
data class Application(
        @Id @GeneratedValue @Column(name = "applicationUUID") val applicationUUID: UUID? = null,
        val environment: Environment,
        val consumer: Consumer,
        val publicKey: String
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}


sealed class Consumer {
    @Entity
    @Table(name = "consumerIdentities")
    data class Identities(
            @Id @GeneratedValue @Column(name = "consumer_identitiesUUID") val usersUUID: UUID? = null,
            val platform: Platform)

    /**
     * A partner consumes a particular feature
     */
    @Entity
    @Table(name = "consumerPartner")
    data class Partner(
            @Id @GeneratedValue @Column(name = "consumer_partnerUUID") val partnerUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String)

    /**
     * A team consumes a particular feature
     */
    @Entity
    @Table(name = "consumerTeam")
    data class Team(
            @Id @GeneratedValue @Column(name = "consumer_teamUUID") val teamUUID: UUID? = null,
            val name: String,
            val platform: Platform,
            val feature: String)
}

@Entity
@Table(name = "platform")
data class Platform(
        @Id @GeneratedValue @Column(name = "platformUUID") val platformUUID: UUID? = null,
        val name: String,
        val ecosystem: String
)