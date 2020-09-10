package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "permission")
data class Permission(
        val permissionUUID: UUID,
        val operation: Operation,
        val resource: Resource
)