package com.reydix.assignment.data.database.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Abstraction with the basic Room dao db actions.
 * Has all the basic Room operations for the local db.
 *
 * @param T Generic Room entity
 */
interface  BaseDao<T> {

    /**
     * Insert single T object on DB
     *
     * @param obj the T object to be inserted
     * @return the rowId for the inserted object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    /**
     * Insert list of T objects on DB
     *
     * @param list list of T objects to be inserted
     * @return the list of rowIds inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(list: List<T>): List<Long>

    /**
     * Update one or more T object(s) on DB
     *
     * @param obj one or more T object(s) to be updated
     * @return number of rows affected
     */
    @Update
    fun update(vararg obj: T): Int

    @Update
    fun updateAll(listOfObj: List<T>): Int

    /**
     * Delete single T object from DB
     *
     * @param obj single T object to be deleted
     * @return number of rows removed
     */
    @Delete
    fun delete(obj: T): Int

    /**
     * Delete list of T objects from the DB
     *
     * @param list list of T objects to be removed
     * @return number of rows affected
     */
    @Delete
    fun deleteAll(list: List<T>): Int

}