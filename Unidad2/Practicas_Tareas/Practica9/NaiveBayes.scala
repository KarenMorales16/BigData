
package org.apache.spark.examples.ml
//Paso 1: carga de paquetes y API necesarios
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

object NaiveBayesExample {
//Paso 2: crear una sesión de Spark
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("NaiveBayesExample").getOrCreate()

/*Paso 3: manejar datos Lo primero que debemos hacer es cargar nuestro archivo de datos. Los datos están en formato CSV
sin una línea de encabezado ni comillas. Podemos abrir el archivo con la función de abrir y leer las líneas de datos
utilizando la función de lector en el módulo CSV.*/
    val data = spark.read.format("libsvm").load("/usr/local/spark-2.3.4-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt")

 /*Paso 4: preparación del conjunto de entrenamiento y prueba
 Prepare el tren y el conjunto de prueba: entrenamiento => 60%, prueba => 40% y semilla => 12345L
 Establecer la semilla para la aleateoridad en un conjunto de datos */
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)

 /*Paso 5: Creando nuestro modelo ingenuo de Bayes Aquí tenemos un  NaiveBayes() método que realiza 
 exactamente las mismas funciones que el código explicado anteriormente:*/
    val model = new NaiveBayes().fit(trainingData)

// Paso 6: Haciendo predicciones
    val predictions = model.transform(testData)
    predictions.show()
//Paso 7:Obteniendo precisión y estadísticas
    val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
    val accuracy = evaluator.evaluate(predictions)
    println(s"Test set accuracy = $accuracy")
// Paso 8: detener la sesión de Spark
spark.stop()
  }
}

