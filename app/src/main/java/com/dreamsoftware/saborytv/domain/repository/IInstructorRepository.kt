package com.dreamsoftware.saborytv.domain.repository

import com.dreamsoftware.saborytv.domain.exception.FetchInstructorByIdException
import com.dreamsoftware.saborytv.domain.exception.FetchInstructorsException
import com.dreamsoftware.saborytv.domain.model.InstructorBO

interface IInstructorRepository {

    @Throws(FetchInstructorsException::class)
    suspend fun getInstructors(): List<InstructorBO>

    @Throws(FetchInstructorByIdException::class)
    suspend fun getInstructorById(id: String): InstructorBO
}