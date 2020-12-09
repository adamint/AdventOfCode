package aoc2020

import common.*

private val realInput = readInput("aoc2020/input7.txt")

object Day7 {
    fun partA() {
        val bags = realInput.asBags()
        val sum = bags.keys.sumBy { bagName ->
            if (isShinyInside(bagName, bags)) 1 else 0
        }
        println("Part A: $sum")


    }

    fun isShinyInside(bagName: String, bags: HashMap<String, List<String>>): Boolean {
        val children = bags[bagName]
        return bagName == "shiny gold" || children != null &&
                (children.any { child -> child == "shiny gold" } || children.any { child -> isShinyInside(child, bags) })
    }

    fun String.asBags(): HashMap<String, List<String>> {
        val bags = hashMapOf<String, List<String>>()
        lines().filterNot { it.endsWith("no other bags.") }
            .filterNot { it.startsWith("shiny gold") }
            .also { println("size ${it.size}") }
            .forEach { line ->
                val (bagName, children) = line.split(" bags contain ")
                val childrenList = children.dropLast(1)
                    .split(", ")
                    .map { bag -> bag.drop(2) }
                    .map { it.replace(" bags", "").replace(" bag", "") }
                bags += bagName to childrenList
            }
        return bags
    }
}

fun main() {
    Day7.partA()
}