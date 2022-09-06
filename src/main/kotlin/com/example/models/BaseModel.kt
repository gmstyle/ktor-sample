package com.example.models

import org.ktorm.entity.Entity

interface BaseModel<T : Entity<T>, R> {

    fun entityToObject(entity: T) : R

}
