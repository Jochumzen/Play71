package com.example.play71.cache

import com.example.play71.domain.DomainMapper
import com.example.play71.domain.Nationality
import com.example.play71.network.NationalityDto

class NationalityEntityMapper : DomainMapper<NationalityEntity, Nationality> {
    override fun mapToDomainModel(model: NationalityEntity): Nationality {
        return Nationality(model.id, null, model.nationality, null, null, null, null)
    }

    override fun mapFromDomainModel(domainModel: Nationality): NationalityEntity {
        return NationalityEntity(domainModel.id, domainModel.nationality)
    }

    fun fromEntityList(initial: List<NationalityEntity>): List<Nationality>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Nationality>): List<NationalityEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}