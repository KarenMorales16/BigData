//Paso 1: carga de paquetes y API necesarios
package org.apache.spark.examples.ml
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession

//Paso 2: crear una sesión de Spark
    val spark = SparkSession.builder.appName("GradientBoostedTreeClassifierExample").getOrCreate()
    
/*Paso 3: manejar datos Lo primero que debemos hacer es cargar nuestro archivo de datos. Los datos están en formato CSV
sin una línea de encabezado ni comillas. Podemos abrir el archivo con la función de abrir y leer las líneas de datos
utilizando la función de lector en el módulo CSV.*/
    val data = spark.read.format("libsvm").load("/usr/local/spark-2.3.4-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt")

/*Paso 4 : Codificación de variable categórica
En nuestro marco de datos, tenemos características numéricas y categóricas. Pero para ingresar las características en nuestro
 modelo de aprendizaje automático, tenemos que transformar todos los atributos categóricos a los numéricos indizándolos. O
  bien son nuestras características de entrada o nuestra columna de etiqueta para el modelo, tenemos que hacerlo para entrenar
   nuestro modelo.*/
    val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
    
    /*Paso 5 :
    No sobrescribimos las características, sino que creamos nuevos atributos concatenando el nombre de las características anteriores y 
    la cadena "Índice" . Para que podamos ingresar solo aquellas características que necesitamos para la capacitación del modelo 
    y mantener intacta la real. Por  a si decirlo Indexamos nuestras etiquetas para trabajarlas */
    val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

   /*Paso 6: preparación del conjunto de entrenamiento y prueba
   Prepare el tren y el conjunto de prueba: entrenamiento => 70%, prueba => 30% */
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

 
    // Paso 7:Entrena un modelo GBT
    val gbt = new GBTClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setMaxIter(10).setFeatureSubsetStrategy("auto")

    /* Paso 8:  Convierte las etiquetas indexadas de nuevo a etiquetas originales.
    En este paso, en realidad reunimos todas las características que necesitamos para ingresar en un modelo.
     Tenemos que proporcionar la lista de esas características numéricas de tipo de conversión y esos atributos 
     categóricos transformados y crear una característica vectorial.*/

    val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
    
    // Paso 9: unimos nuestro modelo gbt a nuestras etiquetas indexadas a través de Pipeline
    val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))
    
   // Paso 10:  Entrenamos nuestra tubería utilizando datos de entrenamiento y la función de ajuste
    val model = pipeline.fit(trainingData)

 // Paso 11: Creamos nuestras predicciones transformando nuestros datos
    val predictions = model.transform(testData)

// Paso 12: selecciona algunas columnas de prueba y las muestra.
    predictions.select("predictedLabel", "label", "features").show(5)

// Paso 13: Seleccionamos nuestras predicciones y etiquetas verdaderas, así como el margen de error obtenido.
    val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

// Paso 14: Evaluaciones y obtenemos el porcentaje de la predicción
    val accuracy = evaluator.evaluate(predictions)
    println(s"Test Error = ${1.0 - accuracy}")

  // Paso 15: Imprimir resultado de árboles usando GBT
    val gbtModel = model.stages(2).asInstanceOf[GBTClassificationModel]
    println(s"Learned classification GBT model:\n ${gbtModel.toDebugString}")