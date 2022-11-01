package com.example.designpatterns

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StateDemo
{

    lateinit var mammoth: Mammoth

    @BeforeEach
    fun init()
    {
        mammoth = Mammoth()
    }

    @Test
    fun test()
    {
        mammoth.observe()
        mammoth.timePasses()
        mammoth.observe()
        mammoth.timePasses()
        mammoth.observe()
        mammoth.timePasses()
        mammoth.observe()
    }
}

interface State
{
    fun onEnterState()
    fun observe()
}

class PeacefulState(val mammoth: Mammoth) : State
{
    override fun observe()
    {
        println("$mammoth is calm and peaceful.")
    }

    override fun onEnterState()
    {
        println("$mammoth calms down.")
    }
}

class AngryState(val mammoth: Mammoth) : State
{
    override fun observe()
    {
        println("$mammoth is furious!")
    }

    override fun onEnterState()
    {
        println("$mammoth gets angry!")
    }
}

class JoyfulState(val mammoth: Mammoth) : State
{
    override fun observe()
    {
        println("$mammoth is laughing!")
    }

    override fun onEnterState()
    {
        println("$mammoth gets joyful!")
    }
}

class Mammoth
{
    private var state: State
    // transition method
    fun timePasses()
    {
        when(state)
        {
            is PeacefulState ->
                changeStateTo(JoyfulState(this))
            is JoyfulState ->
                changeStateTo(AngryState(this))
            is AngryState ->
                changeStateTo(PeacefulState(this))
        }
    }

    private fun changeStateTo(newState: State)
    {
        state = newState
        state.onEnterState()
    }

    override fun toString(): String
    {
        return "The mammoth"
    }

    fun observe()
    {
        state.observe()
    }

    init
    {
        state = PeacefulState(this)
    }
}