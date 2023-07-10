package com.reydix.assignment.data.database.base

/**
 * Base Room Entity that provides specific attributes to our Room Entities.
 * Should be extended by all Item/Product based Entities.
 *
 */
interface BaseDaoIdItem {
    val id: Long

    companion object {
        const val DEFAULT_ID = 0L
    }

}
