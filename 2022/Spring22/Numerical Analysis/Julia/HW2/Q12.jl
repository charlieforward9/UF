using FundamentalsNumericalComputation

A = [1 0 -1; 0 4 5; -1 5 10]
B = [1 0 1; 0 4 5; 1 5 1]
C = cholesky(A)

@which A \ B