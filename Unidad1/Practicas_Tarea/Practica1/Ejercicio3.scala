//3. Dada la variable bird = "tweet", utiliza interpolacion de string para
//   imprimir "Estoy ecribiendo un tweet"

val bird= "tweet"
val bird1 = s"Estoy ecribiendo un ${bird}"
val bird2 = s"Estoy ecribiendo un $bird"

println(bird1 +"\n" +bird2)