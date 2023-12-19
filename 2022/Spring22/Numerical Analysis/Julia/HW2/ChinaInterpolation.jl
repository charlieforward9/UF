using FundamentalsNumericalComputation

year = 1980:10:2010
pop = [984.736, 1148.364, 1263.638, 1330.141]; #In millions

t = year
t2 = year .-1980
y = pop

#Vandermonde matrix for polynomial interpolation
V = [t[i]^j for i = 1:4, j = 0:3]
V2 = [t2[i]^j for i = 1:4, j = 0:3]

#Solve for polynomial coefficients
c = V \ y
c2 = V2 \ y

#Contruct polynomial
p = Polynomial(c)
p2 = Polynomial(c2)
#Apply time shift
print((p(2005) - p2(2005-1980)) / p2(2005-1980))


scatter(t, y, label = "actual", legend = :topleft, xlabel = "years since 1980", ylabel = "population (millions)", title = "Population of China")

#500 times from 0 to 30 years
tt = LinRange(1980, 2020, 500)

#Dot to apply to all elements of the vector
yy = p.(tt)

plot!(tt, p.(tt), label = "interpolant")