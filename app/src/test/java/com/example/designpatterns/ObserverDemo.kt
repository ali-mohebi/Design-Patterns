package com.example.designpatterns

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.ArrayList

class ObserverDemo
{

    var observers: ArrayList<Observer> = arrayListOf()
    lateinit var jobBoardSubject: JobBoardSubject

    @BeforeEach
    fun init()
    {
        observers.add(JobSeeker("Neda"))
        observers.add(JobSeeker("Monire"))
        observers.add(JobSeeker("Ali"))
        observers.add(JobSeeker("AliReza"))
        observers.add(JobSeeker("AmirReza"))
        observers.add(JobSeeker("Vahid"))

        jobBoardSubject = JobBoardSubject()
        jobBoardSubject.attachAll(observers)
    }

    @Test
    fun observe()
    {
        jobBoardSubject.setState(1)
    }
}

class JobBoardSubject
{
    private val observers: MutableList<Observer> = ArrayList<Observer>()
    private var state = 0

    fun getState(): Int
    {
        return state
    }

    fun setState(state: Int)
    {
        this.state = state
        notifyAllObservers()
    }

    fun attach(observer: Observer)
    {
        observers.add(observer)
    }

    fun attachAll(observer: ArrayList<Observer>)
    {
        observers.addAll(observer)
    }

    fun notifyAllObservers()
    {
        for (observer in observers)
        {
            observer.update(state)
        }
    }
}

interface Observer
{
    fun update(state: Int)
}

class JobSeeker(var name: String) : Observer
{
    override fun update(state: Int)
    {
        println("$name notified. New state: $state")
    }
}