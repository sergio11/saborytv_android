package com.dreamsoftware.saborytv.di

import com.dreamsoftware.saborytv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IChefProfilesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IRecipesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IUserSubscriptionsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.impl.AuthRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.CategoryRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.FavoritesRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.ChefProfilesRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.ProfilesRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.RecipesRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.SubscriptionsRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.UserRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.datasource.impl.UserSubscriptionsRemoteDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteRecipeDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.FavoriteRecipeDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.mapper.AddFavoriteRecipeRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.AddUserSubscriptionRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.CategoryRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.CreateProfileRequestRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.CreateUserRequestRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.FavoriteRecipeRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.ChefProfileRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.ProfileRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.RecipeRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.SubscriptionRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.UpdateProfileRequestRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.UpdatedUserRequestRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.UserAuthenticatedRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.UserRemoteMapper
import com.dreamsoftware.saborytv.data.remote.mapper.UserSubscriptionsRemoteMapper
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

// Dagger module for providing Firebase-related dependencies
@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    /**
     * Provides a singleton instance of UserAuthenticatedMapper.
     * @return a new instance of UserAuthenticatedMapper.
     */
    @Provides
    @Singleton
    fun provideUserAuthenticatedRemoteMapper(): IOneSideMapper<FirebaseUser, AuthUserDTO> = UserAuthenticatedRemoteMapper()

    /**
     * Provides a singleton instance of CategoryRemoteMapper.
     * @return a new instance of CategoryRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideCategoryRemoteMapper(): IOneSideMapper<Map<String, Any?>, CategoryDTO> = CategoryRemoteMapper()

    /**
     * Provides a singleton instance of CreateProfileRequestRemoteMapper.
     * @return a new instance of CreateProfileRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideCreateProfileRequestRemoteMapper(): IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>> = CreateProfileRequestRemoteMapper()

    /**
     * Provides a singleton instance of UpdateProfileRequestRemoteMapper.
     * @return a new instance of UpdateProfileRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUpdateProfileRequestRemoteMapper(): IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>> = UpdateProfileRequestRemoteMapper()

    /**
     * Provides a singleton instance of UserRemoteMapper.
     * @return a new instance of UserRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUsersRemoteMapper(): IOneSideMapper<Map<String, Any?>, UserResponseDTO> = UserRemoteMapper()

    /**
     * Provides a singleton instance of UpdatedUserRequestRemoteMapper.
     * @return a new instance of UpdatedUserRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUpdatedUserRequestRemoteMapper(): IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>> = UpdatedUserRequestRemoteMapper()

    /**
     * Provides a singleton instance of CreateUserRequestRemoteMapper.
     * @return a new instance of CreateUserRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideCreateUserRequestRemoteMapper(): IOneSideMapper<CreateUserDTO, Map<String, Any?>> = CreateUserRequestRemoteMapper()

    /**
     * Provides a singleton instance of ProfileRemoteMapper.
     * @return a new instance of ProfileRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideProfileRemoteMapper(): IOneSideMapper<Map<String, Any?>, ProfileDTO> = ProfileRemoteMapper()

    /**
     * Provides a singleton instance of AddFavoriteRecipeRemoteMapper.
     * @return a new instance of AddFavoriteRecipeRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideAddFavoriteRecipeRemoteMapper(): IOneSideMapper<AddFavoriteRecipeDTO, Map<String, Any?>> = AddFavoriteRecipeRemoteMapper()

    /**
     * Provides a singleton instance of provideFavoriteRecipeRemoteMapper.
     * @return a new instance of provideFavoriteRecipeRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideFavoriteRecipeRemoteMapper(): IOneSideMapper<Map<String, Any?>, FavoriteRecipeDTO> = FavoriteRecipeRemoteMapper()

    /**
     * Provides a singleton instance of SubscriptionRemoteMapper.
     * @return a new instance of SubscriptionRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideSubscriptionRemoteMapper(): IOneSideMapper<Map<String, Any?>, SubscriptionDTO> = SubscriptionRemoteMapper()

    /**
     * Provides a singleton instance of AddUserSubscriptionRemoteMapper.
     * @return a new instance of AddUserSubscriptionRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideAddUserSubscriptionRemoteMapper(): IOneSideMapper<AddUserSubscriptionDTO, Map<String, Any?>> = AddUserSubscriptionRemoteMapper()

    /**
     * Provides a singleton instance of UserSubscriptionsRemoteMapper.
     * @return a new instance of UserSubscriptionsRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUserSubscriptionsRemoteMapper(): IOneSideMapper<Map<String, Any?>, UserSubscriptionDTO> = UserSubscriptionsRemoteMapper()

    /**
     * Provides a singleton instance of ChefProfileRemoteMapper.
     * @return a new instance of ChefProfileRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideChefProfileRemoteMapper(): IOneSideMapper<Map<String, Any?>, ChefProfileDTO> = ChefProfileRemoteMapper()

    /**
     * Provides a singleton instance of RecipeRemoteMapper.
     * @return a new instance of RecipeRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideRecipeRemoteMapper(): IOneSideMapper<Map<String, Any?>, RecipeDTO> = RecipeRemoteMapper()

    /**
     * Provides a singleton instance of FirebaseAuth.
     * @return the default instance of FirebaseAuth.
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Provide Firebase Store
     */
    @Provides
    @Singleton
    fun provideFirebaseStore() = Firebase.firestore

    /**
     * Provides a singleton instance of IAuthDataSource.
     * @param userAuthenticatedMapper the IOneSideMapper<FirebaseUser, AuthUserDTO> instance.
     * @param firebaseAuth the FirebaseAuth instance.
     * @return a new instance of AuthDataSourceImpl implementing IAuthDataSource.
     */
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        userAuthenticatedMapper: IOneSideMapper<FirebaseUser, AuthUserDTO>,
        firebaseAuth: FirebaseAuth
    ): IAuthRemoteDataSource = AuthRemoteDataSourceImpl(
        userAuthenticatedMapper,
        firebaseAuth
    )

    @Provides
    @Singleton
    fun provideCategoryRemoteDataSource(
        categoryMapper: IOneSideMapper<Map<String, Any?>, CategoryDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ICategoryRemoteDataSource = CategoryRemoteDataSourceImpl(
        firestore,
        categoryMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideProfilesRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        profilesMapper: IOneSideMapper<Map<String, Any?>, ProfileDTO>,
        createProfileRequestMapper: IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>>,
        updateProfileRequestMapper: IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IProfilesRemoteDataSource = ProfilesRemoteDataSourceImpl(
        firebaseStore,
        profilesMapper,
        createProfileRequestMapper,
        updateProfileRequestMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        usersMapper: IOneSideMapper<Map<String, Any?>, UserResponseDTO>,
        updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>>,
        createUserRequestMapper: IOneSideMapper<CreateUserDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRemoteDataSource = UserRemoteDataSourceImpl(
        firebaseStore,
        usersMapper,
        updatedUserRequestMapper,
        createUserRequestMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideFavoritesRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        addFavoriteMapper: IOneSideMapper<AddFavoriteRecipeDTO, Map<String, Any?>>,
        favoriteMapper: IOneSideMapper<Map<String, Any?>, FavoriteRecipeDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IFavoritesRemoteDataSource = FavoritesRemoteDataSourceImpl(
        firebaseStore,
        addFavoriteMapper,
        favoriteMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideSubscriptionRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        subscriptionsMapper: IOneSideMapper<Map<String, Any?>, SubscriptionDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISubscriptionsRemoteDataSource = SubscriptionsRemoteDataSourceImpl(
        firebaseStore,
        subscriptionsMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideUserSubscriptionsRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        userSubscriptionsMapper: IOneSideMapper<Map<String, Any?>, UserSubscriptionDTO>,
        addSubscriptionMapper: IOneSideMapper<AddUserSubscriptionDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserSubscriptionsRemoteDataSource = UserSubscriptionsRemoteDataSourceImpl(
        firebaseStore,
        userSubscriptionsMapper,
        addSubscriptionMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideInstructorsRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        instructorMapper: IOneSideMapper<Map<String, Any?>, ChefProfileDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IChefProfilesRemoteDataSource = ChefProfilesRemoteDataSourceImpl(
        firebaseStore,
        instructorMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        recipesMapper: IOneSideMapper<Map<String, Any?>, RecipeDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IRecipesRemoteDataSource = RecipesRemoteDataSourceImpl(
        firebaseStore,
        recipesMapper,
        dispatcher
    )
}