/*
Variance is a measure of dispersion that represents the variability of a series of data with respect to its environment.
*/

// returns the unbiased variance of the values in a group

df.select(variance("Sales")).show()
