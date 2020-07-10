//Fibonacci version iterativa 2 variables

def fib(n:Int ):Int={
    a=0
    b=1
    for (k<- Range(0,n)){
        b=b+a
        a=b-a
    }
    return a
}

fib(10)