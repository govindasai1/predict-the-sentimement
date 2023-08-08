package com.example.Predict

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.collections.HashMap

    fun creatingFile(){
        println("enter file name to create")
        var name= readln()
        var file=File(name)
        var creating= file.createNewFile()
    }
    suspend fun Occurance(map:HashMap<String,Int>,file:File):String{

        var String=file.readText()
        var splits=String.split("\\s+".toRegex())
        var score=0
        for(i in splits){
            var s=map[i]?:0
            score+=s
        }
        var positive=File("positive.txt")
        var negitive=File("negitive.txt")
        var nutral=File("nutral.txt")
        delay(1000)
        if(score>0){
            file.renameTo(positive)
            var count: MutableList<String> = mutableListOf()
            for (key in map.keys){
                count.add(count(file,splits,key))
            }
            return "$file $count total sccore is $score changed to +ve"
        }
        else if(score==0){
            file.renameTo(nutral)
            var count: MutableList<String> = mutableListOf()
            for (key in map.keys){
                count.add(count(file,splits,key))
            }
            return "$file $count total sccore is $score changed to nutral"
        }
        else {
            file.renameTo(negitive)
            var count: MutableList<String> = mutableListOf()
            for (key in map.keys){
                count.add(count(file,splits,key))
            }
            return "$file $count total score is $score changed to -ve"
        }

    }
fun count(File:File,list:List<String>,String:String):String{
    var times=list.filter { it.equals(String) }
    return ("$String repeated ${times.size}")
}

fun main()= runBlocking{
    //    creatingFile()
    var map:HashMap<String,Int> = HashMap<String,Int>()
    map.put("hello",0)
    map.put("not",-1)
    map.put("nice",1)
    var file: File =File("1.txt")
    var file2=File("Ab.txt")
    var file3=File("Abc.txt")
    var a=async {  Occurance(map,file)}
    var b=async {  Occurance(map,file2)}
    var c=async {  Occurance(map,file3)}
    println(a.await())
    println(b.await())
    println(c.await())

}