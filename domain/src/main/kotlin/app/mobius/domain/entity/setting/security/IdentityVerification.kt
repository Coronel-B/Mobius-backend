package app.mobius.domain.entity.setting.security

import app.mobius.domain.entity.LivenessStatus
import app.mobius.domain.entity.Country
import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "identityVerification")
data class IdentityVerification(
        @Id @GeneratedValue @Column(name = "identity_verificationUUID") val identityVerificationUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "documentationVerificationUUID", referencedColumnName = "documentationVerificationUUID")
        val documentationVerification: DocumentationVerification,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "livenessUUID", referencedColumnName = "livenessUUID")
        val liveness: Liveness
) {
    constructor() : this(documentationVerification = DocumentationVerification(), liveness = Liveness())
}

@Entity
@Table(name = "documentationVerification")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class DocumentationVerification(
        @Id @GeneratedValue @Column(name = "documentation_verificationUUID") val documentationVerificationUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Column(name = "documentationVerificationStatus") @Type(type = "pgsql_enum")
        val documentationVerificationStatus: DocumentationVerificationStatus = DocumentationVerificationStatus.UNSOLICITED,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "dniUUID", referencedColumnName = "dniUUID")
        val dni: DNI? = null,
)

@Entity
@Table(name = "dni")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class DNI(
        @Id @GeneratedValue @Column(name = "dniUUID") val dniUUID: UUID? = null,
        @Column(name = "surname") val surname: String,
        @Column(name = "name") val name: String,

        @Enumerated(EnumType.STRING) @Column(name = "sex") @Type(type = "pgsql_enum")
        val sex: Sex,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "nationalityUUID", referencedColumnName = "countryUUID")
        val nationality: Country,

        @Column(name = "ejemplar") val ejemplar: String,
        @Column(name = "birthdate") val birthdate: Date,
        @Column(name = "dateIssue") val dateIssue: Date,
        @Column(name = "dateExpiry") val dateExpiry: Date,
        @Column(name = "identificationNumber", unique = true) val identificationNumber: Int,
        @Column(name = "number", unique = true) val number: Int,


        ) {
    constructor() : this(
            surname = "",
            name = "",
            sex = Sex.F,
            nationality = Country(),
            ejemplar = "",
            birthdate = Date(),
            dateIssue = Date(),
            dateExpiry = Date(),
            identificationNumber = -1,
            number = -1
    )
}

enum class Sex {
    F, M
}

enum class DocumentationVerificationStatus {
    UNSOLICITED, PENDING, VERIFIED, BLOCKED
}

@Entity
@Table(name = "liveness")
data class Liveness(
        @Id @GeneratedValue @Column(name = "livenessUUID") val livenessUUID: UUID? = null,
        val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED
)