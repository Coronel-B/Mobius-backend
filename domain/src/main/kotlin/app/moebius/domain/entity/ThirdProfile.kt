package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "third_profile")
data class ThirdProfile(
        @Id @GeneratedValue @Column(name = "third_profile_uuid") val thirdProfileUUID: UUID
)