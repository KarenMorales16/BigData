
//Paso 1: carga de paquetes y API necesarios
package org.apache.spark.examples.ml
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession


object OneVsRestExample {
  def main(args: Array[String]) {
//Paso 2: crear una sesión de Spark
    val spark = SparkSession.builder.appName(s"OneVsRestExample").getOrCreate()

/*Paso 3: manejar datos Lo primero que debemos hacer es cargar nuestro archivo de datos. Los datos están en formato CSV
sin una línea de encabezado ni comillas. Podemos abrir el archivo con la función de abrir y leer las líneas de datos
utilizando la función de lector en el módulo CSV.*/
    val inputData = spark.read.format("libsvm").load("/usr/local/spark-2.3.4-bin-hadoop2.6/data/mllib/sample_multiclass_classification_data.txt")

/*Paso 4: preparación del conjunto de entrenamiento y prueba
Prepare el tren y el conjunto de prueba: entrenamiento => 80%, prueba => 20% */
    val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

//Paso 5: instanciar el clasificador base; Maximo de iteraciones,la tolerancia de convergencia de las iteraciones,Establecer si debemos ajustar la intersección El valor predeterminado es verdadero.
    val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

   
//Paso 6: instanciar el clasificador One Vs Rest.
    val ovr = new OneVsRest().setClassifier(classifier)

//Paso 7: entrenar al modelo multiclase.
    val ovrModel = ovr.fit(train)

//Paso 8: puntúe el modelo en los datos de la prueba.
    val predictions = ovrModel.transform(test)

//Paso 9: Obtener evaluador.
    val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")


//Paso 10:Calcule el error de clasificación en los datos de prueba.
    val accuracy = evaluator.evaluate(predictions)
    println(s"Test Error = ${1 - accuracy}")
 //Paso 11: detener la sesión de Spark
    spark.stop()
  }

}

