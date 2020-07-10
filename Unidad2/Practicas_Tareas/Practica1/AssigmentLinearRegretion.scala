////////////////////////////////////////////
//// LINEAR REGRESSION EXERCISE ///////////
/// Coplete las tareas comentadas ///
/////////////////////////////////////////

// Import LinearRegression
import org.apache.spark.ml.regression.LinearRegression
// Opcional: Utilice el siguiente codigo para configurar errores
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)


// Inicie una simple Sesion Spark
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()
// Utilice Spark para el archivo csv Clean-Ecommerce .

val data = spark.read.option("header", "true").option("inferSchema","true")csv("/usr/local/spark-2.3.4-bin-hadoop2.6/data/mllib/Clean-Ecommerce.csv")
//val df = spark.read.option("header", "true").option("inferSchema","true")csv("Clean-Ecommerce.csv")
// Imprima el schema en el DataFrame.

data.printSchema()
// Imprima un renglon de ejemplo del DataFrane.
data.head(1)


//////////////////////////////////////////////////////
//// Configure el DataFrame para Machine Learning ////
//////////////////////////////////////////////////////

// Transforme el data frame para que tome la forma de
// ("label","features")
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}

// Importe VectorAssembler y Vectors
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
// Renombre la columna Yearly Amount Spent como "label"
// Tambien de los datos tome solo la columa numerica 
// Deje todo esto como un nuevo DataFrame que se llame df
val df = data.select(data("Price").as("label"), $"Avg Area Income", $"Avg Area House Age", $"Avg Area Number of Rooms", $"Avg Area Number of Bedrooms", $"Area Population")

// Que el objeto assembler convierta los valores de entrada a un vector
val assembler = new VectorAssembler().setInputCols(Array("Avg Area Income", "Avg Area House Age", "Avg Area Number of Rooms", "Avg Area Number of Bedrooms", "Area Population")).setOutputCol("features")


// Utilice el objeto VectorAssembler para convertir la columnas de entradas del df
// a una sola columna de salida de un arreglo llamado  "features"
// Configure las columnas de entrada de donde se supone que leemos los valores.
// Llamar a esto nuevo assambler.
// Utilice el assembler para transform nuestro DataFrame a dos columnas: label and features
val assembler = new VectorAssembler().setInputCol(Array("Avg Area Income", "Avg Area House Age", "Avg Area Number of Rooms", "Avg Area Number of Bedrooms", "Area Population")).setOutputCol("features")
