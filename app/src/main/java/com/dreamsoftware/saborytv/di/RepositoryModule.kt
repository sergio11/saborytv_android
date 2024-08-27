package com.dreamsoftware.saborytv.di

import com.dreamsoftware.saborytv.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.saborytv.data.preferences.datasource.IUserPreferencesDataSource
import com.dreamsoftware.saborytv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.saborytv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.saborytv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IInstructorsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.ITrainingSongsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IUserSubscriptionsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.saborytv.data.remote.mapper.ProfileSessionMapper
import com.dreamsoftware.saborytv.data.repository.impl.CategoryRepositoryImpl
import com.dreamsoftware.saborytv.data.repository.impl.InstructorRepositoryImpl
import com.dreamsoftware.saborytv.data.repository.impl.ProfilesRepositoryImpl
import com.dreamsoftware.saborytv.data.repository.impl.SubscriptionsRepositoryImpl
import com.dreamsoftware.saborytv.data.repository.impl.TrainingRepositoryImpl
import com.dreamsoftware.saborytv.data.repository.impl.TrainingSongsRepositoryImpl
import com.dreamsoftware.saborytv.data.repository.impl.UserRepositoryImpl
import com.dreamsoftware.saborytv.data.repository.mapper.AddFavoriteTrainingMapper
import com.dreamsoftware.saborytv.data.repository.mapper.AddUserSubscriptionMapper
import com.dreamsoftware.saborytv.data.repository.mapper.CategoryMapper
import com.dreamsoftware.saborytv.data.repository.mapper.ChallengeMapper
import com.dreamsoftware.saborytv.data.repository.mapper.CreateProfileRequestMapper
import com.dreamsoftware.saborytv.data.repository.mapper.CreateUserMapper
import com.dreamsoftware.saborytv.data.repository.mapper.InstructorMapper
import com.dreamsoftware.saborytv.data.repository.mapper.ProfileMapper
import com.dreamsoftware.saborytv.data.repository.mapper.RoutineMapper
import com.dreamsoftware.saborytv.data.repository.mapper.SeriesMapper
import com.dreamsoftware.saborytv.data.repository.mapper.SubscriptionMapper
import com.dreamsoftware.saborytv.data.repository.mapper.TrainingFilterDataMapper
import com.dreamsoftware.saborytv.data.repository.mapper.TrainingSongMapper
import com.dreamsoftware.saborytv.data.repository.mapper.UpdateProfileRequestMapper
import com.dreamsoftware.saborytv.data.repository.mapper.UpdatedUserRequestMapper
import com.dreamsoftware.saborytv.data.repository.mapper.UserDetailMapper
import com.dreamsoftware.saborytv.data.repository.mapper.UserPreferencesMapper
import com.dreamsoftware.saborytv.data.repository.mapper.UserSubscriptionMapper
import com.dreamsoftware.saborytv.data.repository.mapper.WorkoutMapper
import com.dreamsoftware.saborytv.domain.model.AddFavoriteTrainingBO
import com.dreamsoftware.saborytv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.domain.model.ChallengeBO
import com.dreamsoftware.saborytv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.saborytv.domain.model.InstructorBO
import com.dreamsoftware.saborytv.domain.model.SignUpBO
import com.dreamsoftware.saborytv.domain.model.ProfileBO
import com.dreamsoftware.saborytv.domain.model.RoutineBO
import com.dreamsoftware.saborytv.domain.model.SeriesBO
import com.dreamsoftware.saborytv.domain.model.SubscriptionBO
import com.dreamsoftware.saborytv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.saborytv.domain.model.TrainingSongBO
import com.dreamsoftware.saborytv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.saborytv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.saborytv.domain.model.UserDetailBO
import com.dreamsoftware.saborytv.domain.model.UserPreferenceBO
import com.dreamsoftware.saborytv.domain.model.UserSubscriptionBO
import com.dreamsoftware.saborytv.domain.model.WorkoutBO
import com.dreamsoftware.saborytv.domain.repository.ICategoryRepository
import com.dreamsoftware.saborytv.domain.repository.IInstructorRepository
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.saborytv.domain.repository.ITrainingRepository
import com.dreamsoftware.saborytv.domain.repository.ITrainingSongsRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.saborytv.utils.IMapper
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRoutineMapper(): IOneSideMapper<Pair<RoutineDTO, InstructorDTO>, RoutineBO> = RoutineMapper()

    @Provides
    @Singleton
    fun provideCategoryMapper(): IOneSideMapper<CategoryDTO, CategoryBO> = CategoryMapper()

    @Provides
    @Singleton
    fun provideSeriesMapper(): IOneSideMapper<Pair<SeriesDTO, InstructorDTO>, SeriesBO> = SeriesMapper()

    @Provides
    @Singleton
    fun provideWorkoutMapper(): IOneSideMapper<Pair<WorkoutDTO, InstructorDTO>, WorkoutBO> = WorkoutMapper()

    @Provides
    @Singleton
    fun provideChallengeMapper(
        workoutMapper: IOneSideMapper<Pair<WorkoutDTO, InstructorDTO>, WorkoutBO>
    ): IOneSideMapper<Triple<ChallengeDTO, List<WorkoutDTO>, InstructorDTO>, ChallengeBO> = ChallengeMapper(workoutMapper)

    @Provides
    @Singleton
    fun provideProfileMapper(): IOneSideMapper<ProfileDTO, ProfileBO> = ProfileMapper()

    @Provides
    @Singleton
    fun provideCreateProfileRequestMapper(): IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO> = CreateProfileRequestMapper()

    @Provides
    @Singleton
    fun provideUpdateProfileRequestMapper(): IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO> = UpdateProfileRequestMapper()

    @Provides
    @Singleton
    fun provideProfileSessionMapper(): IMapper<ProfileBO, ProfileSelectedPreferenceDTO> = ProfileSessionMapper()

    @Provides
    @Singleton
    fun provideUserDetailMapper(): IOneSideMapper<UserResponseDTO, UserDetailBO> = UserDetailMapper()

    @Provides
    @Singleton
    fun provideUpdatedUserRequestMapper(): IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO> = UpdatedUserRequestMapper()

    @Provides
    @Singleton
    fun provideUpdatedCreateUserMapper(): IOneSideMapper<SignUpBO, CreateUserDTO> = CreateUserMapper()

    @Provides
    @Singleton
    fun provideAddFavoriteTrainingMapper(): IOneSideMapper<AddFavoriteTrainingBO, AddFavoriteTrainingDTO> = AddFavoriteTrainingMapper()

    @Provides
    @Singleton
    fun provideTrainingFilterDataMapper(): IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO> = TrainingFilterDataMapper()

    @Provides
    @Singleton
    fun provideSubscriptionMapper(): IOneSideMapper<SubscriptionDTO, SubscriptionBO> = SubscriptionMapper()

    @Provides
    @Singleton
    fun provideAddUserSubscriptionMapper(): IOneSideMapper<AddUserSubscriptionBO, AddUserSubscriptionDTO> = AddUserSubscriptionMapper()

    @Provides
    @Singleton
    fun provideUserSubscriptionMapper():  IOneSideMapper<UserSubscriptionDTO, UserSubscriptionBO> = UserSubscriptionMapper()

    @Provides
    @Singleton
    fun provideTrainingSongMapper(): IOneSideMapper<TrainingSongDTO, TrainingSongBO> = TrainingSongMapper()

    @Provides
    @Singleton
    fun provideInstructorMapper(): IOneSideMapper<InstructorDTO, InstructorBO> = InstructorMapper()

    @Provides
    @Singleton
    fun provideUserPreferencesMapper(): IMapper<UserPreferencesDTO, UserPreferenceBO> = UserPreferencesMapper()

    @Provides
    @Singleton
    fun provideInstructorRepository(
        instructorsRemoteDataSource: IInstructorsRemoteDataSource,
        instructorMapper: IOneSideMapper<InstructorDTO, InstructorBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IInstructorRepository =
        InstructorRepositoryImpl(
            instructorsRemoteDataSource,
            instructorMapper,
            dispatcher
        )


    @Provides
    @Singleton
    fun provideTrainingRepository(
        routineRemoteDataSource: IRoutineRemoteDataSource,
        workoutRemoteDataSource: IWorkoutRemoteDataSource,
        seriesRemoteDataSource: ISeriesRemoteDataSource,
        challengesRemoteDataSource: IChallengesRemoteDataSource,
        favoritesRemoteDataSource: IFavoritesRemoteDataSource,
        instructorRemoteDataSource: IInstructorsRemoteDataSource,
        routineMapper: IOneSideMapper<Pair<RoutineDTO, InstructorDTO>, RoutineBO>,
        workoutMapper: IOneSideMapper<Pair<WorkoutDTO, InstructorDTO>, WorkoutBO>,
        seriesMapper: IOneSideMapper<Pair<SeriesDTO, InstructorDTO>, SeriesBO>,
        addFavoriteMapper: IOneSideMapper<AddFavoriteTrainingBO, AddFavoriteTrainingDTO>,
        trainingFilterDataMapper: IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO>,
        challengesMapper: IOneSideMapper<Triple<ChallengeDTO, List<WorkoutDTO>, InstructorDTO>, ChallengeBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ITrainingRepository =
        TrainingRepositoryImpl(
            routineRemoteDataSource,
            workoutRemoteDataSource,
            seriesRemoteDataSource,
            challengesRemoteDataSource,
            favoritesRemoteDataSource,
            instructorRemoteDataSource,
            routineMapper,
            workoutMapper,
            seriesMapper,
            addFavoriteMapper,
            trainingFilterDataMapper,
            challengesMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: IUserRemoteDataSource,
        authRemoteDataSource: IAuthRemoteDataSource,
        userPreferencesDataSource: IUserPreferencesDataSource,
        userDetailMapper: IOneSideMapper<UserResponseDTO, UserDetailBO>,
        updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>,
        userPreferencesMapper: IMapper<UserPreferencesDTO, UserPreferenceBO>,
        createUserMapper: IOneSideMapper<SignUpBO, CreateUserDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRepository =
        UserRepositoryImpl(
            userRemoteDataSource,
            authRemoteDataSource,
            userPreferencesDataSource,
            userDetailMapper,
            userPreferencesMapper,
            updatedUserRequestMapper,
            createUserMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryRemoteDataSource: ICategoryRemoteDataSource,
        categoryMapper: IOneSideMapper<CategoryDTO, CategoryBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ICategoryRepository =
        CategoryRepositoryImpl(
            categoryRemoteDataSource,
            categoryMapper,
            dispatcher
        )


    @Provides
    @Singleton
    fun provideProfilesRepository(
        profilesRemoteDataSource: IProfilesRemoteDataSource,
        userRemoteDataSource: IUserRemoteDataSource,
        favoritesRemoteDataSource: IFavoritesRemoteDataSource,
        profilesMapper: IOneSideMapper<ProfileDTO, ProfileBO>,
        createProfileMapper: IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>,
        updateProfileMapper: IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>,
        profileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO>,
        profileSessionDataSource: IProfileSessionDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IProfilesRepository =
        ProfilesRepositoryImpl(
            profilesRemoteDataSource,
            userRemoteDataSource,
            favoritesRemoteDataSource,
            profilesMapper,
            createProfileMapper,
            updateProfileMapper,
            profileSessionMapper,
            profileSessionDataSource,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideSubscriptionsRepository(
        userSubscriptionRemoteDataSource: IUserSubscriptionsRemoteDataSource,
        subscriptionsRemoteDataSource: ISubscriptionsRemoteDataSource,
        subscriptionMapper: IOneSideMapper<SubscriptionDTO, SubscriptionBO>,
        addUserSubscriptionMapper: IOneSideMapper<AddUserSubscriptionBO, AddUserSubscriptionDTO>,
        userSubscriptionMapper: IOneSideMapper<UserSubscriptionDTO, UserSubscriptionBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISubscriptionsRepository =
        SubscriptionsRepositoryImpl(
            userSubscriptionRemoteDataSource,
            subscriptionsRemoteDataSource,
            subscriptionMapper,
            addUserSubscriptionMapper,
            userSubscriptionMapper,
            dispatcher
        )


    @Provides
    @Singleton
    fun provideTrainingSongsRepository(
        trainingSongsRemoteDataSource: ITrainingSongsRemoteDataSource,
        trainingSongsMapper: IOneSideMapper<TrainingSongDTO, TrainingSongBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ITrainingSongsRepository =
        TrainingSongsRepositoryImpl(
            trainingSongsRemoteDataSource,
            trainingSongsMapper,
            dispatcher
        )
}