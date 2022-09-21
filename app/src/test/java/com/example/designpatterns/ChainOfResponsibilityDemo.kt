package com.example.designpatterns

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest

class ChainOfResponsibilityDemo
{
    lateinit var request: Request
    lateinit var handler: MilitaryRequestHandler

    @BeforeEach
    fun init()
    {
        request = Request()
        handler = Gateway(Guard(Officer(Commander())))
    }

    @RepeatedTest(10)
    fun handle()
    {
        handler.handle(request)
    }
}

class Request
{
    fun giveEntryPermission(militaryRequestHandler: MilitaryRequestHandler)
    {
        println("${militaryRequestHandler.javaClass.simpleName} is handling this request")
        println("You can enter the base")
    }
}

open class MilitaryRequestHandler(var successor: MilitaryRequestHandler? = null)
{
    // a random permission
    // true if the newly generated random number is 1 (from 0 to 4)
    private val hasPermission: Boolean
        get()
        {
            val randomInt = (0..4).random()
            return randomInt == 1
        }

    fun handle(request: Request)
    {
        if (hasPermission)
            request.giveEntryPermission(this)
        else
            handleAndShowMessage(request)
    }

    private fun handleAndShowMessage(request: Request)
    {
        if (successor != null)
        {
            println("${this.javaClass.simpleName} can't handle this request. " +
                    "Forwarding to ${successor?.javaClass?.simpleName}...")
            successor!!.handle(request)
            return
        }
        println("this request has been denied")
    }
}

class Gateway(successor: Guard) : MilitaryRequestHandler(successor)
class Guard(successor: Officer) : MilitaryRequestHandler(successor)
class Officer(successor: Commander) : MilitaryRequestHandler(successor)
class Commander : MilitaryRequestHandler()