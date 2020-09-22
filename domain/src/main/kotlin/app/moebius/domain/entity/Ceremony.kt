package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ceremony")
data class Ceremony(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ceremony_uuid") val ceremonyUUID: UUID,
        val ceremonyType: CeremonyType
)

@Entity
@Table(name = "ceremony_type")
data class CeremonyType(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ceremony_type_uuid") val typeUUID: UUID,
        val name: String
)