package com.dds.theecogame.data.repository

import com.dds.theecogame.common.Resource
import com.dds.theecogame.common.RetrofitInstance
import com.dds.theecogame.data.remote.session.dto.input.EmailDto
import com.dds.theecogame.data.remote.session.dto.input.ImageDto
import com.dds.theecogame.data.remote.session.dto.input.PasswordDto
import com.dds.theecogame.data.remote.session.dto.input.UserCreationDto
import com.dds.theecogame.data.remote.session.dto.input.UsernameDto
import com.dds.theecogame.data.remote.session.dto.toResponse
import com.dds.theecogame.data.remote.session.dto.toUser
import com.dds.theecogame.domain.model.Response
import com.dds.theecogame.domain.model.User
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class UserRepositoryImpl : UserRepository {

    private val api = RetrofitInstance.userService


    override suspend fun checkUsername(username: String): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.checkUsername(UsernameDto(username))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))

        emit(Resource.Error(response.message()))
    }

    override suspend fun checkEmail(email: String): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.checkEmail(EmailDto(email))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))

        emit(Resource.Error(response.message()))
    }

    override suspend fun checkPassword(
        userId: Int,
        password: String
    ): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.checkPassword(userId, PasswordDto(password))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))

        emit(Resource.Error(response.message()))
    }

    override suspend fun getUser(email: String): Flow<Resource<User>> = flow {

        emit(Resource.Loading())

        val response = api.getUser(EmailDto(email))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toUser()))

        emit(Resource.Error(response.message()))
    }

    override suspend fun createUser(
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String
    ): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.createUser(UserCreationDto(email, name, password, surname, username))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))

        emit(Resource.Error(response.message()))
    }

    override suspend fun updateAvatar(file: File): Flow<Resource<Response>> = flow {

        emit(Resource.Loading())

        val response = api.updateAvatar(ImageDto(file))

        if (response.isSuccessful)
            emit(Resource.Success(response.body()!!.toResponse()))

        emit(Resource.Error(response.message()))
    }
}