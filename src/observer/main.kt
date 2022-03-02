package observer

interface Observer {
    fun update(value : Int)
}

class SpreadSheet : Observer {
    override fun update(value : Int) {
        println("SpreadSheet got notified. $value")
    }
}

class Chart : Observer {
    override fun update(value : Int) {
        println("Chart got updated. $value")
    }
}

// in the classic gan of four book the below class is named subject
// but some people called observable
open class Subject {

    private val listOfObserver = mutableListOf<Observer>()

    fun addObserver(observer: Observer){
        listOfObserver.add(observer)
    }

    fun removeObserver(observer: Observer){
        listOfObserver.remove(observer)
    }

    // in the below function we can use Object instead of
    // Int but for simplify example we use Int
    fun notifyObserver(value : Int){
        for (observer in listOfObserver)
            observer.update(value)
    }
}

class DataSource : Subject() {
    private var value = 0

    fun getValue() : Int = value

    fun setValue(input : Int){
        value = input
        notifyObserver(value)
    }

}

fun main() {
    val dataSource = DataSource()

    val sheetOne = SpreadSheet()
    val sheetTwo = SpreadSheet()

    val chartOne = Chart()
    val chartTwo = Chart()

    dataSource.addObserver(sheetOne)
    dataSource.addObserver(sheetTwo)
    dataSource.addObserver(chartOne)
    dataSource.addObserver(chartTwo)

    dataSource.setValue(1)
}