//2. Desarrollar un algoritmo en scala que me diga si un numero es primo
        var numero=1
        var contador = 0
         for (i <- 1 to numero)
         {
            if(numero % i == 0)
            {
                contador=contador+1
            }
         }           
        if(contador > 2)
        {
        println("El numero no es primo")
        }
        else
        {
         println("El numero  es primo")
        }
