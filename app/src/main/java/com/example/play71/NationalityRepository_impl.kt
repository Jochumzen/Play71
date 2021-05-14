package com.example.play71

import com.example.play71.domain.Nationality
import com.example.play71.domain.DomainMapper
import com.example.play71.network.NationalityDto
import com.example.play71.network.NationalityService

class NationalityRepository_impl (
    private val nationalityService: NationalityService,
    private val mapper: DomainMapper<NationalityDto, Nationality>
) : NationalityRepository {
    override suspend fun getNationality(): Nationality {
        return mapper.mapToDomainModel(nationalityService.nationality())
    }
}