package gg.koo.kooggbot.exception

import discord4j.rest.http.client.ClientException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Error

@RestControllerAdvice
class ClientExceptionHandler {

    @ExceptionHandler(ClientException::class)
    fun clientExceptionHandle(exception: ClientException) {
        println(exception.message)
    }
}