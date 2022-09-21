package com.example.designpatterns

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class CommandDemo
{
    lateinit var remoteControl: RemoteControl
    lateinit var bulb: Bulb
    lateinit var turnOn: TurnOn
    lateinit var turnOff: TurnOff

    @BeforeEach
    fun init()
    {
        remoteControl = RemoteControl()
        bulb = Bulb()
        turnOn = TurnOn(bulb)
        turnOff = TurnOff(bulb)
    }

    @Test
    fun command()
    {
        remoteControl.submitExecute(turnOn)
        remoteControl.submitUndo(turnOn)
        remoteControl.submitRedo(turnOn)
        remoteControl.submitExecute(turnOff)
        remoteControl.submitUndo(turnOff)
        remoteControl.submitRedo(turnOff)
    }
}

// Receiver
class Bulb
{
    fun turnOn()
    {
        println("bulb has been turned on!")
    }

    fun turnOff()
    {
        println("bulb has been turned off!")
    }

}

interface Command
{
    fun execute()
    fun undo()
    fun redo()
}

class TurnOn(var bulb: Bulb) : Command
{

    override fun execute()
    {
        bulb.turnOn()
    }

    override fun undo()
    {
        bulb.turnOff()
    }

    override fun redo()
    {
        execute()
    }
}

class TurnOff(var bulb: Bulb) : Command
{

    override fun execute()
    {
        bulb.turnOff()
    }

    override fun undo()
    {
        bulb.turnOn()
    }

    override fun redo()
    {
        execute()
    }
}

// Invoker
class RemoteControl
{
    fun submitExecute(command: Command)
    {
        command.execute()
    }

    fun submitUndo(command: Command)
    {
        command.undo()
    }

    fun submitRedo(command: Command)
    {
        command.redo()
    }
}