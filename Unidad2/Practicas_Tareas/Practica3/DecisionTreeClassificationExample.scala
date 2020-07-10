// import of libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession

    // create spark session variable
    val spark = SparkSession.builder.appName("DecisionTreeClassificationExample").getOrCreate()
    
    // We load our dataset
    val data = spark.read.format("libsvm").load("/home/eduardo/Escritorio/semestre_9/BigData/Unidad2/Practicas_Tareas/Data/sample_libsvm_data.txt")

    // Index labels, adding metadata to the label column.
    // We make an adjustment to our dataframe
    val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
    // We add the characteristics to which we will adjust the dataframe
    val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

    // We divide the dataframe into 70% for training and 30% for testing
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

    // Train a DecisionTree model.
    val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

    // Convert indexed labels back to original labels.
    val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

    // we create a pipeline with pipeline
    val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

    // We train the model
    val model = pipeline.fit(trainingData)

    // We transform predictions
    val predictions = model.transform(testData)

    // Select example rows to display.
    predictions.select("predictedLabel", "label", "features").show(5)

    // We select the labels that will be shown in the prediction
    val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
    val accuracy = evaluator.evaluate(predictions)
    // We print the model accuracy
    println(s"Test Error = ${(1.0 - accuracy)}")
    // We print the decision tree
    val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    println(s"Learned classification tree model:\n ${treeModel.toDebugString}")





