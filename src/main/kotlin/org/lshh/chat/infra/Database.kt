package org.lshh.chat.infra

import java.util.concurrent.locks.ReentrantLock

abstract class Database<T>() {
    protected val db: HashMap<Long, T> = HashMap()
    protected val sequence =  ReentrantLock()

    open fun find(id: Long):T?{
        Thread.sleep(Math.random().toLong() * 100L + 100)
        return db[id]
    }
    open fun findAll():Collection<T>{
        Thread.sleep(Math.random().toLong() * 100L + 100)
        return db.values
    }
}