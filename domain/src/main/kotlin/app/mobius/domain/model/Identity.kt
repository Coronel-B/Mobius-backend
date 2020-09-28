package app.mobius.domain.model

import app.mobius.domain.entity.role.Role
import app.mobius.domain.model.setting.Setting
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "identity")
data class Identity(
        @Id @GeneratedValue @Column(name = "identity_uuid") val identityUUID: UUID? = null,
        val identityname: String,
        val profile: Profile,
        val setting: Setting,
        val role: Role,
)

