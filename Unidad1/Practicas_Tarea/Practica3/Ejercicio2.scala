// Fórmula explícita
n=20
for(n <- Array.range(0,n+1))
{
var fibonnaci =(Math.pow(((1+Math.sqrt(5))/2),n)-Math.pow(((1-Math.sqrt(5))/2),n))/Math.sqrt(5)
println(fibonnaci)
}

