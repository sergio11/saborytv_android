package com.dreamsoftware.saborytv.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainImmediateDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignUpScreenErrorMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FavoritesScreenErrorMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RecipesScreenErrorMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignInScreenErrorMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SaveProfileScreenErrorMapper
