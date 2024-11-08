package com.dreamsoftware.saborytv.di

import com.dreamsoftware.saborytv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.saborytv.domain.model.SignInBO
import com.dreamsoftware.saborytv.domain.model.SignUpBO
import com.dreamsoftware.saborytv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.saborytv.domain.repository.ICategoryRepository
import com.dreamsoftware.saborytv.domain.repository.IChefProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.saborytv.domain.usecase.AddFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.AddUserSubscriptionUseCase
import com.dreamsoftware.saborytv.domain.usecase.ChangeSecurePinUseCase
import com.dreamsoftware.saborytv.domain.usecase.CreateProfileUseCase
import com.dreamsoftware.saborytv.domain.usecase.DeleteProfileUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetFavoritesRecipesByUserUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetFeaturedRecipesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetChefProfileDetailUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetChefProfilesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetProfileSelectedUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetProfilesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetSubscriptionsUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipesByCategoryUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipesByTypeUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipesRecommendedUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetUserDetailUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetUserPreferencesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetUserProfilesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetUserSubscriptionUseCase
import com.dreamsoftware.saborytv.domain.usecase.HasActiveSubscriptionUseCase
import com.dreamsoftware.saborytv.domain.usecase.HasMultiplesProfilesUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveUserSubscriptionUseCase
import com.dreamsoftware.saborytv.domain.usecase.SaveUserPreferencesUseCase
import com.dreamsoftware.saborytv.domain.usecase.SelectProfileUseCase
import com.dreamsoftware.saborytv.domain.usecase.SignInUseCase
import com.dreamsoftware.saborytv.domain.usecase.SignOffUseCase
import com.dreamsoftware.saborytv.domain.usecase.SignUpUseCase
import com.dreamsoftware.saborytv.domain.usecase.UpdateProfileUseCase
import com.dreamsoftware.saborytv.domain.usecase.VerifyPinUseCase
import com.dreamsoftware.saborytv.domain.usecase.VerifyRecipeInFavoritesUseCase
import com.dreamsoftware.saborytv.domain.usecase.VerifyUserSessionUseCase
import com.dreamsoftware.saborytv.domain.validation.IBusinessEntityValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetUserProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetUserProfilesUseCase =
        GetUserProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetCategoriesUseCase(
        categoryRepository: ICategoryRepository
    ): GetCategoriesUseCase =
        GetCategoriesUseCase(
            categoryRepository = categoryRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetRecipesRecommendedUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        recipesRepository: IRecipesRepository
    ): GetRecipesRecommendedUseCase =
        GetRecipesRecommendedUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            recipesRepository = recipesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetRecipeByIdUseCase(
        recipesRepository: IRecipesRepository
    ): GetRecipeByIdUseCase =
        GetRecipeByIdUseCase(
            recipesRepository = recipesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetRecipesByTypeUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        recipesRepository: IRecipesRepository
    ): GetRecipesByTypeUseCase =
        GetRecipesByTypeUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            recipesRepository = recipesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetChefProfilesUseCase(
        chefProfileRepository: IChefProfilesRepository
    ): GetChefProfilesUseCase =
        GetChefProfilesUseCase(
            chefProfilesRepository = chefProfileRepository
        )


    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(
        userRepository: IUserRepository,
        validator: IBusinessEntityValidator<SignInBO>
    ): SignInUseCase =
        SignInUseCase(
            userRepository = userRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository,
        validator: IBusinessEntityValidator<SignUpBO>
    ): SignUpUseCase =
        SignUpUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository,
            validator = validator
        )


    @Provides
    @ViewModelScoped
    fun provideGetFeaturedRecipesUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        recipesRepository: IRecipesRepository
    ): GetFeaturedRecipesUseCase =
        GetFeaturedRecipesUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            recipesRepository = recipesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetRecipesByCategoryUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        recipesRepository: IRecipesRepository
    ): GetRecipesByCategoryUseCase =
        GetRecipesByCategoryUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            recipesRepository = recipesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetCategoryByIdUseCase(
        categoryRepository: ICategoryRepository
    ): GetCategoryByIdUseCase =
        GetCategoryByIdUseCase(
            categoryRepository = categoryRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetUserDetailUseCase(
        userRepository: IUserRepository
    ): GetUserDetailUseCase =
        GetUserDetailUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetProfilesUseCase =
        GetProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideSelectProfileUseCase(
        profilesRepository: IProfilesRepository
    ): SelectProfileUseCase =
        SelectProfileUseCase(
            profilesRepository = profilesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetProfileSelectedUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetProfileSelectedUseCase =
        GetProfileSelectedUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetProfileByIdUseCase(
        profilesRepository: IProfilesRepository
    ): GetProfileByIdUseCase =
        GetProfileByIdUseCase(
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSignOffUseCase(
        userRepository: IUserRepository
    ): SignOffUseCase =
        SignOffUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideVerifyPinUseCase(
        profilesRepository: IProfilesRepository
    ): VerifyPinUseCase =
        VerifyPinUseCase(
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideVerifyUserSessionUseCase(
        userRepository: IUserRepository
    ): VerifyUserSessionUseCase =
        VerifyUserSessionUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideCreateProfileUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository,
        validator: IBusinessEntityValidator<CreateProfileRequestBO>
    ): CreateProfileUseCase =
        CreateProfileUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository,
            validator = validator
        )


    @Provides
    @ViewModelScoped
    fun provideUpdateProfileUseCase(
        profilesRepository: IProfilesRepository,
        validator:  IBusinessEntityValidator<UpdatedProfileRequestBO>
    ): UpdateProfileUseCase =
        UpdateProfileUseCase(
            profilesRepository = profilesRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideAddFavoriteRecipeUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        recipesRepository: IRecipesRepository
    ): AddFavoriteRecipeUseCase =
        AddFavoriteRecipeUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            recipesRepository = recipesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetFavoritesRecipesByUserUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        recipesRepository: IRecipesRepository
    ): GetFavoritesRecipesByUserUseCase =
        GetFavoritesRecipesByUserUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            recipesRepository = recipesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideRemoveFavoriteRecipeUseCase(
        userRepository: IUserRepository,
        recipesRepository: IRecipesRepository
    ): RemoveFavoriteRecipeUseCase =
        RemoveFavoriteRecipeUseCase(
            userRepository = userRepository,
            recipesRepository = recipesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideVerifyRecipeInFavoritesUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        recipesRepository: IRecipesRepository
    ): VerifyRecipeInFavoritesUseCase =
        VerifyRecipeInFavoritesUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            recipesRepository = recipesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDeleteProfileUseCase(
        profileRepository: IProfilesRepository
    ): DeleteProfileUseCase =
        DeleteProfileUseCase(profileRepository = profileRepository)

    @Provides
    @ViewModelScoped
    fun provideHasMultiplesProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): HasMultiplesProfilesUseCase =
        HasMultiplesProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetSubscriptionsUseCase(
        subscriptionsRepository: ISubscriptionsRepository
    ): GetSubscriptionsUseCase =
        GetSubscriptionsUseCase(
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideHasActiveSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): HasActiveSubscriptionUseCase =
        HasActiveSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideRemoveUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): RemoveUserSubscriptionUseCase =
        RemoveUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )


    @Provides
    @ViewModelScoped
    fun provideAddUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): AddUserSubscriptionUseCase =
        AddUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): GetUserSubscriptionUseCase =
        GetUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetUserPreferencesUseCase(
        userRepository: IUserRepository
    ): GetUserPreferencesUseCase =
        GetUserPreferencesUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSaveUserPreferencesUseCase(
        userRepository: IUserRepository
    ): SaveUserPreferencesUseCase =
        SaveUserPreferencesUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetChefProfileDetailUseCase(
        chefProfileRepository: IChefProfilesRepository
    ): GetChefProfileDetailUseCase =
        GetChefProfileDetailUseCase(
            chefProfilesRepository = chefProfileRepository
        )

    @Provides
    @ViewModelScoped
    fun provideChangeSecurePinUseCase(
        profilesRepository: IProfilesRepository,
        validator:  IBusinessEntityValidator<UpdatedProfileRequestBO>
    ): ChangeSecurePinUseCase =
        ChangeSecurePinUseCase(
            profilesRepository = profilesRepository,
            validator = validator
        )
}