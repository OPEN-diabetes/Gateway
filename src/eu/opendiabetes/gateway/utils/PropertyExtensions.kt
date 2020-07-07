package eu.opendiabetes.gateway.utils

import io.ktor.application.Application

fun Application.getStringProperty(path: String) = environment.config.property(path).getString()

fun Application.getBooleanProperty(path: String) = getStringProperty(path).toBoolean()

fun Application.getIntProperty(path: String) = getStringProperty(path).toInt()

fun Application.getLongProperty(path: String) = getStringProperty(path).toLong()
