package app.mobius.data.datasource

import app.mobius.domain.entity.Identity

interface DeceasedStore {

    fun saveDeceased(identity: Identity)

}