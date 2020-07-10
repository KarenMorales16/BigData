// 6. Crea una mapa mutable llamado nombres que contenga los siguiente
//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

val mapamutable = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23),("Susana","27"))
// 6 a . Imprime todas la llaves del mapa
mapamutable.keys
// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)
mapamutable += ("Miguel" -> 23)