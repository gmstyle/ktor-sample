package com.example.plugins

import com.example.repositories.DeviceRepository
import com.example.repositories.UserRepository
import com.example.services.DeviceService
import com.example.services.DeviceServiceImpl
import com.example.services.UserService
import com.example.services.UserServiceImpl
import com.mysql.cj.log.Slf4JLogger
import io.ktor.server.application.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin


fun Application.configKoinModules(){
    install(Koin){
        Slf4JLogger("Slf4JLogger")
        modules(applicationModule)
    }
}
val applicationModule = module(createdAtStart = true){

    // Vecchia sintassi
    /*single<UserService>{ UserServiceImpl() }
    single{ UserRepository()}
    single<DeviceService>{ DeviceServiceImpl() }
    single { DeviceRepository() }*/

    // Nuova sintassi koin 3.2 per ktor
    singleOf(::UserServiceImpl) {bind<UserService>()}
    singleOf(::UserRepository)
    singleOf(::DeviceServiceImpl) {bind<DeviceService>()}
    singleOf(::DeviceRepository)
}
